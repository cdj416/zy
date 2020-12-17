package com.zhongyiguolian.zy;

/**
 * tabtitle显示工具类
 * @author cdj
 * @date 2020/12/10
 */
public class TabTitleBean {

    private String titleName;
    private int titlePosition;

    public TabTitleBean(String titleName, int titlePosition) {
        this.titleName = titleName;
        this.titlePosition = titlePosition;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public int getTitlePosition() {
        return titlePosition;
    }

    public void setTitlePosition(int titlePosition) {
        this.titlePosition = titlePosition;
    }
}
