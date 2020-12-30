package com.zhongyiguolian.zy.data.http;

/**
 * @author cdj
 * @param <T>
 */
public class MyBaseResponse<T> {

    /**
     * data : {}
     * status : {"code":0,"msg":"登录成功"}
     */
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private int state;
    private String message;
    private int precision;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }
}
