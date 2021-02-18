package com.zhongyiguolian.zy.ui.person.beans;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class DoWithdrawalBeans {

    /**
     * state : 0
     * message : 提币申请成功
     * data : {"usedAssets":"0.80000"}
     * precision : 0
     */

    private int state;
    private String message;
    private DataDTO data;
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

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public static class DataDTO {
        /**
         * usedAssets : 0.80000
         */

        private String usedAssets;

        public String getUsedAssets() {
            return usedAssets;
        }

        public void setUsedAssets(String usedAssets) {
            this.usedAssets = usedAssets;
        }
    }
}
