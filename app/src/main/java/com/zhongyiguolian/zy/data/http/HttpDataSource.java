package com.zhongyiguolian.zy.data.http;

import com.zhongyiguolian.zy.data.userbean.TokenBean;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @author cdj
 * @date 2020/12/10
 */
public interface HttpDataSource {

    Observable<MyBaseResponse<TokenBean>> api_token(Map<String, String> params);

}
