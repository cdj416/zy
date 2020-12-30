package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.main.activity.LoginActivity;
import com.zhongyiguolian.zy.ui.person.activity.ChangePasswordActivity;
import com.zhongyiguolian.zy.ui.person.activity.ChangePayPasswordActivity;
import com.zhongyiguolian.zy.ui.person.activity.FeedBackActivity;
import com.zhongyiguolian.zy.ui.person.activity.SetEmbodimentAccountActivity;
import com.zhongyiguolian.zy.ui.person.activity.SetFetchAddressActivity;
import com.zhongyiguolian.zy.ui.person.activity.SetPayPasswordActivity;

/**
 * 设置viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class SettingViewModel extends CustomViewModel<MyRepository> {


    /**
     * 姓名
     */
    public ObservableField<String> idCardName = new ObservableField<>();

    /**
     * @param application
     * @param model
     */
    public SettingViewModel(@NonNull Application application, MyRepository model) {
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
        //显示弹框
        public SingleLiveEvent<Void> showDialog = new SingleLiveEvent<>();
    }


    /**
     * 修改登录密码
     */
    public BindingCommand changePassword = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(ChangePasswordActivity.class);
        }
    });


    /**
     * 设置支付密码
     */
    public BindingCommand setPayPassword = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(SetPayPasswordActivity.class);
        }
    });


    /**
     * 修改支付密码
     */
    public BindingCommand changePayPassword = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(ChangePayPasswordActivity.class);
        }
    });


    /**
     * 设置提取地址
     */
    public BindingCommand setFetchAddressPassword = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(SetFetchAddressActivity.class);
        }
    });


    /**
     * 设置提取账户页面
     */
    public BindingCommand setEmbodimentAccount = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(SetEmbodimentAccountActivity.class);
        }
    });


    /**
     * 设置意见反馈
     */
    public BindingCommand goFeedBack = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(FeedBackActivity.class);
        }
    });


    /**
     * 退出登录
     */
    public BindingCommand returnLogin = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            model.saveUser(null);

            startActivity(LoginActivity.class);
        }
    });

}
