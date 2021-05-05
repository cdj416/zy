package com.zhongyiguolian.zy.base;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import androidx.annotation.Keep;

import com.hongyuan.mvvmhabitx.base.AppManager;
import com.hongyuan.mvvmhabitx.utils.Utils;
import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixApplication;
import com.taobao.sophix.SophixEntry;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;
import com.zhongyiguolian.zy.ui.main.activity.StartupPageActivity;
import com.zhongyiguolian.zy.utils.CustomDialog;

/**
 * Sophix入口类，专门用于初始化Sophix，不应包含任何业务逻辑。
 * 此类必须继承自SophixApplication，onCreate方法不需要实现。
 * 此类不应与项目中的其他类有任何互相调用的逻辑，必须完全做到隔离。
 * AndroidManifest中设置application为此类，而SophixEntry中设为原先Application类。
 * 注意原先Application里不需要再重复初始化Sophix，并且需要避免混淆原先Application类。
 * 如有其它自定义改造，请咨询官方后妥善处理。
 */
public class SophixStubApplication extends SophixApplication {
    private final String TAG = "SophixStubApplication";
    // 此处SophixEntry应指定真正的Application，并且保证RealApplicationStub类名不被混淆。
    @Keep
    @SophixEntry(MyAppApplication.class)
    static class RealApplicationStub {}
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//         如果需要使用MultiDex，需要在此处调用。
//         MultiDex.install(this);
        initSophix();
    }
    private void initSophix() {
        String appVersion = "0.0.0";
        try {
            appVersion = this.getPackageManager()
                    .getPackageInfo(this.getPackageName(), 0)
                    .versionName;
        } catch (Exception e) {
        }
        final SophixManager instance = SophixManager.getInstance();
        instance.setContext(this)
                .setAppVersion(appVersion)
                .setSecretMetaData(null, null, null)
                .setEnableDebug(true)
                .setEnableFullLog()
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            //补丁加载成功
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            //补丁下载成功，需重启 如果需要在后台重启，建议此处用SharePreference保存状态。

                            Handler mainHandler = new Handler(Looper.getMainLooper());
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    //已在主线程中，可以更新UI
                                    CustomDialog.promptDialog(Utils.getRunningContext(),"应用有新内容更新，需重启应用！","确定重启",false, new CustomDialog.DialogClick() {
                                        @Override
                                        public void dialogClick(View v) {
                                            //重启app
                                            Intent intent = new Intent(SophixStubApplication.this, StartupPageActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            SophixStubApplication.this.startActivity(intent);
                                            android.os.Process.killProcess(android.os.Process.myPid());

                                            /**杀死整个进程**/
                                            //AppManager.getAppManager().finishAllActivity();
                                            //android.os.Process.killProcess(android.os.Process.myPid());
                                        }
                                    });
                                }
                            });

                        }
                    }
                }).initialize();
    }
}
