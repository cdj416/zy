package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;
import com.zhongyiguolian.zy.utils.HourMeterUtil;

/**
 * 修改交易密码
 * @author cdj
 * @date 2020/12/10
 */
public class SetPayPasswordViewModel extends CustomViewModel<MyRepository> implements HourMeterUtil.TimeCallBack{


    /**
     * 姓名
     */
    public ObservableField<String> idCardName = new ObservableField<>();

    /**
     * @param application
     * @param model
     */
    public SetPayPasswordViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }


    /**
     * 封装一个界面发生改变的观察者
     */
    public UIChangeObservable uc = new UIChangeObservable();

    /**
     * change
     */
    public class UIChangeObservable {
        //新密码显示方式
        public SingleLiveEvent<Boolean> newOpen = new SingleLiveEvent<>();
        //新密码确认显示方式
        public SingleLiveEvent<Boolean> subNewOpen = new SingleLiveEvent<>();
    }

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

    /*
     * 新密码铭文密码
     * */
    public ObservableField<Boolean> isNewOpen = new ObservableField<>(false);

    /*
     * 再次确认新密码铭文密码
     * */
    public ObservableField<Boolean> isNewOldOpen = new ObservableField<>(false);

    /*
     * 新密码
     * */
    public ObservableField<String> newPassword = new ObservableField<>();

    /*
     * 确定新密码
     * */
    public ObservableField<String> newAginPassword = new ObservableField<>();

    /*
     * 验证码
     * */
    public ObservableField<String> codeText = new ObservableField<>();


    /**
     * 改变显示隐藏密码
     */
    public BindingCommand changeShow = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(isNewOpen.get()){
                uc.newOpen.setValue(false);
                isNewOpen.set(false);
            }else{
                uc.newOpen.setValue(true);
                isNewOpen.set(true);
            }
        }
    });

    /**
     * 改变显示隐藏密码
     */
    public BindingCommand changeAginShow = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(isNewOldOpen.get()){
                uc.subNewOpen.setValue(false);
                isNewOldOpen.set(false);
            }else{
                uc.subNewOpen.setValue(true);
                isNewOldOpen.set(true);
            }
        }
    });

    /**
     * 发送验证码
     */
    public BindingCommand sendMessage = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            setParams("account", AndroidDes3Util.encode(loginBean.getUserName()))
                    .setParams("nationalCode",AndroidDes3Util.encode(loginBean.getNationalCode()))
                    .setParams("type",AndroidDes3Util.encode("2"))
                    .setParams("imgCode",AndroidDes3Util.encode("123456"))
                    .setParams("voice",AndroidDes3Util.encode("0"))
                    .setParams("oTime",AndroidDes3Util.encode("1"))
                    .requestData(Constants.SENDSMSCODE);

            hourMeterUtil.reStartCount();
        }
    });

    /**
     * 提交修改
     */
    public BindingCommand subMit = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(!BaseUtil.isValue(newPassword.get())){
                ToastUtils.showShort("请输入密码");
                return;
            }

            if(!BaseUtil.isValue(newPassword.get())){
                ToastUtils.showShort("请再次输入密码");
                return;
            }

            if(!BaseUtil.isValue(newPassword.get())){
                ToastUtils.showShort("请输入短信验证码");
                return;
            }

            setParams("newPassword",AndroidDes3Util.encode(newPassword.get()))
                    .setParams("newPassword2",AndroidDes3Util.encode(newAginPassword.get()))
                    .setParams("code",AndroidDes3Util.encode(codeText.get()))
                    .setParams("token",AndroidDes3Util.encode(loginBean.getToken()))
                    .requestData(Constants.SETTRANSPASSWORD);
        }
    });

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
     * 初始化订阅者
     */
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化计时对象
        hourMeterUtil = new HourMeterUtil();
        hourMeterUtil.setTimeCallBack(this);
    }


    /**
     * 销毁RxJave
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
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

        if(code == Constants.SENDSMSCODE){

        }

        if(code == Constants.SETTRANSPASSWORD){
            ToastUtils.showShort("设置成功！");
            finish();
        }
    }
}
