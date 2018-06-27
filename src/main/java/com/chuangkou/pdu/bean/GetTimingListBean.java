package com.chuangkou.pdu.bean;

import java.util.List;

/**
 * @Author:
 * @Description:
 * @Date:Created in 19:40 2018/4/3
 */
public class GetTimingListBean {


    private List<TimingTaskListBean> timing_task_list;

    public List<TimingTaskListBean> getTiming_task_list() {
        return timing_task_list;
    }

    public void setTiming_task_list(List<TimingTaskListBean> timing_task_list) {
        this.timing_task_list = timing_task_list;
    }

    public static class TimingTaskListBean {
        /**
         * task_id : 1
         * mode : 0
         * date : 2018-05-01
         * time : 08:00
         * repeat_day : []
         * action : 1
         * state : 0
         */

        private int task_id;
        private int mode;
        private String date;
        private String time;
        private int action;
        private int state;
        private List<?> repeat_day;

        public int getTask_id() {
            return task_id;
        }

        public void setTask_id(int task_id) {
            this.task_id = task_id;
        }

        public int getMode() {
            return mode;
        }

        public void setMode(int mode) {
            this.mode = mode;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getAction() {
            return action;
        }

        public void setAction(int action) {
            this.action = action;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public List<?> getRepeat_day() {
            return repeat_day;
        }

        public void setRepeat_day(List<?> repeat_day) {
            this.repeat_day = repeat_day;
        }
    }
}

