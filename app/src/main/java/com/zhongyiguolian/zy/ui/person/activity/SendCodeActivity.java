package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.databinding.ActivitySendCodeBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.SendCodeViewModel;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 银行信息验证页面
 * @author cdj
 * @date 2020/12/10
 */
public class SendCodeActivity extends CustomActivity<ActivitySendCodeBinding, SendCodeViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_send_code;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public SendCodeViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(SendCodeViewModel.class);
    }


    @Override
    public void initView() {
        super.initView();
        binding.comBack.setOnClickListener(view -> finish());
        //setOnRefresh(binding.refresh,REFRESH_0X4);

    }

}
