package com.zhongyiguolian.zy.data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cdj
 * @date 2020/12/10
 */
public class Constants {

    /*
    * Integer:接口code
    * String:接口名
    * */
    private Map<Integer, String> apis = new HashMap<>();

    private static Constants constants = null;

    private Constants(){
        addApis();
    }

    public static Constants getInstance(){
        if(constants == null){
            constants = new Constants();
        }
        return constants;
    }

    private void addApis(){
        apis.put(GET_TOKEN, "api_token");
        apis.put(LOGIN, "login");
        apis.put(UPFILE_OSS, "upfile_oss");
        apis.put(UPFILE_OSS_MORE, "upfile_oss_more");
        apis.put(REGISTER, "register");
        apis.put(SENDCODE, "sendCode");
        apis.put(CARD_LIST, "card_list");
        apis.put(INVITE_INFO, "invite_info");
        apis.put(SAVE, "save");
        apis.put(CARD_SAVE, "card_save");
    }

    /*
     * 获取请求接口名
     * */
    public String getPath(int code){
        return apis.get(code);
    }


    /*================================接口code=======================================*/

    //token的获取
    public final static int  GET_TOKEN = 0x001;
    //登录接口
    public final static int  LOGIN = 0x002;
    //单文件上传
    public final static int  UPFILE_OSS = 0x03;
    //多文件上传
    public final static int  UPFILE_OSS_MORE = 0x04;
    //注册
    public final static int  REGISTER = 0x05;
    //发送验证码
    public final static int  SENDCODE = 0x06;
    //发送验证码
    public final static int  CARD_LIST = 0x07;
    //获取邀请信息
    public final static int  INVITE_INFO = 0x08;
    //提交意见反馈
    public final static int  SAVE = 0x09;
    //提交银行卡
    public final static int  CARD_SAVE = 0x10;



}
