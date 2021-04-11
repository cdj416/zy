package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.lifecycle.ViewModelProviders;

import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.databinding.ActivitySalesWithdrawalBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.SalesWithdrawalDetailsViewModel;
import com.zhongyiguolian.zy.utils.BigDecimalUtils;
import com.zhongyiguolian.zy.utils.CustomDialog;
import com.zhongyiguolian.zy.utils.StatusBarUtil;

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
        StatusBarUtil.setCommonUI(this,true);
        setOnRefresh(binding.refresh,REFRESH_0X4);
        binding.comBack.setOnClickListener(view -> finish());


        binding.numEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(BaseUtil.isValue(viewModel.nums.get())){
                    String sxf = BigDecimalUtils.mul(viewModel.nums.get(),BigDecimalUtils.div(String.valueOf(viewModel.goWithdrawal.get().getPoundage()),"100",4),2);
                    binding.nums.setText(BaseUtil.getNoZoon(BaseUtil.getNoZoon(sxf)));
                }else {
                    binding.nums.setText("0.00");
                }
            }
        });
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

                CustomDialog.enterPassword(SalesWthdrawalActivity.this,"到账金额",viewModel.nums.get(), "￥"+BigDecimalUtils.sub(viewModel.nums.get(),sxf,2), (v, message) -> {
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
