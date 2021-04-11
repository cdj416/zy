package com.zhongyiguolian.zy.data.userbean;

/**
 * @author cdj
 * @date 2020/12/10
 */
public class MemberLoginBean {


    /**
     * gestureEnable : false
     * password : null
     * userId : 1283
     * token : mobile:token:ojccefxhiiskmfcfidofvuxtuqfeivkzooboufncxeusimmlktxbdqtaogkcvayp
     * googleAuthIsOpen : false
     */

    private boolean gestureEnable;
    private String password;
    private int userId;
    private String token;
    private boolean googleAuthIsOpen;
    private boolean isAgrees;//是否同意协议
    private boolean showGuide;

    public boolean isShowGuide() {
        return showGuide;
    }

    public void setShowGuide(boolean showGuide) {
        this.showGuide = showGuide;
    }

    public boolean isAgrees() {
        return isAgrees;
    }

    public void setAgrees(boolean agrees) {
        isAgrees = agrees;
    }

    private String userName;
    private String nationalCode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public boolean isGestureEnable() {
        return gestureEnable;
    }

    public void setGestureEnable(boolean gestureEnable) {
        this.gestureEnable = gestureEnable;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isGoogleAuthIsOpen() {
        return googleAuthIsOpen;
    }

    public void setGoogleAuthIsOpen(boolean googleAuthIsOpen) {
        this.googleAuthIsOpen = googleAuthIsOpen;
    }
}
