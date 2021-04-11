package com.zhongyiguolian.zy.ui.main.viewmodel;

import android.app.Application;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.RxBus;
import com.hongyuan.mvvmhabitx.bus.RxSubscriptions;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.ui.main.activity.CountrysActivity;
import com.zhongyiguolian.zy.ui.main.activity.WebActivity;
import com.zhongyiguolian.zy.ui.main.beans.CountrysBeans;
import com.zhongyiguolian.zy.ui.main.beans.ImgCodeBeans;
import com.zhongyiguolian.zy.ui.main.beans.InvitationCodeBeans;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;
import com.zhongyiguolian.zy.utils.HourMeterUtil;
import io.michaelrocks.libphonenumber.android.NumberParseException;
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil;
import io.michaelrocks.libphonenumber.android.Phonenumber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * 注册viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class RegisteredViewModel extends CustomViewModel<MyRepository> implements HourMeterUtil.TimeCallBack{

    /**
     * @param application
     * @param model
     */
    public RegisteredViewModel(@NonNull Application application, MyRepository model) {
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
        //注册成功弹框
        public SingleLiveEvent<Void> registerSuccess = new SingleLiveEvent<>();
        //验证手机号是否合规
        public SingleLiveEvent<Boolean> checkPhoneNum = new SingleLiveEvent<>();
        //可再次点击发送验证码
        public SingleLiveEvent<Boolean> canSendCode = new SingleLiveEvent<>();
        //让用户确认信息
        public SingleLiveEvent<InvitationCodeBeans> confirm = new SingleLiveEvent<>();
    }

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
     * 国家码
     */
    public ObservableField<String> countrysId = new ObservableField<>("+86");

    /**
     * 电话号码
     */
    public ObservableField<String> phoneNum = new ObservableField<>("");

    /**
     * 图形验证码
     */
    public ObservableField<String> vCode = new ObservableField<>("");

    /**
     * 短信验证码
     */
    public ObservableField<String> messageCode = new ObservableField<>();

    /**
     * 输入的图形验证码
     */
    public ObservableField<String> inputVcode = new ObservableField<>();

    /**
     * 输入的秘密
     */
    public ObservableField<String> password = new ObservableField<>();

    /**
     * 输入的确认秘密
     */
    public ObservableField<String> confirmSecret = new ObservableField<>();

    /**
     * 邀请码
     */
    public ObservableField<String> invitationCode = new ObservableField<>();

    /**
     * 协议是否选中
     */
    public ObservableField<Boolean> isCheck = new ObservableField<>(false);

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
     * 生成新的验证码
     */
    public BindingCommand changeVcode = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //从新获取图形验证码
            clearParams().setParams("oTime","1").requestData(Constants.GETIMGCODE);
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

    /**
     * 用户协议
     */
    public BindingCommand goUserAgreement = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putString("url","http://app.glydsj.com/appdown/%E7%94%A8%E6%88%B7%E5%8D%8F%E8%AE%AE.html");
            bundle.putString("title","用户协议");
            startActivity(WebActivity.class,bundle);
        }
    });


    /**
     * 隐私政策
     */
    public BindingCommand goPrivacyPolicy = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putString("url","http://app.glydsj.com/appdown/%E9%9A%90%E7%A7%81%E6%94%BF%E7%AD%96.html");
            bundle.putString("title","隐私政策");
            startActivity(WebActivity.class,bundle);
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

    /**
     * 注册账户
     */
    public BindingCommand registered = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

            if(BaseUtil.isValue(invitationCode.get())){
                //根据邀请码获取用户信息
                clearFileParams().setParams("inviteCode",AndroidDes3Util.encode(invitationCode.get()))
                        .requestData(Constants.MYNAME);
            }else{
                ToastUtils.showShort("请填写邀请码！");
            }

            //验证输入的类容是否规范
            //uc.check.call();
        }
    });

    /*
     * 发送验证码
     * */
    public void sendPhneCode(){
        //发送验证码
        clearParams().setParams("account", AndroidDes3Util.encode(phoneNum.get()))
                .setParams("nationalCode",AndroidDes3Util.encode(countrysId.get().substring(1)))
                .setParams("type", AndroidDes3Util.encode("0"))
                .setParams("imgCode", AndroidDes3Util.encode(inputVcode.get()))
                .setParams("voice", AndroidDes3Util.encode("0"))
                .setParams("oTime","1");
        requestData(Constants.SENDSMSCODE);

        hourMeterUtil.reStartCount();
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

    /*
    * 验证图形码是否正确
    * */
    public boolean isCheckVcode(){
        return vCode.get().equals(inputVcode.get());
    }

    /*
     * 验证图两次秘密是否一致
     * */
    public boolean isCheckPassword(){
        return password.get().equals(confirmSecret.get());
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
            //通知可再次允许点击
            uc.canSendCode.call();
        }
    }

    /**
     * 初始化订阅者
     */
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化图形验证码
        vCode.set(String.valueOf((int) ((Math.random() * 9 + 1) * 1000)));

        //初始化计时对象
        hourMeterUtil = new HourMeterUtil();
        hourMeterUtil.setTimeCallBack(this);

        //初始化订阅对象
        mSubscription = RxBus.getDefault().toObservable(CountrysBeans.class)
                .observeOn(AndroidSchedulers.mainThread()) //回调到主线程更新UI
                .subscribe(beans -> {
                    countrysId.set(beans.getCode());
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

        //获取图像验证码
        if(code == Constants.GETIMGCODE){
            ImgCodeBeans codeBeans = (ImgCodeBeans)dataBean;
            vCode.set(codeBeans.getImgCode());
        }

        //获取邀请人信息
        if(code == Constants.MYNAME){
            InvitationCodeBeans codeBeans = (InvitationCodeBeans) dataBean;
            uc.confirm.setValue(codeBeans);
        }

        if(code == Constants.REGISTER){
            uc.registerSuccess.call();
        }
    }
}
