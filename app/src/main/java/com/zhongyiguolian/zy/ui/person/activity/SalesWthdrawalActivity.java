package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.databinding.ActivitySalesWithdrawalBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.SalesWithdrawalDetailsViewModel;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;
import com.zhongyiguolian.zy.utils.BigDecimalUtils;
import com.zhongyiguolian.zy.utils.CustomDialog;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 销售体现页面
 * @author cdj
 * @date 2020/12/10
 */
public class SalesWthdrawalActivity extends CustomActivity<ActivitySalesWithdrawalBinding, SalesWithdrawalDetailsViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_sales_withdrawal;
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
    public SalesWithdrawalDetailsViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(SalesWithdrawalDetailsViewModel.class);
    }

    /**
     * ui变动
     */
    @Override
    public void initView() {
        super.initView();
        binding.comBack.setOnClickListener(view -> finish());
    }

    /**
     * ui变动
     */
    @Override
    public void initViewObservable() {
        super.initViewObservable();

        viewModel.uc.showEnterPassword.observe(this,aVoid -> {
            if(viewModel.nums.get() != null && viewModel.goWithdrawal.get() != null){

                String sxf = BigDecimalUtils.mul(viewModel.nums.get(),BigDecimalUtils.div(String.valueOf(viewModel.goWithdrawal.get().getPoundage()),"100",4),2);

                CustomDialog.enterPassword(SalesWthdrawalActivity.this,viewModel.nums.get(), "￥"+sxf, (v, message) -> {
                    //请求提币
                    viewModel.doWithdrawToken(message);
                });
            }
        });
    }

    /**
     * 数据
     */
    @Override
    public void initData() {
        super.initData();

        //获取银行卡
        viewModel.requestData(Constants.GETPAYCODE);
    }
}
