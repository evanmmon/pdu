package com.chuangkou.pdu.util;

import com.google.gson.Gson;

/**
 * 设备事件类,用于在设备发生事件时通知APP客户端
 * 当设备发生事件后,生成该对象,并调用{@link DeviceEvent#toSocketMessage()}方法,转换为字符串发送给APP Socket
 */
public class DeviceEvent {
    public static final String START_TAG = "<event>";
    public static final String END_TAG = "</event>";
    /**
     * APP客户端上线
     * device_id 中内容为客户端Token
     * device_name 中为客户端账号
     */
    public static final int TYPE_CLIENT_ONLINE = 100;

    /**
     * 设备上线,用户可见消息
     */
    public static final int TYPE_ONLINE = 0;

    /**
     * 设备离线,用户可见消息
     */
    public static final int TYPE_OFFLINE = 1;

    /**
     * 设备漏电,用户可见消息
     */
    public static final int TYPE_ELECTRIC_LEAKAGE = 2;

    /**
     * 设备断路,用户可见消息
     */
    public static final int TYPE_OPEN_CIRCUIT = 3;

    /**
     * 设备功率异常,用户可见消息
     */
    public static final int TYPE_POWER_ABNORMAL = 4;

    /**
     * 设备过压,用户可见消息
     */
    public static final int TYPE_OVER_VOLTAGE = 5;

    /**
     * 设备欠压,用户可见消息
     */
    public static final int TYPE_UNDER_VOLTAGE = 6;

    /**
     * 设备过流,用户可见消息
     */
    public static final int TYPE_OVER_CURRENT = 7;

    /**
     * 设备被手动关闭(空开在线,被手动关闭),用户不可见消息,客户端UI自动更新
     */
    public static final int TYPE_MANUAL_CLOSE = 8;

    /**
     * 设备被手动开启(空开在线,被手动开启),用户不可见消息,客户端UI自动更新
     */
    public static final int TYPE_MANUAL_OPEN = 9;

    /**
    * 烟雾报警
    * */
    public static final int TYPE_SMOKE_OPEN = 10;

    /**
     * 温度报警
     * */
    public static final int TYPE_TEMPERATURE_OPEN = 11;

    /**
     * 液位报警
     * */
    public static final int TYPE_WATERLEVEL_OPEN = 12;

    /**
     * 事件可见性,用户可见
     */
    public static final int VISIBILITY_VISIBLE = 1;

    /**
     * 事件可见性,用户不可见
     */
    public static final int VISIBILITY_INVISIBLE = 0;


    /**
     * 事件类型
     */
    private int event_type;

    /**
     * 设备ID
     */
    private String device_id = "";

    /**
     * 设备名称
     */
    private String device_name = "";

    /**
     * 分组ID
     */
    private String group_id = "";

    /**
     * 分组名称
     */
    private String group_name = "";

    /**
     * 该事件是否用户可见
     * 可选值 用户可见{@link #VISIBILITY_VISIBLE},用户不可见{@link #VISIBILITY_INVISIBLE}
     */
    private int visible;

    /**
     * 事件描述,通知消息内容
     * 例如:研发部设备A已上线
     */
    private String desc = "";

    /**
     * 事件发生事件,格式为:2018-05-10 10:07:30
     */
    private String time = "";

    public DeviceEvent() {
    }

    public DeviceEvent(int event_type, String device_id, String device_name, String group_id, String group_name, int visible, String desc, String time) {
        this.event_type = event_type;//事件类型
        this.device_id = device_id;//设备ID
        this.device_name = device_name;//设备名称
        this.group_id = group_id;//分组ID
        this.group_name = group_name;//分组名称
        this.visible = visible;//是否可见
        this.desc = desc;//消息内容
        this.time = time;//时间发生时间
    }

    public int getEvent_type() {
        return event_type;
    }

    public void setEvent_type(int event_type) {
        this.event_type = event_type;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 将本对象转换为Socket消息
     *
     * @return 由标签包裹的json字符串
     */
    public String toSocketMessage() {
        Gson gson = new Gson();
        String gsonString = gson.toJson(this);
        String message = START_TAG + "\n" + gsonString + "\n" + END_TAG + "\n";
        return message;
    }

    /**
     * 将json字符串转换为本对象
     *
     * @param jsonString json字符串
     * @return DeviceEvent对象
     */
    public static DeviceEvent parseToObject(String jsonString) {
        Gson gson = new Gson();
        DeviceEvent deviceEvent = gson.fromJson(jsonString, DeviceEvent.class);
        return deviceEvent;
    }
}
