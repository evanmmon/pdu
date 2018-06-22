package com.chuangkou.pdu.entity;

public class Log {
    private Integer id;

    private Integer userid;

    private String username;

    private String action;

    private String actiontime;

    public Log() {
    }

    public Log(Integer userid, String username, String action, String actiontime) {
        this.userid = userid;
        this.username = username;
        this.action = action;
        this.actiontime = actiontime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    public String getActiontime() {
        return actiontime;
    }

    public void setActiontime(String actiontime) {
        this.actiontime = actiontime == null ? null : actiontime.trim();
    }


}