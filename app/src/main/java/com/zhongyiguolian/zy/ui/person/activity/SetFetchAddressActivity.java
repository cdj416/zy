package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.databinding.ActivitySetFetchAddressBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.SetFetchAddressViewModel;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 设置提取地址页面
 * @author cdj
 * @date 2020/12/10
 */
public class SetFetchAddressActivity extends CustomActivity<ActivitySetFetchAddressBinding, SetFetchAddressViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_set_fetch_address;
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
    public SetFetchAddressViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(SetFetchAddressViewModel.class);
    }


    /**
     * ui
     */
    @Override
    public void initView() {
        super.initView();
        binding.comBack.setOnClickListener(view -> finish());
        //setOnRefresh(binding.refresh,REFRESH_0X4);
    }

    /**
     * 数据
     */
    @Override
    public void initData() {
        super.initData();

        viewModel.clearParams().setParams("currencyId", AndroidDes3Util.encode("1"))//USDT的id为1
                .setParams("token",AndroidDes3Util.encode(viewModel.loginBean.getToken()))
                .requestData(Constants.WITHDRAW_ADDRESS);
    }
}
