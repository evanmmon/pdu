package com.chuangkou.pdu.bean;

import java.util.List;

/**
 * @Author:
 * @Description:
 * @Date:Created in 13:39 2018/3/21
 */
public class GetNewDeviceList {


    private List<NewDeviceListBean> device_list;

    public List<NewDeviceListBean> getDevice_list() {
        return device_list;
    }

    public void setDevice_list(List<NewDeviceListBean> device_list) {
        this.device_list = device_list;
    }

    public static class NewDeviceListBean {
        /**
         * device_id : 1
         * ip : 192.168.0.0.1
         * machine_id : M2-0E-SS-12-0K
         * qrcode : 1q2w3e
         * state : 2
         * type : address
         */

        private int device_id;
        private String ip;
        private String machine_id;
        private String qrcode;
        private int state;
        private String type;

        public int getDevice_id() {
            return device_id;
        }

        public void setDevice_id(int device_id) {
            this.device_id = device_id;
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

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
