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
public interface HttpDataSource {

    Observable<MyBaseResponse<TokenBean>> api_token(RequestBody params);

    Observable<MyBaseResponse<MemberLoginBean>> base_login(RequestBody params);

    Observable<MyBaseResponse<RegisteredBean>> register(RequestBody params);

    Observable<MyBaseResponse<NoDataBean>> sendCode(RequestBody params);

}
