package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.databinding.ActivityMyServiceBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.MyServiceViewModel;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 销售体现页面
 * @author cdj
 * @date 2020/12/10
 */
public class MyServiceActivity extends CustomActivity<ActivityMyServiceBinding, MyServiceViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_my_service;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public MyServiceViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(MyServiceViewModel.class);
    }

    @Override
    public void initView() {
        super.initView();
        setOnRefresh(binding.refresh,REFRESH_0X4);
        binding.comBack.setOnClickListener(view -> finish());
    }

}
