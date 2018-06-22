package com.chuangkou.pdu.entity;

import java.util.List;

/**
 * @Author:
 * @Description:
 * @Date:Created in 14:10 2018/5/9
 */
public class PduRelation {

    private Integer pduID;

    private Integer switchPduID;

    private String parent_id;

    private String device_id;
    private String device_name;
    private int device_state;
    private String group_id;
    private String group_name;
    private float power;
    private float electricity;
    private float voltage;
    private int pdutype;

    private List childrens;

    public Integer getPduID() {
        return pduID;
    }

    public void setPduID(Integer pduID) {
        this.pduID = pduID;
    }

    public Integer getSwitchPduID() {
        return switchPduID;
    }

    public void setSwitchPduID(Integer switchPduID) {
        this.switchPduID = switchPduID;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

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

    public List getChildrens() {
        return childrens;
    }

    public void setChildrens(List childrens) {
        this.childrens = childrens;
    }
}
