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

/**
 * Fil体现
 * @author cdj
 * @date 2020/12/10
 */
public class ChangePasswordViewModel extends CustomViewModel<MyRepository> {


    /**
     *
     */
    public ObservableField<String> idCardName = new ObservableField<>();

    /**
     * @param application
     * @param model
     */
    public ChangePasswordViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }


    /**
     * 封装一个界面发生改变的观察者
     */
    public UIChangeObservable uc = new UIChangeObservable();

    /*
    * 旧密码是否铭文密码
    * */
    public ObservableField<Boolean> isOldOpen = new ObservableField<>(false);

    /*
     * 新密码铭文密码
     * */
    public ObservableField<Boolean> isNewOpen = new ObservableField<>(false);

    /*
     * 再次确认新密码铭文密码
     * */
    public ObservableField<Boolean> isNewOldOpen = new ObservableField<>(false);

    /*
     * 旧密码
     * */
    public ObservableField<String> oldPassword = new ObservableField<>();

    /*
     * 新密码
     * */
    public ObservableField<String> newPassword = new ObservableField<>();

    /*
     * 确定新密码
     * */
    public ObservableField<String> newAginPassword = new ObservableField<>();


    /**
     * change
     */
    public class UIChangeObservable {

        //旧密码显示方式
        public SingleLiveEvent<Boolean> OldOpen = new SingleLiveEvent<>();
        //新密码显示方式
        public SingleLiveEvent<Boolean> newOpen = new SingleLiveEvent<>();
        //新密码确认显示方式
        public SingleLiveEvent<Boolean> subNewOpen = new SingleLiveEvent<>();
    }


    /**
     * 更改旧密码显示方式
     */
    public BindingCommand isOpengOld = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(isOldOpen.get()){
                uc.OldOpen.setValue(false);
                isOldOpen.set(false);


            }else{
                uc.OldOpen.setValue(true);
                isOldOpen.set(true);
            }

        }
    });

    /**
     * 更改新密码显示方式
     */
    public BindingCommand isOpenNews = new BindingCommand(new BindingAction() {
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
     * 更改新确认密码显示方式
     */
    public BindingCommand isOpenSubMit = new BindingCommand(new BindingAction() {
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
     * 提交更改密码
     */
    public BindingCommand subMit = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(!BaseUtil.isValue(oldPassword.get())){
                ToastUtils.showShort("请输入旧密码");
                return;
            }

            if(!BaseUtil.isValue(newPassword.get())){
                ToastUtils.showShort("请输入新密码");
                return;
            }

            if(!BaseUtil.isValue(newAginPassword.get())){
                ToastUtils.showShort("请再次输入新密码");
                return;
            }

            clearParams().setParams("password", AndroidDes3Util.encode(oldPassword.get()))
                    .setParams("newPassword",AndroidDes3Util.encode(newPassword.get()))
                    .setParams("newPassword2",AndroidDes3Util.encode(newAginPassword.get()))
                    .setParams("token",AndroidDes3Util.encode(loginBean.getToken()))
                    .requestData(Constants.EDITPASSWORD);
        }
    });

    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.EDITPASSWORD){
            ToastUtils.showShort("密码修改成功！");
            finish();
        }
    }
}
