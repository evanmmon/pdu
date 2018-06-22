package com.chuangkou.pdu.entity;

/**
 * @Author:
 * @Description:
 * @Date:Created in 10:57 2018/3/19
 */
public class Message {

    private String head1 = "aa"; //包头

    private String controlType = ""; //数据流向
    private String command = ""; //命令类型
    private String year = "";
    private String month = "";
    private String day = "";
    private String hour = "";
    private String minute = "";
    private String second = "";
    private String messageID = ""; //操作ID
    private String actionType = ""; //操作状态
    private String onlineState = ""; //心跳
    private String receivePduType = "0000";//接收端产品类型
    private String receiveMachineID = "000000"; //接收端产品ID
    private String receiveRandomID = "0000"; //接收端产品随机数
    private String ip = "";
    private String powerType = "00"; //电源类型
    private String voltage = "0000"; //实时电压
    private String setingVoltage = "0000"; //预警阀值电压
    private String voltageAmplitude = "0000"; //过欠压幅度

    private String current = "0000"; //实时电流
    private String setingCurrent = "0000"; //预警阈值电流
    private String currentAmplitude = "0000"; //过流幅度

    private String watt = "0000"; //实时功率
    private String setingWatt = "0000"; //预警阈值功率
    private String wattAmplitude = "0000"; //功率幅度

    private String relayState = "02"; //继电器状态
    private String overvoltage = "00"; //是否过压
    private String undervoltage = "00"; //是否欠压
    private String overcurrent = "00"; //是否过流
    private String circuitbreaker = "00"; //是否断路
    private String electricleakage = "00"; //是否漏电
    private String sendPduType = "0000"; //发送端产品类型
    private String sendMachineID = "000000"; //发送端产品ID
    private String sendRandomID = "0000"; //发送端产品随机数
    private String pduState = "00"; //设备状态
    private String num = ""; //求和校验位
    private String end1 = "55"; //包尾


    private String reservedWord1 = "00";  //保留字1
    private String reservedWord2 = "00"; //保留字2
    private String reservedWord3 = "00"; //保留字3
    private String reservedWord4 = "000000000000000000"; //保留字4

    private String temperature = "0000";//温度
    private String smoke = "00";//烟雾

    private String waterLevel = "00"; //液位

    private String  electronictages1 = "00"; //电子标签
    private String  electronictages2 = "00"; //电子标签
    private String  electronictages3 = "00"; //电子标签
    private String  electronictages4 = "00"; //电子标签
    private String  electronictages5 = "00"; //电子标签
    private String  electronictages6 = "00"; //电子标签



    public String getHead1() {
        return head1;
    }

    public void setHead1(String head1) {
        this.head1 = head1;
    }


    public String getControlType() {
        return controlType;
    }

    public void setControlType(String controlType) {
        this.controlType = controlType;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getOnlineState() {
        return onlineState;
    }

    public void setOnlineState(String onlineState) {
        this.onlineState = onlineState;
    }

    public String getReceivePduType() {
        return receivePduType;
    }

    public void setReceivePduType(String receivePduType) {
        this.receivePduType = receivePduType;
    }

    public String getReceiveMachineID() {
        return receiveMachineID;
    }

    public void setReceiveMachineID(String receiveMachineID) {
        this.receiveMachineID = receiveMachineID;
    }

    public String getReceiveRandomID() {
        return receiveRandomID;
    }

    public void setReceiveRandomID(String receiveRandomID) {
        this.receiveRandomID = receiveRandomID;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPowerType() {
        return powerType;
    }

    public void setPowerType(String powerType) {
        this.powerType = powerType;
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

    public String getRelayState() {
        return relayState;
    }

    public void setRelayState(String relayState) {
        this.relayState = relayState;
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

    public String getSendPduType() {
        return sendPduType;
    }

    public void setSendPduType(String sendPduType) {
        this.sendPduType = sendPduType;
    }

    public String getSendMachineID() {
        return sendMachineID;
    }

    public void setSendMachineID(String sendMachineID) {
        this.sendMachineID = sendMachineID;
    }

    public String getSendRandomID() {
        return sendRandomID;
    }

    public void setSendRandomID(String sendRandomID) {
        this.sendRandomID = sendRandomID;
    }

    public String getPduState() {
        return pduState;
    }

    public void setPduState(String pduState) {
        this.pduState = pduState;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getEnd1() {
        return end1;
    }

    public void setEnd1(String end1) {
        this.end1 = end1;
    }



    public String getReservedWord1() {
        return reservedWord1;
    }

    public void setReservedWord1(String reservedWord1) {
        this.reservedWord1 = reservedWord1;
    }

    public String getReservedWord2() {
        return reservedWord2;
    }

    public void setReservedWord2(String reservedWord2) {
        this.reservedWord2 = reservedWord2;
    }

    public String getReservedWord3() {
        return reservedWord3;
    }

    public void setReservedWord3(String reservedWord3) {
        this.reservedWord3 = reservedWord3;
    }

    public String getReservedWord4() {
        return reservedWord4;
    }

    public void setReservedWord4(String reservedWord4) {
        this.reservedWord4 = reservedWord4;
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

    public String getSetingVoltage() {
        return setingVoltage;
    }

    public void setSetingVoltage(String setingVoltage) {
        this.setingVoltage = setingVoltage;
    }

    public String getVoltageAmplitude() {
        return voltageAmplitude;
    }

    public void setVoltageAmplitude(String voltageAmplitude) {
        this.voltageAmplitude = voltageAmplitude;
    }

    public String getSetingCurrent() {
        return setingCurrent;
    }

    public void setSetingCurrent(String setingCurrent) {
        this.setingCurrent = setingCurrent;
    }

    public String getCurrentAmplitude() {
        return currentAmplitude;
    }

    public void setCurrentAmplitude(String currentAmplitude) {
        this.currentAmplitude = currentAmplitude;
    }

    public String getSetingWatt() {
        return setingWatt;
    }

    public void setSetingWatt(String setingWatt) {
        this.setingWatt = setingWatt;
    }

    public String getWattAmplitude() {
        return wattAmplitude;
    }

    public void setWattAmplitude(String wattAmplitude) {
        this.wattAmplitude = wattAmplitude;
    }

    public String getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(String waterLevel) {
        this.waterLevel = waterLevel;
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
