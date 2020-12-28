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
    private Status status;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    public static class Status {
        /**
         * code : 0
         * msg : 登录成功
         */

        private int code;
        private String msg;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
