package com.chuangkou.pdu.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PduGroup {
//    @SerializedName("group_id")
    private Integer id;

    private String groupname;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname == null ? null : groupname.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public PduGroup() {
    }

    public PduGroup(Integer id, String groupname, String remark) {
        this.id = id;
        this.groupname = groupname;
        this.remark = remark;
    }

}