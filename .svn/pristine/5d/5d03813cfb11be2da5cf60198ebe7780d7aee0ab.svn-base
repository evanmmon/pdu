package com.chuangkou.pdu.util;

import com.chuangkou.pdu.controller.PduApp;
import com.chuangkou.pdu.entity.Message;
import com.chuangkou.pdu.entity.Pdu;

import java.awt.*;
import java.io.IOException;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:
 * @Description:
 * @Date:Created in 11:27 2018/2/28
 */
public class StringUtil {

    static String[] hexStr = {"0", "1", "2", "3", "4", "5", "6", "7", "8",
            "9", "A", "B", "C", "D", "E", "F"};

    public static void main(String[] args) {
//        hexMessage("2", 3, "1232","1");
        actionMessage("2341");
    }

    public static String randomStr() {
        String a = "0123456789";
        char[] rands = new char[4];
        String str = "";
        for (int i = 0; i < rands.length; i++) {
            int rand = (int) (Math.random() * a.length());
            rands[i] = a.charAt(rand);
            str += rands[i];

        }
        for (int i = 0; i < rands.length; i++) {
//            System.out.println(rands[i]);
        }
        return str;
    }


    /**
     * @param updown 上下行 actype 操作类型  pdu 实体  message 报文随机码ID  ac 操作类型1 操作继电器 0：查询设备状态
     * @Author:xulei
     * @Description: 16进制报文组合
     * @Date: 208-02-28
     */
//    public static String hexMessage(String updown, int actype, Pdu pdu, String messageID,String ac) {
    public static String hexMessage(String updown, int actype, String messageID, String ac) {
        String[] str = new String[37];
        str[0] = "aa";//帧头
        str[1] = "aa";//帧头
        str[2] = "";//控制字
        str[3] = "";//命令字
        str[4] = "";//年
        str[5] = "";//月
        str[6] = "";//日
        str[7] = "";//时
        str[8] = "";//分
        str[9] = "";//秒
        str[10] = "";//操作ID
        str[11] = "";//操作状态
        str[12] = "";//心跳
        str[13] = "";//接收端--产品类型 设备ID前三位
        str[14] = "";//接收端--产品序号 设备ID中间四位
        str[15] = "";//接收端--产品随机码 设备ID后三位
        str[16] = "";//设备IP
        str[17] = "00";//保留
        str[18] = "00";//保留
        str[19] = "00";//保留
        str[20] = "00";//0--市电  1—UPS 电源类型
        str[21] = "0000";//电压
        str[22] = "0000";//电流
        str[23] = "0000";//功率
        str[24] = "00";//继电器状态
        str[25] = "00";//过压状态
        str[26] = "00";//欠压状态
        str[27] = "00";//过流状态
        str[28] = "00";//断路状态
        str[29] = "00";//漏电状态
        str[30] = "0000";//发送端--产品类型 设备ID前三位
        str[31] = "000000";//发送端--产品序号 设备ID中间四位
        str[32] = "0000";//发送端--产品随机码 设备ID后三位
        str[33] = "";//48-60位都是保留字
        str[34] = "";//校验和
        str[35] = "55";//帧尾
        str[36] = "55";//帧尾

        String mes = "aa|aa|b2|a2|12|02|1c|09|0f|12|04 D2|00|11|00 00|01 86 9f|03e5|00000000|00|00|……|和校验位|55|55|";
        //updown 1表示上行0xb1  2表示下行0xb2
        if (updown.equals("1")) {
            str[2] = "b1";
        }
        if (updown.equals("2")) {
            str[2] = "b2";
        }

        //actype
        switch (actype) {
            case 1:
                str[3] = "a1";//1表示 自动对时&网络配置信息上报（0xa1）
                break;
            case 2:
                str[3] = "a2"; //2表示工作状态信息查询（0xa2）
                break;
            case 3:
                str[3] = "a3"; //3表示控制命令下发（0xa3）
                break;
            case 4:
                str[3] = "a4"; //4表示故障告警信息主动上报（0xa4）
                break;
        }

        //获取当前系统时间
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(df.format(System.currentTimeMillis()));
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);

        StringExchange stringExchange = new StringExchange();
        int year2 = Integer.valueOf(String.valueOf(year).substring(2, 4));
        str[4] = Integer.toHexString(year2);
//        System.out.println("str[4]" + str[4]);

        str[5] = addZeroForNum(Integer.toHexString(month), 2);
        str[6] = addZeroForNum(Integer.toHexString(day), 2);
        str[7] = addZeroForNum(Integer.toHexString(hour), 2);
        str[8] = addZeroForNum(Integer.toHexString(minute), 2);
        str[9] = addZeroForNum(Integer.toHexString(second), 2);
//        str[10] = stringExchange.str2HexStr(DateUtil.getWeek());
        str[10] = addZeroForNum(Integer.toHexString(2341), 4);//10-11操作ID
//        System.out.println(str[10]);
        str[11] = "00"; //操作状态
        str[12] = "00"; //心跳

        String yy = "";
        for (int y = 0; y < 13; y++) {
            yy += str[y].toString();
        }

//        String pdutype = stringExchange.str2HexStr(pdu.getType());
//        String machineID = stringExchange.str2HexStr(pdu.getMachineid());
        String pdutype = "0001";
        String machineID = "0015555123";

        str[13] = addZeroForNum(Integer.toHexString(Integer.valueOf(machineID.substring(1, 3))), 4);//产品类型

        str[14] = addZeroForNum(Integer.toHexString(Integer.valueOf(machineID.substring(3, 7))), 6);//16-18产品序号

        str[15] = addZeroForNum(Integer.toHexString(Integer.valueOf(machineID.substring(7, 10))), 4);//19-20产品随机码

//        String[] ips = pdu.getIp().split(".");
        String address = "192.12.0.1";
        String[] ips = address.split("\\.");
        String ip = "";

//        System.out.println("ip1===" + ips[0].toString());
//        System.out.println("ip1===" + Integer.valueOf(ips[0].toString()));
//        System.out.println("ip1===" + addZeroForNum(Integer.toHexString(Integer.valueOf(ips[0].toString())), 2));
        ip += addZeroForNum(Integer.toHexString(Integer.valueOf(ips[0].toString())), 2);
//        System.out.println("ip1====" + ip);

//        System.out.println("ip2===" + addZeroForNum(Integer.toHexString(Integer.valueOf(ips[1].toString())), 2));
        ip += addZeroForNum(Integer.toHexString(Integer.valueOf(ips[1].toString())), 2);
//        System.out.println("ip2====" + ip);

//        System.out.println("ip2===" + addZeroForNum(Integer.toHexString(Integer.valueOf(ips[2].toString())), 2));
        ip += addZeroForNum(Integer.toHexString(Integer.valueOf(ips[2].toString())), 2);
//        System.out.println("ip3====" + ip);

//        System.out.println("ip4===" + addZeroForNum(Integer.toHexString(Integer.valueOf(ips[3].toString())), 2));
        ip += addZeroForNum(Integer.toHexString(Integer.valueOf(ips[3].toString())), 2);
//        System.out.println("ip4====" + ip);

        str[16] = ip;  //ip地址
//        str[16] = stringExchange.str2HexStr(addZeroForNum(ips[0],4));//255.255.255.255  21-27
//        str[23] = stringExchange.str2HexStr(addZeroForNum(ips[1],4));
//        str[25] = stringExchange.str2HexStr(addZeroForNum(ips[2],4));
//        str[27] = stringExchange.str2HexStr(addZeroForNum(ips[3],4));

//        str[29] = stringExchange.str2HexStr("0");//保留
//        str[30] = stringExchange.str2HexStr("0");//保留
//        str[31] = stringExchange.str2HexStr("0");//保留

//        str[32] = stringExchange.str2HexStr("00");//电源类型

        String zz = "";
        for (int z = 0; z < 17; z++) {
            zz += str[z].toString();
//            System.out.println(zz);
        }
//        System.out.println("zz.length==" + zz.length() + "zz.str===" + zz);

        String st = "";
        for (int i = 48; i <= 60; i++) {
            st += "00";
        }
//        System.out.println("st===" + st);
        str[33] = st;

        if (ac.equals("1")) { //1表示控制继电器操作，如果ac不等于1表示进行的是查询状态操作
//            if(pdu.getActionState().equals("1")){
//                str[24] = addZeroForNum(stringExchange.str2HexStr("2"),2);//继电器控制关闭
//            }
//            if(pdu.getActionState().equals("2")){
//                str[24] = addZeroForNum(stringExchange.str2HexStr("1"),2);//继电器控制开启
//            }
            str[24] = "01";

        }

