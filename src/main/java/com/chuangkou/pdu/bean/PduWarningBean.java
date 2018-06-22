package com.chuangkou.pdu.bean;

public class PduWarningBean {


    /**
     * electric_leakage_remind : 1
     * open_circuit_remind : 1
     * power_reference : 900
     * voltage_reference : 220
     * current_reference : 10
     * power_remind : 1
     * voltage_remind : 1
     * current_remind : 1
     * power_range : 0.1
     * voltage_range : 0.15
     * current_range : 0.05
     */

    private String electric_leakage_remind;
    private String open_circuit_remind;
    private String power_reference;
    private String voltage_reference;
    private String current_reference;
    private String power_remind;
    private String voltage_remind;
    private String current_remind;
    private Float power_range;
    private Float voltage_range;
    private Float current_range;

    private int power_start_delay;//功率启动延时,没有设置则为0
    private int power_resume_delay;//功率恢复延时,没有设置则为0
    private int voltage_start_delay;//过欠压启动延时,没有设置则为0
    private int voltage_resume_delay;//过欠压恢复延时,没有设置则为0
    private int current_start_delay;//电流启动延时,没有设置则为0
    private int current_resume_delay;//电流恢复延时,没有设置则为0

    private int electricity_remind;//是否开启电量提醒
    private Double electricity_value;//电量提醒值

    private int temperature_remind;//过热提醒
    private int temperature_range;//温度阀值
    private int temperature_start_delay;//过热启动延时,没有设置则为0
    private int temperature_resume_delay;//过热恢复延时,没有设置则为0

    private int poor_contact_remind;//接触不良提醒
    private int poor_contact_delay;//接触不良探测间隔时间,没有设置则为0

    public String getElectric_leakage_remind() {
        return electric_leakage_remind;
    }

    public void setElectric_leakage_remind(String electric_leakage_remind) {
        this.electric_leakage_remind = electric_leakage_remind;
    }

    public String getOpen_circuit_remind() {
        return open_circuit_remind;
    }

    public void setOpen_circuit_remind(String open_circuit_remind) {
        this.open_circuit_remind = open_circuit_remind;
    }

    public String getPower_reference() {
        return power_reference;
    }

    public void setPower_reference(String power_reference) {
        this.power_reference = power_reference;
    }

    public String getVoltage_reference() {
        return voltage_reference;
    }

    public void setVoltage_reference(String voltage_reference) {
        this.voltage_reference = voltage_reference;
    }

    public String getCurrent_reference() {
        return current_reference;
    }

    public void setCurrent_reference(String current_reference) {
        this.current_reference = current_reference;
    }

    public String getPower_remind() {
        return power_remind;
    }

    public void setPower_remind(String power_remind) {
        this.power_remind = power_remind;
    }

    public String getVoltage_remind() {
        return voltage_remind;
    }

    public void setVoltage_remind(String voltage_remind) {
        this.voltage_remind = voltage_remind;
    }

    public String getCurrent_remind() {
        return current_remind;
    }

    public void setCurrent_remind(String current_remind) {
        this.current_remind = current_remind;
    }

    public Float getPower_range() {
        return power_range;
    }

    public void setPower_range(Float power_range) {
        this.power_range = power_range;
    }

    public Float getVoltage_range() {
        return voltage_range;
    }

    public void setVoltage_range(Float voltage_range) {
        this.voltage_range = voltage_range;
    }

    public Float getCurrent_range() {
        return current_range;
    }

    public void setCurrent_range(Float current_range) {
        this.current_range = current_range;
    }

    public int getPower_start_delay() {
        return power_start_delay;
    }

    public void setPower_start_delay(int power_start_delay) {
        this.power_start_delay = power_start_delay;
    }

    public int getPower_resume_delay() {
        return power_resume_delay;
    }

    public void setPower_resume_delay(int power_resume_delay) {
        this.power_resume_delay = power_resume_delay;
    }

    public int getVoltage_start_delay() {
        return voltage_start_delay;
    }

    public void setVoltage_start_delay(int voltage_start_delay) {
        this.voltage_start_delay = voltage_start_delay;
    }

    public int getVoltage_resume_delay() {
        return voltage_resume_delay;
    }

    public void setVoltage_resume_delay(int voltage_resume_delay) {
        this.voltage_resume_delay = voltage_resume_delay;
    }

    public int getCurrent_start_delay() {
        return current_start_delay;
    }

    public void setCurrent_start_delay(int current_start_delay) {
        this.current_start_delay = current_start_delay;
    }

    public int getCurrent_resume_delay() {
        return current_resume_delay;
    }

    public void setCurrent_resume_delay(int current_resume_delay) {
        this.current_resume_delay = current_resume_delay;
    }

    public int getElectricity_remind() {
        return electricity_remind;
    }

    public void setElectricity_remind(int electricity_remind) {
        this.electricity_remind = electricity_remind;
    }

    public Double getElectricity_value() {
        return electricity_value;
    }

    public void setElectricity_value(Double electricity_value) {
        this.electricity_value = electricity_value;
    }

    public int getTemperature_remind() {
        return temperature_remind;
    }

    public void setTemperature_remind(int temperature_remind) {
        this.temperature_remind = temperature_remind;
    }

    public int getTemperature_range() {
        return temperature_range;
    }

    public void setTemperature_range(int temperature_range) {
        this.temperature_range = temperature_range;
    }

    public int getTemperature_start_delay() {
        return temperature_start_delay;
    }

    public void setTemperature_start_delay(int temperature_start_delay) {
        this.temperature_start_delay = temperature_start_delay;
    }

    public int getTemperature_resume_delay() {
        return temperature_resume_delay;
    }

    public void setTemperature_resume_delay(int temperature_resume_delay) {
        this.temperature_resume_delay = temperature_resume_delay;
    }

    public int getPoor_contact_remind() {
        return poor_contact_remind;
    }

    public void setPoor_contact_remind(int poor_contact_remind) {
        this.poor_contact_remind = poor_contact_remind;
    }

    public int getPoor_contact_delay() {
        return poor_contact_delay;
    }

    public void setPoor_contact_delay(int poor_contact_delay) {
        this.poor_contact_delay = poor_contact_delay;
    }
}
