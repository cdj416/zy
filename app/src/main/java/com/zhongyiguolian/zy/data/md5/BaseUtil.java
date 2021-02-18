package com.zhongyiguolian.zy.data.md5;

import android.text.TextUtils;

/**
 * @author cdj
 * @date 2020/12/10
 */
public class BaseUtil {

    /*
    * 判断是否有值
    * */
    public static boolean isValue(Object value){
        if(value == null || TextUtils.isEmpty(value.toString()) || "null".equals(value.toString())){
            return false;
        }
        return true;
    }

    /*
    * json判断是否有数据
    * */
    public static boolean isJsonValue(Object value){
        if(value == null || TextUtils.isEmpty(value.toString()) || "[]".equals(value.toString()) || "{}".equals(value.toString()) || "null".equals(value.toString())){
            return false;
        }
        return true;
    }

    /*
    * 去零处理
    * */
    public static String getNoZoon(Object number){
        String s = String.valueOf(number);
        if(s != null){
            if(s.indexOf(".") > 0){
                s = s.replaceAll("0+?$", "");//去掉多余的0
                s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
            }
        }
        return s;
    }

    /*
    * 转换成字符串处理
    * */
    public static String getString(Object number){
        return String.valueOf(number);
    }
}
