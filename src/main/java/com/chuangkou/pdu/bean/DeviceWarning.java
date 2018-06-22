package com.chuangkou.pdu.bean;

/**
 * @Author: Kanyuanfeng
 * @Description: 场景设备预警值
 * @Date: 2018/4/11
 * @Modified By:
 */
public class DeviceWarning {
    private Integer id;//来源于pdu
    private String name;//来源于pdu
    private String voltage;//来源于PduWarningSet
    private String current;//来源于PduWarningSet

    public DeviceWarning() {
    }

    public DeviceWarning(Integer id, String name, String voltage, String current) {
        this.id = id;
        this.name = name;
        this.voltage = voltage;
        this.current = current;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}

