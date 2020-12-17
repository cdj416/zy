package com.github.tifezh.kchartlib.utils;

import android.content.Context;

/**
 * demo
 * @author cdj
 * @date 2020/12/10
 */
public class ViewUtil {
    static public int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    static public int Px2Dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}
