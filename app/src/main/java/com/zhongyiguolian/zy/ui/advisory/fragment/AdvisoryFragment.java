package com.zhongyiguolian.zy.ui.advisory.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomFragment;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.databinding.FragmentAdvisoryBinding;
import com.zhongyiguolian.zy.myview.tablayout.MyPageTransformer;
import com.zhongyiguolian.zy.myview.tablayout.MyTabLayoutMediator;
import com.zhongyiguolian.zy.myview.tablayout.TabLayout;
import com.zhongyiguolian.zy.ui.Page2Adapter;
import com.zhongyiguolian.zy.ui.main.fragment.TestFragment;
import java.util.ArrayList;
import java.util.List;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 咨询页面
 * @author cdj
 * @date 2020/12/10
 */

public class AdvisoryFragment extends CustomFragment<FragmentAdvisoryBinding, CustomViewModel> {

    private List<CustomFragment> fragments;

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_advisory;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initView() {
        super.initView();

        //初始化数据
        fragments = new ArrayList<>();
        fragments.add(new HotspotListFragment().setTabTag("热点").setPosition("0"));
        fragments.add(new CircleListFragment().setTabTag("圈子").setPosition("1"));

        binding.viewPager.setAdapter(new Page2Adapter(getActivity(),fragments));
        MyTabLayoutMediator tabLayoutMediator = new MyTabLayoutMediator(binding.layoutMenu, binding.viewPager, (tab, position) -> {
            CustomFragment f = fragments.get(position);
            tab.setText(f.getTabTag());
        });
        tabLayoutMediator.attach();

        binding.layoutMenu.post(() -> setScale(MyPageTransformer.MAX_SCALE));
        binding.viewPager.setPageTransformer(new MyPageTransformer(binding.layoutMenu));

    }

    /**
     * 将 Tab[index] 放大为初始的 scale 倍
     */
    private void setScale(float scale) {
        LinearLayout ll = (LinearLayout) binding.layoutMenu.getChildAt(0);
        TabLayout.TabView tb = (TabLayout.TabView) ll.getChildAt(0);
        View view  = tb.getTextView();
        view.setScaleX(scale);
        view.setScaleY(scale);
    }

}
