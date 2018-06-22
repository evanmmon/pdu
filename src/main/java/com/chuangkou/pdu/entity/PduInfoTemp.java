package com.chuangkou.pdu.entity;

/**
 * @Author:xulei
 * @Description:主要用于接口返回信息的实体，包括设备主表、设备状态表、分组表的信息
 * @Date:Created in 10:01 2018/3/8
 */
public class PduInfoTemp {

    private Integer pduid;

    private String type;

    private String machineid;

    private String name;

    private String ip;

    private String onlinestate;//设备运行状态  在线、离线

    private String updateTime;

    private String voltage;

    private String current;

    private String watt;

    private String relaystate;

    private String overvoltage;

    private String undervoltage;

    private String overcurrent;

    private String circuitbreaker;

    private String electricleakage;

    private Integer pdugroupid;

    private String groupname;

    private String electronictages1;//电子标签1
    private String electronictages2;//电子标签2
    private String electronictages3;//电子标签3
    private String electronictages4;//电子标签4
    private String electronictages5;//电子标签5
    private String electronictages6;//电子标签6

    private String quantity;//总电量
    private String monthQuantity;//当月电量

    private String ifcontrol;//是否可控
    private String access_token;


    public Integer getPduid() {
        return pduid;
    }

    public void setPduid(Integer pduid) {
        this.pduid = pduid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMachineid() {
        return machineid;
    }

    public void setMachineid(String machineid) {
        this.machineid = machineid;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getWatt() {
        return watt;
    }

    public void setWatt(String watt) {
        this.watt = watt;
    }

    public String getRelaystate() {
        return relaystate;
    }

    public void setRelaystate(String relaystate) {
        this.relaystate = relaystate;
    }

    public String getOvervoltage() {
        return overvoltage;
    }

    public void setOvervoltage(String overvoltage) {
        this.overvoltage = overvoltage;
    }

    public String getUndervoltage() {
        return undervoltage;
    }

    public void setUndervoltage(String undervoltage) {
        this.undervoltage = undervoltage;
    }

    public String getOvercurrent() {
        return overcurrent;
    }

    public void setOvercurrent(String overcurrent) {
        this.overcurrent = overcurrent;
    }

    public String getCircuitbreaker() {
        return circuitbreaker;
    }

    public void setCircuitbreaker(String circuitbreaker) {
        this.circuitbreaker = circuitbreaker;
    }

    public String getElectricleakage() {
        return electricleakage;
    }

    public void setElectricleakage(String electricleakage) {
        this.electricleakage = electricleakage;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMonthQuantity() {
        return monthQuantity;
    }

    public void setMonthQuantity(String monthQuantity) {
        this.monthQuantity = monthQuantity;
    }

    public String getIfcontrol() {
        return ifcontrol;
    }

    public void setIfcontrol(String ifcontrol) {
        this.ifcontrol = ifcontrol;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

}
