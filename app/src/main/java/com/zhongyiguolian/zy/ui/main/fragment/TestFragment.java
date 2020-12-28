package com.zhongyiguolian.zy.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomFragment;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.databinding.FragmentTestBinding;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 测试占位
 * @author cdj
 * @date 2020/12/10
 */
public class TestFragment extends CustomFragment<FragmentTestBinding, CustomViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_test;
    }

    /**
     * @return
     */
    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    /**
     *
     */
    @Override
    public void initView() {
        super.initView();
    }
}
