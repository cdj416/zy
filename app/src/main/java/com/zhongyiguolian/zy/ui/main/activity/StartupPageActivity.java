package com.zhongyiguolian.zy.ui.main.activity;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.databinding.ActivityStartupPageBinding;
import com.zhongyiguolian.zy.ui.main.viewmodel.StartupPageViewModel;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 启动页activity
 * @author cdj
 * @date 2020/12/10
 */
public class StartupPageActivity extends CustomActivity<ActivityStartupPageBinding, StartupPageViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_startup_page;
    }

    /**
     * @return
     */
    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    /**
     * @return
     */
    @Override
    public StartupPageViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(StartupPageViewModel.class);
    }
}
