package com.chuangkou.pdu.entity;
/**
 * 用于token
 * 刘哲
 * 18/4/11
 */
public class Token {
    private boolean jieguo;
    private String username;
    private String Md5Password;

    public Token() {
    }

    public Token(boolean jieguo, String username, String md5Password) {
        this.jieguo = jieguo;
        this.username = username;
        Md5Password = md5Password;
    }

    public boolean getJieguo() {
        return jieguo;
    }

    public void setJieguo(boolean jieguo) {
        this.jieguo = jieguo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMd5Password() {
        return Md5Password;
    }

    public void setMd5Password(String md5Password) {
        Md5Password = md5Password;
    }
}
