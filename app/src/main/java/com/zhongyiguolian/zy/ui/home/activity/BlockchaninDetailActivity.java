package com.zhongyiguolian.zy.ui.home.activity;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.databinding.ActivityBlockchainDetailsBinding;
import com.zhongyiguolian.zy.ui.home.viewmodel.BlockchainDetailsViewModel;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 行业知识详情页面
 * @author cdj
 * @date 2020/12/10
 */
public class BlockchaninDetailActivity extends CustomActivity<ActivityBlockchainDetailsBinding, BlockchainDetailsViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_blockchain_details;
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
    public BlockchainDetailsViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(BlockchainDetailsViewModel.class);
    }

    /**
     * 页面初始化
     */
    @Override
    public void initView() {
        super.initView();
        setOnRefresh(binding.refresh,REFRESH_0X4);

        //setEnableLoadMore(Constants.GET_CIRCLE_CATEGORY_LIST);
        //setEnableRefresh(Constants.GET_CIRCLE_CATEGORY_LIST);

        binding.comBack.setOnClickListener(view -> finish());
    }

    /**
     * 数据
     */
    @Override
    public void initData() {
        super.initData();

        //请求左边数据，只请求一次
    }

}