        String date = ""; //校验和
        for (int s = 0; s < 34; s++) {
            date += str[s].toString();
//            System.out.println(date);
        }
//        System.out.println(date);
        str[34] = makeChecksum(date);

        mes = date + str[34] + str[35] + str[36];
//        System.out.println("发送报文内容为：" + mes);
        return mes;
    }

    private static String toHexString(String str, int start, int end) {
        return Long.valueOf(str.substring(start, end), 16).toString();
    }

    /**
     * @Author:xulei
     * @Description:解析回传的报文
     * @Date:2019-02-28
     */
//    public static String actionMessage(String result,String messageID,Pdu pdu) {
    public static String actionMessage(String messageID) {
        String res = "aaaab2a3120205051b2e0925000000010015b3007bc00c0001000000000000000000000100000000000000000000000000000000000000000000000000505555";
        String[] str = new String[37];
        Byte ii = 123;
        int s = ii.intValue();
//        System.out.println(s);
//        System.out.println(new Byte("12").intValue());
//        System.out.println(Integer.valueOf(res.substring(0, 2).toString(), 16));

        String actiontype = null;
        try {
            str[0] = toHexString(res, 0, 2);//帧头
            str[1] = toHexString(res, 2, 4);//帧头
            str[2] = toHexString(res, 4, 6);//控制字
            str[3] = toHexString(res, 6, 8);//命令字
            str[4] = toHexString(res, 8, 10);//年
            str[5] = toHexString(res, 10, 12);//月
            str[6] = toHexString(res, 12, 14);//日
            str[7] = toHexString(res, 14, 16);//时
            str[8] = toHexString(res, 16, 18);//分
            str[9] = toHexString(res, 18, 20);//秒
            str[10] = toHexString(res, 20, 24);//操作ID
            str[11] = toHexString(res, 24, 26);//操作状态
            str[12] = toHexString(res, 26, 28);//心跳
            str[13] = toHexString(res, 28, 32);//接收端--产品类型 设备ID前三位
            str[14] = toHexString(res, 32, 38);//接收端--产品序号 设备ID中间四位
            str[15] = toHexString(res, 38, 42);//接收端--产品随机码 设备ID后三位
            str[16] = toHexString(res, 42, 44) + "."
                    + toHexString(res, 44, 46) + "."
                    + toHexString(res, 46, 48) + "."
                    + toHexString(res, 48, 50);//设备IP
            str[17] = toHexString(res, 50, 52);//保留
            str[18] = toHexString(res, 52, 54);//保留
            str[19] = toHexString(res, 54, 56);//保留
            str[20] = toHexString(res, 56, 58);//0--市电  1—UPS 电源类型
            str[21] = toHexString(res, 58, 62);//电压
            str[22] = toHexString(res, 62, 66);//电流
            str[23] = toHexString(res, 66, 70);//功率
            str[24] = toHexString(res, 70, 72);//继电器状态
            str[25] = toHexString(res, 72, 74);//过压状态
            str[26] = toHexString(res, 74, 76);//欠压状态
            str[27] = toHexString(res, 76, 78);//过流状态
            str[28] = toHexString(res, 78, 80);//断路状态
            str[29] = toHexString(res, 80, 82);//漏电状态
            str[30] = toHexString(res, 82, 88);//发送端--产品类型 设备ID前三位
            str[31] = toHexString(res, 88, 94);//发送端--产品序号 设备ID中间四位
            str[32] = toHexString(res, 94, 98);//发送端--产品随机码 设备ID后三位
            str[33] = toHexString(res, 98, 122);//48-60位都是保留字
            str[34] = toHexString(res, 122, 124);//校验和
            str[35] = toHexString(res, 124, 126);//帧尾
            str[36] = toHexString(res, 126, 128);//帧尾

            String acstate = "";//操作状态
            String mechineID = ""; //设备ID
            String ip = "";
            actiontype = "";

            for (int rss = 0; rss <= 36; rss++) {
//                System.out.println(str[rss]);
            }
//            System.out.println("str[10]==" + str[10].toString());

            //根据操作类型封装相应的返回值，用map返回
            //str[3] = "a1";//1表示 自动对时&网络配置信息上报（0xa1）
            Map map = new HashMap<String, String>();
            String actype = Long.toHexString(Integer.valueOf(str[3]));
            if (actype.equals("a1")) {//a1表示 自动对时&网络配置信息上报
                //获取machineID、IP地址、设备类型
                map.put("machineid", str[13] + str[14] + str[15]);
                map.put("ip", str[16]);
                map.put("type", str[13]);

            }
            if (actype.equals("a2")) {//a2表示工作状态信息查询
                //获取machineID、IP地址、设备类型
                map.put("machineid", str[13] + str[14] + str[15]);
                map.put("ip", str[16]);
                map.put("type", str[13]);

            }
            if (actype.equals("a2")) {//a3表示控制命令下发
                //获取machineID、IP地址、设备类型
                map.put("machineid", str[13] + str[14] + str[15]);
                map.put("ip", str[16]);
                map.put("type", str[13]);

            }
            if (actype.equals("a2")) {//a4表示故障告警信息主动上报
                //获取machineID、IP地址、设备类型
                map.put("machineid", str[13] + str[14] + str[15]);
                map.put("ip", str[16]);
                map.put("type", str[13]);

            }


            //判断操作ID是否一致
            if (messageID.equals(str[10])) {
                acstate = str[11].toString();
                mechineID = str[13].toString() + str[14].toString() + str[15].toString();
                ip = str[16].toString();

                //判断设备ID、Ip地址是否一致 以及操作是否成功
                //            if(acstate.equals("1") && mechineID.equals(pdu.getMachineid()) && ip.equals(pdu.getIp())){
                //                actiontype = "1";
                //            }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return actiontype;
    }


    /**
     * @Author:xulei
     * @Description: 接收的hex报文字符串解析
     * @Date: 2018-03-19
     */
    public static Message receiveMessage(String msg) {
        Message message = new Message();

        String actiontype = null;
        try {
            message.setHead1(toHexString(msg, 0, 2));//帧头
//            message.setHead2(toHexString(msg, 2, 4));//帧头
            message.setControlType(toHexString(msg, 4, 6)); //控制字
            message.setCommand(toHexString(msg, 6, 8));//命令字
            message.setYear(toHexString(msg, 8, 10));//年
            message.setMonth(toHexString(msg, 10, 12));//月
            message.setDay(toHexString(msg, 12, 14));//日
            message.setHour(toHexString(msg, 14, 16));//时
            message.setMinute(toHexString(msg, 16, 18));//分
            message.setSecond(toHexString(msg, 18, 20)); //秒
            message.setMessageID(toHexString(msg, 20, 24));//操作ID
            message.setActionType(toHexString(msg, 24, 26));//操作状态
            message.setOnlineState(toHexString(msg, 26, 28));//心跳
            message.setReceivePduType(toHexString(msg, 28, 32));//接收端--产品类型 设备ID前三位
            message.setReceiveRandomID(toHexString(msg, 32, 38));//接收端--产品序号 设备ID中间四位
            message.setReceiveRandomID(toHexString(msg, 38, 42));//接收端--产品随机码 设备ID后三位
            message.setIp(toHexString(msg, 42, 44) + "."
                    + toHexString(msg, 44, 46) + "."
                    + toHexString(msg, 46, 48) + "."
                    + toHexString(msg, 48, 50));//设备IP
            message.setReservedWord1(toHexString(msg, 50, 52));//保留
            message.setReservedWord2(toHexString(msg, 52, 54));//保留
            message.setReservedWord3(toHexString(msg, 54, 56));//保留
            message.setPowerType(toHexString(msg, 56, 58));//0--市电  1—UPS 电源类型
            message.setVoltage(toHexString(msg, 58, 62));//电压
            message.setCurrent(toHexString(msg, 62, 66));//电流
            message.setWatt(toHexString(msg, 66, 70));//功率
            message.setRelayState(toHexString(msg, 70, 72));//继电器状态
            message.setOvervoltage(toHexString(msg, 72, 74));//过压状态
            message.setUndervoltage(toHexString(msg, 74, 76));//欠压状态
            message.setOvercurrent(toHexString(msg, 76, 78));//过流状态
            message.setCircuitbreaker(toHexString(msg, 78, 80));//断路状态
            message.setElectricleakage(toHexString(msg, 80, 82)); //漏电状态
            message.setSendPduType(toHexString(msg, 82, 88));//发送端--产品类型 设备ID前三位
            message.setSendMachineID(toHexString(msg, 88, 94));//发送端--产品序号 设备ID中间四位
            message.setSendRandomID(toHexString(msg, 94, 98));//发送端--产品随机码 设备ID后三位
            message.setPduState(toHexString(msg, 98, 100));//设备是否已添加；
            message.setTemperature(toHexString(msg, 100, 104));//温度；
            message.setSmoke(toHexString(msg, 104, 106));//烟雾；
            message.setReservedWord4(toHexString(msg, 100, 122)); //49-60位都是保留字
            message.setNum(toHexString(msg, 122, 124)); //校验和
            message.setEnd1(toHexString(msg, 124, 126)); //帧尾
//            message.setEnd2(toHexString(msg, 126, 128)); //帧尾

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return message;
    }


    /**
     * @Author:xulei
     * @Description: 接收的hex报文字符串解析--设备上线连接报文
     * @Date: 2018-03-19
     */
    public static Message receiveMessage1(String msg) {

        Message message = new Message();
        String actiontype = null;
        String at = msg.substring(0, 8);
        if (at.equals("41540D0A")) {
            msg = msg.substring(8, msg.length());
        }
        int msgword = msg.length();
        if (!msg.equals("")) {


            try {

                message.setHead1(msg.substring(0, 2));
                message.setControlType(msg.substring(2, 4));//数据流向
                message.setCommand(msg.substring(4, 6));//命令类型

                //获取年月日时分秒
                String date = hexString2binaryString(msg.substring(6, 16));
//                System.out.println(date);

                String year = date.substring(0, 8);
                String month = date.substring(8, 12);
                String day = date.substring(12, 17);
                String hour = date.substring(17, 24);
                String minute = date.substring(24, 32);
                String second = date.substring(32, 40);

                year = Integer.valueOf(year, 2).toString();
                month = Integer.valueOf(month, 2).toString();
                day = Integer.valueOf(day, 2).toString();
                hour = Integer.valueOf(hour, 2).toString();
                minute = Integer.valueOf(minute, 2).toString();
                second = Integer.valueOf(second, 2).toString();

//                System.out.println("解析报文时间：" + year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second);

                String controlType = message.getControlType(); //上传还是下行
                String command = message.getCommand();//命令类型
                if (controlType.equals("B1") && command.equals("A1")) {//判断是设备加电上线
//                    System.out.println("报文类型为B1A1类型报文--注册、对时");
                    String pdudetail = addZeroForNum(hexString2binaryString(msg.substring(16, 26)), 40);//十六进制转二进制
//                System.out.println(pdudetail);

                    String receivePduType = pdudetail.substring(0, 10);
                    String receiveMachineID = pdudetail.substring(10, 30);
                    String receiveRandomID = pdudetail.substring(30, 40);

                    receivePduType = addZeroForNum(Integer.valueOf(receivePduType, 2).toString(), 3);
                    receiveMachineID = addZeroForNum(Integer.valueOf(receiveMachineID, 2).toString(), 5);
                    receiveRandomID = addZeroForNum(Integer.valueOf(receiveRandomID, 2).toString(), 3);

                    receiveMachineID = receivePduType + receiveMachineID + receiveRandomID;
//                    System.out.println("receiveMachineID==" + receiveMachineID);

                    message.setReceivePduType(receivePduType);
                    message.setReceiveMachineID(receiveMachineID);
                    message.setReceiveRandomID(receiveRandomID);

//                    System.out.println("设备编号：" + receivePduType + receiveMachineID + receiveRandomID);


                    String ip1 = msg.substring(26, 28);
                    String ip2 = msg.substring(28, 30);
                    String ip3 = msg.substring(30, 32);
                    String ip4 = msg.substring(32, 34);

                    message.setIp(toHexString(msg, 26, 28) + "."
                            + toHexString(msg, 28, 30) + "."
                            + toHexString(msg, 30, 32) + "."
                            + toHexString(msg, 32, 34));//设备IP

//                    System.out.println("设备IP地址：" + message.getIp());

                    message.setNum(msg.substring(34, 36));
//                System.out.println("setNum==-=="+msg.substring(34, 36));
                    message.setEnd1(msg.substring(36, 38));
//                System.out.println("msgend==-=="+msg.substring(38,42));
                    String end = msg.substring(38, 42);
                    if (!end.equals("0d0a") && !end.equals("0D0A")) {
                        System.out.println("报文错误，请检查报文是否正确");
                    } else {
                        System.out.println("B1A1类型报文解析成功");
                    }
                }

                if (controlType.equals("B1") && command.equals("A4") && msgword == 62) {//判断是预警信息上报
                    System.out.println("上报预警信息" + msg);
                    String pdudetail = hexString2binaryString(msg.substring(16, 26));

                    String receivePduType = pdudetail.substring(0, 10);
                    String receiveMachineID = pdudetail.substring(10, 30);
                    String receiveRandomID = pdudetail.substring(30, 40);

                    receivePduType = addZeroForNum(Integer.valueOf(receivePduType, 2).toString(), 3);
                    receiveMachineID = addZeroForNum(Integer.valueOf(receiveMachineID, 2).toString(), 5);
                    receiveRandomID = addZeroForNum(Integer.valueOf(receiveRandomID, 2).toString(), 3);

                    receiveMachineID = receivePduType + receiveMachineID + receiveRandomID;
//                    System.out.println("receiveMachineID==" + receiveMachineID);

                    message.setReceivePduType(receivePduType);
                    message.setReceiveMachineID(receiveMachineID);
                    message.setReceiveRandomID(receiveRandomID);

//                    System.out.println("receivePduType===" + receivePduType);
//                    System.out.println("receiveMachineID===" + receiveMachineID);
//                    System.out.println("receiveRandomID===" + receiveRandomID);

                    String ip1 = msg.substring(26, 28);
                    String ip2 = msg.substring(28, 30);
                    String ip3 = msg.substring(30, 32);
                    String ip4 = msg.substring(32, 34);

                    message.setIp(toHexString(msg, 26, 28) + "."
                            + toHexString(msg, 28, 30) + "."
                            + toHexString(msg, 30, 32) + "."
                            + toHexString(msg, 32, 34));//设备IP

                    //解析发送端设备类型、编号、随机数
                    String pdudetail2 = hexString2binaryString(msg.substring(34, 44));

                    String sendPduType = pdudetail2.substring(0, 10);
                    String sendMachineID = pdudetail2.substring(10, 30);
                    String sendRandomID = pdudetail2.substring(30, 40);

                    sendPduType = addZeroForNum(Integer.valueOf(sendPduType, 2).toString(), 3);
                    sendMachineID = addZeroForNum(Integer.valueOf(sendMachineID, 2).toString(), 5);
                    sendRandomID = addZeroForNum(Integer.valueOf(sendRandomID, 2).toString(), 3);

                    sendMachineID = sendPduType + sendMachineID + sendRandomID;

                    message.setSendPduType(sendPduType);
                    message.setSendMachineID(sendMachineID);
                    message.setSendRandomID(sendRandomID);

                    String pdudetai3 = hexString2binaryString(msg.substring(44, 48));

                    String relayState = pdudetai3.substring(0, 1);//继电器状态
                    String overvoltage = pdudetai3.substring(1, 2);//是否过压
                    String undervoltage = pdudetai3.substring(2, 3);//是否欠压
                    String overcurrent = pdudetai3.substring(3, 4);//是否过流
                    String circuitbreaker = pdudetai3.substring(4, 5);//是否断路
                    String electricleakage = pdudetai3.substring(5, 6);//是否漏电
                    String temperature = pdudetai3.substring(6, 7);//温度异常
                    String smoke = pdudetai3.substring(7, 8);//烟雾异常
                    String waterLevel = pdudetai3.substring(8, 16);//液位异常

                    relayState = Integer.valueOf(relayState, 2).toString();
                    overvoltage = Integer.valueOf(overvoltage, 2).toString();
                    undervoltage = Integer.valueOf(undervoltage, 2).toString();
                    overcurrent = Integer.valueOf(overcurrent, 2).toString();
                    circuitbreaker = Integer.valueOf(circuitbreaker, 2).toString();
                    electricleakage = Integer.valueOf(electricleakage, 2).toString();
                    temperature = Integer.valueOf(temperature, 2).toString();
                    smoke = Integer.valueOf(smoke, 2).toString();
                    waterLevel = Integer.valueOf(waterLevel, 2).toString();


                    message.setRelayState(relayState);
                    message.setOvervoltage(overvoltage);
                    message.setUndervoltage(undervoltage);
                    message.setOvercurrent(overcurrent);
                    message.setCircuitbreaker(circuitbreaker);
                    message.setElectricleakage(electricleakage);
                    message.setTemperature(temperature);
                    message.setSmoke(smoke);
                    message.setWaterLevel(waterLevel);


//                String pdudetail4 = hexString2binaryString(msg.substring(34, 42));
//                System.out.println(pdudetail4);
//
//                String temperature = pdudetail4.substring(0, 1);
//                String smoke = pdudetail4.substring(1, 2);
//                String waterLevel = pdudetail4.substring(2);
//
//                temperature = Integer.valueOf(temperature, 2).toString();
//                smoke = Integer.valueOf(smoke, 2).toString();
//                waterLevel = Integer.valueOf(waterLevel, 2).toString();
//
//                message.setTemperature(temperature);
//                message.setMonth(smoke);
//                message.setWaterLevel(waterLevel);

                    message.setMessageID(msg.substring(48, 52));

                    String pdudetail4 = hexString2binaryString(msg.substring(52, 54));
                    String actionType = pdudetail4.substring(0, 1);
                    String pduState = pdudetail4.substring(1, 8);

                    message.setActionType(actionType);
                    message.setPduState(pduState);

                    message.setNum(msg.substring(54, 56));
                    message.setEnd1(msg.substring(56, 58));

                    if (!msg.substring(58, 62).equals("0d0a") && !msg.substring(58, 62).equals("0D0A")) {
                        System.out.println("报文错误，请检查报文是否正确");
                    }
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        return message;
    }


    /**
     * @Author:xulei
     * @Description:设备信息采集报文解析
     * @Date:2018-03-27
     */
    public static Message receiveMessage2(String msg) {
        System.out.println("开始解析设备信息采集报文......");
        Message message = new Message();
        int msgnum = msg.length();
        String actiontype = null;

        try {

            message.setHead1(msg.substring(0, 2));
            message.setControlType(msg.substring(2, 4));
            message.setCommand(msg.substring(4, 6));

            //获取年月日时分秒
            String date = hexString2binaryString(msg.substring(6, 16));

            String year = date.substring(0, 8);
            String month = date.substring(8, 12);
            String day = date.substring(12, 17);
            String hour = date.substring(17, 24);
            String minute = date.substring(24, 32);
            String second = date.substring(32, 40);

            year = Integer.valueOf(year, 2).toString();
            month = Integer.valueOf(month, 2).toString();
            day = Integer.valueOf(day, 2).toString();
            hour = Integer.valueOf(hour, 2).toString();
            minute = Integer.valueOf(minute, 2).toString();
            second = Integer.valueOf(second, 2).toString();

//            System.out.println("设备信息采集时间：" + year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second);

            String controlType = message.getControlType(); //上传还是下行
            String command = message.getCommand();//命令类型
            if (controlType.equals("B1") && command.equals("A2")) {//判断是设备信息采集

                String pdudetail = hexString2binaryString(msg.substring(16, 26));

                String receivePduType = pdudetail.substring(0, 10);
                String receiveMachineID = pdudetail.substring(10, 30);
                String receiveRandomID = pdudetail.substring(30, 40);

                receivePduType = addZeroForNum(Integer.valueOf(receivePduType, 2).toString(), 3);
                receiveMachineID = addZeroForNum(Integer.valueOf(receiveMachineID, 2).toString(), 5);
                receiveRandomID = addZeroForNum(Integer.valueOf(receiveRandomID, 2).toString(), 3);

                receiveMachineID = receivePduType + receiveMachineID + receiveRandomID;

                message.setReceivePduType(receivePduType);
                message.setReceiveMachineID(receiveMachineID);
                message.setReceiveRandomID(receiveRandomID);


                String ip1 = msg.substring(26, 28);
                String ip2 = msg.substring(28, 30);
                String ip3 = msg.substring(30, 32);
                String ip4 = msg.substring(32, 34);

                message.setIp(toHexString(msg, 26, 28) + "."
                        + toHexString(msg, 28, 30) + "."
                        + toHexString(msg, 30, 32) + "."
                        + toHexString(msg, 32, 34));//设备IP

                //解析发送端设备类型、编号、随机数
                String pdudetail2 = hexString2binaryString(msg.substring(34, 44));

                String sendPduType = pdudetail2.substring(0, 10);
                String sendMachineID = pdudetail2.substring(10, 30);
                String sendRandomID = pdudetail2.substring(30, 40);

                sendPduType = addZeroForNum(Integer.valueOf(sendPduType, 2).toString(), 3);
                sendMachineID = addZeroForNum(Integer.valueOf(sendMachineID, 2).toString(), 5);
                sendRandomID = addZeroForNum(Integer.valueOf(sendRandomID, 2).toString(), 3);

                sendMachineID = sendPduType + sendMachineID + sendRandomID;

                message.setSendPduType(sendPduType);
                message.setSendMachineID(sendMachineID);
                message.setSendRandomID(sendRandomID);

                //解析设备电源类型、实时电压、实时电流、实时功率
                String pdudetail3 = hexString2binaryString(msg.substring(44, 56));

                String powerType = pdudetail3.substring(0, 2);
                String voltage = pdudetail3.substring(2, 16);
                String current = pdudetail3.substring(16, 26);
                String watt = pdudetail3.substring(26, 40);
                String relayState = pdudetail3.substring(40, 41);

                powerType = Integer.valueOf(powerType, 2).toString();
                voltage = Integer.valueOf(voltage, 2).toString();
                current = Integer.valueOf(current, 2).toString();
                watt = Integer.valueOf(watt, 2).toString();
                relayState = Integer.valueOf(relayState, 2).toString();


                float voltageF = Float.parseFloat(voltage);
                voltageF = (float) Math.round(voltageF) / 10;
//                DecimalFormat dfVoltage = new DecimalFormat("000.0");
//                dfVoltage.setRoundingMode(RoundingMode.HALF_UP);
//                voltage = dfVoltage.format(voltageF);

                float currentF = Float.parseFloat(current);
                currentF = (float) Math.round(currentF) / 100;

                float wattF = Float.parseFloat(watt);
                wattF = (float) Math.round(wattF) / 10;

                message.setPowerType(powerType);//电源类型
                message.setVoltage(String.valueOf(voltageF));//实时电压
                message.setCurrent(String.valueOf(currentF));//实时电流
                message.setWatt(String.valueOf(wattF));//实时功率
                message.setRelayState(relayState);//继电器状态

                long electronictages1 = Long.valueOf(toHexString(msg, 56, 58)) - 14;
                long electronictages2 = Long.valueOf(toHexString(msg, 58, 60)) - 14;
                long electronictages3 = Long.valueOf(toHexString(msg, 60, 62)) - 14;
                long electronictages4 = Long.valueOf(toHexString(msg, 62, 64)) - 14;
                long electronictages5 = Long.valueOf(toHexString(msg, 64, 66)) - 14;
                long electronictages6 = Long.valueOf(toHexString(msg, 66, 68)) - 14;

                String fileName = "/config.properties";

                String elect1 = PropertiesUtils.loaderGetValues(fileName, "pdu.electronictages." + String.valueOf(electronictages1));
                String elect2 = PropertiesUtils.loaderGetValues(fileName, "pdu.electronictages." + String.valueOf(electronictages2));
                String elect3 = PropertiesUtils.loaderGetValues(fileName, "pdu.electronictages." + String.valueOf(electronictages3));
                String elect4 = PropertiesUtils.loaderGetValues(fileName, "pdu.electronictages." + String.valueOf(electronictages4));
                String elect5 = PropertiesUtils.loaderGetValues(fileName, "pdu.electronictages." + String.valueOf(electronictages5));
                String elect6 = PropertiesUtils.loaderGetValues(fileName, "pdu.electronictages." + String.valueOf(electronictages6));


//                elect1 = URLEncoder.encode(elect1, "UTF-8");

                message.setElectronictages1(elect1);
                message.setElectronictages2(elect2);
                message.setElectronictages3(elect3);
                message.setElectronictages4(elect4);
                message.setElectronictages5(elect5);
                message.setElectronictages6(elect6);

                message.setMessageID(msg.substring(68, 72));

                String pdudetail4 = hexString2binaryString(msg.substring(72, 74));
                String actionType = pdudetail4.substring(0, 1);
                String pduState = pdudetail4.substring(1, 8);

                message.setActionType(actionType);
                message.setPduState(pduState);

                message.setNum(msg.substring(74, 76));
                message.setEnd1(msg.substring(76, 78));

                if (!msg.substring(78, 82).equals("0d0a") && !msg.substring(78, 82).equals("0D0A")) {
                    System.out.println("报文错误，请检查报文是否正确");
                } else {
                    System.out.println("报文解析完成......");
                }

            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return message;
    }

    /**
     * @Author:xulei
     * @Description: 设备对时报文组合
     * @Date: 2018-03-26
     */
    public static String sendMessage1(Message message) {
        System.out.println("开始设备对时报文组合......");
//        String ss = "aab1a107e203f97efb00101A80C0A801702355";
        String sendStr = ""; //发送的报文字符串

        String[] str = new String[8];
        str[0] = message.getHead1(); //帧头
        str[1] = message.getControlType(); //控制字
        str[2] = message.getCommand(); //命令字
        str[3] = ""; //时间 年月日 时分秒
        str[4] = ""; //产品信息、类型、ID、随机数
        str[5] = message.getIp();//设备IP
        str[6] = "";//校验和
        str[7] = message.getEnd1();//帧尾

        //1表示上行0xb1  2表示下行0xb2
        if (str[1].equals("1")) {
            str[1] = "b1";
        }
        if (str[1].equals("2")) {
            str[1] = "b2";
        }

        //actype
        switch (Integer.valueOf(str[2].toString())) {
            case 1:
                str[2] = "a1";//1表示 自动对时&网络配置信息上报（0xa1）
                break;
            case 2:
                str[2] = "a2"; //2表示工作状态信息查询（0xa2）
                break;
            case 3:
                str[2] = "a3"; //3表示控制命令下发（0xa3）
                break;
            case 4:
                str[2] = "a4"; //4表示故障告警信息主动上报（0xa4）
                break;
        }

//        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
//        int year = c.get(Calendar.YEAR);
//        int month = c.get(Calendar.MONTH);
//        int day = c.get(Calendar.DATE);
//        int hour = c.get(Calendar.HOUR);
//        int minute = c.get(Calendar.MINUTE);
//        int second = c.get(Calendar.SECOND);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = df.format(System.currentTimeMillis());
        int y = Integer.parseInt("33", 16);

        int year = Integer.valueOf(datetime.substring(2, 4));
        int month = Integer.valueOf(datetime.substring(5, 7));
        int day = Integer.valueOf(datetime.substring(8, 10));
        int hour = Integer.valueOf(datetime.substring(11, 13));
        int minute = Integer.valueOf(datetime.substring(14, 16));
        int second = Integer.valueOf(datetime.substring(17, 19));

//	        StringExchange stringExchange = new StringExchange();
//        int year1 = Integer.valueOf(String.valueOf(year).substring(0, 2));
//        int year2 = Integer.valueOf(String.valueOf(year).substring(2, 4));

//	        int  yearBin = Integer.toBinaryString(year1);
//	        int s = Integer.valueOf(yearBin,4);
        str[3] = addZeroForNum(Integer.toHexString(year), 2);

        String monthBin = addZeroForNum(Integer.toBinaryString(month), 4);
        String dayBin = addZeroForNum(Integer.toBinaryString(day), 5);
        String hourBin = addZeroForNum(Integer.toBinaryString(hour), 7);
        String minuteBin = addZeroForNum(Integer.toBinaryString(minute), 8);
        String secondBin = addZeroForNum(Integer.toBinaryString(second), 8);

        String hexday1 = monthBin + dayBin.substring(0, 4);
        hexday1 = b2h(hexday1);

        String hexday2 = dayBin.substring(4, 5) + hourBin;
        hexday2 = b2h(hexday2);


        String hexday3 = b2h(minuteBin);
        String hexday4 = b2h(secondBin);


        String time = hexday1 + hexday2 + hexday3 + hexday4;

        str[3] = str[3] + addZeroForNum(time, 8);

        String receivePduType = addZeroForNum(Integer.toBinaryString(Integer.valueOf(message.getReceivePduType())), 10);
        String receiveMachineID = addZeroForNum(Integer.toBinaryString(Integer.valueOf(message.getReceiveMachineID())), 20);
        String receiveRandomID = addZeroForNum(Integer.toBinaryString(Integer.valueOf(message.getReceiveRandomID())), 10);

        String pdudetail0 = receivePduType.substring(0, 8);
        pdudetail0 = b2h(pdudetail0);

        String pdudetail1 = receivePduType.substring(8, 10) + receiveMachineID.substring(0, 6);
        pdudetail1 = b2h(pdudetail1);

        String pdudetail2 = receiveMachineID.substring(6, 14);
        pdudetail2 = b2h(pdudetail2);


        String pdudetail3 = receiveMachineID.substring(14, 20) + receiveRandomID.substring(0, 2);
        pdudetail3 = b2h(pdudetail3);

        String pdudetail4 = receiveRandomID.substring(2, 10);
        pdudetail4 = b2h(pdudetail4);


        String pdudetail = pdudetail1 + pdudetail2 + pdudetail3 + pdudetail4;

        str[4] = addZeroForNum(pdudetail, 10);

        String[] ips = str[5].split("\\.");
        String ip = "";

        ip += addZeroForNum(Integer.toHexString(Integer.valueOf(ips[0].toString())), 2);
        ip += addZeroForNum(Integer.toHexString(Integer.valueOf(ips[1].toString())), 2);
        ip += addZeroForNum(Integer.toHexString(Integer.valueOf(ips[2].toString())), 2);
        ip += addZeroForNum(Integer.toHexString(Integer.valueOf(ips[3].toString())), 2);

        str[5] = ip;

        String date = ""; //求校验和
        for (int s = 1; s < 6; s++) {
            date += str[s].toString();
        }

        str[6] = makeChecksum(date);

        sendStr = str[0] + date + str[6] + str[7] + "0d0a";
        System.out.println("发送对时报文内容为：" + sendStr);

        if (sendStr.length() == 42) {
            System.out.println("发送对时报文长度校验正确,报文长度为：" + sendStr.length());
        } else {
            System.out.println("发送对时报文长度错误，报文长度为：" + sendStr.length());
        }

        return sendStr;
    }


    /**
     * @Author:xulei
     * @Description: 继电器开光控制报文组合
     * @Date:2018-03-28
     */
    public static String sendMessage2(Message message) {
        System.out.println("开始设备控制报文组合......");
        String sendStr = ""; //发送的报文字符串

        String[] str = new String[13];
        str[0] = message.getHead1(); //帧头
        str[1] = message.getControlType(); //控制字
        str[2] = message.getCommand(); //命令字
        str[3] = ""; //时间 年月日 时分秒
        str[4] = ""; //产品信息、类型、ID、随机数
        str[5] = message.getIp();//设备IP
        str[6] = "";//发送设备信息
        str[7] = message.getRelayState();//继电器状态
        str[8] = "";//电压功率阀值
        str[9] = "";//操作ID
        str[10] = "";//操作状态、设备添加状态
        str[11] = "";//校验和
        str[12] = "55";//帧尾

        //1表示上行0xb1  2表示下行0xb2
        if (str[1].equals("1")) {
            str[1] = "b1";
        }
        if (str[1].equals("2")) {
            str[1] = "b2";
        }

        //actype
        switch (Integer.valueOf(str[2].toString())) {
            case 1:
                str[2] = "a1";//1表示 自动对时&网络配置信息上报（0xa1）
                break;
            case 2:
                str[2] = "a2"; //2表示工作状态信息查询（0xa2）
                break;
            case 3:
                str[2] = "a3"; //3表示控制命令下发（0xa3）
                break;
            case 4:
                str[2] = "a4"; //4表示故障告警信息主动上报（0xa4）
                break;
        }

        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);

//	        StringExchange stringExchange = new StringExchange();
        int year1 = Integer.valueOf(String.valueOf(year).substring(0, 2));
        int year2 = Integer.valueOf(String.valueOf(year).substring(2, 4));

//	        int  yearBin = Integer.toBinaryString(year1);
//	        int s = Integer.valueOf(yearBin,4);
        str[3] = addZeroForNum(Integer.toHexString(year2), 2);

        String monthBin = addZeroForNum(Integer.toBinaryString(month), 4);
        String dayBin = addZeroForNum(Integer.toBinaryString(day), 5);
        String hourBin = addZeroForNum(Integer.toBinaryString(hour), 7);
        String minuteBin = addZeroForNum(Integer.toBinaryString(minute), 8);
        String secondBin = addZeroForNum(Integer.toBinaryString(second), 8);

        String hexday1 = monthBin + dayBin.substring(0, 4);
//        System.out.println(hexday1);
        hexday1 = b2h(hexday1);
//        System.out.println(hexday1);

        String hexday2 = dayBin.substring(4, 5) + hourBin;
//        System.out.println(hexday2);
        hexday2 = b2h(hexday2);
//        System.out.println(hexday2);

//        System.out.println(minuteBin);
//        System.out.println(secondBin);

        String hexday3 = b2h(minuteBin);
        String hexday4 = b2h(secondBin);
//        System.out.println(hexday3 + hexday4);


        String time = hexday1 + hexday2 + hexday3 + hexday4;
//        System.out.println(time);

        str[3] = str[3] + addZeroForNum(time, 8);
//        System.out.println(str[3]);

        String receivePduType = addZeroForNum(Integer.toBinaryString(Integer.valueOf(message.getReceivePduType())), 10);
        String receiveMachineID = addZeroForNum(Integer.toBinaryString(Integer.valueOf(message.getReceiveMachineID())), 20);
        String receiveRandomID = addZeroForNum(Integer.toBinaryString(Integer.valueOf(message.getReceiveRandomID())), 10);

        String pdudetail0 = receivePduType.substring(0, 8);
//        System.out.println(pdudetail0);
        pdudetail0 = b2h(pdudetail0);
//        System.out.println(pdudetail0);

        String pdudetail1 = receivePduType.substring(8, 10) + receiveMachineID.substring(0, 6);
//        System.out.println(pdudetail1);
        pdudetail1 = b2h(pdudetail1);
//        System.out.println(pdudetail1);

        String pdudetail2 = receiveMachineID.substring(6, 14);
//        System.out.println(pdudetail2);
        pdudetail2 = b2h(pdudetail2);
//        System.out.println(pdudetail2);


        String pdudetail3 = receiveMachineID.substring(14, 20) + receiveRandomID.substring(0, 2);
//        System.out.println(pdudetail3);
        pdudetail3 = b2h(pdudetail3);
//        System.out.println(pdudetail3);

        String pdudetail4 = receiveRandomID.substring(2, 10);
//        System.out.println(pdudetail4);
        pdudetail4 = b2h(pdudetail4);
//        System.out.println(pdudetail4);


        String pdudetail = pdudetail0 + pdudetail1 + pdudetail2 + pdudetail3 + pdudetail4;
//        System.out.println(pdudetail);

        str[4] = addZeroForNum(pdudetail, 10);
//        System.out.println(str[4]);

        String[] ips = str[5].split("\\.");
        String ip = "";

        ip += addZeroForNum(Integer.toHexString(Integer.valueOf(ips[0].toString())), 2);
        ip += addZeroForNum(Integer.toHexString(Integer.valueOf(ips[1].toString())), 2);
        ip += addZeroForNum(Integer.toHexString(Integer.valueOf(ips[2].toString())), 2);
        ip += addZeroForNum(Integer.toHexString(Integer.valueOf(ips[3].toString())), 2);

        str[5] = ip;
//        System.out.println(str[5]);

        str[6] = "0000000000";//发送端的设备信息 类型 ID 主要用于lora

        String relayState = addZeroForNumRight(Integer.toBinaryString(Integer.valueOf(str[7])), 2);//继电器状态
        String settingVoltage = addZeroForNum(Integer.toBinaryString(0), 86);
        relayState = relayState + settingVoltage.substring(0, 6);
        settingVoltage = settingVoltage.substring(6, settingVoltage.length());
        str[7] = b2h(relayState);
        str[8] = b2h(settingVoltage);
//        str[8] = "0000000000000000000";//本方法只设定继电器开关，所以预警阀值设定的数据为0

        str[9] = RandomUtils.generateStringHex(4); //操作ID 随机码

        String actionType = addZeroForNum(Integer.toBinaryString(Integer.valueOf(0)), 2);
        String pduState = addZeroForNum(Integer.toBinaryString(Integer.valueOf(1)), 6);

        str[10] = b2h(actionType + pduState);//操作状态、添加状态

        String date = ""; //校验和
        for (int s = 1; s < 11; s++) {
            date += str[s].toString();
//            System.out.println(date);
        }

        str[11] = makeChecksum(date);
//        System.out.println(str[11]);

        sendStr = str[0] + date + str[11] + str[12] + "0d0a";
        System.out.println("发送设备控制报文内容为：" + sendStr);

        if (sendStr.length() == 80) {
            System.out.println("发送控制报文长度校验正确，报文长度为：" + sendStr.length());
        } else {
            System.out.println("发送控制报文长度错误，报文长度为：" + sendStr.length());
        }

        return sendStr;
    }

    /**
     * @Author:xulei
     * @Description: 设备预警值设置报文组合
     * @Date:2018-03-28
     */
    public static String sendMessage3(Message message) {
        System.out.println("开始组合设备预警信息设置报文组合.......");
        String sendStr = ""; //发送的报文字符串

        String[] str = new String[11];
        str[0] = message.getHead1(); //帧头
        str[1] = message.getControlType(); //控制字
        str[2] = message.getCommand(); //命令字
        str[3] = ""; //时间 年月日 时分秒
        str[4] = ""; //产品信息、类型、ID、随机数
        str[5] = message.getIp();//设备IP
        str[6] = message.getRelayState();//继电器状态
        str[7] = "";//发送设备信息
        str[8] = "";//电压功率阀值
        str[9] = "";//操作ID
        str[10] = "";//操作状态、设备添加状态
        str[11] = "";//校验和
        str[12] = "55";//帧尾

        //1表示上行0xb1  2表示下行0xb2
        if (str[1].equals("1")) {
            str[1] = "b1";
        }
        if (str[1].equals("2")) {
            str[1] = "b2";
        }

        //actype
        switch (Integer.valueOf(str[2])) {
            case 1:
                str[2] = "a1";//1表示 自动对时&网络配置信息上报（0xa1）
                break;
            case 2:
                str[2] = "a2"; //2表示工作状态信息查询（0xa2）
                break;
            case 3:
                str[2] = "a3"; //3表示控制命令下发（0xa3）
                break;
            case 4:
                str[2] = "a4"; //4表示故障告警信息主动上报（0xa4）
                break;
        }

        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);

//	        StringExchange stringExchange = new StringExchange();
        int year1 = Integer.valueOf(String.valueOf(year).substring(0, 2));
        int year2 = Integer.valueOf(String.valueOf(year).substring(2, 4));

//	        int  yearBin = Integer.toBinaryString(year1);
//	        int s = Integer.valueOf(yearBin,4);
        str[3] = addZeroForNum(Integer.toHexString(year2), 2);

        String monthBin = addZeroForNum(Integer.toBinaryString(month), 4);
        String dayBin = addZeroForNum(Integer.toBinaryString(day), 5);
        String hourBin = addZeroForNum(Integer.toBinaryString(hour), 7);
        String minuteBin = addZeroForNum(Integer.toBinaryString(minute), 8);
        String secondBin = addZeroForNum(Integer.toBinaryString(second), 8);

        String hexday1 = monthBin + dayBin.substring(0, 4);
        System.out.println(hexday1);
        hexday1 = b2h(hexday1);
        System.out.println(hexday1);

        String hexday2 = dayBin.substring(4, 5) + hourBin;
        System.out.println(hexday2);
        hexday2 = b2h(hexday2);
        System.out.println(hexday2);

        System.out.println(minuteBin);
        System.out.println(secondBin);

        String hexday3 = b2h(minuteBin);
        String hexday4 = b2h(secondBin);
        System.out.println(hexday3 + hexday4);


        String time = hexday1 + hexday2 + hexday3 + hexday4;
        System.out.println(time);

        str[3] = str[3] + addZeroForNum(time, 8);
        System.out.println(str[3]);

        String receivePduType = addZeroForNum(Integer.toBinaryString(Integer.valueOf(message.getReceivePduType())), 10);
        String receiveMachineID = addZeroForNum(Integer.toBinaryString(Integer.valueOf(message.getReceiveMachineID())), 20);
        String receiveRandomID = addZeroForNum(Integer.toBinaryString(Integer.valueOf(message.getReceiveRandomID())), 10);

        String pdudetail0 = receivePduType.substring(0, 8);
        System.out.println(pdudetail0);
        pdudetail0 = b2h(pdudetail0);
        System.out.println(pdudetail0);

        String pdudetail1 = receivePduType.substring(8, 10) + receiveMachineID.substring(0, 6);
        System.out.println(pdudetail1);
        pdudetail1 = b2h(pdudetail1);
        System.out.println(pdudetail1);

        String pdudetail2 = receiveMachineID.substring(6, 14);
        System.out.println(pdudetail2);
        pdudetail2 = b2h(pdudetail2);
        System.out.println(pdudetail2);


        String pdudetail3 = receiveMachineID.substring(14, 20) + receiveRandomID.substring(0, 2);
        System.out.println(pdudetail3);
        pdudetail3 = b2h(pdudetail3);
        System.out.println(pdudetail3);

        String pdudetail4 = receiveRandomID.substring(2, 10);
        System.out.println(pdudetail4);
        pdudetail4 = b2h(pdudetail4);
        System.out.println(pdudetail4);


        String pdudetail = pdudetail1 + pdudetail2 + pdudetail3 + pdudetail4;
        System.out.println(pdudetail);

        str[4] = addZeroForNum(pdudetail, 8);
        System.out.println(str[4]);

        String[] ips = str[5].split("\\.");
        String ip = "";

        ip += addZeroForNum(Integer.toHexString(Integer.valueOf(ips[0].toString())), 2);
        ip += addZeroForNum(Integer.toHexString(Integer.valueOf(ips[1].toString())), 2);
        ip += addZeroForNum(Integer.toHexString(Integer.valueOf(ips[2].toString())), 2);
        ip += addZeroForNum(Integer.toHexString(Integer.valueOf(ips[3].toString())), 2);

        str[5] = ip;
        System.out.println(str[5]);

        str[6] = addZeroForNum(str[6], 2);

        str[7] = "00000000";//发送端的设备信息 类型 ID 主要用于lora

        // 本方法只设定继电器开关，所以预警阀值设定的数据为0
        String setingVoltage = addZeroForNum(Integer.toHexString(Integer.valueOf(message.getSetingVoltage())), 4);
        String voltageAmplitude = addZeroForNum(Integer.toHexString(Integer.valueOf(message.getVoltageAmplitude())), 2);
        String setingWatt = addZeroForNum(Integer.toHexString(Integer.valueOf(message.getSetingWatt())), 4);
        String wattAmplitude = addZeroForNum(Integer.toHexString(Integer.valueOf(message.getWattAmplitude())), 2);
        String setingCurrent = addZeroForNum(Integer.toHexString(Integer.valueOf(message.getSetingCurrent())), 4);
        String currentAmplitude = addZeroForNum(Integer.toHexString(Integer.valueOf(message.getCurrentAmplitude())), 2);

        str[8] = setingVoltage + voltageAmplitude + setingWatt + wattAmplitude + setingCurrent + currentAmplitude + "0000";//包括闹钟设置保留字0000

        str[9] = RandomUtils.generateStringHex(4); //操作ID 4位16进制随机码

        String actionType = addZeroForNum(Integer.toBinaryString(Integer.valueOf(0)), 2);
        String pduState = addZeroForNum(Integer.toBinaryString(Integer.valueOf(1)), 6);

        str[10] = b2h(actionType + pduState);//操作状态、添加状态

        String date = ""; //校验和
        for (int s = 1; s < 11; s++) {
            date += str[s].toString();
            System.out.println(date);
        }

        str[11] = makeChecksum(date);
        System.out.println(str[11]);

        sendStr = str[0] + date + str[11] + str[12] + "0d0a";
        System.out.println("预警信息设置报文组合成功，内容为：" + sendStr);

        if (sendStr.length() == 80) {
            System.out.println("发送预警信息设置报文长度校验正确，长度为：" + sendStr.length());
        } else {
            System.out.println("发送预警信息设置报文长度错误，报文长度为：" + sendStr.length());
        }

        return sendStr;
    }


    public int binaryToDecimal(int n) {
        int bin = 0;
        for (int i = 31; i >= 0; i--) {
            bin = n >>> i & 1;
        }
        return bin;
    }

    /**
     * @Author:xulei
     * @Description: 发送回复报文
     * @Date: 2018-03-19
     */
    public static String sendMessage(Message message) {

        String mes = "";

        String[] str = new String[40];
        str[0] = message.getHead1();//帧头
//        str[1] = message.getHead2();//帧头
        str[2] = message.getControlType();//控制字
        str[3] = message.getCommand();//命令字
        str[4] = "";//年
        str[5] = "";//月
        str[6] = "";//日
        str[7] = "";//时
        str[8] = "";//分
        str[9] = "";//秒
        str[10] = message.getMessageID();//操作ID
        str[11] = message.getActionType();//操作状态
        str[12] = "";//心跳
        str[13] = message.getReceivePduType();//接收端--产品类型 设备ID前三位
        str[14] = message.getReceiveMachineID();//接收端--产品序号 设备ID中间四位
        str[15] = message.getReceiveRandomID();//接收端--产品随机码 设备ID后三位
        str[16] = message.getIp();//设备IP
        str[17] = message.getReservedWord1();//保留
        str[18] = message.getReservedWord2();
        ;//保留
        str[19] = message.getReservedWord3();
        ;//保留
        str[20] = message.getPowerType();//0--市电  1—UPS 电源类型
        str[21] = message.getVoltage();//电压
        str[22] = message.getCurrent();//电流
        str[23] = message.getWatt();//功率
        str[24] = message.getRelayState();//继电器状态
        str[25] = message.getOvervoltage();//过压状态
        str[26] = message.getUndervoltage();//欠压状态
        str[27] = message.getOvercurrent();//过流状态
        str[28] = message.getCircuitbreaker();//断路状态
        str[29] = message.getElectricleakage();//漏电状态
        str[30] = message.getSendPduType();//发送端--产品类型 设备ID前三位
        str[31] = message.getSendMachineID();//发送端--产品序号 设备ID中间四位
        str[32] = message.getSendRandomID();//发送端--产品随机码 设备ID后三位
        str[33] = message.getPduState();
        str[34] = message.getTemperature();//温度
        str[35] = message.getSmoke();//烟雾
        str[36] = message.getReservedWord4();//52-60位都是保留字
        str[37] = "";//校验和
        str[38] = message.getEnd1();//帧尾
//        str[39] = message.getEnd2();//帧尾


        //1表示上行0xb1  2表示下行0xb2
        if (str[2].equals("1")) {
            str[2] = "b1";
        }
        if (str[2].equals("2")) {
            str[2] = "b2";
        }

        //actype
        switch (Integer.valueOf(str[3])) {
            case 1:
                str[3] = "a1";//1表示 自动对时&网络配置信息上报（0xa1）
                break;
            case 2:
                str[3] = "a2"; //2表示工作状态信息查询（0xa2）
                break;
            case 3:
                str[3] = "a3"; //3表示控制命令下发（0xa3）
                break;
            case 4:
                str[3] = "a4"; //4表示故障告警信息主动上报（0xa4）
                break;
        }
        //获取当前系统时间
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(df.format(System.currentTimeMillis()));
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);

        StringExchange stringExchange = new StringExchange();
        int year2 = Integer.valueOf(String.valueOf(year).substring(2, 4));
        str[4] = Integer.toHexString(year2);

        str[5] = addZeroForNum(Integer.toHexString(month), 2);
        str[6] = addZeroForNum(Integer.toHexString(day), 2);
        str[7] = addZeroForNum(Integer.toHexString(hour), 2);
        str[8] = addZeroForNum(Integer.toHexString(minute), 2);
        str[9] = addZeroForNum(Integer.toHexString(second), 2);

        str[10] = addZeroForNum(Integer.toHexString(Integer.valueOf(str[10])), 4);//操作ID
        str[11] = addZeroForNum(Integer.toHexString(Integer.valueOf(str[11])), 2); //操作状态
        str[12] = "00"; //心跳

//        String yy = "";
//        for (int y = 0; y < 13; y++) {
//            yy += str[y].toString();
//            System.out.println(yy);
//        }
//        System.out.println("yy.length==" + yy.length() + "yy.str===" + yy);

//        String pdutype = "001";
//        String machineID = "0015555123";

        str[13] = addZeroForNum(Integer.toHexString(Integer.valueOf(str[13])), 4);//产品类型
        str[14] = addZeroForNum(Integer.toHexString(Integer.valueOf(str[14])), 6);//16-18产品序号
        str[15] = addZeroForNum(Integer.toHexString(Integer.valueOf(str[15])), 4);//19-20产品随机码

//        String[] ips = pdu.getIp().split(".");
//        String address = "192.12.0.1";
        String[] ips = str[16].split("\\.");
        String ip = "";

        ip += addZeroForNum(Integer.toHexString(Integer.valueOf(ips[0].toString())), 2);
        ip += addZeroForNum(Integer.toHexString(Integer.valueOf(ips[1].toString())), 2);
        ip += addZeroForNum(Integer.toHexString(Integer.valueOf(ips[2].toString())), 2);
        ip += addZeroForNum(Integer.toHexString(Integer.valueOf(ips[3].toString())), 2);
        str[16] = ip;  //ip地址

        str[17] = addZeroForNum(Integer.toHexString(Integer.valueOf(str[17].toString())), 2);//保留字
        str[18] = addZeroForNum(Integer.toHexString(Integer.valueOf(str[17].toString())), 2);//保留字
        str[19] = addZeroForNum(Integer.toHexString(Integer.valueOf(str[17].toString())), 2);//保留字

        str[20] = addZeroForNum(Integer.toHexString(Integer.valueOf(str[20].toString())), 2);//电源类型

        str[21] = addZeroForNum(Integer.toHexString(Integer.valueOf(str[21].toString())), 4);//电压
        str[22] = addZeroForNum(Integer.toHexString(Integer.valueOf(str[22].toString())), 4);//电流
        str[23] = addZeroForNum(Integer.toHexString(Integer.valueOf(str[23].toString())), 4);//功率
        str[24] = addZeroForNum(Integer.toHexString(Integer.valueOf(str[24].toString())), 2);//继电器状态
        str[25] = addZeroForNum(Integer.toHexString(Integer.valueOf(str[25].toString())), 2);//过压状态
        str[26] = addZeroForNum(Integer.toHexString(Integer.valueOf(str[26].toString())), 2);//欠压状态
        str[27] = addZeroForNum(Integer.toHexString(Integer.valueOf(str[27].toString())), 2);//过流状态
        str[28] = addZeroForNum(Integer.toHexString(Integer.valueOf(str[28].toString())), 2);//断路状态
        str[29] = addZeroForNum(Integer.toHexString(Integer.valueOf(str[29].toString())), 2);//漏电状态

        str[30] = addZeroForNum(Integer.toHexString(Integer.valueOf(str[30])), 4);
        str[31] = addZeroForNum(Integer.toHexString(Integer.valueOf(str[31])), 6);
        str[32] = addZeroForNum(Integer.toHexString(Integer.valueOf(str[32])), 4);
        str[33] = addZeroForNum(Integer.toHexString(Integer.valueOf(str[33])), 2);
        str[34] = addZeroForNum(Integer.toHexString(Integer.valueOf(str[34])), 4);
        str[35] = addZeroForNum(Integer.toHexString(Integer.valueOf(str[35])), 2);

        String st = "";
        for (int i = 52; i <= 60; i++) {
            st += "00";
        }
        str[36] = st;

        String date = ""; //校验和
        for (int s = 0; s < 36; s++) {
            date += str[s].toString();
        }

        str[37] = makeChecksum(date);
        System.out.println("校验和==" + str[37]);

        mes = date + str[37] + str[38] + str[39] + "0d0a";
        System.out.println("发送报文内容为：" + mes);
        System.out.println("发送报文长度为：" + mes.length());
        return mes;
    }


    //字符串左边补0
    public static String addZeroForNum(String str, int strLength) {
        int strLen = str.length();
        StringBuffer sb = null;
        while (strLen < strLength) {
            sb = new StringBuffer();
            sb.append("0").append(str);// 左补0
            // sb.append(str).append("0");//右补0
            str = sb.toString();
            strLen = str.length();
        }
        return str;
    }

    //字符串左边补0
    public static String addZeroForNumRight(String str, int strLength) {
        int strLen = str.length();
        StringBuffer sb = null;
        while (strLen < strLength) {
            sb = new StringBuffer();
//            sb.append("0").append(str);// 左补0
            sb.append(str).append("0");//右补0
            str = sb.toString();
            strLen = str.length();
        }
        return str;
    }

    //16进制累加和
    public static String makeChecksum(String data) {
        if (data == null || data.equals("")) {
            return "";
        }
        int total = 0;
        int len = data.length();
        int num = 0;
        while (num < len) {
            String s = data.substring(num, num + 2);
//            System.out.println(s);
            total += Integer.parseInt(s, 16);
            num = num + 2;
        }
        /**
         * 用256求余最大是255，即16进制的FF
         */
        int mod = total % 256;
        String hex = Integer.toHexString(mod);
        len = hex.length();
        // 如果不够校验位的长度，补0,这里用的是两位校验
        if (len < 2) {
            hex = "0" + hex;
        }
        return hex;
    }


    //二进制字符串装16进制
    public static String b2h(String binary) {
        // 这里还可以做些判断，比如传进来的数字是否都是0和1
//        System.out.println(binary);
        int length = binary.length();
        int temp = length % 4;
        // 每四位2进制数字对应一位16进制数字
        // 补足4位
        if (temp != 0) {
            for (int i = 0; i < 4 - temp; i++) {
                binary = "0" + binary;
            }
        }
        // 重新计算长度
        length = binary.length();
        StringBuilder sb = new StringBuilder();
        // 每4个二进制数为一组进行计算
        for (int i = 0; i < length / 4; i++) {
            int num = 0;
            // 将4个二进制数转成整数
            for (int j = i * 4; j < i * 4 + 4; j++) {
                num <<= 1;// 左移
                num |= (binary.charAt(j) - '0');// 或运算
            }
            // 直接找到该整数对应的16进制，这里不用switch来做
            sb.append(hexStr[num]);
            // 这里如果要用switch case来做，大概是这个样子
            // switch(num){
            // case 0:
            // sb.append('0');
            // break;
            // case 1:
            // ...
            // case 15:
            // sb.append('F');
            // break;
            // }
        }
        return sb.toString();
    }


    //十六进制转二进制
    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static byte toByte(char c) {
        byte b = (byte) "0123456789abcdef".indexOf(c);
        return b;
    }


    //十六进制字符串转二进制字符串
    public static String hexString2binaryString(String hexString) {
        if (hexString == null || hexString.length() % 2 != 0)
            return null;
        String bString = "", tmp;
        for (int i = 0; i < hexString.length(); i++) {
            tmp = "0000"
                    + Integer.toBinaryString(Integer.parseInt(hexString
                    .substring(i, i + 1), 16));
            bString += tmp.substring(tmp.length() - 4);
        }
        return bString;
    }


    //十六进制转byte[]
    public static byte[] hexToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }

        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] bytes = new byte[length];
        String hexDigits = "0123456789abcdef";
        for (int i = 0; i < length; i++) {
            int pos = i * 2; // 两个字符对应一个byte
            int h = hexDigits.indexOf(hexChars[pos]) << 4; // 注1
            int l = hexDigits.indexOf(hexChars[pos + 1]); // 注2
            if (h == -1 || l == -1) { // 非16进制字符
                return null;
            }
            bytes[i] = (byte) (h | l);
        }
        return bytes;
    }

    /**
     * @Author:
     * @Description:字符串转十六进制字符串
     * @Date:
     */
    public static String str2HexStr(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
            // sb.append(' ');
        }
        return sb.toString().trim();
    }

    /**
     * @Author:xulei
     * @Description: 字符串转字节数组
     * @Date:
     */
    public static byte[] strToByteArray(String str) {
        if (str == null) {
            return null;
        }
        byte[] byteArray = str.getBytes();
        return byteArray;
    }

    /**
     * 16进制字符串转换为字符串
     *
     * @param s
     * @return
     */
    public static String hexStringToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(
                        s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "gbk");
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }


    /**
     * @Author:xulei
     * @Description:字符串转十六进制字符串
     * @Date:
     */
    public static String stringToHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }


    public static String str2HexStr(byte[] bs) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
//        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
            // sb.append(' ');
        }
        return sb.toString().trim();
    }


    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] b = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
            b[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
                    .digit(s.charAt(i + 1), 16));
        }
        return b;
    }


    /**
     * 转换时间日期格式字串为long型
     *
     * @param time 格式为：yyyy-MM-dd HH:mm:ss的时间日期类型
     */
    public static Long convertTimeToLong(String time) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = sdf.parse(time);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

}
