package com.zhongyiguolian.zy.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.zhongyiguolian.zy.R;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class MyAdapter extends PagerAdapter {

    private Context mContext;

    public MyAdapter(Context context){
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return 4;//无限轮播
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_new_home,null);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object
            object) {
//            super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
