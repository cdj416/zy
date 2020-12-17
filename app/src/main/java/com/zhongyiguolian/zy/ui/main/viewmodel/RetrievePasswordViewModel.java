package com.zhongyiguolian.zy.ui.main.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.main.activity.RetrievePasswordActivity;

/**
 * 找回密码
 * @author cdj
 * @date 2020/12/10
 */
public class RetrievePasswordViewModel extends CustomViewModel<MyRepository> {

    public RetrievePasswordViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    //关闭
    public BindingCommand finish = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });

}
