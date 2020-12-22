package com.zhongyiguolian.zy.ui.home.activity;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.databinding.ActivityConfirmOrderBinding;
import com.zhongyiguolian.zy.databinding.ActivityShoppingCartBinding;
import com.zhongyiguolian.zy.ui.home.viewmodel.ComfirmOrderViewModel;
import com.zhongyiguolian.zy.ui.home.viewmodel.ShoppingCartViewModel;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 服务器购物车
 * @author cdj
 * @date 2020/12/10
 */
public class ShoppingCartActivity extends CustomActivity<ActivityShoppingCartBinding, ShoppingCartViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_shopping_cart;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public ShoppingCartViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(ShoppingCartViewModel.class);
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
