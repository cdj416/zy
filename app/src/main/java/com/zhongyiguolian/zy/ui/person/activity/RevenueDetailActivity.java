package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.databinding.ActivityRevenueDetailBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.RevenueViewModel;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 提币地址列表页面
 * @author cdj
 * @date 2020/12/10
 */
public class RevenueDetailActivity extends CustomActivity<ActivityRevenueDetailBinding, RevenueViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_revenue_detail;
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
    public RevenueViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(RevenueViewModel.class);
    }

    /**
     * 初始化
     */
    @Override
    public void initView() {
        super.initView();
        setOnRefresh(binding.refresh,REFRESH_0X3);
        setEnableLoadMore(Constants.GETMYINCOME1);
        setEnableRefresh(Constants.GETMYINCOME1);

        //setEnableLoadMore(Constants.GET_CIRCLE_CATEGORY_LIST);
        //setEnableRefresh(Constants.GET_CIRCLE_CATEGORY_LIST);

        binding.comBack.setOnClickListener(view -> finish());

        binding.title.setText(getBundle().getString("title"));
    }


    /**
     * 获取数据
     */
    @Override
    public void initData() {
        super.initData();

        viewModel.rType.set(getBundle().getString("type"));

        viewModel.setRefParams("type",AndroidDes3Util.encode(viewModel.rType.get()))
                .setRefParams("pageIndex",AndroidDes3Util.encode("1"))
                .setRefParams("pageSize",AndroidDes3Util.encode("10"))
                .setRefParams("token",AndroidDes3Util.encode(viewModel.loginBean.getToken()));

        viewModel.clearParams().setParams("pageIndex", AndroidDes3Util.encode("1"))
                .setParams("pageSize",AndroidDes3Util.encode("10"))
                .setParams("type",AndroidDes3Util.encode(viewModel.rType.get()))
                .setParams("token",AndroidDes3Util.encode(viewModel.loginBean.getToken()))
                .requestData(Constants.GETMYINCOME1);
    }
}
