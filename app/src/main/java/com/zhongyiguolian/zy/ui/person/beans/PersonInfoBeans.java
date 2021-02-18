package com.zhongyiguolian.zy.ui.person.beans;

import android.graphics.Color;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.utils.BigDecimalUtils;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class PersonInfoBeans {

    /**
     * customerVo : {"id":1283,"mobile":"13952404134","channelId":null,"mobileAuth":true,"email":null,"headPortrait":null,"mailAuth":false,"hasPayPassword":true,"kpiLevel":0,"lastLoginTime":1609829099000,"lastLoginIP":"172.19.9.5","regTime":1597282538000,"enable":true,"whiteList":false,"inviteCode":"ONEGSAX","inviteLink":"https://www.zygl.com/ac/los/index.html?code=ONEGSAX","inviter":741,"googleSecretAuth":false,"userName":"13952404134","nickName":null,"realName":null,"idCardNo":null,"nationalId":null,"idInfoStatus":"UNSUBMIT","idInfoStatusString":null,"agentInfoStatus":"UNSUBMIT","agentInfoStatusString":null,"regType":"MOBILE","superLeaderWeightPercent":0,"deduct":false,"token":"mobile:token:cvhqppgvrrppmbkmyvaddvatrcjhfzygyepizsmxbltjumslqpalpzokxiwujvoj","googleAuthIsOpen":false,"withdrawGoogleAuthIsOpen":false,"levelFlag":true,"whetherSalesCommission":true,"hasC2CTrade":false,"teamflag":false,"volunteerStatus":"NO","volunteerStatusString":null,"volunteerNumber":null,"volunteerRate":0,"communityId":1,"relation":"null1283-","nodeId":1,"level":1,"superLevel":false,"cfdRate":null,"nodeName":null,"agentName":null,"gestureEnable":null,"partner":false,"project":false}
     * count : {"myExpectedRevenue":0.04787969,"totalCountYesterDay":0.03465167,"doneCalculationPowerCount":0,"calculationPowerCount":1,"totalRevenue":18463.1550828,"notOpenCalculationPowerCount":0,"totalCount":1.95785314,"tradeCalculationPowerCount":1}
     */

    private CustomerVoDTO customerVo;
    private CountDTO count;

    public CustomerVoDTO getCustomerVo() {
        return customerVo;
    }

    public void setCustomerVo(CustomerVoDTO customerVo) {
        this.customerVo = customerVo;
    }

    public CountDTO getCount() {
        return count;
    }

    public void setCount(CountDTO count) {
        this.count = count;
    }

    public static class CustomerVoDTO {
        /**
         * id : 1283
         * mobile : 13952404134
         * channelId : null
         * mobileAuth : true
         * email : null
         * headPortrait : null
         * mailAuth : false
         * hasPayPassword : true
         * kpiLevel : 0
         * lastLoginTime : 1609829099000
         * lastLoginIP : 172.19.9.5
         * regTime : 1597282538000
         * enable : true
         * whiteList : false
         * inviteCode : ONEGSAX
         * inviteLink : https://www.zygl.com/ac/los/index.html?code=ONEGSAX
         * inviter : 741
         * googleSecretAuth : false
         * userName : 13952404134
         * nickName : null
         * realName : null
         * idCardNo : null
         * nationalId : null
         * idInfoStatus : UNSUBMIT
         * idInfoStatusString : null
         * agentInfoStatus : UNSUBMIT
         * agentInfoStatusString : null
         * regType : MOBILE
         * superLeaderWeightPercent : 0
         * deduct : false
         * token : mobile:token:cvhqppgvrrppmbkmyvaddvatrcjhfzygyepizsmxbltjumslqpalpzokxiwujvoj
         * googleAuthIsOpen : false
         * withdrawGoogleAuthIsOpen : false
         * levelFlag : true
         * whetherSalesCommission : true
         * hasC2CTrade : false
         * teamflag : false
         * volunteerStatus : NO
         * volunteerStatusString : null
         * volunteerNumber : null
         * volunteerRate : 0
         * communityId : 1
         * relation : null1283-
         * nodeId : 1
         * level : 1
         * superLevel : false
         * cfdRate : null
         * nodeName : null
         * agentName : null
         * gestureEnable : null
         * partner : false
         * project : false
         */

        private int id;
        private String mobile;
        private Object channelId;
        private boolean mobileAuth;
        private Object email;
        private String headPortrait;
        private boolean mailAuth;
        private boolean hasPayPassword;
        private int kpiLevel;
        private long lastLoginTime;
        private String lastLoginIP;
        private long regTime;
        private boolean enable;
        private boolean whiteList;
        private String inviteCode;
        private String inviteLink;
        private int inviter;
        private boolean googleSecretAuth;
        private String userName;
        private Object nickName;
        private String realName;
        private Object idCardNo;
        private Object nationalId;
        private String idInfoStatus;
        private Object idInfoStatusString;
        private String agentInfoStatus;
        private Object agentInfoStatusString;
        private String regType;
        private int superLeaderWeightPercent;
        private boolean deduct;
        private String token;
        private boolean googleAuthIsOpen;
        private boolean withdrawGoogleAuthIsOpen;
        private boolean levelFlag;
        private boolean whetherSalesCommission;
        private boolean hasC2CTrade;
        private boolean teamflag;
        private String volunteerStatus;
        private Object volunteerStatusString;
        private Object volunteerNumber;
        private int volunteerRate;
        private int communityId;
        private String relation;
        private int nodeId;
        private int level;
        private boolean superLevel;
        private Object cfdRate;
        private Object nodeName;
        private Object agentName;
        private Object gestureEnable;
        private boolean partner;
        private boolean project;

        public int getId() {
            return id;
        }

        public String getStrId() {
            return ""+id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Object getChannelId() {
            return channelId;
        }

        public void setChannelId(Object channelId) {
            this.channelId = channelId;
        }

        public boolean isMobileAuth() {
            return mobileAuth;
        }

        public void setMobileAuth(boolean mobileAuth) {
            this.mobileAuth = mobileAuth;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public String getHeadPortrait() {
            return headPortrait;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
        }

        public boolean isMailAuth() {
            return mailAuth;
        }

        public void setMailAuth(boolean mailAuth) {
            this.mailAuth = mailAuth;
        }

        public boolean isHasPayPassword() {
            return hasPayPassword;
        }

        public void setHasPayPassword(boolean hasPayPassword) {
            this.hasPayPassword = hasPayPassword;
        }

        public int getKpiLevel() {
            return kpiLevel;
        }

        public void setKpiLevel(int kpiLevel) {
            this.kpiLevel = kpiLevel;
        }

        public long getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(long lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public String getLastLoginIP() {
            return lastLoginIP;
        }

        public void setLastLoginIP(String lastLoginIP) {
            this.lastLoginIP = lastLoginIP;
        }

        public long getRegTime() {
            return regTime;
        }

        public void setRegTime(long regTime) {
            this.regTime = regTime;
        }

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public boolean isWhiteList() {
            return whiteList;
        }

        public void setWhiteList(boolean whiteList) {
            this.whiteList = whiteList;
        }

        public String getInviteCode() {
            return inviteCode;
        }

        public void setInviteCode(String inviteCode) {
            this.inviteCode = inviteCode;
        }

        public String getInviteLink() {
            return inviteLink;
        }

        public void setInviteLink(String inviteLink) {
            this.inviteLink = inviteLink;
        }

        public int getInviter() {
            return inviter;
        }

        public void setInviter(int inviter) {
            this.inviter = inviter;
        }

        public boolean isGoogleSecretAuth() {
            return googleSecretAuth;
        }

        public void setGoogleSecretAuth(boolean googleSecretAuth) {
            this.googleSecretAuth = googleSecretAuth;
        }

        public String getUserName() {

            String phoneStr = "";

            if(BaseUtil.isValue(userName)){
                phoneStr = userName.substring(0,3)+"****"+userName.substring(userName.length() - 4);
            }

            return phoneStr;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Object getNickName() {
            return nickName;
        }

        public void setNickName(Object nickName) {
            this.nickName = nickName;
        }

        public String getRealName() {
            if(realName == null && "PASS".equals(idInfoStatus)){
                return "匿名";
            }
            if(realName == null && "UNSUBMIT".equals(idInfoStatus)){
                return "未实名";
            }
            if(realName == null && "NOT_PASS".equals(idInfoStatus)){
                return "实名未通过";
            }
            if(realName == null && "WAITING".equals(idInfoStatus)){
                return "实名中";
            }
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public Object getIdCardNo() {
            return idCardNo;
        }

        public void setIdCardNo(Object idCardNo) {
            this.idCardNo = idCardNo;
        }

        public Object getNationalId() {
            return nationalId;
        }

        public void setNationalId(Object nationalId) {
            this.nationalId = nationalId;
        }

        public String getIdInfoStatus() {
            return idInfoStatus;
        }

        public void setIdInfoStatus(String idInfoStatus) {
            this.idInfoStatus = idInfoStatus;
        }

        public Object getIdInfoStatusString() {
            return idInfoStatusString;
        }

        public void setIdInfoStatusString(Object idInfoStatusString) {
            this.idInfoStatusString = idInfoStatusString;
        }

        public String getAgentInfoStatus() {
            return agentInfoStatus;
        }

        public void setAgentInfoStatus(String agentInfoStatus) {
            this.agentInfoStatus = agentInfoStatus;
        }

        public Object getAgentInfoStatusString() {
            return agentInfoStatusString;
        }

        public void setAgentInfoStatusString(Object agentInfoStatusString) {
            this.agentInfoStatusString = agentInfoStatusString;
        }

        public String getRegType() {
            return regType;
        }

        public void setRegType(String regType) {
            this.regType = regType;
        }

        public int getSuperLeaderWeightPercent() {
            return superLeaderWeightPercent;
        }

        public void setSuperLeaderWeightPercent(int superLeaderWeightPercent) {
            this.superLeaderWeightPercent = superLeaderWeightPercent;
        }

        public boolean isDeduct() {
            return deduct;
        }

        public void setDeduct(boolean deduct) {
            this.deduct = deduct;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public boolean isGoogleAuthIsOpen() {
            return googleAuthIsOpen;
        }

        public void setGoogleAuthIsOpen(boolean googleAuthIsOpen) {
            this.googleAuthIsOpen = googleAuthIsOpen;
        }

        public boolean isWithdrawGoogleAuthIsOpen() {
            return withdrawGoogleAuthIsOpen;
        }

        public void setWithdrawGoogleAuthIsOpen(boolean withdrawGoogleAuthIsOpen) {
            this.withdrawGoogleAuthIsOpen = withdrawGoogleAuthIsOpen;
        }

        public boolean isLevelFlag() {
            return levelFlag;
        }

        public void setLevelFlag(boolean levelFlag) {
            this.levelFlag = levelFlag;
        }

        public boolean isWhetherSalesCommission() {
            return whetherSalesCommission;
        }

        public void setWhetherSalesCommission(boolean whetherSalesCommission) {
            this.whetherSalesCommission = whetherSalesCommission;
        }

        public boolean isHasC2CTrade() {
            return hasC2CTrade;
        }

        public void setHasC2CTrade(boolean hasC2CTrade) {
            this.hasC2CTrade = hasC2CTrade;
        }

        public boolean isTeamflag() {
            return teamflag;
        }

        public void setTeamflag(boolean teamflag) {
            this.teamflag = teamflag;
        }

        public String getVolunteerStatus() {
            return volunteerStatus;
        }

        public void setVolunteerStatus(String volunteerStatus) {
            this.volunteerStatus = volunteerStatus;
        }

        public Object getVolunteerStatusString() {
            return volunteerStatusString;
        }

        public void setVolunteerStatusString(Object volunteerStatusString) {
            this.volunteerStatusString = volunteerStatusString;
        }

        public Object getVolunteerNumber() {
            return volunteerNumber;
        }

        public void setVolunteerNumber(Object volunteerNumber) {
            this.volunteerNumber = volunteerNumber;
        }

        public int getVolunteerRate() {
            return volunteerRate;
        }

        public void setVolunteerRate(int volunteerRate) {
            this.volunteerRate = volunteerRate;
        }

        public int getCommunityId() {
            return communityId;
        }

        public void setCommunityId(int communityId) {
            this.communityId = communityId;
        }

        public String getRelation() {
            return relation;
        }

        public void setRelation(String relation) {
            this.relation = relation;
        }

        public int getNodeId() {
            return nodeId;
        }

        public void setNodeId(int nodeId) {
            this.nodeId = nodeId;
        }

        public int getLevel() {
            return level;
        }

        public String getStrLevel() {

            if(level == 1){
                return "普通用户";
            }else if(level == 2){
                return "初级销售经理";
            }else if(level == 3){
                return "中级销售经理";
            }else if(level == 4){
                return "高级销售经理";
            }else if(level == 5){
                return "销售总监";
            }else{
                if(level > 5){
                    return "神一样的存在";
                }else{
                    return "游客";
                }

            }

        }

        public int getLevelImg(){
            if(level == 1){
                return R.mipmap.normal_grade_mark;
            }else if(level == 2){
                return R.mipmap.proficiency_level_mark;
            }else if(level == 3){
                return R.mipmap.technical_grade_mark;
            }else if(level == 4){
                return R.mipmap.foreman_level_mark;
            }else if(level == 5){
                return R.mipmap.master_level_mark;
            }else{
                return R.mipmap.normal_grade_mark;
            }
        }

        public int getLevelColor(){
            if(level == 1){
                return Color.parseColor("#3EA4F9");
            }else if(level == 2){
                return Color.parseColor("#24DBC2");
            }else if(level == 3){
                return Color.parseColor("#FFB54B");
            }else if(level == 4){
                return Color.parseColor("#9D63FD");
            }else if(level == 5){
                return Color.parseColor("#F81F76");
            }else{
                return Color.parseColor("#3EA4F9");
            }
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public boolean isSuperLevel() {
            return superLevel;
        }

        public void setSuperLevel(boolean superLevel) {
            this.superLevel = superLevel;
        }

        public Object getCfdRate() {
            return cfdRate;
        }

        public void setCfdRate(Object cfdRate) {
            this.cfdRate = cfdRate;
        }

        public Object getNodeName() {
            return nodeName;
        }

        public void setNodeName(Object nodeName) {
            this.nodeName = nodeName;
        }

        public Object getAgentName() {
            return agentName;
        }

        public void setAgentName(Object agentName) {
            this.agentName = agentName;
        }

        public Object getGestureEnable() {
            return gestureEnable;
        }

        public void setGestureEnable(Object gestureEnable) {
            this.gestureEnable = gestureEnable;
        }

        public boolean isPartner() {
            return partner;
        }

        public void setPartner(boolean partner) {
            this.partner = partner;
        }

        public boolean isProject() {
            return project;
        }

        public void setProject(boolean project) {
            this.project = project;
        }
    }

    public static class CountDTO {
        /**
         * profitAmount : null
         * myExpectedRevenue : 0.04815027
         * dayPendAmount : null
         * totalReve  nue : 21169.14059177
         * notOpenCalculationPowerCount : 0
         * totalCount : 2.15955152
         * bofuAmount : 2.2332125056124448
         * tradeCalculationPowerCount : 1
         * pendAmount : null
         * totalpledge : 8.874175
         * availableAmount : 0
         * pendingrelease : 0
         * totalCountYesterDay : 0.03465167
         * doneCalculationPowerCount : 0
         * calculationPowerCount : 1
         */

        private double profitAmount;
        private double myExpectedRevenue;
        private double dayPendAmount;
        @SerializedName("totalReve  nue")
        private double _$TotalReveNue38;// FIXME check this code
        private double notOpenCalculationPowerCount;
        private double totalCount;
        private double bofuAmount;
        private int tradeCalculationPowerCount;
        private double pendAmount;
        private double totalpledge;
        private double availableAmount;
        private double pendingrelease;
        private double totalCountYesterDay;
        private double mySuanLiAmount;
        private double myAvailAmount;
        private int doneCalculationPowerCount;
        private int calculationPowerCount;
        private double lejiAmount;

        public double getLejiAmount() {
            return lejiAmount;
        }

        public void setLejiAmount(double lejiAmount) {
            this.lejiAmount = lejiAmount;
        }

        public double getMySuanLiAmount() {
            return mySuanLiAmount;
        }

        public void setMySuanLiAmount(double mySuanLiAmount) {
            this.mySuanLiAmount = mySuanLiAmount;
        }

        public double getMyAvailAmount() {
            return myAvailAmount;
        }

        public void setMyAvailAmount(double myAvailAmount) {
            this.myAvailAmount = myAvailAmount;
        }

        public double getProfitAmount() {
            return profitAmount;
        }

        public void setProfitAmount(double profitAmount) {
            this.profitAmount = profitAmount;
        }

        public double getMyExpectedRevenue() {
            return myExpectedRevenue;
        }

        public void setMyExpectedRevenue(double myExpectedRevenue) {
            this.myExpectedRevenue = myExpectedRevenue;
        }

        public double getDayPendAmount() {
            return dayPendAmount;
        }

        public void setDayPendAmount(double dayPendAmount) {
            this.dayPendAmount = dayPendAmount;
        }

        public double get_$TotalReveNue38() {
            return _$TotalReveNue38;
        }

        public void set_$TotalReveNue38(double _$TotalReveNue38) {
            this._$TotalReveNue38 = _$TotalReveNue38;
        }

        public double getNotOpenCalculationPowerCount() {
            return notOpenCalculationPowerCount;
        }

        public void setNotOpenCalculationPowerCount(double notOpenCalculationPowerCount) {
            this.notOpenCalculationPowerCount = notOpenCalculationPowerCount;
        }

        public double getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(double totalCount) {
            this.totalCount = totalCount;
        }

        public double getBofuAmount() {
            return bofuAmount;
        }

        public void setBofuAmount(double bofuAmount) {
            this.bofuAmount = bofuAmount;
        }

        public int getTradeCalculationPowerCount() {
            return tradeCalculationPowerCount;
        }

        public void setTradeCalculationPowerCount(int tradeCalculationPowerCount) {
            this.tradeCalculationPowerCount = tradeCalculationPowerCount;
        }

        public double getPendAmount() {
            return pendAmount;
        }

        public void setPendAmount(double pendAmount) {
            this.pendAmount = pendAmount;
        }

        public double getTotalpledge() {
            return totalpledge;
        }

        public void setTotalpledge(double totalpledge) {
            this.totalpledge = totalpledge;
        }

        public double getAvailableAmount() {
            return availableAmount;
        }

        public void setAvailableAmount(double availableAmount) {
            this.availableAmount = availableAmount;
        }

        public double getPendingrelease() {
            return pendingrelease;
        }

        public void setPendingrelease(double pendingrelease) {
            this.pendingrelease = pendingrelease;
        }

        public double getTotalCountYesterDay() {
            return totalCountYesterDay;
        }

        public void setTotalCountYesterDay(double totalCountYesterDay) {
            this.totalCountYesterDay = totalCountYesterDay;
        }

        public int getDoneCalculationPowerCount() {
            return doneCalculationPowerCount;
        }

        public void setDoneCalculationPowerCount(int doneCalculationPowerCount) {
            this.doneCalculationPowerCount = doneCalculationPowerCount;
        }

        public int getCalculationPowerCount() {
            return calculationPowerCount;
        }

        public void setCalculationPowerCount(int calculationPowerCount) {
            this.calculationPowerCount = calculationPowerCount;
        }

        public String getTotalCountStr() {
            return String.valueOf(BigDecimalUtils.round(totalCount,4));
        }
    }
}
