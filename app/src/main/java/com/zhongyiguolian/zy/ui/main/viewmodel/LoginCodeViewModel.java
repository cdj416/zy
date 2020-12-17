package com.zhongyiguolian.zy.ui.main.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.main.activity.CountrysActivity;
import com.zhongyiguolian.zy.ui.main.activity.MainActivity;
import com.zhongyiguolian.zy.ui.main.activity.RetrievePasswordActivity;

import java.util.List;

/**
 * 登录viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class LoginCodeViewModel extends CustomViewModel<MyRepository> {

    //是否密码登录
    public ObservableField<Boolean> isPassword = new ObservableField<>(false);
    //协议是否选中
    public ObservableField<Boolean> isCheck = new ObservableField<>(false);

    public LoginCodeViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    //密码登录
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

    //选择国家
    public BindingCommand goCountrys = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(CountrysActivity.class);
        }
    });

    //忘记密码
    public BindingCommand goRetrievePassword = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(RetrievePasswordActivity.class);
        }
    });

    //进入主页
    public BindingCommand goMain = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(MainActivity.class);
        }
    });

    //选中协议
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

    //封装一个界面发生改变的观察者
    public UIChangeObservable uc = new UIChangeObservable();
    public class UIChangeObservable {
        //改变协议选中状态
        public SingleLiveEvent<Boolean> checkXy = new SingleLiveEvent<>();
    }

}
