package com.hongyuan.mvvmhabitx.http.interceptor;

import com.hongyuan.mvvmhabitx.http.download.ProgressResponseBody;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * demo
 * @author cdj
 * @date 2020/12/10
 */
public class ProgressInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        return originalResponse.newBuilder()
                .body(new ProgressResponseBody(originalResponse.body()))
                .build();
    }
}
