package com.zhongyiguolian.zy.ui.person.beans;

import android.util.Log;

import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.data.md5.BaseUtil;

import java.io.Serializable;
import java.util.List;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class TransactionRecordBeans implements Serializable {

    /**
     * groupTime : 2021-01-25
     * recordLog : [{"symbol":"FIL","payType":"","fee":"0.10000","imageUrl":"/currency/1587376646149.png","num":"0.20000","precision":5,"id":93,"state":"成功","time":1611572806000,"type":0}]
     */

    private String groupTime;
    private List<RecordLogDTO> recordLog;

    public String getGroupTime() {
        return groupTime;
    }

    public void setGroupTime(String groupTime) {
        this.groupTime = groupTime;
    }

    public List<RecordLogDTO> getRecordLog() {
        return recordLog;
    }

    public void setRecordLog(List<RecordLogDTO> recordLog) {
        this.recordLog = recordLog;
    }

    public static class RecordLogDTO implements Serializable{
        /**
         * symbol : FIL
         * payType :
         * fee : 0.10000
         * imageUrl : /currency/1587376646149.png
         * num : 0.20000
         * precision : 5
         * id : 93
         * state : 成功
         * time : 1611572806000
         * type : 0
         */

        private String symbol;
        private String payType;
        private String fee;
        private String imageUrl;
        private String num;
        private int precision;
        private int id;
        private String state;
        private long time;
        private int type;
        private int markType;

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public int getPrecision() {
            return precision;
        }

        public void setPrecision(int precision) {
            this.precision = precision;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getStrNum() {
            if(markType == 0){
                return BaseUtil.getNoZoon(num)+"FIL";
            }else if(markType == 1){
                return BaseUtil.getNoZoon(num)+"FIL";
            }else if(markType == 2){
                return "￥"+BaseUtil.getNoZoon(num);
            }else{
                return BaseUtil.getNoZoon(num)+"FIL";
            }
        }

        /*public int getMarkType() {
            if(markType == 0){
                return R.mipmap.mention_text_mark;
            }else if(markType == 1){
                return R.mipmap.charge_text_mark;
            }else if(markType == 2){
                return R.mipmap.rmb_text_mark;
            }else{
                return R.mipmap.borrow_text_mark;
            }
        }*/

        public void setMarkType(int markType) {
            this.markType = markType;
        }

        public int showMark(){
            if(markType == 0){
                return R.mipmap.mention_text_mark;
            }else if(markType == 1){
                return R.mipmap.charge_text_mark;
            }else if(markType == 2){
                return R.mipmap.rmb_text_mark;
            }else{
                return R.mipmap.pledge_text_mark;
            }
        }

        public String showMarkText(){
            if(markType == 0){
                return "提FIL";
            }else if(markType == 1){
                return "充FIL";
            }else if(markType == 2){
                return "销售提现";
            }else{
                return "充质押";
            }
        }


    }
}
