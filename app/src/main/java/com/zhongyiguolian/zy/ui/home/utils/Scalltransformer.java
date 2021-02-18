package com.zhongyiguolian.zy.ui.home.utils;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class Scalltransformer implements ViewPager.PageTransformer {
    private float MINSCALE=0.9f;//最小缩放值

    /**
     * position取值特点：
     * 假设页面从0～1，则：
     * 第一个页面position变化为[0,-1]
     * 第二个页面position变化为[1,0]
     *
     * @param view
     * @param v
     */
    @Override
    public void transformPage(@NonNull View view, float v) {

        float scale;//view  应缩放的值
        if(v>1||v<-1){
            scale=MINSCALE;
        }else if(v<0){
            scale=MINSCALE+(1+v)*(1-MINSCALE);
        }else{
            scale=MINSCALE+(1-v)*(1-MINSCALE);
        }
        view.setScaleY(scale);
        view.setScaleX(scale);
    }
}
