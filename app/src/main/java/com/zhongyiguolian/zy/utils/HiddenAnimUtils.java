package com.zhongyiguolian.zy.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
/*
 * Description:显示隐藏布局的属性动画(铺展)
 * */
public class HiddenAnimUtils {

    private Context mContext;
    private int mHeight;//伸展高度
    private int startHeigh;//开始高度
    private View hideView,down;//需要展开隐藏的布局，开关控件
    private RotateAnimation animation;//旋转动画
    private boolean isOpen;//展开收缩状态

    /**
     * 构造器(通过隐藏显示实现展开收缩)
     * @param context 上下文
     * @param hideView 需要隐藏或显示的布局view
     * @param down 按钮开关的view
     * @param height 布局展开的高度(根据实际需要传)
     */
    public static HiddenAnimUtils newInstance(Context context, View hideView, View down, int height,boolean isConversion){
        return new HiddenAnimUtils(context,hideView,down,height,isConversion);
    }

    /**
     * 构造器(通过改变控件高度实现展开收缩)
     * @param context 上下文
     * @param hideView 需要隐藏或显示的布局view
     * @param down 按钮开关的view
     * @param height 布局展开的高度(根据实际需要传)
     * @param startHeight 展开的初始高度
     * @param isOpen 设置初始展开还是收缩值
     */
    public static HiddenAnimUtils newInstance(Context context, View hideView, View down, int height,int startHeight,boolean isOpen){
        return new HiddenAnimUtils(context,hideView,down,height,startHeight,isOpen);
    }

    /*
     * 通过隐藏显示实现展开收缩
     * */
    private HiddenAnimUtils(Context context,View hideView,View down,int height,boolean isConversion){
        this.mContext = context;
        this.hideView = hideView;
        this.down = down;

        if(isConversion){
            mHeight = getViewHeight(height);//伸展高度
        }else{
            mHeight = height;
        }

    }

    /*
     * 通过改变控件高度实现展开收缩
     * */
    private HiddenAnimUtils(Context context,View hideView,View down,int viewHeight,int startHeight,boolean isOpen){
        this.mContext = context;
        this.hideView = hideView;
        this.down = down;
        mHeight = getViewHeight(viewHeight);//伸展高度
        startHeigh = getViewHeight(startHeight);
        this.isOpen = isOpen;

        if(!isOpen){
            hideView.getLayoutParams().height = startHeigh;
            hideView.requestLayout();
            this.isOpen = true;
        }
    }

    /**
     * 显示隐藏形式的收缩展开开关
     */
    public void toggle(){
        startAnimation();
        if (View.VISIBLE == hideView.getVisibility()) {
            closeAnimate(hideView);//布局隐藏
        } else {
            openAnim(hideView);//布局铺开
        }
    }

    /**
     * 开关旋转动画
     */
    private void startAnimation() {
        if (View.VISIBLE == hideView.getVisibility()) {
            animation = new RotateAnimation(90, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        } else {
            animation = new RotateAnimation(0, 270, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        }
        animation.setDuration(300);//设置动画持续时间
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatMode(Animation.REVERSE);//设置反方向执行
        animation.setFillAfter(true);//动画执行完后是否停留在执行完的状态
        down.startAnimation(animation);
    }
    /**
     * 开关旋转动画
     */
    private void startAnimation(boolean isOpen) {
        if (!isOpen) {
            animation = new RotateAnimation(90, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        } else {
            animation = new RotateAnimation(0, 270, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        }
        animation.setDuration(300);//设置动画持续时间
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatMode(Animation.REVERSE);//设置反方向执行
        animation.setFillAfter(true);//动画执行完后是否停留在执行完的状态
        down.startAnimation(animation);
    }

    private void openAnim(View v) {
        v.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(v, 0, mHeight);
        animator.start();
    }

    private void closeAnimate(final View view) {
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, origHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });
        animator.start();
    }

    private ValueAnimator createDropAnimator(final View v, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(arg0 -> {
            int value = (int) arg0.getAnimatedValue();
            ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
            layoutParams.height = value;
            v.setLayoutParams(layoutParams);
        });
        return animator;
    }

    /*
     * 通过改变高度实现展开收缩
     * */
    public void toggleHeight(){
        startAnimation(isOpen);
        //属性动画对象
        ValueAnimator va ;
        if(isOpen){
            //显示view，高度从0变到height值
            va = ValueAnimator.ofInt(startHeigh,mHeight);
            isOpen = false;
        }else{
            //隐藏view，高度从height变为0
            va = ValueAnimator.ofInt(mHeight,startHeigh);
            isOpen = true;
        }
        va.addUpdateListener(valueAnimator -> {
            //获取当前的height值
            int h =(Integer)valueAnimator.getAnimatedValue();
            //动态更新view的高度
            hideView.getLayoutParams().height = h;
            hideView.requestLayout();
        });
        va.setDuration(300);
        //开始动画
        va.start();
    }

    /*
     * 计算实际使用高度
     * */
    private int getViewHeight(int dbHeight){
        float mDensity = mContext.getResources().getDisplayMetrics().density;
        return  (int) (mDensity * dbHeight + 0.5);//伸展高度
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
