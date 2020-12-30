package com.zhongyiguolian.zy.ui.main.beans;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class RegisteredBean {

    /**
     * customer : {"ID":2,"CreatedAt":"2020-12-29T10:13:50.009831+08:00","UpdatedAt":"2020-12-29T10:13:50.009831+08:00","mobile":"18183185173","password":"76d80224611fc919a5d54f0ff9fba446","username":"18183185173","head_portrait":"","authority_id":"","kpi_level":0,"level":0,"node_id":0,"relation":"","reg_type":"MOBILE","auth_id":0,"mobile_auth":0,"invite_code":"WDpkFOx2","invite_link":"https://www.zygl.com/ac/los/index.html?code=WDpkFOx2","email":"","email_auth":0,"inviter_id":1,"last_login_time":"2020-12-29T10:13:50.003818+08:00","uuid":"5b0ea759-4ea8-46a0-af7d-022dc5f1332f"}
     */

    private CustomerDTO customer;

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public static class CustomerDTO {
        /**
         * ID : 2
         * CreatedAt : 2020-12-29T10:13:50.009831+08:00
         * UpdatedAt : 2020-12-29T10:13:50.009831+08:00
         * mobile : 18183185173
         * password : 76d80224611fc919a5d54f0ff9fba446
         * username : 18183185173
         * head_portrait :
         * authority_id :
         * kpi_level : 0
         * level : 0
         * node_id : 0
         * relation :
         * reg_type : MOBILE
         * auth_id : 0
         * mobile_auth : 0
         * invite_code : WDpkFOx2
         * invite_link : https://www.zygl.com/ac/los/index.html?code=WDpkFOx2
         * email :
         * email_auth : 0
         * inviter_id : 1
         * last_login_time : 2020-12-29T10:13:50.003818+08:00
         * uuid : 5b0ea759-4ea8-46a0-af7d-022dc5f1332f
         */

        private int ID;
        private String CreatedAt;
        private String UpdatedAt;
        private String mobile;
        private String password;
        private String username;
        private String head_portrait;
        private String authority_id;
        private int kpi_level;
        private int level;
        private int node_id;
        private String relation;
        private String reg_type;
        private int auth_id;
        private int mobile_auth;
        private String invite_code;
        private String invite_link;
        private String email;
        private int email_auth;
        private int inviter_id;
        private String last_login_time;
        private String uuid;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getCreatedAt() {
            return CreatedAt;
        }

        public void setCreatedAt(String CreatedAt) {
            this.CreatedAt = CreatedAt;
        }

        public String getUpdatedAt() {
            return UpdatedAt;
        }

        public void setUpdatedAt(String UpdatedAt) {
            this.UpdatedAt = UpdatedAt;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getHead_portrait() {
            return head_portrait;
        }

        public void setHead_portrait(String head_portrait) {
            this.head_portrait = head_portrait;
        }

        public String getAuthority_id() {
            return authority_id;
        }

        public void setAuthority_id(String authority_id) {
            this.authority_id = authority_id;
        }

        public int getKpi_level() {
            return kpi_level;
        }

        public void setKpi_level(int kpi_level) {
            this.kpi_level = kpi_level;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getNode_id() {
            return node_id;
        }

        public void setNode_id(int node_id) {
            this.node_id = node_id;
        }

        public String getRelation() {
            return relation;
        }

        public void setRelation(String relation) {
            this.relation = relation;
        }

        public String getReg_type() {
            return reg_type;
        }

        public void setReg_type(String reg_type) {
            this.reg_type = reg_type;
        }

        public int getAuth_id() {
            return auth_id;
        }

        public void setAuth_id(int auth_id) {
            this.auth_id = auth_id;
        }

        public int getMobile_auth() {
            return mobile_auth;
        }

        public void setMobile_auth(int mobile_auth) {
            this.mobile_auth = mobile_auth;
        }

        public String getInvite_code() {
            return invite_code;
        }

        public void setInvite_code(String invite_code) {
            this.invite_code = invite_code;
        }

        public String getInvite_link() {
            return invite_link;
        }

        public void setInvite_link(String invite_link) {
            this.invite_link = invite_link;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getEmail_auth() {
            return email_auth;
        }

        public void setEmail_auth(int email_auth) {
            this.email_auth = email_auth;
        }

        public int getInviter_id() {
            return inviter_id;
        }

        public void setInviter_id(int inviter_id) {
            this.inviter_id = inviter_id;
        }

        public String getLast_login_time() {
            return last_login_time;
        }

        public void setLast_login_time(String last_login_time) {
            this.last_login_time = last_login_time;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }
    }
}
