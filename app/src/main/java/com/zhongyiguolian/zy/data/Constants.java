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
        apis.put(MEMBERLOGIN, "memberlogin");
        apis.put(UPFILE_OSS, "upfile_oss");
        apis.put(UPFILE_OSS_MORE, "upfile_oss_more");
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
    public final static int  MEMBERLOGIN = 0x002;
    //单文件上传
    public final static int  UPFILE_OSS = 0x03;
    //多文件上传
    public final static int  UPFILE_OSS_MORE = 0x04;

}
