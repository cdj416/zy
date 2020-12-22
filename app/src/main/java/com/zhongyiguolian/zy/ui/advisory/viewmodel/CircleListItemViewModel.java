package com.zhongyiguolian.zy.ui.advisory.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.enitity.UserViewInfo;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.ItemViewModel;
import com.zhongyiguolian.zy.myview.nine_gridimg.NineGridImageViewAdapter;
import com.zhongyiguolian.zy.ui.advisory.activity.CircleDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 圈子itemViewModel
 * @author cdj
 * @date 2020/12/10
 */
public class CircleListItemViewModel extends ItemViewModel<CircleListViewModel> {

    public ObservableField<String> entity = new ObservableField<>();
    //是否为第一条数据
    public boolean isFirst;

    public List<String> sList = new ArrayList<>();

    public CircleListItemViewModel(@NonNull CircleListViewModel viewModel, String bean, boolean flag) {
        super(viewModel);
        entity.set(bean);
        isFirst = flag;

        sList.add("http://image.biaobaiju.com/uploads/20190508/17/1557306881-eMKPXBTwHt.jpg");
        sList.add("http://image.biaobaiju.com/uploads/20190508/17/1557306881-pcSQFfumLh.jpeg");
        sList.add("http://image.biaobaiju.com/uploads/20190508/17/1557306881-eMKPXBTwHt.jpg");
        sList.add("http://image.biaobaiju.com/uploads/20190508/17/1557306881-pcSQFfumLh.jpeg");
    }

    //条目的点击事件
    public BindingCommand itemClick = new BindingCommand(() -> {
        //Bundle bundle = new Bundle();
        //bundle.putString("user_id", String.valueOf(entity.get().getM_id()));
        //bundle.putString("userPhone",entity.get().getM_mobile());
        //viewModel.startActivity(UserInfoActivity.class,bundle);
        viewModel.startActivity(CircleDetailsActivity.class);
    });

    public NineGridImageViewAdapter adapter =  new NineGridImageViewAdapter<String>() {
        @Override
        protected void onDisplayImage(Context context, ImageView imageView, String s) {
            RequestOptions options = new RequestOptions().placeholder(R.mipmap.fang_default).error(R.mipmap.fang_default).centerCrop();
            Glide.with(context).load(s).apply(options).into(imageView);
        }

        @Override
        protected ImageView generateImageView(Context context) {
            return super.generateImageView(context);
        }

        @Override
        protected void onItemImageClick(Context context, List<ImageView> mImageViewList, ImageView imageView, int index, List<String> list) {

            //点击查看大图功能
            GPreviewBuilder.from((Activity) context)
                    .setData(getInfoList(list,mImageViewList))
                    .setCurrentIndex(index)
                    .setType(GPreviewBuilder.IndicatorType.Dot)
                    .start();
        }
    };

    /*
     * 获取图片集和图片所处位置
     * */
    public List<UserViewInfo> getInfoList(List<String> list, List<ImageView> mImageViewList){
        List<UserViewInfo> imgList = new ArrayList<>();
        for(int i = 0 ; i < list.size() ; i++){
            imgList.add(new UserViewInfo(list.get(i)));
            Rect bounds = new Rect();
            mImageViewList.get(i).getGlobalVisibleRect(bounds);
            imgList.get(i).setBounds(bounds);
        }

        return imgList;
    }
}
