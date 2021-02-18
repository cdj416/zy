package com.zhongyiguolian.zy.myview.scllor_view;

import java.util.ArrayList;
import java.util.List;

public abstract class UnitBeanUtils<T>{

    private List<UnitBean> useList;

    /*
     * 获取适配集合
     * */
    public List<UnitBean> getUnitList(List<T> dataList){
        List<UnitBean> mList = new ArrayList<>();
        if(dataList != null)
            for(int i = 0 ; i < dataList.size() ; i++){
                UnitBean unitBean = new UnitBean();
                unitBean.unit = unit(dataList.get(i));
                unitBean.unit_cn = unit_cn(dataList.get(i));
                mList.add(unitBean);
            }
        //给当前转换的集合赋值
        useList = mList;

        return mList;
    }



    /*
     * 获取Id值
     * */
    public String getUseId(String storeName){
        for(UnitBean bean : useList){
            if(bean.unit_cn.equals(storeName)){
                return String.valueOf(bean.unit);
            }
        }
        return "";
    }

    /*
    * 获取显示值
    * */
    public String getMessage(String id){
        for(UnitBean bean : useList){
            if(bean.unit.equals(id)){
                return bean.unit_cn;
            }
        }
        return "";
    }

    public abstract String unit(T t);
    public abstract String unit_cn(T t);
}
