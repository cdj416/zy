package com.zhongyiguolian.zy.base;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.myview.UMExpandLayout;
import com.zhongyiguolian.zy.myview.nine_gridimg.NineGridImageView;
import com.zhongyiguolian.zy.myview.nine_gridimg.NineGridImageViewAdapter;
import com.zhongyiguolian.zy.utils.UseGlideImageLoader;

import java.io.File;
import java.util.List;

/**
 * @author cdj
 * @date 2020/12/10
 */
public class MyBindingAdapter {

    @BindingAdapter(value = {"imageUrl", "defaultImg"}, requireAll = false)
    public static void bindImageUrl(ImageView view, Object imageUrl, Drawable defaultImg){
        if(BaseUtil.isValue(imageUrl)){
            Glide.with(view.getContext()).load(imageUrl).into(view);
        }else{
            if(defaultImg != null){
                Glide.with(view.getContext()).load(defaultImg).into(view);
            }else{
                Glide.with(view.getContext()).load(R.mipmap.fang_default).into(view);
            }
        }
    }

    @BindingAdapter("bannerList")
    public static void bindBanner(Banner banner, List<String> mList){
        if(mList == null || mList.size() <= 0)return;

        banner.setImages(mList)
                .setImageLoader(new UseGlideImageLoader())
                .setDelayTime(3000)
                .isAutoPlay(true)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR )
                .setIndicatorGravity(BannerConfig.CENTER)
                .start();
    }

    @BindingAdapter("imageFile")
    public static void bindImageUrl(ImageView view, File imageFile){
        if(!BaseUtil.isValue(imageFile))return;

        Glide.with(view.getContext()).load(imageFile).into(view);
    }


    @BindingAdapter("intImg")
    public static void bindIntImg(ImageView img, int imgId){
        if(imgId != 0){
            img.setImageResource(imgId);
        }else{
            img.setImageResource(R.color.theme_FFFFFF);
        }

    }


    @BindingAdapter("setIntText")
    public static void bindPraiseImg(TextView textView, int nums){
        textView.setText(String.valueOf(nums));
    }


    @BindingAdapter("setIntBg")
    public static void bindViwBg(View view, int bgId){
        view.setBackgroundResource(bgId);
    }

    @BindingAdapter("setMyTextColor")
    public static void bindTextColorBg(TextView view, int bgId){
        view.setTextColor(view.getResources().getColor(bgId));
    }

    @BindingAdapter("setRadioGroupCheck")
    public static void bindRadioGroupCheck(RadioGroup view, int checkId){
        if(checkId == -1){
            view.clearCheck();
        }else{
            view.check(checkId);
        }
    }

    @BindingAdapter("setNGimgAdapter")
    public static void bindNGAdapter(NineGridImageView view, NineGridImageViewAdapter adapter){
        view.setAdapter(adapter);
    }

    @BindingAdapter("setNGimgData")
    public static void bindNGData(NineGridImageView view, List<String> strs){
        view.setImagesData(strs);
    }

    @BindingAdapter("isOpenUME")
    public static void bindNGData(UMExpandLayout view, boolean flag){
        if(flag){
            view.expand();
        }else{
            view.collapse();
        }
    }

    @BindingAdapter("isOpenAnim")
    public static void bindImgAnim(ImageView view, boolean flag){

        RotateAnimation animation;

        if (!flag) {
            animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        } else {
            animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        }
        animation.setDuration(300);//设置动画持续时间
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatMode(Animation.REVERSE);//设置反方向执行
        animation.setFillAfter(true);//动画执行完后是否停留在执行完的状态
        view.startAnimation(animation);
    }
}
