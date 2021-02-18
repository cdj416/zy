package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;
import android.view.View;

import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.databinding.ActivityChargeFilBinding;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 选择充值类型
 * @author cdj
 * @date 2020/12/10
 */
public class ChargeFilActivity extends CustomActivity<ActivityChargeFilBinding, CustomViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_charge_fil;
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
        setOnRefresh(binding.refresh,REFRESH_0X4);
        binding.comBack.setOnClickListener(view -> finish());

        binding.check1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.bg1.setAlpha(1);
                binding.bg2.setAlpha(0.5f);

                binding.tx1.setVisibility(View.VISIBLE);
                binding.tx2.setVisibility(View.GONE);
            }
        });

        binding.check2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.bg1.setAlpha(0.5f);
                binding.bg2.setAlpha(1);

                binding.tx1.setVisibility(View.GONE);
                binding.tx2.setVisibility(View.VISIBLE);
            }
        });

        binding.goNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(OperateFilActivity.class);
            }
        });
    }
}
