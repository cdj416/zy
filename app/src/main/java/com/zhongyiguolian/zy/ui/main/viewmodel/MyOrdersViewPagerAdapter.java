package com.zhongyiguolian.zy.ui.main.viewmodel;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.zhongyiguolian.zy.TabTitleBean;
import com.zhongyiguolian.zy.base.CustomFragment;
import com.zhongyiguolian.zy.myview.dynamicviewpage.CustomViewpager;
import com.zhongyiguolian.zy.ui.main.fragment.LoginCodeFragment;
import com.zhongyiguolian.zy.ui.main.fragment.RegisteredFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * viewpagespq
 * @author cdj
 * @date 2020/12/10
 */
public class MyOrdersViewPagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    private List<TabTitleBean> beans;

    public MyOrdersViewPagerAdapter(FragmentManager fm, CustomViewpager customViewpager) {
        super(fm);
        setData(customViewpager);
    }

    @Override
    public CustomFragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return beans.get(position).getTitleName();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    /*
     * 初始化数据
     * */
    public void setData(CustomViewpager customViewpager) {
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        if(beans == null){
            beans = new ArrayList<>();
        }
        fragments.clear();
        beans.clear();
        beans.add(new TabTitleBean("登录",0));
        beans.add(new TabTitleBean("注册",1));
        fragments.add(new LoginCodeFragment().setViewPager(customViewpager));
        fragments.add(new RegisteredFragment().setViewPager(customViewpager));

        notifyDataSetChanged();
    }
}
