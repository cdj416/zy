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

    /**
     * fragments
     */
    private List<CustomFragment> fragments;

    /**
     * @param fragmentActivity
     * @param fragments
     */
    public Page2Adapter(@NonNull FragmentActivity fragmentActivity, List<CustomFragment> fragments) {
        super(fragmentActivity);

        this.fragments = fragments;
    }

    /**
     * @param position
     * @return
     */
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    /**
     * @return
     */
    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
