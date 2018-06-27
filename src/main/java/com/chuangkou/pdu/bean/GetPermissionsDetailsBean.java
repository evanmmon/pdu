package com.chuangkou.pdu.bean;

import java.util.List;

/**
 * @Author:
 * @Description:
 * @Date:Created in 18:23 2018/6/19
 */
public class GetPermissionsDetailsBean {


    private List<PermissionListBean> permission_list;

    public List<PermissionListBean> getPermission_list() {
        return permission_list;
    }

    public void setPermission_list(List<PermissionListBean> permission_list) {
        this.permission_list = permission_list;
    }

    public static class PermissionListBean {
        /**
         * device_id : 39
         * device_type : 180
         * device_statu : true
         * device_operation : false
         */

        private int device_id;
        private String device_type;
        private String device_statu;//是否可见
        private String device_operation;//是否可控

        public int getDevice_id() {
            return device_id;
        }

        public void setDevice_id(int device_id) {
            this.device_id = device_id;
        }

        public String getDevice_type() {
            return device_type;
        }

        public void setDevice_type(String device_type) {
            this.device_type = device_type;
        }

        public String isDevice_statu() {
            return device_statu;
        }

        public void setDevice_statu(String device_statu) {
            this.device_statu = device_statu;
        }

        public String isDevice_operation() {
            return device_operation;
        }

        public void setDevice_operation(String device_operation) {
            this.device_operation = device_operation;
        }
    }
}
