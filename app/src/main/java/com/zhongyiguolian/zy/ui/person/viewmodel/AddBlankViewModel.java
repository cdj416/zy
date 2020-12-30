package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.RxBus;
import com.hongyuan.mvvmhabitx.bus.RxSubscriptions;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.main.activity.CountrysActivity;
import com.zhongyiguolian.zy.ui.main.beans.CountrysBeans;
import com.zhongyiguolian.zy.ui.person.activity.SendCodeActivity;
import com.zhongyiguolian.zy.ui.person.activity.VerifiedSuccessActivity;

import io.michaelrocks.libphonenumber.android.NumberParseException;
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil;
import io.michaelrocks.libphonenumber.android.Phonenumber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * 添加银行卡
 * @author cdj
 * @date 2020/12/10
 */
public class AddBlankViewModel extends CustomViewModel<MyRepository> {

    /**
     * 广播订阅
     */
    private Disposable mSubscription;

    /**
     * 银行卡卡号
     */
    public ObservableField<String> blankCardNum = new ObservableField<>();

    /**
     * 银行名
     */
    public ObservableField<String> blankName = new ObservableField<>();

    /**
     * 持卡人姓名
     */
    public ObservableField<String> userName = new ObservableField<>();

    /**
     * 开户行
     */
    public ObservableField<String> accountBank = new ObservableField<>();

    /**
     * 手机号
     */
    public ObservableField<String> phoneNum = new ObservableField<>();

    /**
     * 国家码
     */
    public ObservableField<String> countrysId = new ObservableField<>("+86");

    /**
     * @param application
     * @param model
     */
    public AddBlankViewModel(@NonNull Application application, MyRepository model) {
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
        //拍照获取银行卡号
        public SingleLiveEvent<Void> getBlankNums = new SingleLiveEvent<>();
        //验证手机号是否合规
        public SingleLiveEvent<Boolean> checkPhoneNum = new SingleLiveEvent<>();
    }


    /**
     * 获取银行卡号
     */
    public BindingCommand goCamera = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.getBlankNums.call();
        }
    });


    /**
     * 验证手机号并提交服务端
     */
    public BindingCommand goSendCode = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.checkPhoneNum.call();

            //startActivity(SendCodeActivity.class);
        }
    });

    /**
     * 选择国家
     */
    public BindingCommand goCountrys = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(CountrysActivity.class);
        }
    });

    /**
     * 根据区号判断是否是正确的电话号码
     * @param phoneUtil   ：检验工具类
     * return ：true 合法  false：不合法
     */
    public boolean isPhoneNumberValid(PhoneNumberUtil phoneUtil){
        try{
            Phonenumber.PhoneNumber numberProto = phoneUtil.parse(countrysId.get()+phoneNum.get(), countrysId.get().substring(1));
            return phoneUtil.isValidNumber(numberProto);
        }catch (NumberParseException e){
            System.err.println("isPhoneNumberValid NumberParseException was thrown: " + e.toString());
        }
        return false;
    }

    /**
     * 初始化订阅者
     */
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化订阅对象
        mSubscription = RxBus.getDefault().toObservable(CountrysBeans.class)
                .observeOn(AndroidSchedulers.mainThread()) //回调到主线程更新UI
                .subscribe(beans -> {
                    countrysId.set(beans.getCountrysId());
                });
        //将订阅者加入管理站
        RxSubscriptions.add(mSubscription);
    }


    /**
     * 销毁RxJave
     */
    @Override
    public void onDestroy() {
        super.onDestroy();

        //取消订阅，防止内存泄漏
        RxSubscriptions.remove(mSubscription);
    }

    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.CARD_SAVE){
            Bundle bundle = new Bundle();
            bundle.putString("mTitle","银行卡绑定结果");
            bundle.putString("mProText","绑定成功");
            startActivity(VerifiedSuccessActivity.class,bundle);
        }
    }
}
