package com.zhongyiguolian.zy.ui.main.beans;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class VersionBeans {

    /**
     * id : 2
     * type : 2
     * version : 1.0.2
     * path : https://apk.ilucode.com/uploads/app/20210131/127d4ee2a6798c314f62ebdcc98f11dc.apk
     * createTime : 1611995610000
     */

    private int id;
    private String type;
    private String version;
    private String path;
    private long createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
