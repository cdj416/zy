package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.hongyuan.mvvmhabitx.base.AppManager;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.BR;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.base.ItemViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.ui.main.activity.MainActivity;
import com.zhongyiguolian.zy.ui.person.activity.ExtractResultActivity;
import com.zhongyiguolian.zy.ui.person.beans.GoWithdrawalBeans;
import com.zhongyiguolian.zy.ui.person.beans.UnopenedServiceBeans;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;
import com.zhongyiguolian.zy.utils.BigDecimalUtils;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 冲fil
 * @author cdj
 * @date 2020/12/10
 */
public class OperateFillViewModel extends CustomViewModel<MyRepository> {

    /**
     * @param application
     * @param model
     */
    public OperateFillViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    /**
     * 提币需要的数据，比如利率
     */
    public ObservableField<GoWithdrawalBeans> entity = new ObservableField<>();

    /**
     * 数据
     */
    public ObservableField<Boolean> isCheckAll = new ObservableField<>(false);

    /**
     * 可提余额是否充足
     */
    public ObservableField<Boolean> isAdequate = new ObservableField<>(false);

    /**
     * 钱包地址余额是否充足
     */
    public ObservableField<Boolean> isAddress = new ObservableField<>(false);

    /**
     * 总共需要充值的费用
     */
    public ObservableField<String> allNums = new ObservableField<>("0");

    /**
     * 充值质押需要的ids
     */
    public ObservableField<String> ids = new ObservableField<>("");


    /**
     * 封装一个界面发生改变的观察者
     */
    public UIChangeObservable uc = new UIChangeObservable();
    public class UIChangeObservable {
        //点击全选改变状态
        public SingleLiveEvent<Boolean> changeAll = new SingleLiveEvent<>();
        //显示选择充值通道
        public SingleLiveEvent<Void> showchannel = new SingleLiveEvent<>();

        //输入密码进行充质押
        public SingleLiveEvent<Void> enterPassword = new SingleLiveEvent<>();

        //显示余额不足
        public SingleLiveEvent<Void> showbalance = new SingleLiveEvent<>();
    }


    /**
     * 给RecyclerView添加ObservableList
     */
    public ObservableList<OperateFillItemViewModel> observableList = new ObservableArrayList<>();


