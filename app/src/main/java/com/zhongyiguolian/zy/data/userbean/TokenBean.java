package com.zhongyiguolian.zy.data.userbean;

/**
 * @author cdj
 * @date 2020/12/10
 */
public class TokenBean {


    private int at_id;
    private String at_name;
    private String at_pwd;
    private String token;

    public int getAt_id() {
        return at_id;
    }

    public void setAt_id(int at_id) {
        this.at_id = at_id;
    }

    public String getAt_name() {
        return at_name;
    }

    public void setAt_name(String at_name) {
        this.at_name = at_name;
    }

    public String getAt_pwd() {
        return at_pwd;
    }

    public void setAt_pwd(String at_pwd) {
        this.at_pwd = at_pwd;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
