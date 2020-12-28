package com.zhongyiguolian.zy.data.http;


import com.zhongyiguolian.zy.data.userbean.MemberLoginBean;
import com.zhongyiguolian.zy.data.userbean.NoDataBean;
import com.zhongyiguolian.zy.data.userbean.TokenBean;
import com.zhongyiguolian.zy.ui.main.beans.RegisteredBean;
import io.reactivex.Observable;
import okhttp3.RequestBody;

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
    public Observable<MyBaseResponse<TokenBean>> api_token(RequestBody params) {
        return apiService.api_token(params);
    }

    @Override
    public Observable<MyBaseResponse<MemberLoginBean>> base_login(RequestBody params) {
        return apiService.base_login(params);
    }

    @Override
    public Observable<MyBaseResponse<RegisteredBean>> register(RequestBody params) {
        return apiService.register(params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> sendCode(RequestBody params) {
        return apiService.sendCode(params);
    }

}
