package com.chuangkou.pdu.bean;

import java.util.List;

/**
 * @Author:
 * @Description:
 * @Date:Created in 9:11 2018/3/22
 */
public class GetPduInfoHistory {
    /**
     * query_type : 0
     * history_list : [{"datetime":"2018-01-01 00:00:00","value":3000},{"datetime":"2018-01-02 00:00:00","value":5000},{"datetime":"2018-01-03 00:00:00","value":2300}]
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
         * datetime : 2018-01-01 00:00:00
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
