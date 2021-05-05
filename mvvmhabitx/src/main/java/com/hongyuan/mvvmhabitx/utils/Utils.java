package com.hongyuan.mvvmhabitx.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;

import com.hongyuan.mvvmhabitx.base.AppManager;

/**
 * 常用工具类
 * demo
 * @author cdj
 * @date 2020/12/10
 */
public final class Utils {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(@NonNull final Context context) {
        Utils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("should be initialized in application");
    }

    /*
    * 获取正在运行的activity的context
    * */
    public static Context getRunningContext(){
        if(AppManager.getAppManager().getRunningActivity() != null){
            return AppManager.getAppManager().getRunningActivity();
        }else{
            return context;
        }
    }
}