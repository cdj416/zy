package com.zhongyiguolian.zy.utils;

import android.content.Context;

/**
 * 单位换算工具类
 * @author cdj
 * @date 2020/12/10
 */
public class DensityUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /*
    * 获取屏宽px
    * */
    public static int getScreensWith(Context context){
       return context.getResources().getDisplayMetrics().widthPixels;
    }

    /*
    * 获取列宽
    * columnNum:有多少列
    * consumeDp：不参与平分的dp数（需要先减去的部分）
    * */
    public static int getColumnWhith(Context context, int consumeDp, int columnNum){
        return (getScreensWith(context) - dip2px(context,consumeDp)) / columnNum;
    }
}
