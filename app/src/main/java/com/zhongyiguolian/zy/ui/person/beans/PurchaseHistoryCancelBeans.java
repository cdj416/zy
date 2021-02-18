package com.zhongyiguolian.zy.ui.person.beans;

import java.util.List;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class PurchaseHistoryCancelBeans {


    /**
     * total : 0
     * result : false
     * message : 取消成功
     * code : 0
     * rows : []
     * resultMap : {}
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
    }
}
