package com.zhongyiguolian.zy.myview.nine_gridimg;

import android.content.Context;
import android.widget.ImageView;

import java.util.List;

/**
 * @author cdj
 * @date 2020/12/10
 */
public interface ItemImageLongClickListener<T> {
    boolean onItemImageLongClick(Context context, ImageView imageView, int index, List<T> list);
}
