package com.chuangkou.pdu.entity;

public class PduSelfcheck {
    private Integer id;

    private String selfcheckname;

    private String createtime;

    private String state;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSelfcheckname() {
        return selfcheckname;
    }

    public void setSelfcheckname(String selfcheckname) {
        this.selfcheckname = selfcheckname == null ? null : selfcheckname.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}