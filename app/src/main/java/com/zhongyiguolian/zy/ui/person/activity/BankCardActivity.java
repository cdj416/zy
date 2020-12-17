package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.databinding.ActivityBankCardBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.BankCardViewModel;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 设置页面
 * @author cdj
 * @date 2020/12/10
 */
public class BankCardActivity extends CustomActivity<ActivityBankCardBinding, BankCardViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_bank_card;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public BankCardViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(BankCardViewModel.class);
    }


    @Override
    public void initView() {
        super.initView();
        binding.comBack.setOnClickListener(view -> finish());
        setOnRefresh(binding.refresh,REFRESH_0X4);
    }

}
