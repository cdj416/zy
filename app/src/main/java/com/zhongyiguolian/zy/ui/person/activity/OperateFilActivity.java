package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;
import android.view.View;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.databinding.ActivityOperateFilBinding;
import com.zhongyiguolian.zy.utils.CustomDialog;
import com.zhongyiguolian.zy.utils.HiddenAnimUtils;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 充值详情页类型
 * @author cdj
 * @date 2020/12/10
 */
public class OperateFilActivity extends CustomActivity<ActivityOperateFilBinding, CustomViewModel> {

    /**
     * 动画工具类
     */
    private HiddenAnimUtils animUtils;

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_operate_fil;
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

        animUtils = HiddenAnimUtils.newInstance(this,binding.hidView,binding.dwonImg,31,true);

        binding.clickView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animUtils.toggle();
            }
        });

        binding.moText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.moText1.setBackgroundResource(R.drawable.shape_radius24_5690ff);
                binding.moText2.setBackgroundResource(R.drawable.shape_radius24_adc7ff);
                binding.moText3.setBackgroundResource(R.drawable.shape_radius24_adc7ff);

                binding.showText.setText(binding.moText1.getText().toString());

                animUtils.toggle();
            }
        });

        binding.moText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.moText1.setBackgroundResource(R.drawable.shape_radius24_adc7ff);
                binding.moText2.setBackgroundResource(R.drawable.shape_radius24_5690ff);
                binding.moText3.setBackgroundResource(R.drawable.shape_radius24_adc7ff);

                binding.showText.setText(binding.moText2.getText().toString());

                animUtils.toggle();
            }
        });

        binding.moText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.moText1.setBackgroundResource(R.drawable.shape_radius24_adc7ff);
                binding.moText2.setBackgroundResource(R.drawable.shape_radius24_adc7ff);
                binding.moText3.setBackgroundResource(R.drawable.shape_radius24_5690ff);

                binding.showText.setText(binding.moText3.getText().toString());

                animUtils.toggle();
            }
        });

        binding.openDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.showFilCharge(OperateFilActivity.this, new CustomDialog.DialogClick() {
                    @Override
                    public void dialogClick(View v) {

                    }
                });
            }
        });
    }
}
