package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.zhongyiguolian.zy.BR;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.person.activity.FilWthdrawalActivity;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class FilReflectDetailViewModel extends CustomViewModel<MyRepository> {

    public FilReflectDetailViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    //封装一个界面发生改变的观察者
    public UIChangeObservable uc = new UIChangeObservable();
    public class UIChangeObservable {
        //显示密码输入框
        public SingleLiveEvent<Void> showEnterPassword = new SingleLiveEvent<>();
    }

    //输入密码
    public BindingCommand enterPassword = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.showEnterPassword.call();
        }
    });


}
