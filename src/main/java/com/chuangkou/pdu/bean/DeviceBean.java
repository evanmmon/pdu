package com.chuangkou.pdu.bean;

import com.google.gson.annotations.SerializedName;

/**
 * App场景返回的设备
 */
public class DeviceBean {
   /* device_id	列表中的设备ID	是	[string]
    device_name	列表中的设备名称	是	[string]	*/
   private String device_id;
   private String device_name;

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
}