    /**
     * 给RecyclerView添加ItemBinding
     */
    public ItemBinding<OperateFillItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_operate_fil_list);


    /**
     * 全选
     */
    public BindingCommand checkAll = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(isCheckAll.get()){
                isCheckAll.set(false);

                //设置全部未选中
                setNoCheckAll();
            }else{
                isCheckAll.set(true);

                //设置全部选中
                setCheckAll();
            }

            uc.changeAll.setValue(isCheckAll.get());

            //改变总数量
            calNums();
        }
    });

    /*
    * 点击充值
    * */
    public BindingCommand checkGo = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(Double.parseDouble(allNums.get()) <= 0){
                ToastUtils.showShort("充值额度不能为0");
                return;
            }

            if(entity.get() == null){
                ToastUtils.showShort("数据获取失败！");
                return;
            }

            if(!isAddress.get()){
                uc.showbalance.call();
                return;
            }

            //输入密码
            uc.enterPassword.call();

            //选择充值通道
            //uc.showchannel.call();
        }
    });

    /*
    * 请求用户的可用余额
    * */
    @Override
    public void onResume() {
        super.onResume();

        //请求未开机的服务器
        requestData(Constants.MINERDAL);

        //获取提币需要的数据
        clearParams().setParams("currencyId", AndroidDes3Util.encode("2"))
                .requestData(Constants.GOWITHDRAWTOKEN);
    }

    /*
     * 检测是否全部选中
     * */
    public boolean checkIsAll(){
        for(OperateFillItemViewModel itemViewModel : observableList){
            if(!itemViewModel.entity.get().isSelect()){
                return false;
            }
        }

        return true;
    }

    /*
     * 总数量计算
     * */
    public void calNums(){
        double allNum = 0.00;
        double checkAllNum = 0.00;

        //情况下选中的ids
        ids.set("");

        for(OperateFillItemViewModel itemViewModel : observableList){
            if(itemViewModel.entity.get().isSelect()){

                //增加0.5个币，以此避免充质押失败问题
                allNum = Double.parseDouble(BigDecimalUtils.add(BigDecimalUtils.add(String.valueOf(allNum),String.valueOf(itemViewModel.entity.get().getMyAboutFil()),4),"0.5",4));
                //用于判断质押是否足够
                checkAllNum = Double.parseDouble(BigDecimalUtils.add(String.valueOf(checkAllNum),String.valueOf(itemViewModel.entity.get().getMyAboutFil()),4));

                //allNum += itemViewModel.entity.get().getMyAboutFil();
                ids.set(ids.get()+itemViewModel.entity.get().getId()+"#");
            }
        }

        allNums.set(BaseUtil.getNoZoon(BigDecimalUtils.round(allNum,4)));

        //对比可提余额是否充足
        /*if(entity.get() != null && allNum <= Double.parseDouble(entity.get().getUsedAssets())){
            isAdequate.set(true);
        }else{
            isAdequate.set(false);
        }*/

        //对比钱包地址余额是否充足
        if(entity.get() != null && checkAllNum <= entity.get().getAddressValibe()){
            isAddress.set(true);
        }else{
            isAddress.set(false);
        }
    }

    /*
     * 设置全部选中
     * */
    public void setCheckAll(){
        for(OperateFillItemViewModel itemViewModel : observableList){
            itemViewModel.entity.get().setSelect(true);
            itemViewModel.entity.notifyChange();
        }
    }

    /*
     * 设置全部未选中
     * */
    public void setNoCheckAll(){
        for(OperateFillItemViewModel itemViewModel : observableList){
            itemViewModel.entity.get().setSelect(false);
            itemViewModel.entity.notifyChange();
        }
    }

    /*
    * 充质押
    * */
    public void goPledge(String password,String channelNum){

        clearParams().setParams("currencyId",AndroidDes3Util.encode("2"))
                .setParams("minerId",AndroidDes3Util.encode(ids.get()))
                .setParams("transPassword",AndroidDes3Util.encode(password))
                .setParams("type",AndroidDes3Util.encode(channelNum))
                .requestData(Constants.CHONGZIYA);
    }

    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.MINERDAL){
            observableList.clear();
            allNums.set("0");
            isCheckAll.set(false);
            uc.changeAll.setValue(isCheckAll.get());

            UnopenedServiceBeans serviceBeans = (UnopenedServiceBeans) dataBean;

            for(UnopenedServiceBeans.MinListDTO bean: serviceBeans.getMinList()){
                OperateFillItemViewModel viewModel = new OperateFillItemViewModel(this,bean);
                observableList.add(viewModel);
            }
        }

        if(code == Constants.GOWITHDRAWTOKEN){
            GoWithdrawalBeans beans = (GoWithdrawalBeans)dataBean;
            entity.set(beans);
        }

        if(code == Constants.CHONGZIYA){
            Bundle bundle = new Bundle();
            bundle.putString("title","充质押结果");
            bundle.putString("proText","充质押处理中");
            bundle.putString("proText1","已提交申请，等待处理...");
            startActivity(ExtractResultActivity.class,bundle);
            AppManager.getAppManager().goActivity(MainActivity.class);
        }
    }

    /*
    * 点击回调
    * */
    @Override
    public void itemClick(ItemViewModel itemVM) {
        super.itemClick(itemVM);

        if(checkIsAll()){
            isCheckAll.set(true);
        }else{
            isCheckAll.set(false);
        }

        //改变选中状态
        uc.changeAll.setValue(isCheckAll.get());

        //改变总数量
        calNums();
    }
}
