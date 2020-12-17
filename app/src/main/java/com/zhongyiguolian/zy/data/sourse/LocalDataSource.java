package com.zhongyiguolian.zy.data.sourse;


import com.zhongyiguolian.zy.data.userbean.MemberLoginBean;
import com.zhongyiguolian.zy.data.userbean.TokenBean;

/**
 * @author cdj
 * @date 2020/12/10
 */
public interface LocalDataSource {

    /**
     * 保存token信息
     */
    void saveToken(TokenBean bean);

    /*
    * 获取token信息
    * */
    TokenBean getToken();

    /*
    * 保存用户登录信息
    * */
    void saveUser(MemberLoginBean bean);

    /*
    * 获取用户登录信息
    * */
    MemberLoginBean getUser();
}
