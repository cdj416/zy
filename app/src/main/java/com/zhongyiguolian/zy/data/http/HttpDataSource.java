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
public interface HttpDataSource {

    Observable<MyBaseResponse<TokenBean>> api_token(String token,Map<String,String> params);

    Observable<MyBaseResponse<MemberLoginBean>> login(Map<String,String> params);

    Observable<MyBaseResponse<RegisteredBean>> register(String token,Map<String,String> params);

    Observable<MyBaseResponse<NoDataBean>> sendCode(String token,Map<String,String> params);

    Observable<MyBaseResponse<NoDataBean>> card_list(String token,Map<String,String> params);

    Observable<MyBaseResponse<NoDataBean>> card_save(String token,Map<String,String> params);

    Observable<MyBaseResponse<InviteBeans>> invite_info(String token, Map<String,String> params);

    Observable<MyBaseResponse<NoDataBean>> save(String token, Map<String,String> params);

}
