package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.databinding.ActivityMyProductDetailsBinding;
import com.zhongyiguolian.zy.ui.person.beans.PurchaseHistoryBeans;
import com.zhongyiguolian.zy.ui.person.viewmodel.MyProductDetailsViewModel;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;
import com.zhongyiguolian.zy.utils.CustomDialog;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 我的产品详情页面
 * @author cdj
 * @date 2020/12/10
 */
public class MyProductDeatilsActivity extends CustomActivity<ActivityMyProductDetailsBinding, MyProductDetailsViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_my_product_details;
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
    public MyProductDetailsViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(MyProductDetailsViewModel.class);
    }

    /**
     * ui变动
     */
    @Override
    public void initView() {
        super.initView();
        setOnRefresh(binding.refresh,REFRESH_0X4);

        binding.comBack.setOnClickListener(view -> finish());

        viewModel.entity.set((PurchaseHistoryBeans.MinerDetailVoDTO) getBundle().getSerializable("entity"));

    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        viewModel.uc.showPayType.observe(this,aVoid -> {

            //支付弹框
            CustomDialog.enterPayPassword(MyProductDeatilsActivity.this, (v1, message) -> {
                viewModel.payPassword(message);
            });

        });
    }

    @Override
    public void initData() {
        super.initData();

        //获取商家银行卡信息
        viewModel.requestData(Constants.GETSYSTEMPAYCODE);

        viewModel.clearParams().setParams("orderId",String.valueOf(viewModel.entity.get().getProductId()))
                .setParams("productType", AndroidDes3Util.encode("CUSTODY"))
                .requestNoData(Constants.CUSTODY_FEE_INFO);
    }
}
