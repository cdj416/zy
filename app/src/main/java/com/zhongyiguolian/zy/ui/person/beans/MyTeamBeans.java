package com.zhongyiguolian.zy.ui.person.beans;

import java.util.List;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class MyTeamBeans {

    private List<MyTeamVosDTO> myTeamVos;

    public List<MyTeamVosDTO> getMyTeamVos() {
        return myTeamVos;
    }

    public void setMyTeamVos(List<MyTeamVosDTO> myTeamVos) {
        this.myTeamVos = myTeamVos;
    }

    public static class MyTeamVosDTO {
        /**
         * createDate : 1609948800000
         * realName : null
         * mobile : 13451552114
         * customerId : 2569
         * cnt : 1
         * amount : 0
         * teamAmount : 0
         */

        private long createDate;
        private String realName;
        private String mobile;
        private int customerId;
        private int cnt;
        private int amount;
        private int teamAmount;

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public int getCnt() {
            return cnt;
        }

        public void setCnt(int cnt) {
            this.cnt = cnt;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getTeamAmount() {
            return teamAmount;
        }

        public void setTeamAmount(int teamAmount) {
            this.teamAmount = teamAmount;
        }
    }
}
