package com.zhongyiguolian.zy.base;

import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.myview.UMExpandLayout;
import com.zhongyiguolian.zy.myview.nine_gridimg.NineGridImageView;
import com.zhongyiguolian.zy.myview.nine_gridimg.NineGridImageViewAdapter;
import com.zhongyiguolian.zy.ui.home.viewmodel.ComfirmOrderBottomMultiViewModel;
import com.zhongyiguolian.zy.utils.DensityUtil;
import com.zhongyiguolian.zy.utils.GlideRoundTransform;
import com.zhongyiguolian.zy.utils.UseGlideImageLoader;

import java.io.File;
import java.util.List;
import java.util.Random;

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

    @BindingAdapter("changeHeightImg")
    public static void bindHeightImg(ImageView imgView,String imageUrl){
        if(!BaseUtil.isValue(imageUrl))return;

        int max=50,min=5;
        int ran2 = (int) (Math.random()*(max-min)+min);

        //获取屏宽
        int windWith = DensityUtil.getColumnWhith(imgView.getContext(),44,2);

        try {
            //获取宽高并计算比例
            //String whStr = imageUrl.substring((imageUrl.lastIndexOf("_")+1),imageUrl.lastIndexOf("."));
            //String[] wh = whStr.split("x");
            //float ratio = Float.valueOf(wh[0])/Float.valueOf(wh[1]);

            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imgView.getLayoutParams();
            //layoutParams.height = (int)(windWith/ratio);
            layoutParams.height = (int)(windWith/ran2*50);
            imgView.setLayoutParams(layoutParams);

        }catch (Exception e){
            e.printStackTrace();
        }

        //显示图片
        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).transform(new GlideRoundTransform(6));
        Glide.with(imgView.getContext()).load(imageUrl).transition(DrawableTransitionOptions.withCrossFade()).apply(options).into(imgView);
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

    /*
    * 字数与输入控件的绑定
    * */
    @BindingAdapter("setTextWatcher")
    public static void bindTextWatcher(EditText etText, ComfirmOrderBottomMultiViewModel viewModel){
        etText.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int editStart;
            private int editEnd;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp = charSequence;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                editStart = etText.getSelectionStart();
                editEnd = etText.getSelectionEnd();
                viewModel.etNums.set(String.valueOf(temp.length()));
                if (temp.length() > 150) {//输入字数限制
                    editable.delete(editStart - 1, editEnd);//删除限制外的内容
                    int tempSelection = editStart;
                    etText.setText(editable);//显示限制内的内容
                    etText.setSelection(tempSelection);//光标焦点设置在行末
                }
            }
        });
    }
}
