package com.zhongyiguolian.zy.ui.home.activity;

import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.databinding.ActivityServiceBinding;
import com.zhongyiguolian.zy.ui.home.viewmodel.ServiceViewModel;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 服务器列表页面
 * @author cdj
 * @date 2020/12/10
 */
public class ServiceActivity extends CustomActivity<ActivityServiceBinding, ServiceViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_service;
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
    public ServiceViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(ServiceViewModel.class);
    }

    /**
     * 初始化
     */
    @Override
    public void initView() {
        super.initView();

        setOnRefresh(binding.refresh,REFRESH_0X3);
        setEnableLoadMore(Constants.PRODUCT_LIST);
        setEnableRefresh(Constants.PRODUCT_LIST);

        binding.comBack.setOnClickListener(view -> finish());
    }

    /**
     * 数据
     */
    @Override
    public void initData() {
        super.initData();
        //加载参数数据
        viewModel.setRefParams("orderStatus","EX_ORDER_STATUS_UPPER_SHELF");

        //请求左边数据，只请求一次
        viewModel.setParams("orderStatus","EX_ORDER_STATUS_UPPER_SHELF")
                .requestNoData(Constants.PRODUCT_LIST);
    }

}
