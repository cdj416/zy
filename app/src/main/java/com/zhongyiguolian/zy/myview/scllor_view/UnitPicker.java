package com.zhongyiguolian.zy.myview.scllor_view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen.yingjie on 2019/6/22
 * description
 */
public class UnitPicker extends WheelPicker<String> {

    private CustomActivity mContext;

    public UnitPicker(Context context) {
        this(context, null);
        if(context instanceof CustomActivity){
            mContext = (CustomActivity) context;

            setTextColor(mContext.getResources().getColor(R.color.theme_999999));

            /*if(mContext.skin.equals(SkinConstants.SKIN_NAME.DEFAULT)){
                setTextColor(mContext.getResources().getColor(R.color.color_FF999999));
            }
            if(mContext.skin.equals(SkinConstants.SKIN_NAME.BLACK)){
                setTextColor(mContext.getResources().getColor(R.color.color_FFFFFF));
                //设置幕布颜色
                setCurtainColor(mContext.getResources().getColor(R.color.translucent_black_70));
            }*/
        }
    }

    public UnitPicker(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        if(context instanceof CustomActivity){
            mContext = (CustomActivity) context;

            setTextColor(mContext.getResources().getColor(R.color.theme_999999));

            /*if(mContext.skin.equals(SkinConstants.SKIN_NAME.DEFAULT)){
                setTextColor(mContext.getResources().getColor(R.color.color_FF999999));
            }
            if(mContext.skin.equals(SkinConstants.SKIN_NAME.BLACK)){
                setTextColor(mContext.getResources().getColor(R.color.color_FFFFFF));
                //设置幕布颜色
                setCurtainColor(mContext.getResources().getColor(R.color.translucent_black_70));
            }*/
        }
    }

    public UnitPicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if(context instanceof CustomActivity){
            mContext = (CustomActivity) context;

            setTextColor(mContext.getResources().getColor(R.color.theme_999999));

            /*if(mContext.skin.equals(SkinConstants.SKIN_NAME.DEFAULT)){
                setTextColor(mContext.getResources().getColor(R.color.color_FF999999));
            }
            if(mContext.skin.equals(SkinConstants.SKIN_NAME.BLACK)){
                setTextColor(mContext.getResources().getColor(R.color.color_FFFFFF));
                //设置幕布颜色
                setCurtainColor(mContext.getResources().getColor(R.color.translucent_black_70));
            }*/
        }

        data = new ArrayList<>();
        setOnWheelChangeListener(new OnWheelChangeListener<String>() {

            @Override
            public void onWheelSelected(String item, int position) {
                if (mOnItemSelectedListener != null) {
                    mOnItemSelectedListener.onItemSelected(data.get(position), position);
                }
            }
        });
    }

    private List<UnitBean> data;

    public void setData(List<UnitBean> unitBeans) {
        if (unitBeans != null) {
            data.addAll(unitBeans);
            ArrayList<String> list = new ArrayList<>();
            for (UnitBean unitBean : unitBeans) {
                list.add(unitBean.unit_cn);
            }
            setDataList(list);
        }
    }

    public interface OnItemSelectedListener {
        void onItemSelected(UnitBean unitBean, int position);
    }

    private OnItemSelectedListener mOnItemSelectedListener;

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.mOnItemSelectedListener = onItemSelectedListener;
    }

    public UnitBean setUnit(int unit) {
        for (UnitBean bean : data) {
            if (Integer.valueOf(bean.unit) == unit) {
                setCurrentPosition(data.indexOf(bean), false);
                return bean;
            }
        }
        return null;
    }

    public UnitBean getCurrentUnit() {
        try {
            return data.get(getCurrentPosition());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
