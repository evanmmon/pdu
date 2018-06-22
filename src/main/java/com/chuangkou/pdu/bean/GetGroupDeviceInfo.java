package com.chuangkou.pdu.bean;

import java.util.List;

/**
 * @Author:
 * @Description:
 * @Date:Created in 18:13 2018/3/20
 */
public class GetGroupDeviceInfo {


    private List<GroupDeviceInfoBean> device_list;

    private GroupDeviceInfoBean device_detail;


    public List<GroupDeviceInfoBean> getDevice_list() {
        return device_list;
    }

    public void setDevice_list(List<GroupDeviceInfoBean> device_list) {
        this.device_list = device_list;
    }

    public GroupDeviceInfoBean getDevice_detail() {
        return device_detail;
    }

    public void setDevice_detail(GroupDeviceInfoBean device_detail) {
        this.device_detail = device_detail;
    }

    public static class GroupDeviceInfoBean {
        /**
         * device_id : 1001
         * device_name : 设备1
         * device_state : 1
         * group_id : 101
         * group_name : 分组A
         * power : 3.0
         * electricity : 1.0
         * voltage : 2.0
         * overvoltage : 0.1
         * undervoltage : 0.1
         * overcurrent : 1.0
         * open_circuit : false
         * electric_leakage : false
         * relay : false
         * ip : 192.168.0.101
         * machine_id : 05-5A-3F-89-76
         * uptime : 1519698841915
         */

        private String device_id;
        private String device_name;
        private int device_state;
        private String group_id;
        private String group_name;
        private float power;
        private float electricity;
        private float voltage;
        private int overvoltage;
        private int undervoltage;
        private int overcurrent;
        private int open_circuit;
        private int electric_leakage;
        private int relay;
        private String ip;
        private String machine_id;
        private String pdutype;
        private long uptime;
        private String electronictages1;
        private String electronictages2;
        private String electronictages3;
        private String electronictages4;
        private String electronictages5;
        private String electronictages6;

        private Double total_electricity;
        private Double month_electricity;

        private String  enable;

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

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getMachine_id() {
            return machine_id;
        }

        public void setMachine_id(String machine_id) {
            this.machine_id = machine_id;
        }

        public String getPdutype() {
            return pdutype;
        }

        public void setPdutype(String pdutype) {
            this.pdutype = pdutype;
        }

        public long getUptime() {
            return uptime;
        }

        public void setUptime(long uptime) {
            this.uptime = uptime;
        }

        public void setOvervoltage(int overvoltage) {
            this.overvoltage = overvoltage;
        }

        public void setUndervoltage(int undervoltage) {
            this.undervoltage = undervoltage;
        }

        public void setOvercurrent(int overcurrent) {
            this.overcurrent = overcurrent;
        }

        public int getOpen_circuit() {
            return open_circuit;
        }

        public void setOpen_circuit(int open_circuit) {
            this.open_circuit = open_circuit;
        }

        public int getElectric_leakage() {
            return electric_leakage;
        }

        public void setElectric_leakage(int electric_leakage) {
            this.electric_leakage = electric_leakage;
        }

        public int getRelay() {
            return relay;
        }

        public void setRelay(int relay) {
            this.relay = relay;
        }

        public int getOvervoltage() {
            return overvoltage;
        }

        public int getUndervoltage() {
            return undervoltage;
        }

        public int getOvercurrent() {
            return overcurrent;
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

        public Double getTotal_electricity() {
            return total_electricity;
        }

        public void setTotal_electricity(Double total_electricity) {
            this.total_electricity = total_electricity;
        }

        public Double getMonth_electricity() {
            return month_electricity;
        }

        public void setMonth_electricity(Double month_electricity) {
            this.month_electricity = month_electricity;
        }

        public String getEnable() {
            return enable;
        }

        public void setEnable(String enable) {
            this.enable = enable;
        }
    }
}
