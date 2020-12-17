package com.zhongyiguolian.zy.data.userbean;

/**
 * @author cdj
 * @date 2020/12/10
 */
public class TokenSingleBean {

    public static TokenSingleBean tokenBean;

    private TokenSingleBean(){}

    public static TokenSingleBean getInstance(){
        if(tokenBean == null){
            tokenBean = new TokenSingleBean();
        }
        return tokenBean;
    }

    private int at_id;
    private String at_name;
    private String at_pwd;
    private String token;
    private String ntoken;
    private String randomnum;
    private String timespan;

    private String os_id;

    //用户登录成功时获取的id
    private String m_id;
    private String m_mobile;
    private String role_id;
    private String headUrl;

    //发现的权限
    private int findState;

    public int getFindState() {
        return findState;
    }

    public void setFindState(int findState) {
        this.findState = findState;
    }

    public String getOs_id() {
        return os_id;
    }

    public void setOs_id(String os_id) {
        this.os_id = os_id;
    }

    public String getNtoken() {
        return ntoken;
    }

    public void setNtoken(String ntoken) {
        this.ntoken = ntoken;
    }

    public String getRandomnum() {
        return randomnum;
    }

    public void setRandomnum(String randomnum) {
        this.randomnum = randomnum;
    }

    public String getTimespan() {
        return timespan;
    }

    public void setTimespan(String timespan) {
        this.timespan = timespan;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

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

    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
    }

    public String getM_mobile() {
        return m_mobile;
    }

    public void setM_mobile(String m_mobile) {
        this.m_mobile = m_mobile;
    }



    /*
    * 清空登录信息
    * */
    public void clearToken(){
        this.m_id = null;
        this.m_mobile = null;
    }

}
