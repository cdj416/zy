package com.zhongyiguolian.zy.data.http;


import com.zhongyiguolian.zy.data.userbean.TokenBean;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @author cdj
 * @date 2020/12/10
 */
public class HttpDataSourceImpl implements HttpDataSource {
    private MyApiService apiService;
    private volatile static HttpDataSourceImpl INSTANCE = null;

    public static HttpDataSourceImpl getInstance(MyApiService apiService) {
        if (INSTANCE == null) {
            synchronized (HttpDataSourceImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpDataSourceImpl(apiService);
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private HttpDataSourceImpl(MyApiService apiService) {
        this.apiService = apiService;
    }


    @Override
    public Observable<MyBaseResponse<TokenBean>> api_token(Map<String, String> params) {
        return apiService.api_token(params);
    }

}
