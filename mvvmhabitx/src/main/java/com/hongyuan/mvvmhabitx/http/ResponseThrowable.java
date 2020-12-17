package com.hongyuan.mvvmhabitx.http;


/**
 * demo
 * @author cdj
 * @date 2020/12/10
 */
public class ResponseThrowable extends Exception {
    public int code;
    public String message;

    public ResponseThrowable(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }
}
