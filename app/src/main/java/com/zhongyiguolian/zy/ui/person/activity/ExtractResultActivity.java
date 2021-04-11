package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;

import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.databinding.ActivityExtractResultBinding;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 体现状态页面
 * @author cdj
 * @date 2020/12/10
 */
public class ExtractResultActivity extends CustomActivity<ActivityExtractResultBinding, CustomViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_extract_result;
    }

    /**
     * @return
     */
    @Override
    public int initVariableId() {
        return BR.viewModel;
    }


    /**
     * ui变化
     */
    @Override
    public void initView() {
        super.initView();
        setOnRefresh(binding.refresh,REFRESH_0X4);

        binding.comBack.setOnClickListener(view -> finish());
        binding.goMain.setOnClickListener(v -> finish());

        binding.title.setText(getBundle().getString("title"));
        binding.proText.setText(getBundle().getString("proText"));
        binding.proText1.setText(getBundle().getString("proText1"));
    }

}
