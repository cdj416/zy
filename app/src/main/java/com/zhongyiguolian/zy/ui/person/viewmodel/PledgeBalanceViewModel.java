package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.zhongyiguolian.zy.BR;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.person.beans.PledgBalanceListBean;
import com.zhongyiguolian.zy.ui.person.beans.PledgeBalanceBean;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 质押余额
 * @author cdj
 * @date 2020/12/10
 */
public class PledgeBalanceViewModel extends CustomViewModel<MyRepository> {

    /**
     * 开启的服务器数据列表
     */
    public ObservableField<List<PledgBalanceListBean.MinListDTO>> serviceList = new ObservableField<>(new ArrayList<>());

    /**
     * 服务器质押明细
     */
    public ObservableField<List<PledgeBalanceBean.MinerLogDTO>> mxList = new ObservableField<>(new ArrayList<>());

    /*
     * 选中的服务器详细数据
     * */
    public ObservableField<PledgeBalanceBean> pledgeBeans = new ObservableField<>();

    /*
    * 选中的服务器编号数据
    * */
    public ObservableField<String> selServiceNums = new ObservableField<>("");

    /*
     * 选中的服务器的大小
     * */
    public ObservableField<String> selServicePower = new ObservableField<>("");

    /*
     * 选种服务器ID
     * */
    public ObservableField<String> selId = new ObservableField<>("");

    /**
     * @param application
     * @param model
     */
    public PledgeBalanceViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    /**
     * 封装一个界面发生改变的观察者
     */
    public UIChangeObservable uc = new UIChangeObservable();
    public class UIChangeObservable {
        //显示pop
        public SingleLiveEvent<Void> showPop = new SingleLiveEvent<>();
        //设置数据
        public SingleLiveEvent<List<PledgBalanceListBean.MinListDTO>> setOpenServices = new SingleLiveEvent<>();
        //当没数据时提示
        public SingleLiveEvent<Void> showNoData = new SingleLiveEvent<>();
    }


    /**
     * 给RecyclerView添加ObservableList
     */
    public ObservableList<PledgeBalancelItemViewModel> observableList = new ObservableArrayList<>();


    /**
     * 给RecyclerView添加ItemBinding
     */
    public ItemBinding<PledgeBalancelItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_pledge_balance);


    /**
     * 体现详情页
     */
    public BindingCommand showService = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.showPop.call();
        }
    });

    /*
    * 编号截取结果
    * */
    public String getShowNums(String num){
        //return "编号"+num.substring(0,6)+"..."+num.substring(num.length() - 6);
        return "编号"+num;
    }

    /*
    * 获取机器每天消耗的费用详情
    * */
    public void getDals(String id){
        selId.set(id);

        setRefParams("pageIndex",AndroidDes3Util.encode("1"))
                .setRefParams("pageSize",AndroidDes3Util.encode("10"))
                .setRefParams("minerId",AndroidDes3Util.encode(selId.get()));

        //获取机器每天消耗的详情
        setParams("pageIndex", AndroidDes3Util.encode("1"))
                .setParams("pageSize",AndroidDes3Util.encode("10"))
                .setParams("minerId",AndroidDes3Util.encode(id))
                .requestData(Constants.MINERDALS);
    }

    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.OPENMINER){
            PledgBalanceListBean listBean = (PledgBalanceListBean) dataBean;

            if(listBean.getMinList() != null && listBean.getMinList().size() > 0){
                listBean.getMinList().get(0).setSelect(true);
                selServiceNums.set(getShowNums(listBean.getMinList().get(0).getOrderNumber()));
                selServicePower.set(listBean.getMinList().get(0).getCalculationPower()+"T");

                //设置更新弹框数据
                uc.setOpenServices.setValue(listBean.getMinList());

                serviceList.set(listBean.getMinList());

                //获取机器数据
                getDals(String.valueOf(listBean.getMinList().get(0).getId()));
            }else{
                uc.showNoData.call();
            }
        }

        if(code == Constants.MINERDALS){
            PledgeBalanceBean balanceBean = (PledgeBalanceBean) dataBean;
            pledgeBeans.set(balanceBean);


            List<PledgeBalanceBean.MinerLogDTO> mList = balanceBean.getMinerLog();

            //清除旧数据
            if(curPage == FIRST_PAGE){
                observableList.clear();
                mxList.get().clear();
                mxList.notifyChange();
            }

            if(mList != null && mList.size() > 0){

                mxList.get().addAll(mList);
                mxList.notifyChange();

                for(PledgeBalanceBean.MinerLogDTO bean : mList){
                    PledgeBalancelItemViewModel itemViewModel = new PledgeBalancelItemViewModel(this,bean);
                    observableList.add(itemViewModel);
                }

                //处理最后一个item底部阴影显示情况
                if(curPage != FIRST_PAGE){
                    for(PledgeBalancelItemViewModel itemViewModel : observableList){
                        if(itemViewModel.entity.get().isLast()){
                            itemViewModel.entity.get().setLast(false);
                            itemViewModel.entity.notifyChange();
                        }
                    }
                }
                if(observableList.size() > 10){
                    observableList.get(observableList.size() - 1).entity.get().setLast(true);
                    observableList.get(observableList.size() - 1).entity.notifyChange();
                }

            }

            if(observableList.size() > 0){
                if(mList == null || mList.size() == 0){
                    getUC().getFinishLoadMoreData().call();
                }
            }
        }
    }
}
