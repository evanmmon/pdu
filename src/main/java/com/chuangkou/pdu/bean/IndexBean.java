package com.chuangkou.pdu.bean;

import java.util.List;

public class IndexBean {

    /**
     * device_number : 30
     * total_power : 12927.1
     * normal_number : 25
     * closed_number : 2
     * fault_number : 1
     * offline_number : 2
     * group_list : [{"group_id":"180000","group_name":"办公室","total_power":9000.1,"normal_number":5,"closed_number":0,"fault_number":0,"offline_number":0,"group_state":1,"group_type":1},{"group_id":"180002","group_name":"会议室","total_power":0,"normal_number":0,"closed_number":2,"fault_number":0,"offline_number":0,"group_state":0,"group_type":1},{"group_id":"180004","group_name":"仓库","total_power":2030,"normal_number":5,"closed_number":0,"fault_number":0,"offline_number":0,"group_state":1,"group_type":1},{"group_id":"180009","group_name":"生产车间","total_power":1897,"normal_number":5,"closed_number":0,"fault_number":0,"offline_number":0,"group_state":1,"group_type":1}]
     */

    private int device_number;
    private double total_power;
    private int normal_number;
    private int closed_number;
    private int fault_number;
    private int offline_number;
    private int air_switch_number;
    private int socket_number;
    private int d45_number;

    private List<GroupListBean> group_list;

    public int getDevice_number() {
        return device_number;
    }

    public void setDevice_number(int device_number) {
        this.device_number = device_number;
    }

    public double getTotal_power() {
        return total_power;
    }

    public void setTotal_power(double total_power) {
        this.total_power = total_power;
    }

    public int getNormal_number() {
        return normal_number;
    }

    public void setNormal_number(int normal_number) {
        this.normal_number = normal_number;
    }

    public int getClosed_number() {
        return closed_number;
    }

    public void setClosed_number(int closed_number) {
        this.closed_number = closed_number;
    }

    public int getFault_number() {
        return fault_number;
    }

    public void setFault_number(int fault_number) {
        this.fault_number = fault_number;
    }

    public int getOffline_number() {
        return offline_number;
    }

    public void setOffline_number(int offline_number) {
        this.offline_number = offline_number;
    }

    public int getAir_switch_number() {
        return air_switch_number;
    }

    public void setAir_switch_number(int air_switch_number) {
        this.air_switch_number = air_switch_number;
    }

    public int getSocket_number() {
        return socket_number;
    }

    public void setSocket_number(int socket_number) {
        this.socket_number = socket_number;
    }

    public int getD45_number() {
        return d45_number;
    }

    public void setD45_number(int d45_number) {
        this.d45_number = d45_number;
    }

    public List<GroupListBean> getGroup_list() {
        return group_list;
    }

    public void setGroup_list(List<GroupListBean> group_list) {
        this.group_list = group_list;
    }

    public static class GroupListBean {
        /**
         * group_id : 180000
         * group_name : 办公室
         * total_power : 9000.1
         * normal_number : 5
         * closed_number : 0
         * fault_number : 0
         * offline_number : 0
         * group_state : 1
         * group_type : 1
         */

        private String group_id;
        private String group_name;
        private long total_power;
        private int normal_number;
        private int closed_number;
        private int fault_number;
        private int offline_number;
        private int group_state;
//        private int group_type;

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

        public long getTotal_power() {
            return total_power;
        }

        public void setTotal_power(long total_power) {
            this.total_power = total_power;
        }

        public int getNormal_number() {
            return normal_number;
        }

        public void setNormal_number(int normal_number) {
            this.normal_number = normal_number;
        }

        public int getClosed_number() {
            return closed_number;
        }

        public void setClosed_number(int closed_number) {
            this.closed_number = closed_number;
        }

        public int getFault_number() {
            return fault_number;
        }

        public void setFault_number(int fault_number) {
            this.fault_number = fault_number;
        }

        public int getOffline_number() {
            return offline_number;
        }

        public void setOffline_number(int offline_number) {
            this.offline_number = offline_number;
        }

        public int getGroup_state() {
            return group_state;
        }

        public void setGroup_state(int group_state) {
            this.group_state = group_state;
        }

//        public int getGroup_type() {
//            return group_type;
//        }
//
//        public void setGroup_type(int group_type) {
//            this.group_type = group_type;
//        }
    }
}

