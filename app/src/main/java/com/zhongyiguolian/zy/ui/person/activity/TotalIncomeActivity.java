package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.databinding.ActivityMyWalletBinding;
import com.zhongyiguolian.zy.databinding.ActivityTotalIncomeBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.MyWalletViewModel;
import com.zhongyiguolian.zy.ui.person.viewmodel.TotalIncomeViewModel;
import com.zhongyiguolian.zy.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 累计收益
 * @author cdj
 * @date 2020/12/10
 */
public class TotalIncomeActivity extends CustomActivity<ActivityTotalIncomeBinding, TotalIncomeViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_total_income;
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
    public TotalIncomeViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(TotalIncomeViewModel.class);
    }

    /**
     * 初始化
     */
    @Override
    public void initView() {
        super.initView();

        //设置状态栏为黑色
        StatusBarUtil.setCommonUI(this,true);
        setOnRefresh(binding.refresh,REFRESH_0X4);

        binding.comBack.setOnClickListener(view -> finish());
    }

    /**
     * 数据
     */
    @Override
    public void initData() {
        super.initData();

        /*viewModel.setRefParams("type",AndroidDes3Util.encode("Static"))
                .setParams("pageIndex",AndroidDes3Util.encode("1"))
                .setParams("pageSize",AndroidDes3Util.encode("10"));

        viewModel.setParams("type", AndroidDes3Util.encode("Static"))
                .setParams("pageIndex",AndroidDes3Util.encode("1"))
                .setParams("pageSize",AndroidDes3Util.encode("10"))
                .requestData(Constants.GETMYINCOME1);

        //获取可提现FIL币
        viewModel.setParams("accountType",AndroidDes3Util.encode("base"))
                .requestData(Constants.GETALLASSETS);*/
    }

}
