package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayoutMediator;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.base.CustomFragment;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.databinding.ActivityWithdrawBinding;
import com.zhongyiguolian.zy.ui.Page2Adapter;
import com.zhongyiguolian.zy.ui.person.fragment.FilReflectFragment;
import com.zhongyiguolian.zy.ui.person.fragment.SalesWithdrawalFragment;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 关于我们页面
 * @author cdj
 * @date 2020/12/10
 */
public class WithdrawalActivity extends CustomActivity<ActivityWithdrawBinding, CustomViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_withdraw;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }


    @Override
    public void initView() {
        super.initView();
        binding.comBack.setOnClickListener(view -> finish());

        //初始化数据
        List<CustomFragment> fragments = new ArrayList<>();
        fragments.add(new FilReflectFragment().setTabTag("FIL提现"));
        fragments.add(new SalesWithdrawalFragment().setTabTag("销售提现"));

        binding.viewPager.setAdapter(new Page2Adapter(this,fragments));
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(binding.layoutMenu, binding.viewPager, (tab, position) -> {
            CustomFragment f = fragments.get(position);
            tab.setText(f.getTabTag());
        });
        tabLayoutMediator.attach();
    }

}
