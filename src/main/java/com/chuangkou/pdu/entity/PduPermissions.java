package com.chuangkou.pdu.entity;

public class PduPermissions {
    private Integer roleId;
    private Integer mpermissionsId;
    private String roleName;
    private Integer id;
   private String name;
    private String  machineid;
    private String ip;
    private String ifview;
    private String ifcontrol;
    private String groupname;
    private String pduifiewradio;
    private String pduifcontrolradio;

    public PduPermissions() {
    }

    public PduPermissions(Integer roleId, Integer mpermissionsId, String roleName, String name, String machineid, String ip, String ifview, String ifcontrol, String groupname, String pduifiewradio, String pduifcontrolradio) {
        this.roleId = roleId;
        this.mpermissionsId = mpermissionsId;
        this.roleName = roleName;
        this.name = name;
        this.machineid = machineid;
        this.ip = ip;
        this.ifview = ifview;
        this.ifcontrol = ifcontrol;
        this.groupname = groupname;
        this.pduifiewradio = pduifiewradio;
        this.pduifcontrolradio = pduifcontrolradio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMachineid() {
        return machineid;
    }

    public void setMachineid(String machineid) {
        this.machineid = machineid;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMpermissionsId() {
        return mpermissionsId;
    }

    public void setMpermissionsId(Integer mpermissionsId) {
        this.mpermissionsId = mpermissionsId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIfview() {
        return ifview;
    }

    public void setIfview(String ifview) {
        this.ifview = ifview;
    }

    public String getIfcontrol() {
        return ifcontrol;
    }

    public void setIfcontrol(String ifcontrol) {
        this.ifcontrol = ifcontrol;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getPduifiewradio() {
        return pduifiewradio;
    }

    public void setPduifiewradio(String pduifiewradio) {
        this.pduifiewradio = pduifiewradio;
    }

    public String getPduifcontrolradio() {
        return pduifcontrolradio;
    }

    public void setPduifcontrolradio(String pduifcontrolradio) {
        this.pduifcontrolradio = pduifcontrolradio;
    }
}
