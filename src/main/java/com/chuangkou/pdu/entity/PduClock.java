package com.chuangkou.pdu.entity;

public class PduClock {
    private Integer id;

    private Integer sceneid;

    private Integer pduid;

    private String action;

    private String actiontime;

    private String resultstate;

    private String mode; //定时模式   0为指定具体的日期只执行一次([date,time]生效) 1为重复执行

    private String repeatday; //重复日期 1、2、3、4、5、6、0 分别代表周一至周日

    private String clockdate;//定时日期

    private String clocktime;//定时时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSceneid() {
        return sceneid;
    }

    public void setSceneid(Integer sceneid) {
        this.sceneid = sceneid;
    }

    public Integer getPduid() {
        return pduid;
    }

    public void setPduid(Integer pduid) {
        this.pduid = pduid;
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

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getRepeatday() {
        return repeatday;
    }

    public void setRepeatday(String repeatday) {
        this.repeatday = repeatday;
    }

    public String getClockdate() {
        return clockdate;
    }

    public void setClockdate(String clockdate) {
        this.clockdate = clockdate;
    }

    public String getClocktime() {
        return clocktime;
    }

    public void setClocktime(String clocktime) {
        this.clocktime = clocktime;
    }
}