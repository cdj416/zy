package com.zhongyiguolian.zy.data.http;

/**
 * @author cdj
 * @date 2020/12/10
 */
public class MyBaseResponse<T> {

    /**
     * data : {"at_id":1,"at_name":"fit","at_pwd":"14e1b600b1fd579f47433b88e8d85291","token":"8910349115cffa3c12d3d62.66901630"}
     * status : {"succeed":"1"}
     */

    private T data;
    private StatusBean status;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public static class StatusBean {

        /**
         * succeed : 0
         * error_code : 600
         * error_desc : 手机或密码输入错误
         */

        private String succeed;
        private int error_code;
        private String error_desc;

        public String getSucceed() {
            return succeed;
        }

        public void setSucceed(String succeed) {
            this.succeed = succeed;
        }

        public int getError_code() {
            return error_code;
        }

        public void setError_code(int error_code) {
            this.error_code = error_code;
        }

        public String getError_desc() {
            return error_desc;
        }

        public void setError_desc(String error_desc) {
            this.error_desc = error_desc;
        }
    }
}
