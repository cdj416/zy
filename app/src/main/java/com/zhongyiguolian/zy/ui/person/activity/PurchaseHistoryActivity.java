package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayoutMediator;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.base.CustomFragment;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.databinding.ActivityPurchaseHistoryBinding;
import com.zhongyiguolian.zy.ui.Page2Adapter;
import com.zhongyiguolian.zy.ui.person.fragment.PurchaseHistoryFragment;
import java.util.ArrayList;
import java.util.List;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 服务器购买记录
 * @author cdj
 * @date 2020/12/10
 */
public class PurchaseHistoryActivity extends CustomActivity<ActivityPurchaseHistoryBinding, CustomViewModel> {

    /**
     * 数据
     */
    private List<CustomFragment> fragments;

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_purchase_history;
    }

    /**
     * @return
     */
    @Override
    public int initVariableId() {
        return BR.viewModel;
    }


    /**
     * ui
     */
    @Override
    public void initView() {
        super.initView();
        binding.comBack.setOnClickListener(view -> finish());

        //初始化数据
        fragments = new ArrayList<>();
        fragments.add(new PurchaseHistoryFragment().setTabTag("全部订单").setPosition(""));
        fragments.add(new PurchaseHistoryFragment().setTabTag("待付款").setPosition("TRADE"));
        fragments.add(new PurchaseHistoryFragment().setTabTag("已完成").setPosition("DONE"));

        binding.viewPager.setAdapter(new Page2Adapter(this,fragments));
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(binding.layoutMenu, binding.viewPager, (tab, position) -> {
            CustomFragment f = fragments.get(position);
            tab.setText(f.getTabTag());
        });
        tabLayoutMediator.attach();
    }

}
