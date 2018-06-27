package com.chuangkou.pdu.entity;

public class Pdu {
    private Integer id;

    private String machineid;

    private String qrcode;

    private String parentid;

    private String type;

    private String name;

    private String ip;

    private String onlinestate;//0关闭、1运行、2离线、3故障

    private String state;//0 删除、1正常

    private String createTime;

    private String updateTime;

    private String remark;

    private String actionState;//继电器状态 0关 1开

    private String powerType;//电源类型 1市电  2UPS供电
    private Mpermissions mpermissions;
    private PduGroup pduGroup;
    private RolePduRelation rolePduRelation;

    private String electronictages1;//电子标签1
    private String electronictages2;//电子标签2
    private String electronictages3;//电子标签3
    private String electronictages4;//电子标签4
    private String electronictages5;//电子标签5
    private String electronictages6;//电子标签6


    public Pdu() {
    }

    public Pdu(Integer id, String machineid, String qrcode, String parentid, String type, String name, String ip, String onlinestate, String state, String createTime, String updateTime, String remark, String actionState, String powerType, Mpermissions mpermissions, PduGroup pduGroup, RolePduRelation rolePduRelation, String electronictages1, String electronictages2,String electronictages3,String electronictages4,String electronictages5,String electronictages6) {
        this.id = id;
        this.machineid = machineid;
        this.qrcode = qrcode;
        this.parentid = parentid;
        this.type = type;
        this.name = name;
        this.ip = ip;
        this.onlinestate = onlinestate;
        this.state = state;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.remark = remark;
        this.actionState = actionState;
        this.powerType = powerType;
        this.mpermissions = mpermissions;
        this.pduGroup = pduGroup;
        this.rolePduRelation = rolePduRelation;
        this.electronictages1 = electronictages1;
        this.electronictages2 = electronictages2;
        this.electronictages3 = electronictages3;
        this.electronictages4 = electronictages4;
        this.electronictages5 = electronictages5;
        this.electronictages6 = electronictages6;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMachineid() {
        return machineid;
    }

    public void setMachineid(String machineid) {
        this.machineid = machineid;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOnlinestate() {
        return onlinestate;
    }

    public void setOnlinestate(String onlinestate) {
        this.onlinestate = onlinestate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getActionState() {
        return actionState;
    }

    public void setActionState(String actionState) {
        this.actionState = actionState;
    }

    public String getPowerType() {
        return powerType;
    }

    public void setPowerType(String powerType) {
        this.powerType = powerType;
    }

    public Mpermissions getMpermissions() {
        return mpermissions;
    }

    public void setMpermissions(Mpermissions mpermissions) {
        this.mpermissions = mpermissions;
    }

    public PduGroup getPduGroup() {
        return pduGroup;
    }

    public void setPduGroup(PduGroup pduGroup) {
        this.pduGroup = pduGroup;
    }

    public RolePduRelation getRolePduRelation() {
        return rolePduRelation;
    }

    public void setRolePduRelation(RolePduRelation rolePduRelation) {
        this.rolePduRelation = rolePduRelation;
    }

    public String getElectronictages1() {
        return electronictages1;
    }

    public void setElectronictages1(String electronictages1) {
        this.electronictages1 = electronictages1;
    }

    public String getElectronictages2() {
        return electronictages2;
    }

    public void setElectronictages2(String electronictages2) {
        this.electronictages2 = electronictages2;
    }

    public String getElectronictages3() {
        return electronictages3;
    }

    public void setElectronictages3(String electronictages3) {
        this.electronictages3 = electronictages3;
    }

    public String getElectronictages4() {
        return electronictages4;
    }

    public void setElectronictages4(String electronictages4) {
        this.electronictages4 = electronictages4;
    }

    public String getElectronictages5() {
        return electronictages5;
    }

    public void setElectronictages5(String electronictages5) {
        this.electronictages5 = electronictages5;
    }

    public String getElectronictages6() {
        return electronictages6;
    }

    public void setElectronictages6(String electronictages6) {
        this.electronictages6 = electronictages6;
    }
}