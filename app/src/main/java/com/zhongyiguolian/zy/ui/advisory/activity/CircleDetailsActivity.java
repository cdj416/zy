package com.zhongyiguolian.zy.ui.advisory.activity;

import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.databinding.ActivityCircleDetailsBinding;
import com.zhongyiguolian.zy.ui.advisory.viewmodel.CircleDetailsViewModel;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 区块链页面
 * @author cdj
 * @date 2020/12/10
 */
public class CircleDetailsActivity extends CustomActivity<ActivityCircleDetailsBinding, CircleDetailsViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_circle_details;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public CircleDetailsViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(CircleDetailsViewModel.class);
    }

    @Override
    public void initView() {
        super.initView();
        setOnRefresh(binding.refresh,REFRESH_0X4);

        //setEnableLoadMore(Constants.GET_CIRCLE_CATEGORY_LIST);
        //setEnableRefresh(Constants.GET_CIRCLE_CATEGORY_LIST);

        binding.comBack.setOnClickListener(view -> finish());
    }

    @Override
    public void initData() {
        super.initData();

        //请求左边数据，只请求一次
    }

}
