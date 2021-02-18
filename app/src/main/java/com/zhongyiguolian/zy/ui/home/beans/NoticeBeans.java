package com.zhongyiguolian.zy.ui.home.beans;

import java.util.List;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class NoticeBeans {

    /**
     * total : 3
     * noticeTitles : [{"id":1,"title":"关于服务器价格调整的公告 ","publishTime":1607323740000},{"id":12,"title":"2020新基建数字经济高峰论坛暨大数据分布式存储产业（苏州）峰会成功召开 ","publishTime":1600948800000},{"id":9,"title":"国链云之声电台正式开通","publishTime":1600142400000}]
     */

    private int total;
    private List<NoticeTitlesDTO> noticeTitles;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<NoticeTitlesDTO> getNoticeTitles() {
        return noticeTitles;
    }

    public void setNoticeTitles(List<NoticeTitlesDTO> noticeTitles) {
        this.noticeTitles = noticeTitles;
    }

    public static class NoticeTitlesDTO {
        /**
         * id : 1
         * title : 关于服务器价格调整的公告
         * publishTime : 1607323740000
         */

        private int id;
        private String title;
        private long publishTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }
    }
}
