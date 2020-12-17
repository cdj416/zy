package com.zhongyiguolian.zy.utils;

import android.text.Html;

/**
 * 人民币字符处理工具类
 * @author cdj
 * @date 2020/12/10
 */
public class StringUtils {

    //¥ 获取人民币符号
    public static String getYen(){
        return String.valueOf(Html.fromHtml("&yen"));
    }

    //空格
    public static String getEmpty(){
        return " ";
    }
}
