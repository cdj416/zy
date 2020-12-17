package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.MyRepository;

/**
 * Fil体现
 * @author cdj
 * @date 2020/12/10
 */
public class ChangePasswordViewModel extends CustomViewModel<MyRepository> {

    //姓名
    public ObservableField<String> idCardName = new ObservableField<>();
    public ChangePasswordViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    //封装一个界面发生改变的观察者
    public UIChangeObservable uc = new UIChangeObservable();
    public class UIChangeObservable {
        //显示弹框
        public SingleLiveEvent<Void> showDialog = new SingleLiveEvent<>();
    }

    //更改头像操作
    public BindingCommand changeHeader = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.showDialog.call();
        }
    });

}
