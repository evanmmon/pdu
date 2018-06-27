package com.chuangkou.pdu.entity;

public class User {
    private Integer id;

    private String phone;

    private String password;

    private Integer partyid;

    private Integer state;

    private String remark;

    private String username;

    private String email;

    private String avatar;

    private String nickname;

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    private Long create_time;

    private Role role;

    private Organization organization;

    private Mpermissions Mpermissions;

    public User() {
    }

    public User(Integer id, String phone, String password, Integer partyid, Integer state, String remark, String username, String email, Long create_time, Role role, Organization organization, com.chuangkou.pdu.entity.Mpermissions mpermissions) {
        this.id = id;
        this.phone = phone;
        this.password = password;
        this.partyid = partyid;
        this.state = state;
        this.remark = remark;
        this.username = username;
        this.email = email;
        this.create_time = create_time;
        this.role = role;
        this.organization = organization;
        Mpermissions = mpermissions;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPartyid() {
        return partyid;
    }

    public void setPartyid(Integer partyid) {
        this.partyid = partyid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public com.chuangkou.pdu.entity.Mpermissions getMpermissions() {
        return Mpermissions;
    }

    public void setMpermissions(com.chuangkou.pdu.entity.Mpermissions mpermissions) {
        Mpermissions = mpermissions;
    }
}