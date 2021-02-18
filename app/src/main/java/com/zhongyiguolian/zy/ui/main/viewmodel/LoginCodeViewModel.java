package com.zhongyiguolian.zy.ui.main.viewmodel;

import android.app.Application;

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
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.data.userbean.MemberLoginBean;
import com.zhongyiguolian.zy.ui.main.activity.CountrysActivity;
import com.zhongyiguolian.zy.ui.main.activity.MainActivity;
import com.zhongyiguolian.zy.ui.main.activity.PrivacyPolicyActivity;
import com.zhongyiguolian.zy.ui.main.activity.RetrievePasswordActivity;
import com.zhongyiguolian.zy.ui.main.activity.UserAgreementActivity;
import com.zhongyiguolian.zy.ui.main.beans.CountrysBeans;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;
import com.zhongyiguolian.zy.utils.HourMeterUtil;

import io.michaelrocks.libphonenumber.android.NumberParseException;
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil;
import io.michaelrocks.libphonenumber.android.Phonenumber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * 登录viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class LoginCodeViewModel extends CustomViewModel<MyRepository> implements HourMeterUtil.TimeCallBack{

    /**
     * 广播订阅
     */
    private Disposable mSubscription;


    /**
     * 计时器
     */
    private HourMeterUtil hourMeterUtil;

    /**
     * nums显示总秒数
     */
    private int nums = 60;

    /**
     * 验证码发送时文字显示
     */
    public ObservableField<String> sendString = new ObservableField<>("发送");

    /**
     * 短信验证码
     */
    public ObservableField<String> messageCode = new ObservableField<>();

    /**
     * 是否密码登录
     */
    public ObservableField<Boolean> isPassword = new ObservableField<>(true);

    /**
     * 输入的秘密
     */
    public ObservableField<String> password = new ObservableField<>("");

    /**
     * 是否记住密码
     */
    public ObservableField<Boolean> isRememberPassword = new ObservableField<>(false);

    /**
     * 协议是否选中
     */
    public ObservableField<Boolean> isCheck = new ObservableField<>(false);

    /**
     * 国家码
     */
    public ObservableField<String> countrysId = new ObservableField<>("+86");

    /**
     * 电话号码
     */
    public ObservableField<String> phoneNum = new ObservableField<>("");

    /**
     * @param application
     * @param model
     */
    public LoginCodeViewModel(@NonNull Application application, MyRepository model) {
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
        //改变协议选中状态
        public SingleLiveEvent<Boolean> checkXy = new SingleLiveEvent<>();
        //验证输入是否规范
        public SingleLiveEvent<Boolean> checkInput = new SingleLiveEvent<>();
        //更改是否记住密码显示状态
        public SingleLiveEvent<Boolean> changeRemember = new SingleLiveEvent<>();
        //验证手机号是否合规
        public SingleLiveEvent<Boolean> checkPhoneNum = new SingleLiveEvent<>();
    }


    /**
     * 密码登录
     */
    public BindingCommand goPassword = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

            if(isPassword.get()){
                isPassword.set(false);
            }else{
                isPassword.set(true);
            }

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
     * 忘记密码
     */
    public BindingCommand goRetrievePassword = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(RetrievePasswordActivity.class);
        }
    });


    /**
     * 验证输入内容并请求登录接口
     */
    public BindingCommand goMain = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.checkInput.call();
        }
    });


    /**
     * 用户协议
     */
    public BindingCommand goUserAgreement = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(UserAgreementActivity.class);
        }
    });


    /**
     * 隐私政策
     */
    public BindingCommand goPrivacyPolicy = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(PrivacyPolicyActivity.class);
        }
    });

    /**
     * 发送验证码
     */
    public BindingCommand sendCode = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.checkPhoneNum.call();
        }
    });

    /*
    * 发送验证码
    * */
    public void sendPhneCode(){
        //发送验证码
        clearParams().setParams("account", AndroidDes3Util.encode(phoneNum.get()))
                .setParams("type", AndroidDes3Util.encode("3"))
                .setParams("imgCode", AndroidDes3Util.encode("123456"))//暂时写的死的
                .setParams("voice", AndroidDes3Util.encode("0"))//暂时写的死的
                .setParams("oTime","1");//暂时写的死的

        requestData(Constants.SENDSMSCODE);

        hourMeterUtil.reStartCount();
    }

    /**
     * 是否记住密码
     */
    public BindingCommand rememberPassword = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(isRememberPassword.get()){
                isRememberPassword.set(false);
            }else{
                isRememberPassword.set(true);
            }
            uc.changeRemember.setValue(isRememberPassword.get());
        }
    });


    /**
     * 是否选中协议
     */
    public BindingCommand checkXy = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(isCheck.get()){
                isCheck.set(false);
            }else{
                isCheck.set(true);
            }
            uc.checkXy.setValue(isCheck.get());
        }
    });

    /*
    * 回显账号和秘密
    * */
    public void inputUser(){
        MemberLoginBean bean = model.getUser();

        if(bean != null && BaseUtil.isValue(bean.getToken())){
            phoneNum.set(bean.getUserName());
            password.set(bean.getPassword());

            //勾选协议
            uc.checkXy.setValue(true);
            isCheck.set(true);

            uc.changeRemember.setValue(true);
            isRememberPassword.set(true);
        }else{
            phoneNum.set("");
            password.set("");
            isRememberPassword.set(false);
        }
    }


    /**
     * @param passedTime 秒数
     */
    @Override
    public void onTime(int passedTime) {
        if(passedTime <= nums && passedTime > 0){
            sendString.set((nums - passedTime) + "s");
        }else{
            sendString.set("发送");
            hourMeterUtil.pauseCount();
        }
    }

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

        //初始化计时对象
        hourMeterUtil = new HourMeterUtil();
        hourMeterUtil.setTimeCallBack(this);

        //初始化订阅对象
        mSubscription = RxBus.getDefault().toObservable(CountrysBeans.class)
                .observeOn(AndroidSchedulers.mainThread()) //回调到主线程更新UI
                .subscribe(beans -> {
                    countrysId.set(beans.getCountrysId());
                });
        //将订阅者加入管理站
        RxSubscriptions.add(mSubscription);

        //回显用户账号秘密
        inputUser();
    }


    /**
     * 销毁RxJave
     */
    @Override
    public void onDestroy() {
        super.onDestroy();

        //取消订阅，防止内存泄漏
        RxSubscriptions.remove(mSubscription);
        //销毁计时
        hourMeterUtil.onDestory();
    }

    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.LOGIN){
            MemberLoginBean loginBean = (MemberLoginBean)dataBean;
            loginBean.setUserName(phoneNum.get());
            loginBean.setPassword(password.get());
            loginBean.setAgrees(true);

            //存入手机中，以此实现二次免登陆。
            model.saveUser(loginBean);
            /*if(isRememberPassword.get()){
                //记住密码时，去存储到手机中
                model.saveUser(loginBean);
            }else{
                //非记住密码时，去释放存储信息
                model.saveUser(null);
            }*/

            //登录成功进入主界面
            startActivity(MainActivity.class);
        }
    }
}
