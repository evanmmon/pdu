package com.chuangkou.pdu.bean;

/**
 * @Author:
 * @Description:
 * @Date:Created in 10:25 2018/6/1
 */
public class NewVersionBean {


    /**
     * download_url : http://192.168.1.100/user/101/avatar.png
     * version_code : 1
     * version_name : 1.1
     * description : 修复了一些bug
     * length : 8388608
     * update_time : 1521166664298
     */

    private String download_url;
    private int version_code;
    private String version_name;
    private String description;
    private long length;
    private long update_time;

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public int getVersion_code() {
        return version_code;
    }

    public void setVersion_code(int version_code) {
        this.version_code = version_code;
    }

    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String version_name) {
        this.version_name = version_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }
}
