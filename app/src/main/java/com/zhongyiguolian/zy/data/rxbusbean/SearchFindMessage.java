package com.zhongyiguolian.zy.data.rxbusbean;

/**
 * @author cdj
 * @date 2020/12/10
 */
public class SearchFindMessage {
    private String searchContent;

    public SearchFindMessage(String searchContent) {
        this.searchContent = searchContent;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }
}
