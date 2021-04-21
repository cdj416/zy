package com.zhongyiguolian.zy.base;

import com.hongyuan.mvvmhabitx.base.BaseApplication;
import com.hongyuan.mvvmhabitx.crash.CaocConfig;
import com.hongyuan.mvvmhabitx.utils.KLog;
import com.previewlibrary.ZoomMediaLoader;
import com.taobao.sophix.SophixManager;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.zhongyiguolian.zy.BuildConfig;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.ui.main.activity.LoginActivity;
import com.zhongyiguolian.zy.ui.main.activity.StartupPageActivity;
import com.zhongyiguolian.zy.utils.ImageLoaderUtil;

/**
 * @author cdj
 * @date 2020/12/10
 */
public class MyAppApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        //是否开启打印日志
        KLog.init(BuildConfig.DEBUG);

        //配置全局异常崩溃操作
        CaocConfig.Builder.create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //背景模式,开启沉浸式
                .enabled(true) //是否启动全局异常捕获
                .showErrorDetails(true) //是否显示错误详细信息
                .showRestartButton(true) //是否显示重启按钮
                .trackActivities(true) //是否跟踪Activity
                .minTimeBetweenCrashesMs(2000) //崩溃的间隔时间(毫秒)
                .errorDrawable(R.mipmap.ic_launcher) //错误图标
                .restartActivity(LoginActivity.class) //重新启动后的activity
                .errorActivity(StartupPageActivity.class) //崩溃后的错误activity
                //.eventListener(new YourCustomEventListener()) //崩溃后的错误监听
                .apply();

        //初始化图片加载器
        ZoomMediaLoader.getInstance().init(new ImageLoaderUtil());

        //极光推送
        //JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        //JPushInterface.init(this);     		// 初始化 JPush

        //初始化组件化基础库, 所有友盟业务SDK都必须调用此初始化接口。
        //UMConfigure.init(this, "5ffe8d0df1eb4f3f9b5c6b74", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        UMConfigure.init(this, "6017898e6a2a470e8f9a00a8", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        //选择AUTO页面采集模式，统计SDK基础指标无需手动埋点可自动采集。
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
        //优盟统计开启debug模式
        UMConfigure.setLogEnabled(false);

        // 微信设置
        PlatformConfig.setWeixin("wx4be4833cf35c619a", "3baf1193c85774b3fd9d18447d76cab0");
        PlatformConfig.setWXFileProvider("com.tencent.sample2.fileprovider");
        // QQ设置
        PlatformConfig.setQQZone("101830139", "5d63ae8858f1caab67715ccd6c18d7a5");
        PlatformConfig.setQQFileProvider("com.tencent.sample2.fileprovider");

        //热修复，加载获取新补丁
        SophixManager.getInstance().queryAndLoadNewPatch();
    }
}
