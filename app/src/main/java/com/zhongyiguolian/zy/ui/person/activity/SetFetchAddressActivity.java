package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.databinding.ActivitySetFetchAddressBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.SetFetchAddressViewModel;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 设置提取地址页面
 * @author cdj
 * @date 2020/12/10
 */
public class SetFetchAddressActivity extends CustomActivity<ActivitySetFetchAddressBinding, SetFetchAddressViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_set_fetch_address;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public SetFetchAddressViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(SetFetchAddressViewModel.class);
    }


    @Override
    public void initView() {
        super.initView();
        binding.comBack.setOnClickListener(view -> finish());
        //setOnRefresh(binding.refresh,REFRESH_0X4);
    }

}