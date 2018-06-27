package com.chuangkou.pdu.bean;

import java.util.List;

/**
 * @Author:
 * @Description:
 * @Date:Created in 10:14 2018/6/13
 */
public class PduSubChildrensBean {

    /**
     * device_id : 1001
     * device_name : 空调插座
     * device_state : 1
     * group_id : 101
     * group_name : 分组A
     * power : 2200
     * electricity : 10
     * voltage : 220
     * pdutype : 2
     * sub_childrens : [{"device_id":"1001","device_name":"空调插座","device_state":1,"group_id":"101","group_name":"分组A","power":2200,"electricity":10,"voltage":220,"pdutype":2,"sub_childrens":[{}]}]
     */

    private String device_id;
    private String device_name;
    private int device_state;
    private String group_id;
    private String group_name;
    private float power;
    private float electricity;
    private float voltage;
    private int pdutype;
    private List<PduSubChildrensBean> childrens;

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public int getDevice_state() {
        return device_state;
    }

    public void setDevice_state(int device_state) {
        this.device_state = device_state;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public float getElectricity() {
        return electricity;
    }

    public void setElectricity(float electricity) {
        this.electricity = electricity;
    }

    public float getVoltage() {
        return voltage;
    }

    public void setVoltage(float voltage) {
        this.voltage = voltage;
    }

    public int getPdutype() {
        return pdutype;
    }

    public void setPdutype(int pdutype) {
        this.pdutype = pdutype;
    }

    public List<PduSubChildrensBean> getSub_childrens() {
        return childrens;
    }

    public void setSub_childrens(List<PduSubChildrensBean> sub_childrens) {
        this.childrens = sub_childrens;
    }
}
