package com.zhongyiguolian.zy.ui.home.activity;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.databinding.ActivityEncyclopediaBinding;
import com.zhongyiguolian.zy.ui.home.viewmodel.EncyclopediaViewModel;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 百科页面
 * @author cdj
 * @date 2020/12/10
 */
public class EncyclopediaActivity extends CustomActivity<ActivityEncyclopediaBinding, EncyclopediaViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_encyclopedia;
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
    public EncyclopediaViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(EncyclopediaViewModel.class);
    }

    /**
     * 初始化页面
     */
    @Override
    public void initView() {
        super.initView();
        setOnRefresh(binding.refresh,REFRESH_0X3);

        //setEnableLoadMore(Constants.GET_CIRCLE_CATEGORY_LIST);
        //setEnableRefresh(Constants.GET_CIRCLE_CATEGORY_LIST);

        binding.comBack.setOnClickListener(view -> finish());
    }

    /**
     * 首次请求数据
     */
    @Override
    public void initData() {
        super.initData();

        //请求左边数据，只请求一次
    }

}
