package com.zhongyiguolian.zy.data.http;


import com.zhongyiguolian.zy.data.userbean.MemberLoginBean;
import com.zhongyiguolian.zy.data.userbean.NoDataBean;
import com.zhongyiguolian.zy.data.userbean.TokenBean;
import com.zhongyiguolian.zy.ui.main.beans.RegisteredBean;
import com.zhongyiguolian.zy.ui.person.beans.InviteBeans;

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
    public Observable<MyBaseResponse<TokenBean>> api_token(String token,Map<String,String> params) {
        return apiService.api_token(token,params);
    }

    @Override
    public Observable<MyBaseResponse<MemberLoginBean>> login(Map<String,String> params) {
        return apiService.login(params);
    }

    @Override
    public Observable<MyBaseResponse<RegisteredBean>> register(String token,Map<String,String> params) {
        return apiService.register(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> sendCode(String token,Map<String,String> params) {
        return apiService.sendCode(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> card_list(String token ,Map<String, String> params) {
        return apiService.card_list(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> card_save(String token, Map<String, String> params) {
        return apiService.card_save(token,params);
    }

    @Override
    public Observable<MyBaseResponse<InviteBeans>> invite_info(String token, Map<String, String> params) {
        return apiService.invite_info(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> save(String token, Map<String, String> params) {
        return apiService.save(token,params);
    }

}
