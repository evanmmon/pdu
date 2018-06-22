package com.chuangkou.pdu.entity;

/**
 * @Author:
 * @Description:
 * @Date:Created in 19:12 2018/3/22
 */
public class PduInfo {
    private Integer id;

    private Integer pduid;

    private String collectiontime;

    private String voltage;

    private String current;

    private String watt;

    private String relaystate;

    private String overvoltage;

    private String undervoltage;

    private String overcurrent;

    private String circuitbreaker;

    private String electricleakage;

    private String state;

    private String remark;

    private String temperature;//温度

    private String smoke;//烟雾

    private String starttime;

    private String endtime;

    private String waterLevel;//液位

    private String quantity; //电量

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

    public String getCollectiontime() {
        return collectiontime;
    }

    public void setCollectiontime(String collectiontime) {
        this.collectiontime = collectiontime == null ? null : collectiontime.trim();
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage == null ? null : voltage.trim();
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current == null ? null : current.trim();
    }

    public String getWatt() {
        return watt;
    }

    public void setWatt(String watt) {
        this.watt = watt == null ? null : watt.trim();
    }

    public String getRelaystate() {
        return relaystate;
    }

    public void setRelaystate(String relaystate) {
        this.relaystate = relaystate == null ? null : relaystate.trim();
    }

    public String getOvervoltage() {
        return overvoltage;
    }

    public void setOvervoltage(String overvoltage) {
        this.overvoltage = overvoltage == null ? null : overvoltage.trim();
    }

    public String getUndervoltage() {
        return undervoltage;
    }

    public void setUndervoltage(String undervoltage) {
        this.undervoltage = undervoltage == null ? null : undervoltage.trim();
    }

    public String getOvercurrent() {
        return overcurrent;
    }

    public void setOvercurrent(String overcurrent) {
        this.overcurrent = overcurrent == null ? null : overcurrent.trim();
    }

    public String getCircuitbreaker() {
        return circuitbreaker;
    }

    public void setCircuitbreaker(String circuitbreaker) {
        this.circuitbreaker = circuitbreaker == null ? null : circuitbreaker.trim();
    }

    public String getElectricleakage() {
        return electricleakage;
    }

    public void setElectricleakage(String electricleakage) {
        this.electricleakage = electricleakage == null ? null : electricleakage.trim();
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

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getSmoke() {
        return smoke;
    }

    public void setSmoke(String smoke) {
        this.smoke = smoke;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(String waterLevel) {
        this.waterLevel = waterLevel;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
