package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.BR;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.base.ItemViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.person.beans.PurchaseHistoryBeans;
import com.zhongyiguolian.zy.ui.person.beans.PurchaseHistoryCancelBeans;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 行情viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class PuchaseHistoryViewModel extends CustomViewModel<MyRepository> {

    /**
     * @param application
     * @param model
     */
    public PuchaseHistoryViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    /**
     * 封装一个界面发生改变的观察者
     */
    public UIChangeObservable uc = new UIChangeObservable();

    /**
     * 方法
     */
    public class UIChangeObservable {
        //显示提示是否取消订单
        public SingleLiveEvent<Void> showCancel = new SingleLiveEvent<>();
    }


    /**
     * 给RecyclerView添加ObservableList
     */
    public ObservableList<PurchaseHistoryItemViewModel> observableList = new ObservableArrayList<>();


    /**
     * 给RecyclerView添加ItemBinding
     */
    public ItemBinding<PurchaseHistoryItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_purchase_history);

    /**
     * 取消订单
     */
    public void canCelOrder(){
        if(itemViewModel != null){
            setParams("id", AndroidDes3Util.encode(String.valueOf(itemViewModel.entity.get().getId())))
                    .setParams("productType", AndroidDes3Util.encode("PRODUCT"))
                    .requestNoData(Constants.DETAIL_CANCEL);
        }
    }

    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        //产品数据
        if(code == Constants.DETAIL_LIST){
            List<PurchaseHistoryBeans.MyMmmProductDetailVoDTO> mList = ((PurchaseHistoryBeans)dataBean).getMyMmmProductDetailVo();

            //清除旧数据
            if(curPage == FIRST_PAGE){
                observableList.clear();
            }

            if(mList != null && mList.size() > 0){
                int firstNum = 0;
                for(PurchaseHistoryBeans.MyMmmProductDetailVoDTO bean : mList){
                    firstNum++;
                    PurchaseHistoryItemViewModel itemViewModel;
                    if(firstNum == 1 && curPage == FIRST_PAGE){
                        itemViewModel = new PurchaseHistoryItemViewModel(this,bean,true);
                    }else{
                        itemViewModel = new PurchaseHistoryItemViewModel(this,bean,false);
                    }

                    observableList.add(itemViewModel);
                }
            }

            if(observableList.size() > 0){
                if(mList == null || mList.size() == 0){
                    getUC().getFinishLoadMoreData().call();
                }
            }
        }

        //删除订单
        if(code == Constants.DETAIL_CANCEL){
            PurchaseHistoryCancelBeans cancelBeans = (PurchaseHistoryCancelBeans)dataBean;

            if(cancelBeans.getCode() == 0 && itemViewModel != null){//取消成功
                //移除显示数据
                observableList.remove(itemViewModel);
                //初始化为空
                itemViewModel = null;
            }

            ToastUtils.showShort(cancelBeans.getMessage());
        }

        /*if(code == Constants.GET_FRIEND_MSG_LIST){
            List<MessageFansBean.ListBean> mList = ((MessageFansBean) dataBean).getList();
            //清除旧数据
            if(curPage == FIRST_PAGE){
                observableList.clear();
            }

            if(mList != null && mList.size() > 0){
                for(MessageFansBean.ListBean bean : mList){
                    MessageFansItemViewModel itemViewModel = new MessageFansItemViewModel(this,bean);
                    observableList.add(itemViewModel);
                }
            }

            if(observableList.size() > 0){
                if(mList == null || mList.size() == 0){
                    getUC().getFinishLoadMoreData().call();
                }
            }else{

            }
        }*/
    }


    /**
     * 需要操作的itemViewModel
     */
    private PurchaseHistoryItemViewModel itemViewModel;

    /**
     * @param itemVM
     */
    @Override
    public void itemClick(ItemViewModel itemVM) {
        super.itemClick(itemVM);

        this.itemViewModel = (PurchaseHistoryItemViewModel) itemVM;
    }
}
