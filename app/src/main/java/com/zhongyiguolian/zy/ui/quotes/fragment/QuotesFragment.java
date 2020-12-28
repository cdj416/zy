package com.zhongyiguolian.zy.ui.quotes.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomFragment;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.databinding.FragmentQuotesBinding;
import com.zhongyiguolian.zy.myview.tablayout.MyPageTransformer;
import com.zhongyiguolian.zy.myview.tablayout.MyTabLayoutMediator;
import com.zhongyiguolian.zy.myview.tablayout.TabLayout;
import com.zhongyiguolian.zy.ui.Page2Adapter;
import java.util.ArrayList;
import java.util.List;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 行情页面
 * @author cdj
 * @date 2020/12/10
 */
public class QuotesFragment extends CustomFragment<FragmentQuotesBinding, CustomViewModel> {

    /**
     * 数据
     */
    private List<CustomFragment> fragments;

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_quotes;
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

        //初始化数据
        fragments = new ArrayList<>();
        fragments.add(new QuotesListFragment().setTabTag("行情"));
        fragments.add(new DynamicFragment().setTabTag("热点"));

        binding.viewPager.setAdapter(new Page2Adapter(getActivity(),fragments));
        MyTabLayoutMediator tabLayoutMediator = new MyTabLayoutMediator(binding.layoutMenu, binding.viewPager, (tab, position) -> {
            CustomFragment f = fragments.get(position);
            tab.setText(f.getTabTag());
        });
        tabLayoutMediator.attach();

        binding.layoutMenu.post(() -> setScale(MyPageTransformer.MAX_SCALE));
        binding.viewPager.setPageTransformer(new MyPageTransformer(binding.layoutMenu));

        binding.viewPager.setUserInputEnabled(false);

    }

    /**
     * 将 Tab[index] 放大为初始的 scale 倍
     * @param scale
     */
    private void setScale(float scale) {
        LinearLayout ll = (LinearLayout) binding.layoutMenu.getChildAt(0);
        TabLayout.TabView tb = (TabLayout.TabView) ll.getChildAt(0);
        View view  = tb.getTextView();
        view.setScaleX(scale);
        view.setScaleY(scale);
    }

}
