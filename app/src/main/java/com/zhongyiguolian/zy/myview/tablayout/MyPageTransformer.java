package com.zhongyiguolian.zy.myview.tablayout;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

import java.util.HashMap;

/**
 * @author cdj
 * @date 2020/12/10
 */
public class MyPageTransformer implements ViewPager2.PageTransformer {

    private static final String TAG = "MyPageTransformer";
    public static final float MAX_SCALE = 1.5f;

    //下标标识
    private int mPosition = 0;

    private TabLayout mTabLayout;
    @SuppressLint("UseSparseArrays")
    private HashMap<Integer, Float> mLastMap = new HashMap<>();

    public MyPageTransformer(TabLayout mTabLayout) {
        this.mTabLayout = mTabLayout;
    }

    @Override
    public void transformPage(@NonNull View view, float v) {
        if (v > -1 && v < 1) {

            if(view.getTag() == null){
                view.setTag(mPosition++);
            }

            int currPosition = (int) view.getTag();
            final float currV = Math.abs(v);
            if (!mLastMap.containsKey(currPosition)) {
                mLastMap.put(currPosition, currV);
                return;
            }
            float lastV = mLastMap.get(currPosition);
            // 获取当前 TabView 的 TextView 
            LinearLayout ll = (LinearLayout) mTabLayout.getChildAt(0);
            TabLayout.TabView tb = (TabLayout.TabView) ll.getChildAt(currPosition);
            View textView = tb.getTextView();

            // 先判断是要变大还是变小
            // 如果 currV > lastV，则为变小；如果 currV < lastV，则为变大
            if (currV > lastV) {
                float leavePercent = currV; // 计算离开屏幕的百分比
                // 变小
                textView.setScaleX(MAX_SCALE - (MAX_SCALE - 1.0f) * leavePercent);
                textView.setScaleY(MAX_SCALE - (MAX_SCALE - 1.0f) * leavePercent);
            } else if (currV < lastV) {
                float enterPercent = 1 - currV; // 进入屏幕的百分比
                // 变大
                textView.setScaleX(1.0f + (MAX_SCALE - 1.0f) * enterPercent);
                textView.setScaleY(1.0f + (MAX_SCALE - 1.0f) * enterPercent);
            }
            mLastMap.put(currPosition, currV);
        }
    }
}
