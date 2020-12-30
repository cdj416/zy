package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.databinding.ActivityBankCardBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.BankCardViewModel;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 银行卡选中列表
 * @author cdj
 * @date 2020/12/10
 */
public class BankCardActivity extends CustomActivity<ActivityBankCardBinding, BankCardViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_bank_card;
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
    public BankCardViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(BankCardViewModel.class);
    }


    /**
     * UI初始化
     */
    @Override
    public void initView() {
        super.initView();
        binding.comBack.setOnClickListener(view -> finish());
        setOnRefresh(binding.refresh,REFRESH_0X4);
    }

    /**
     * 查询数据
     */
    @Override
    public void initData() {
        super.initData();

        //查询银行卡列表
        viewModel.requestData(Constants.CARD_LIST);
    }
}
