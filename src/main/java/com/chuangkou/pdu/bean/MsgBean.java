package com.chuangkou.pdu.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.Gson;

public class MsgBean<T> {
    private int code = 0;
    private String msg = "";
//    @JsonSerialize(include = JsonSerialize.Inclusion.ALWAYS)
    public T data = null;

    public MsgBean() {
    }

    public MsgBean(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <M> MsgBean<M> getInstance() {
        MsgBean<M> msgBean = new MsgBean<M>();
        msgBean.setCode(0);
        msgBean.setMsg("操作成功");
        msgBean.setData((M) new Object());
        return msgBean;
    }

    public static <M> MsgBean<M> getFalseInstance(String msg) {
        MsgBean<M> msgBean = new MsgBean<M>();
        msgBean.setData((M) new Object());
        msgBean.setCode(-1);
        msgBean.setMsg(msg);
        return msgBean;
    }

    public static<M> MsgBean<M> getTokenFalseInstance() {
        MsgBean<M> msgBean = new MsgBean();
        msgBean.setData((M) new Object());
        msgBean.setCode(403);
        msgBean.setMsg("用户信息验证错误，请重新登录！");
        return msgBean;
    }

    public String toJsonString(){
        Gson gson=new Gson();
        return gson.toJson(this);
    }
}
