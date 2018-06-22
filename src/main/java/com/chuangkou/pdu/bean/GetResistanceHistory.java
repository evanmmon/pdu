package com.chuangkou.pdu.bean;

import java.util.List;

/**
 * @Author:
 * @Description:
 * @Date:Created in 15:02 2018/5/11
 */
public class GetResistanceHistory {


    /**
     * query_type : 0
     * history_list : [{"datetime":"2018-05-04 10:00:00","value":3000},{"datetime":"2018-05-04 11:00:00","value":3100},{"datetime":"2018-05-04 12:00:00","value":3300},{"datetime":"2018-05-04 13:00:00","value":3500},{"datetime":"2018-05-04 14:00:00","value":2800},{"datetime":"2018-05-04 15:00:00","value":3800},{"datetime":"2018-05-04 16:00:00","value":2900},{"datetime":"2018-05-04 17:00:00","value":3200},{"datetime":"2018-05-04 18:00:00","value":1800}]
     */

    private int query_type;
    private List<HistoryListBean> history_list;

    public int getQuery_type() {
        return query_type;
    }

    public void setQuery_type(int query_type) {
        this.query_type = query_type;
    }

    public List<HistoryListBean> getHistory_list() {
        return history_list;
    }

    public void setHistory_list(List<HistoryListBean> history_list) {
        this.history_list = history_list;
    }

    public static class HistoryListBean {
        /**
         * datetime : 2018-05-04 10:00:00
         * value : 3000
         */

        private String datetime;
        private float value;

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }
    }
}
