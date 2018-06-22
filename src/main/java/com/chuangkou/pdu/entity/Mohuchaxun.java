package com.chuangkou.pdu.entity;

public class Mohuchaxun {
    private  Integer id;
    private Integer pduid;
    private String name;
    private Integer pdugroupid;
    private String groupname;
    private String warningtype;
    private String warningtime;
    private String state;

    public Mohuchaxun() {
    }

    public Mohuchaxun(Integer id, Integer pduid, String name, Integer pdugroupid, String groupname, String warningtype, String warningtime, String state) {
        this.id = id;
        this.pduid = pduid;
        this.name = name;
        this.pdugroupid = pdugroupid;
        this.groupname = groupname;
        this.warningtype = warningtype;
        this.warningtime = warningtime;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPduid() {
        return pduid;
    }

    public void setPduid(Integer pduid) {
        this.pduid = pduid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPdugroupid() {
        return pdugroupid;
    }

    public void setPdugroupid(Integer pdugroupid) {
        this.pdugroupid = pdugroupid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getWarningtype() {
        return warningtype;
    }

    public void setWarningtype(String warningtype) {
        this.warningtype = warningtype;
    }

    public String getWarningtime() {
        return warningtime;
    }

    public void setWarningtime(String warningtime) {
        this.warningtime = warningtime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
