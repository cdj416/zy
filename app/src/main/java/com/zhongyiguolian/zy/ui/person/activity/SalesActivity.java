package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.tabs.TabLayoutMediator;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.base.CustomFragment;
import com.zhongyiguolian.zy.databinding.ActivitySalesBinding;
import com.zhongyiguolian.zy.ui.Page2Adapter;
import com.zhongyiguolian.zy.ui.person.fragment.BrokerageFragment;
import com.zhongyiguolian.zy.ui.person.fragment.KpiFragment;
import com.zhongyiguolian.zy.ui.person.viewmodel.SalesViewModel;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 销售提成页面
 * @author cdj
 * @date 2020/12/10
 */
public class SalesActivity extends CustomActivity<ActivitySalesBinding, SalesViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_sales;
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
    public SalesViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(SalesViewModel.class);
    }


    /**
     * ui变动
     */
    @Override
    public void initView() {
        super.initView();
        binding.comBack.setOnClickListener(view -> finish());

        //初始化数据
        List<CustomFragment> fragments = new ArrayList<>();
        fragments.add(new BrokerageFragment().setTabTag("佣金收益"));
        fragments.add(new KpiFragment().setTabTag("KPI收益"));

        binding.viewPager.setAdapter(new Page2Adapter(this,fragments));
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(binding.layoutMenu, binding.viewPager, (tab, position) -> {
            CustomFragment f = fragments.get(position);
            tab.setText(f.getTabTag());
        });
        tabLayoutMediator.attach();
    }

}
