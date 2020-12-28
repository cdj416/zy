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
 * viewpage登录页面
 * @author cdj
 * @date 2020/12/10
 */
public class MyOrdersViewPagerAdapter extends FragmentPagerAdapter {

    /**
     * fragments
     */
    private List<CustomFragment> fragments;

    /**
     * 标题栏
     */
    private List<TabTitleBean> beans;

    /**
     * @param fm
     * @param customViewpager
     */
    public MyOrdersViewPagerAdapter(FragmentManager fm, CustomViewpager customViewpager) {
        super(fm);
        setData(customViewpager);
    }

    /**
     * @param position
     * @return
     */
    @Override
    public CustomFragment getItem(int position) {
        return fragments.get(position);
    }

    /**
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return beans.get(position).getTitleName();
    }

    /**
     * @return
     */
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
