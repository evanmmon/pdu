package com.chuangkou.pdu.entity;

public class PduAutomaticInfo {
    private Integer id;

    private String action;

    private String actiontime;

    private String resultstate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getResultstate() {
        return resultstate;
    }

    public void setResultstate(String resultstate) {
        this.resultstate = resultstate == null ? null : resultstate.trim();
    }
}