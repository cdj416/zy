package com.zhongyiguolian.zy.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.loader.ImageLoader;
import com.zhongyiguolian.zy.R;

/**
 * banner加载图片工具类
 * @author cdj
 * @date 2020/12/10
 */
public class UseGlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        //Glide 加载图片简单用法
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.delelet_home_item_img).error(R.mipmap.delelet_home_item_img).centerCrop();

        String imgPath = "";

        if(!path.toString().contains("R_")){
            imgPath = path.toString();
            Glide.with(context).load(imgPath).apply(options).transform(new GlideRoundTransform()).into(imageView);
        }else{
            Glide.with(context).load(Integer.valueOf(path.toString().substring(2))).apply(options).transform(new GlideRoundTransform()).into(imageView);
        }

    }
}
