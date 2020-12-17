package com.zhongyiguolian.zy.data.md5;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author cdj
 * @date 2020/12/10
 */
public class EncryptionUtil {

    /**
     * 一次加密
     * 32位MD5加密
     * @param content -- 待加密内容
     * @return
     */
    public static String md5Decode(String content) {

        if(!BaseUtil.isValue(content)){
            return "a1234567dff";
        }

        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(content.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException",e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncodingException", e);
        }
        //对生成的16字节数组进行补零操作
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10){
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
    /**
     * 两次加密
     * 32位MD5加密
     * @param content -- 待加密内容
     * @return
     */
    public static String md5DecodeTwo(String content) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(content.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException",e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncodingException", e);
        }
        //对生成的16字节数组进行补零操作
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10){
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return md5Decode(hex.toString());
    }

    /**
     * 16位MD5加密
     * 实际是截取的32位加密结果的中间部分(8-24位)
     * @param content
     * @return
     */
    public String md5Decode16(String content) {
        return md5Decode(content).substring(8, 24);
    }

    /*
    * base64加密encode
    * */
    public static String base64encode(String content){
        String str = "";
        try {
            str = URLEncoder.encode(Base64.encodeToString(content.getBytes(), Base64.DEFAULT),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }
}
