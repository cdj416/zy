package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.databinding.ActivityTransactionDetailsBinding;
import com.zhongyiguolian.zy.ui.person.beans.TransactionRecordBeans;
import com.zhongyiguolian.zy.ui.person.viewmodel.TransactionDetailsViewModel;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 交易记录详情页
 * @author cdj
 * @date 2020/12/10
 */
public class TransactionDetailActivity extends CustomActivity<ActivityTransactionDetailsBinding, TransactionDetailsViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_transaction_details;
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
    public TransactionDetailsViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(TransactionDetailsViewModel.class);
    }

    /**
     * 初始化
     */
    @Override
    public void initView() {
        super.initView();
        setOnRefresh(binding.refresh,REFRESH_0X4);

        binding.comBack.setOnClickListener(view -> finish());
    }


    /**
     * 获取数据
     */
    @Override
    public void initData() {
        super.initData();

        viewModel.entity.set((TransactionRecordBeans.RecordLogDTO) getBundle().getSerializable("bean"));
    }
}
