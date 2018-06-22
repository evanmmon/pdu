package com.chuangkou.pdu.bean;

import java.util.List;

/**
 * @Author:
 * @Description:
 * @Date:Created in 16:37 2018/3/21
 */
public class DeviceToGroup {

    private List<DeviceListBean> device_list;

    public List<DeviceListBean> getDevice_list() {
        return device_list;
    }

    public void setDevice_list(List<DeviceListBean> device_list) {
        this.device_list = device_list;
    }

    public static class DeviceListBean {
        /**
         * device_id : 123456
         */

        private String device_id;

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }
    }
}
