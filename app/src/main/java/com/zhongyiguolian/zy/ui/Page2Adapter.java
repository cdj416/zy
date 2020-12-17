package com.zhongyiguolian.zy.ui;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.zhongyiguolian.zy.base.CustomFragment;

import java.util.List;

/**
 * tabspq
 * @author cdj
 * @date 2020/12/10
 */
public class Page2Adapter extends FragmentStateAdapter {

    private List<CustomFragment> fragments;

    public Page2Adapter(@NonNull FragmentActivity fragmentActivity, List<CustomFragment> fragments) {
        super(fragmentActivity);

        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
