package com.zhongyiguolian.zy.data.userbean;

/**
 * @author cdj
 * @date 2020/12/10
 */
public class MemberLoginBean {

    private String m_id;
    private String m_mobile;
    private String m_name;
    private String mi_head;
    private int role_id;
    private int sl_id;

    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
    }

    public String getM_mobile() {
        return m_mobile;
    }

    public void setM_mobile(String m_mobile) {
        this.m_mobile = m_mobile;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getMi_head() {
        return mi_head;
    }

    public void setMi_head(String mi_head) {
        this.mi_head = mi_head;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getSl_id() {
        return sl_id;
    }

    public void setSl_id(int sl_id) {
        this.sl_id = sl_id;
    }
}
