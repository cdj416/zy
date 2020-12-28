package com.zhongyiguolian.zy.ui.advisory.activity;

import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.databinding.ActivityForwardBinding;
import com.zhongyiguolian.zy.ui.advisory.viewmodel.ForwardViewModel;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 转发页面
 * @author cdj
 * @date 2020/12/10
 */
public class ForwardActivity extends CustomActivity<ActivityForwardBinding, ForwardViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_forward;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public ForwardViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(ForwardViewModel.class);
    }

    @Override
    public void initView() {
        super.initView();
        setOnRefresh(binding.refresh,REFRESH_0X4);
        binding.comBack.setOnClickListener(view -> finish());
    }

    @Override
    public void initData() {
        super.initData();

        //请求左边数据，只请求一次
    }

}
