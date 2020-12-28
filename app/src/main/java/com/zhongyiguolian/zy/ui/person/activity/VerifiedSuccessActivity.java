package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;

import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.databinding.ActivityVerifiedSuccessBinding;
import com.zhongyiguolian.zy.ui.main.activity.MainActivity;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 体现状态页面
 * @author cdj
 * @date 2020/12/10
 */
public class VerifiedSuccessActivity extends CustomActivity<ActivityVerifiedSuccessBinding, CustomViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_verified_success;
    }

    /**
     * @return
     */
    @Override
    public int initVariableId() {
        return BR.viewModel;
    }


    /**
     * ui初始化
     */
    @Override
    public void initView() {
        super.initView();
        binding.comBack.setOnClickListener(view -> finish());

        setOnRefresh(binding.refresh,REFRESH_0X4);

        binding.goMain.setOnClickListener(v -> {
            startActivity(MainActivity.class);
        });

        binding.title.setText(getBundle().getString("mTitle"));
        binding.proText.setText(getBundle().getString("mProText"));


    }

}
