package com.hongyuan.mvvmhabitx.http.interceptor.logging;

import okhttp3.internal.platform.Platform;

/**
 * demo
 * @author cdj
 * @date 2020/12/10
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface Logger {
    void log(int level, String tag, String msg);

    Logger DEFAULT = new Logger() {
        @Override
        public void log(int level, String tag, String message) {
            Platform.get().log(level, message, null);
        }
    };
}
