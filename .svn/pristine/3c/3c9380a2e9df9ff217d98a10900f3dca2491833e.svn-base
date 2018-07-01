package com.chuangkou.pdu.bean;

import java.util.List;

/**
 * App接口返回对象
 */
public class SceneBean {
   /* scene_list	场景列表	是	[array]
     scene_id	场景ID	是	[string]	100	查看
     name	场景名称	是	[string]	场景名	查看
     start_time	开启时间	是	[string]	00:00	查看
     end_time	结束时间	是	[string]	23:59	查看
     repeat_day	重复日期,0星期天,1星期一...6星期六	是	[array]	[1,2,3,4,5]	查看
     power	功率幅度， 0表示不限制(未设置) 0.02表示上下幅度为2%	是	[float]	0.02	查看
     overvoltage	电压幅度,0% 0表示不限制(未设置)	是	[float]	0	查看
     undervoltage	欠压,2% 0表示不限制(未设置)		[float]	0.02	查看
     overcurrent	电流幅度， 0表示不限制(未设置) 0.02表示 2%	是	[float]	0.02	查看
     open_circuit	断路 1表示监控 0表示不限制(未设置)	是	[int]	1	查看
     electric_leakage	漏电 0表示不限制(未设置)	是	[int]	1	查看
     state	场景开关状态	是	[int]	1	查看
    device_list	场景关联的设备列表	是	[array]
    device_id	列表中的设备ID	是	[string]	123456	查看
    device_name	列表中的设备名称	是	[string]	设备A*/
    private String scene_id;//场景ID
    private String name;	//场景名称
    private String start_time;	//开启时间
    private String end_time;	//结束时间
    private int[] repeat_day;	//重复日期,0星期天,1星期一...6星期六	是	[array]	[1,2,3,4,5]
    private Float power;	//功率幅度， 0表示不限制(未设置) 0.02表示上下幅度为2%	是	[float]	0.02
    private Float overvoltage;	//电压幅度,0% 0表示不限制(未设置)	是	[float]	0
    private Float undervoltage;	//欠压,2% 0表示不限制(未设置)		[float]	0.02
    private Float overcurrent;	//电流幅度， 0表示不限制(未设置) 0.02表示 2%	是	[float]	0.02	查看
    private Integer open_circuit;	//断路 1表示监控 0表示不限制
    private Integer electric_leakage;	//漏电 0表示不限制
    private Integer state;	//场景开关状态
    private List<DeviceBean> device_list;	//场景关联的设备列表

    public String getScene_id() {
        return scene_id;
    }

    public void setScene_id(String scene_id) {
        this.scene_id = scene_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public int[] getRepeat_day() {
        return repeat_day;
    }

    public void setRepeat_day(int[] repeat_day) {
        this.repeat_day = repeat_day;
    }

    public Float getPower() {
        return power;
    }

    public void setPower(Float power) {
        this.power = power;
    }

    public Float getOvervoltage() {
        return overvoltage;
    }

    public void setOvervoltage(Float overvoltage) {
        this.overvoltage = overvoltage;
    }

    public Float getUndervoltage() {
        return undervoltage;
    }

    public void setUndervoltage(Float undervoltage) {
        this.undervoltage = undervoltage;
    }

    public Float getOvercurrent() {
        return overcurrent;
    }

    public void setOvercurrent(Float overcurrent) {
        this.overcurrent = overcurrent;
    }

    public Integer getOpen_circuit() {
        return open_circuit;
    }

    public void setOpen_circuit(Integer open_circuit) {
        this.open_circuit = open_circuit;
    }

    public Integer getElectric_leakage() {
        return electric_leakage;
    }

    public void setElectric_leakage(Integer electric_leakage) {
        this.electric_leakage = electric_leakage;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<DeviceBean> getDevice_list() {
        return device_list;
    }

    public void setDevice_list(List<DeviceBean> device_list) {
        this.device_list = device_list;
    }
}
