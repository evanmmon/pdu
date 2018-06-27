package com.chuangkou.pdu.bean;

import java.util.List;

public class SaveGroupList {
    public SaveGroupList() {
    }

    public SaveGroupList(List<GroupListBean> group_list) {
        this.group_list = group_list;
    }

    private List<GroupListBean> group_list;

    public List<GroupListBean> getGroup_list() {
        return group_list;
    }

    public void setGroup_list(List<GroupListBean> group_list) {
        this.group_list = group_list;
    }

    public static class GroupListBean {
        /**
         * group_id : 123
         * group_name : 分组A
         */

        private Integer group_id;
        private String group_name;

        public Integer getGroup_id() {
            return group_id;
        }

        public void setGroup_id(Integer group_id) {
            this.group_id = group_id;
        }

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }
    }
}
