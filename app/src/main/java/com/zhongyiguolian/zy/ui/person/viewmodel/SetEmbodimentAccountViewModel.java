package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.person.activity.BankCardActivity;

/**
 * Fil体现
 * @author cdj
 * @date 2020/12/10
 */
public class SetEmbodimentAccountViewModel extends CustomViewModel<MyRepository> {

    //姓名
    public ObservableField<String> idCardName = new ObservableField<>();
    public SetEmbodimentAccountViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    //封装一个界面发生改变的观察者
    public UIChangeObservable uc = new UIChangeObservable();
    public class UIChangeObservable {
        //显示弹框
        public SingleLiveEvent<Void> showDialog = new SingleLiveEvent<>();
    }

    //更换体现银行卡
    public BindingCommand goBankList = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(BankCardActivity.class);
        }
    });

}
