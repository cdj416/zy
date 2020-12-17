package com.zhongyiguolian.zy.ui.main.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.main.activity.CountrysActivity;

/**
 * 注册viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class RegisteredViewModel extends CustomViewModel<MyRepository> {

    public RegisteredViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    //选择国家
    public BindingCommand goCountrys = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(CountrysActivity.class);
        }
    });

}
