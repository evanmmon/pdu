package com.chuangkou.pdu.entity;

import java.util.List;
import com.chuangkou.pdu.entity.Pdu;

public class PduGroupRelation {
    private  Integer id;
    private Integer pdugroupid=1;
    private Integer pduid;
    private PduGroup pdugroup;
    private Pdu pdu;
 private List<Pdu> pdus;
 private List<PduWarning>pduwarnings;

    public PduGroupRelation() {
    }

    public PduGroupRelation(Integer id, Integer pdugroupid, Integer pduid, PduGroup pdugroup, Pdu pdu, List<Pdu> pdus, List<PduWarning> pduwarnings) {
        this.id = id;
        this.pdugroupid = pdugroupid;
        this.pduid = pduid;
        this.pdugroup = pdugroup;
        this.pdu = pdu;
        this.pdus = pdus;
        this.pduwarnings = pduwarnings;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPdugroupid() {
        return pdugroupid;
    }

    public void setPdugroupid(Integer pdugroupid) {
        this.pdugroupid = pdugroupid;
    }

    public Integer getPduid() {
        return pduid;
    }

    public void setPduid(Integer pduid) {
        this.pduid = pduid;
    }

    public PduGroup getPdugroup() {
        return pdugroup;
    }

    public void setPdugroup(PduGroup pdugroup) {
        this.pdugroup = pdugroup;
    }

    public Pdu getPdu() {
        return pdu;
    }

    public void setPdu(Pdu pdu) {
        this.pdu = pdu;
    }

    public List<Pdu> getPdus() {
        return pdus;
    }

    public void setPdus(List<Pdu> pdus) {
        this.pdus = pdus;
    }

    public List<PduWarning> getPduwarnings() {
        return pduwarnings;
    }

    public void setPduwarnings(List<PduWarning> pduwarnings) {
        this.pduwarnings = pduwarnings;
    }
}