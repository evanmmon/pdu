package com.chuangkou.pdu.bean;

import com.chuangkou.pdu.entity.PduRelation;

import java.util.List;

/**
 * @Author:
 * @Description:
 * @Date:Created in 11:10 2018/5/11
 */
public class GetDeviceRelationshipList {


    private List<DeviceListBean> device_list;

    public List<DeviceListBean> getDevice_list() {
        return device_list;
    }

    public void setDevice_list(List<DeviceListBean> device_list) {
        this.device_list = device_list;
    }

    public static class DeviceListBean {
        /**
         * device_id : 1001
         * device_name : 研发部线路空开
         * device_state : 1
         * group_id : 101
         * group_name : 研发部
         * power : 3
         * electricity : 1
         * voltage : 2
         * electronictages1 :
         * electronictages2 :
         * electronictages3 :
         * electronictages4 :
         * electronictages5 :
         * electronictages6 :
         * pdutype : 1
         * childrens : [{"device_id":"1001","device_name":"空调插座","device_state":1,"group_id":"101","group_name":"分组A","power":2200,"electricity":10,"voltage":220,"pdutype":2}]
         */

        private String device_id;
        private String device_name;
        private int device_state;
        private String group_id;
        private String group_name;
        private float power;
        private float electricity;
        private float voltage;
        private String electronictages1;
        private String electronictages2;
        private String electronictages3;
        private String electronictages4;
        private String electronictages5;
        private String electronictages6;
        private int pdutype;
        private String parent_id;//父节点id
        private List<ChildrensBean> childrens;

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

        public int getPdutype() {
            return pdutype;
        }

        public void setPdutype(int pdutype) {
            this.pdutype = pdutype;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public List<ChildrensBean> getChildrens() {
            return childrens;
        }

        public void setChildrens(List<ChildrensBean> childrens) {
            this.childrens = childrens;
        }

        public static class ChildrensBean {
            /**
             * device_id : 1001
             * device_name : 空调插座
             * device_state : 1
             * group_id : 101
             * group_name : 分组A
             * power : 2200
             * electricity : 10
             * voltage : 220
             * pdutype : 2
             */

            private String device_id;
            private String device_name;
            private int device_state;
            private String group_id;
            private String group_name;
            private float power;
            private float electricity;
            private float voltage;
            private int pdutype;
            private String parent_id;//父节点id
            private List<PduRelation> childrens; //修改字段sub_childrens为childrens

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public List<PduRelation> getChildrens() {
                return childrens;
            }

            public void setChildrens(List<PduRelation> childrens) {
                this.childrens = childrens;
            }

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

            public int getPdutype() {
                return pdutype;
            }

            public void setPdutype(int pdutype) {
                this.pdutype = pdutype;
            }


            @Override
            public String toString() {
                return "ChildrensBean{" +
                        "device_id='" + device_id + '\'' +
                        ", device_name='" + device_name + '\'' +
                        ", device_state=" + device_state +
                        ", group_id='" + group_id + '\'' +
                        ", group_name='" + group_name + '\'' +
                        ", power=" + power +
                        ", electricity=" + electricity +
                        ", voltage=" + voltage +
                        ", pdutype=" + pdutype +
                        ", parent_id=" + parent_id +
                        ", childrens=" + childrens +
                        '}';
            }
        }
    }
}

