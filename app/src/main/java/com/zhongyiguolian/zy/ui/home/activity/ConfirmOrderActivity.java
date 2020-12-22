package com.zhongyiguolian.zy.ui.home.activity;

import android.os.Bundle;
import android.view.View;
import androidx.lifecycle.ViewModelProviders;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.databinding.ActivityConfirmOrderBinding;
import com.zhongyiguolian.zy.ui.home.viewmodel.ComfirmOrderViewModel;
import com.zhongyiguolian.zy.utils.CustomDialog;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 服务器详情
 * @author cdj
 * @date 2020/12/10
 */
public class ConfirmOrderActivity extends CustomActivity<ActivityConfirmOrderBinding, ComfirmOrderViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_confirm_order;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public ComfirmOrderViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(ComfirmOrderViewModel.class);
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

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        viewModel.uc.showPayType.observe(this, aVoid -> {
            CustomDialog.changePay(this, new CustomDialog.DialogClick() {
                @Override
                public void dialogClick(View v) {

                }
            });
        });
    }
}
