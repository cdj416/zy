package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayoutMediator;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.base.CustomFragment;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.databinding.ActivityOrderRecordBinding;
import com.zhongyiguolian.zy.ui.Page2Adapter;
import com.zhongyiguolian.zy.ui.person.fragment.MyServiceRecordFragment;
import java.util.ArrayList;
import java.util.List;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 体现状态页面
 * @author cdj
 * @date 2020/12/10
 */
public class OrderRecordActivity extends CustomActivity<ActivityOrderRecordBinding, CustomViewModel> {

    private List<CustomFragment> fragments;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_order_record;
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
        fragments = new ArrayList<>();
        fragments.add(new MyServiceRecordFragment().setTabTag("全部订单"));
        fragments.add(new MyServiceRecordFragment().setTabTag("待付款"));
        fragments.add(new MyServiceRecordFragment().setTabTag("已完成"));

        binding.viewPager.setAdapter(new Page2Adapter(this,fragments));
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(binding.layoutMenu, binding.viewPager, (tab, position) -> {
            CustomFragment f = fragments.get(position);
            tab.setText(f.getTabTag());
        });
        tabLayoutMediator.attach();
    }

}
