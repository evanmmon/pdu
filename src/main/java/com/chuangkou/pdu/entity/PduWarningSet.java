package com.chuangkou.pdu.entity;

public class PduWarningSet {
    private Integer pduid;

    private String voltage;             //电压

    private String current;             //电流

    private String watt;                //功率

    private String relaystate;         //继电器状态

    private String overvoltage;         //过压提醒

    private String undervoltage;        //欠压提醒

    private String overcurrent;         //过流提醒

    private String circuitbreaker;      //断路提醒

    private String electricleakage;     //漏电提醒

    private String powerremind;			//功率提醒

    private Float setingvoltage;		//电压幅度

    private Float wattamplitude;		//功率幅度

    private Float currentamplitude;		//电流幅度

    private int powerStartDelay; //功率延时时间
    private int powerResumeDelay; //功率恢复延时
    private int voltageStartDelay; //过欠压启动延时
    private int voltageResumedDelay; //过欠压恢复延时
    private int currentStartDelay; //电流启动延时
    private int currentResumeDelay; //电流恢复延时

    private int electricityRemind; //电量提醒
    private Float lowerLimitQuantity; //电量下限

    private int temperatureRemind;//温度提醒
    private Float temperatureAmplitude; //温度阀值
    private int temperatureStartDelay;//温度延时时间
    private int temperatureResumeDelay;//温度恢复延时

    private int poorContactRemind;//接触不良提醒
    private int poorContact;//接触不良间隔时间

    public Integer getPduid() {
        return pduid;
    }

    public void setPduid(Integer pduid) {
        this.pduid = pduid;
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

    public String getPowerremind() {
        return powerremind;
    }

    public void setPowerremind(String powerremind) {
        this.powerremind = powerremind == null ? null : powerremind.trim();
    }

    public Float getSetingvoltage() {
        return setingvoltage;
    }

    public void setSetingvoltage(Float setingvoltage) {
        this.setingvoltage = setingvoltage;
    }

    public Float getWattamplitude() {
        return wattamplitude;
    }

    public void setWattamplitude(Float wattamplitude) {
        this.wattamplitude = wattamplitude;
    }

    public Float getCurrentamplitude() {
        return currentamplitude;
    }

    public void setCurrentamplitude(Float currentamplitude) {
        this.currentamplitude = currentamplitude;
    }

    public int getPowerStartDelay() {
        return powerStartDelay;
    }

    public void setPowerStartDelay(int powerStartDelay) {
        this.powerStartDelay = powerStartDelay;
    }

    public int getPowerResumeDelay() {
        return powerResumeDelay;
    }

    public void setPowerResumeDelay(int powerResumeDelay) {
        this.powerResumeDelay = powerResumeDelay;
    }

    public int getVoltageStartDelay() {
        return voltageStartDelay;
    }

    public void setVoltageStartDelay(int voltageStartDelay) {
        this.voltageStartDelay = voltageStartDelay;
    }

    public int getVoltageResumedDelay() {
        return voltageResumedDelay;
    }

    public void setVoltageResumedDelay(int voltageResumedDelay) {
        this.voltageResumedDelay = voltageResumedDelay;
    }

    public int getCurrentStartDelay() {
        return currentStartDelay;
    }

    public void setCurrentStartDelay(int currentStartDelay) {
        this.currentStartDelay = currentStartDelay;
    }

    public int getCurrentResumeDelay() {
        return currentResumeDelay;
    }

    public void setCurrentResumeDelay(int currentResumeDelay) {
        this.currentResumeDelay = currentResumeDelay;
    }

    public Float getLowerLimitQuantity() {
        return lowerLimitQuantity;
    }

    public void setLowerLimitQuantity(Float lowerLimitQuantity) {
        this.lowerLimitQuantity = lowerLimitQuantity;
    }

    public Float getTemperatureAmplitude() {
        return temperatureAmplitude;
    }

    public void setTemperatureAmplitude(Float temperatureAmplitude) {
        this.temperatureAmplitude = temperatureAmplitude;
    }

    public int getTemperatureStartDelay() {
        return temperatureStartDelay;
    }

    public void setTemperatureStartDelay(int temperatureStartDelay) {
        this.temperatureStartDelay = temperatureStartDelay;
    }

    public int getTemperatureResumeDelay() {
        return temperatureResumeDelay;
    }

    public void setTemperatureResumeDelay(int temperatureResumeDelay) {
        this.temperatureResumeDelay = temperatureResumeDelay;
    }

    public int getPoorContact() {
        return poorContact;
    }

    public void setPoorContact(int poorContact) {
        this.poorContact = poorContact;
    }

    public int getElectricityRemind() {
        return electricityRemind;
    }

    public void setElectricityRemind(int electricityRemind) {
        this.electricityRemind = electricityRemind;
    }

    public int getTemperatureRemind() {
        return temperatureRemind;
    }

    public void setTemperatureRemind(int temperatureRemind) {
        this.temperatureRemind = temperatureRemind;
    }

    public int getPoorContactRemind() {
        return poorContactRemind;
    }

    public void setPoorContactRemind(int poorContactRemind) {
        this.poorContactRemind = poorContactRemind;
    }
}