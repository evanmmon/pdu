package com.chuangkou.pdu.entity;

/**
 * @Author:
 * @Description:
 * @Date:Created in 18:00 2018/5/10
 */

public class PduOldLine {

    private Integer id;

    private Integer pduID;

    private String  collectiontime;

    private String  resistance;

    private String starttime;

    private String endtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPduID() {
        return pduID;
    }

    public void setPduID(Integer pduID) {
        this.pduID = pduID;
    }

    public String getCollectiontime() {
        return collectiontime;
    }

    public void setCollectiontime(String collectiontime) {
        this.collectiontime = collectiontime;
    }

    public String getResistance() {
        return resistance;
    }

    public void setResistance(String resistance) {
        this.resistance = resistance;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
}
