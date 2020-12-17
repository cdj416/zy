package com.zhongyiguolian.zy.data.rxbusbean;

/**
 * @author cdj
 * @date 2020/12/10
 */
public class ChangeStoreMessage {

    public ChangeStoreMessage(String os_id) {
        this.os_id = os_id;
    }

    private String os_id;

    public String getOs_id() {
        return os_id;
    }

    public void setOs_id(String os_id) {
        this.os_id = os_id;
    }
}
