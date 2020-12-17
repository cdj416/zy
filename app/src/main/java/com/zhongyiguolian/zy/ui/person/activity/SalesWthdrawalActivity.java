package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;

import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.databinding.ActivitySalesWithdrawalBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.SalesWithdrawalDetailsViewModel;
import com.zhongyiguolian.zy.utils.CustomDialog;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 销售体现页面
 * @author cdj
 * @date 2020/12/10
 */
public class SalesWthdrawalActivity extends CustomActivity<ActivitySalesWithdrawalBinding, SalesWithdrawalDetailsViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_sales_withdrawal;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public SalesWithdrawalDetailsViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(SalesWithdrawalDetailsViewModel.class);
    }

    @Override
    public void initView() {
        super.initView();
        binding.comBack.setOnClickListener(view -> finish());
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        viewModel.uc.showEnterPassword.observe(this,aVoid -> {
            CustomDialog.enterPassword(SalesWthdrawalActivity.this, new CustomDialog.DialogClick() {
                @Override
                public void dialogClick(View v) {
                    startActivity(ExtractResultActivity.class);
                }
            });
        });
    }
}
