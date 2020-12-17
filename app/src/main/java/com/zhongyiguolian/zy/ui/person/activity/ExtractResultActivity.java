package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;

import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
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

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_extract_result;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }


    @Override
    public void initView() {
        super.initView();
        binding.comBack.setOnClickListener(view -> finish());
    }

}
