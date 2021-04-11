package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.hongyuan.mvvmhabitx.base.AppManager;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.ui.main.activity.MainActivity;
import com.zhongyiguolian.zy.ui.person.activity.ExtractResultActivity;
import com.zhongyiguolian.zy.ui.person.beans.GoWithdrawalBeans;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;

/**
 * Fil转换viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class FilConversionViewModel extends CustomViewModel<MyRepository> {

    /**
     * @param application
     * @param model
     */
    public FilConversionViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }


    /**
     * 封装一个界面发生改变的观察者
     */
    public UIChangeObservable uc = new UIChangeObservable();

    /**
     * ui变动
     */
    public class UIChangeObservable {
        //显示密码输入框
        public SingleLiveEvent<Void> showEnterPassword = new SingleLiveEvent<>();
        //余额不足，需要去其他平台充币
        public SingleLiveEvent<Void> showInsufficient = new SingleLiveEvent<>();
    }

    /**
     * 提币需要的数据，比如利率
     */
    public ObservableField<GoWithdrawalBeans> entity = new ObservableField<>();

    /*
     * 最低提现金额描述
     * */
    public ObservableField<String> thinFilText = new ObservableField<>("最小提币数量为1.0FIL。");

    /**
     * 提取数量
     */
    public ObservableField<String> nums = new ObservableField<>();


    /**
     * 点击清空数量
     */
    public BindingCommand clearNums = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            nums.set("");
        }
    });


    /**
     * 输入密码谈起输入密码
     */
    public BindingCommand enterPassword = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

            if(entity.get() == null){
                ToastUtils.showShort("无数据！");
                return;
            }

            if(!BaseUtil.isValue(nums.get()) || Double.parseDouble(nums.get()) <= 0){
                ToastUtils.showShort("请输入充币数量！");
                return;
            }

           /*if(Double.parseDouble(nums.get()) < entity.get().getTheMin()){
                ToastUtils.showShort("数量不能少于"+entity.get().getTheMin());
                return;
            }*/

            if(entity.get().getAddressValibe() < Double.parseDouble(nums.get())){
                uc.showInsufficient.call();
                return;
            }

            uc.showEnterPassword.call();
        }
    });


    /**
     *
     */
    @Override
    public void onResume() {
        super.onResume();

        //获取提币需要的数据
        clearParams().setParams("currencyId", AndroidDes3Util.encode("2"))
                .requestData(Constants.GOWITHDRAWTOKEN);
    }

    /*
    * 开始充值余额
    * */
    public void rechargeBalance(String password){
        clearParams().setParams("currencyId",AndroidDes3Util.encode("2"))
                .setParams("quantity",AndroidDes3Util.encode(nums.get()))
                .setParams("transPassword",AndroidDes3Util.encode(password))
                .requestData(Constants.COMPANY);
    }

    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.GOWITHDRAWTOKEN){
            GoWithdrawalBeans beans = (GoWithdrawalBeans)dataBean;
            entity.set(beans);

            thinFilText.set("最小充币数量为"+beans.getTheMin()+"FIL。");

            if(beans.getAddressValibe() <= 0 ){
                uc.showInsufficient.call();
            }
        }

        if(code == Constants.COMPANY){
            Bundle bundle = new Bundle();
            bundle.putString("title","充值结果");
            bundle.putString("proText","充值处理中");
            bundle.putString("proText1","已提交申请，等待处理...");
            startActivity(ExtractResultActivity.class,bundle);
            AppManager.getAppManager().goActivity(MainActivity.class);
        }
    }
}
