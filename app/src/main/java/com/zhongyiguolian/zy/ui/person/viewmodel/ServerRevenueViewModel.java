package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.person.activity.SendCodeActivity;

/**
 * 添加银行卡
 * @author cdj
 * @date 2020/12/10
 */
public class ServerRevenueViewModel extends CustomViewModel<MyRepository> {

    //银行卡卡号
    public ObservableField<String> blankCardNum = new ObservableField<>();
    //银行名
    public ObservableField<String> blankName = new ObservableField<>();

    public ServerRevenueViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    //封装一个界面发生改变的观察者
    public UIChangeObservable uc = new UIChangeObservable();
    public class UIChangeObservable {
        //拍照获取银行卡号
        public SingleLiveEvent<Void> getBlankNums = new SingleLiveEvent<>();
    }

    //获取银行卡号
    public BindingCommand goCamera = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.getBlankNums.call();
        }
    });

    //获取验证码验证信息
    public BindingCommand goSendCode = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(SendCodeActivity.class);
        }
    });

}
