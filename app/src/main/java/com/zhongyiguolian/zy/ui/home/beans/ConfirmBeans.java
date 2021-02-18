package com.zhongyiguolian.zy.ui.home.beans;

import java.util.List;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class ConfirmBeans {

    /**
     * total : 0
     * result : true
     * message : success
     * code : 1
     * rows : []
     * resultMap : {"ppwToken":"prxjedvvwjdrpjxjuwltsmqesxfftxwnqozaodwotceeyklskpttkikldgrrlrkq"}
     */

    private int total;
    private boolean result;
    private String message;
    private int code;
    private ResultMapDTO resultMap;
    private List<?> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ResultMapDTO getResultMap() {
        return resultMap;
    }

    public void setResultMap(ResultMapDTO resultMap) {
        this.resultMap = resultMap;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public static class ResultMapDTO {
        /**
         * ppwToken : prxjedvvwjdrpjxjuwltsmqesxfftxwnqozaodwotceeyklskpttkikldgrrlrkq
         */

        private String ppwToken;

        public String getPpwToken() {
            return ppwToken;
        }

        public void setPpwToken(String ppwToken) {
            this.ppwToken = ppwToken;
        }
    }
}
