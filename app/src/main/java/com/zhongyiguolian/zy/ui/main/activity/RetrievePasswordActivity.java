package com.zhongyiguolian.zy.ui.main.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.databinding.ActivityRetrievePasswordBinding;
import com.zhongyiguolian.zy.ui.main.viewmodel.RetrievePasswordViewModel;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 注册页面
 * @author cdj
 * @date 2020/12/10
 */
public class RetrievePasswordActivity extends CustomActivity<ActivityRetrievePasswordBinding, RetrievePasswordViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_retrieve_password;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public RetrievePasswordViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(RetrievePasswordViewModel.class);
    }

    @Override
    public void initView() {
        super.initView();

        setOnRefresh(binding.refresh,REFRESH_0X4);
        binding.comBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("cnn","=-=======================点击了没=====");

                finish();
            }
        });
    }

    @Override
    public void initData() {
        super.initData();

    }
}
