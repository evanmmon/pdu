package com.chuangkou.pdu.util;

import com.chuangkou.pdu.entity.MessageDLT;
import com.chuangkou.pdu.entity.PduInfoTemp;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.text.SimpleDateFormat;

/**
 * @Author:xulei
 * @Description:645协议报文工具类
 * @Date:Created in 10:36 2018/4/22
 */
public class MessageStringDLTUtils {

    /**
     * @Author:xulei
     * @Description:主站读报文组合、写报文组合
     * @Date: 2018-05-02
     */
    public static String messageToHex(MessageDLT message, String action) {

        String sendmes = "";

        try {
            String machineAddress = message.getMachineAddress();
            machineAddress = addZeroForNumLeft(machineAddress, 12);
            machineAddress = machineAddressHex(machineAddress);

            if (action.equals("read")) {//主站读数据
                MessageDLT messageBean = new MessageDLT();

                String dataStr = dateIDhex(message.getDataStr());
                String dateLong = addZeroForNumLeft(String.valueOf(dataStr.length() / 2), 2);
                String control = "11";


                messageBean.setStartFrame("68");
                messageBean.setMachineAddress(machineAddress);
                messageBean.setStartFrame2("68");
                messageBean.setControl(control);
                messageBean.setDataLong(dateLong);
                messageBean.setDataStr(dataStr);

                String datesum = makeChecksum("68" + machineAddress + "68" + control + dateLong + dataStr);
                messageBean.setDatasum(datesum);
                messageBean.setEndStr("16");

                sendmes = messageBean.getStartFrame() + messageBean.getMachineAddress() +
                        messageBean.getStartFrame2() + messageBean.getControl() + messageBean.getDataLong() +
                        messageBean.getDataStr() + messageBean.getDatasum() + messageBean.getEndStr();
            }

            if (action.equals("write")) {//主站写数据
                MessageDLT messageBean = new MessageDLT();
                String dataTab = dateIDhex(message.getDataTab());
                String dataStr = dateIDhex(message.getDataStr());

                messageBean.setStartFrame("68");
                messageBean.setMachineAddress(machineAddress);
                messageBean.setStartFrame2("68");
                messageBean.setControl(message.getControl());

                String data = message.getDataTab() + message.getPassword() + message.getAuth() + message.getDataStr();
                //数据长度需要转成16进制
                String dateLong = addZeroForNumLeft(String.valueOf(Integer.toHexString(data.length() / 2)), 2);

                messageBean.setDataLong(dateLong);
                messageBean.setDataTab(dataTab);
                messageBean.setPassword(message.getPassword());
                messageBean.setAuth(message.getAuth());
                messageBean.setDataStr(dataStr);

                String datesum = makeChecksum("68" + machineAddress + "68" + messageBean.getControl() + dateLong
                        + messageBean.getDataTab() + messageBean.getPassword() + messageBean.getAuth() + messageBean.getDataStr());

                messageBean.setDatasum(datesum);
                messageBean.setEndStr("16");

                sendmes = messageBean.getStartFrame() + messageBean.getMachineAddress() +
                        messageBean.getStartFrame2() + messageBean.getControl() + messageBean.getDataLong() +
                        messageBean.getDataTab() + messageBean.getPassword() + messageBean.getAuth() + messageBean.getDataStr() +
                        messageBean.getDatasum() + messageBean.getEndStr();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println("sendmess==" + sendmes);
        return sendmes;
    }


    /**
     * @param :旧设备地址、旧数据标、回复数据
     * @return :回复报文
     * @Author:xulei
     * @Description:645下位机上发的报文，进行回复
     * @Date: 208-05-02
     */
    public static String receiveMessageToHex(MessageDLT message) {

        String receiveMsg = "";

        MessageDLT messageBean = new MessageDLT();

        String machineAddress = message.getMachineAddress();//旧地址
        machineAddress = machineAddressHex(machineAddress);
        String dataTab = message.getDataTab();//旧数据标

        String dataStr = dataTab + dateIDhex(message.getDataStr());
        String dateLong = addZeroForNumLeft(String.valueOf(dataStr.length() / 2), 2);
        String control = "91";

        String datesum = makeChecksum(makeChecksum("68" + machineAddress + "68" + control + dateLong + dataStr));
        String head = "FEFEFEFE";//回复数据头
        messageBean.setStartFrame("68");
        messageBean.setMachineAddress(machineAddress);
        messageBean.setStartFrame2("68");
        messageBean.setControl(control);
        messageBean.setDataLong(dateLong);
        messageBean.setDataTab(dataTab);
        messageBean.setDataStr(dataStr);
        messageBean.setDatasum(datesum);
        messageBean.setEndStr("16");

        receiveMsg = head + messageBean.getStartFrame() + messageBean.getMachineAddress() +
                messageBean.getStartFrame2() + messageBean.getControl() + messageBean.getDataLong() +
                messageBean.getDataTab() + messageBean.getDataStr() + messageBean.getDatasum() +
                messageBean.getEndStr();

        return receiveMsg;
    }


    /**
     * @param :旧设备地址、旧数据标、回复数据
     * @return :回复报文
     * @Author:xulei
     * @Description:645下位机上发的报文，进行回复
     * @Date: 208-05-02
     */
    public static String receiveUpdateToIp(MessageDLT message) {
        String receiveMsg = "";

        MessageDLT messageBean = new MessageDLT();

        String machineAddress = message.getMachineAddress();
        String control = message.getControl();
        String dateLong = message.getDataLong();
        String dataTab = message.getDataTab();
        String dataStr = message.getDataStr();

        String datesum = message.getDatasum();
        String head = "FEFEFEFE";//回复数据头
        messageBean.setStartFrame("68");
        messageBean.setMachineAddress(machineAddress);
        messageBean.setStartFrame2("68");
        messageBean.setControl(control);
        messageBean.setDataLong(dateLong);
        messageBean.setDataTab(dataTab);
        messageBean.setDataStr(dataStr);
        messageBean.setDatasum(datesum);
        messageBean.setEndStr("16");

        receiveMsg = head + messageBean.getStartFrame() + messageBean.getMachineAddress() +
                messageBean.getStartFrame2() + messageBean.getControl() + messageBean.getDataLong() +
                messageBean.getDataTab() + messageBean.getDataStr() + messageBean.getDatasum() +
                messageBean.getEndStr() + "0D0A";

        return receiveMsg;


    }

    /**
     * @Author:xulei
     * @Description:解析报文数据,返回报文中的值
     * @Date: 2018-4-22
     * @param：收到的报文、旧设备地址、旧数据标
     * @return：返回解析后的数据值
     */

    public static String receiveMessageToDate(String receiveMessage, MessageDLT sendMessage) {

        String msg = "";
        String receiveHead = receiveMessage.substring(0, 8);
        String receiveEnd = receiveMessage.substring(receiveMessage.length() - 2, receiveMessage.length());
//        if (receiveHead.equals("FEFEFEFE") && receiveEnd.equals("16")) {//先判断报文头和尾是否正确
        MessageDLT messageDLT = new MessageDLT();

        messageDLT.setStartFrame(receiveMessage.substring(8, 10));
        messageDLT.setMachineAddress(receiveMessage.substring(10, 22));
        messageDLT.setStartFrame2(receiveMessage.substring(22, 24));
        messageDLT.setControl(receiveMessage.substring(24, 26));
        messageDLT.setDataLong(receiveMessage.substring(26, 28));
        int along = 28 + Integer.valueOf(receiveMessage.substring(26, 28)) * 2;
//        System.out.println("along===" + along);
        messageDLT.setDataStr(receiveMessage.substring(28, along));
        messageDLT.setDatasum(receiveMessage.substring(along, along + 2));
        messageDLT.setEndStr(receiveMessage.substring(along + 2, along + 4));

        String olddatastr = "";
        String oldaddress = "";
        //判断的返回报文如果是异常数据格式，则返回二进制异常
        String controlID = receiveMessage.substring(24, 25);
        if (!controlID.equals("D")) {
            oldaddress = machineAddressHex(sendMessage.getMachineAddress());//地址倒序
            olddatastr = dateIDhex(sendMessage.getDataStr());//数据标倒序
            if (messageDLT.getMachineAddress().equals(oldaddress)) {//验证返回的设备ID是否相同
                String dataStr = messageDLT.getDataStr();
                if(dataStr.length() > 8) {
//                    System.out.println("olddatastr===" + olddatastr);
//                    System.out.println("dataStr===" + dataStr.substring(0, 8));
                    if (olddatastr.equals(dataStr.substring(0, 8))) {//判断数据标是否一致
                        //获取返回数据值
                        String datainfo = dataStr.substring(8, dataStr.length());

                        //解析十六进制返回数据；
                        msg = receiverVlue(datainfo);

                    } else {
                        System.out.println("返回数据标异常！");
                    }
                }
            } else {

                System.out.println("返回设备ID异常!");
            }

        } else {
            //解析错误报文
            String errors = errorMessage(receiveMessage);
            System.out.println("返回错误异常!错误代码：" + errors);

        }

//        }
        return msg;

    }

    /**
     * @Author:xulei
     * @Description:通用645报文解析
     * @Date: 208-05-2
     * @param：收到的报文
     * @return：解析后的报文类
     */

    public static MessageDLT receiveMessageToBean(String receiveMessage) {
        MessageDLT messageDLT = new MessageDLT();

//        String receiveHead = receiveMessage.substring(0, 8);
//        String receiveEnd = receiveMessage.substring(receiveMessage.length() - 2, receiveMessage.length());
//        if (receiveHead.equals("FEFEFEFE") && receiveEnd.equals("16")) {//先判断报文头和尾是否正确
        try {
            if(receiveMessage != null) {
                if (!receiveMessage.equals("") && receiveMessage.length() >= 32) {
                    messageDLT.setStartFrame(receiveMessage.substring(8, 10));
                    messageDLT.setMachineAddress(receiveMessage.substring(10, 22));
                    messageDLT.setStartFrame2(receiveMessage.substring(22, 24));
                    messageDLT.setControl(receiveMessage.substring(24, 26));
                    messageDLT.setDataLong(receiveMessage.substring(26, 28));
                    int datalong = Integer.valueOf(receiveMessage.substring(26, 28));
//                    System.out.println("datalong===" + datalong);
                    int along = 28 + datalong * 2;
//                    System.out.println("along===" + along);

                    if (datalong >= 4) {//例如 拉闸、合闸、预警一些报文没有返回标记的
                        messageDLT.setDataTab(receiveMessage.substring(28, 28 + 8));
                        messageDLT.setDataStr(receiveMessage.substring(28 + 8, along));
                    }

                    if (datalong > 0 && datalong < 4) {
                        messageDLT.setDataTab("");
                        messageDLT.setDataStr(receiveMessage.substring(28, along));
                    }

                    if (datalong == 0) {//拉闸的时候回返回数据长度为0
                        messageDLT.setDataTab("");
                        messageDLT.setDataStr("");
                    }

                    messageDLT.setDatasum(receiveMessage.substring(along, along + 2));
                    messageDLT.setEndStr(receiveMessage.substring(along + 2, along + 4));
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return messageDLT;
    }


    /**
     * @Author:xulei
     * @Description:645设备上线发送报文
     * @Date: 208-05-2
     * @param：收到的报文
     * @return：解析后的报文类
     */

    public static MessageDLT onlineMessage(String receiveMessage) {
        MessageDLT messageDLT = new MessageDLT();
//        System.out.println("onlineMessage111=========" + receiveMessage);
        if (!receiveMessage.equals("")) {
            receiveMessage = receiveMessage.replaceAll("FEFEFEFE", "");
//            String receivetype = receiveMessage.substring(0, 8);
//
//            if (receivetype.equals("FEFEFEFE")) {
//                receiveMessage = receiveMessage.substring(8, receiveMessage.length());
//            }

            messageDLT.setStartFrame(receiveMessage.substring(0, 2));
            messageDLT.setMachineAddress(receiveMessage.substring(2, 14));
            messageDLT.setStartFrame2(receiveMessage.substring(14, 16));
            messageDLT.setControl(receiveMessage.substring(16, 18));

            messageDLT.setDataLong(receiveMessage.substring(18, 20));
            int datalong = Integer.valueOf(receiveMessage.substring(18, 20));
//            System.out.println("datalong===" + datalong);

            int along = 20 + datalong * 2;
//            System.out.println("along===" + along);


            if (datalong >= 4) {//例如 拉闸、合闸、预警一些报文没有返回标记的
//                System.out.println("onlineMessage2====" + receiveMessage);
                messageDLT.setDataTab(receiveMessage.substring(20, 20 + 8));
                messageDLT.setDataStr(receiveMessage.substring(20 + 8, along));
            }

            if (datalong > 0 && datalong < 4) {
                messageDLT.setDataTab("");
                messageDLT.setDataStr(receiveMessage.substring(20, along));
            }

            if (datalong == 0) {//拉闸的时候回返回数据长度为0
                messageDLT.setDataTab("");
                messageDLT.setDataStr("");
            }

            messageDLT.setDatasum(receiveMessage.substring(along, along + 2));
            messageDLT.setEndStr(receiveMessage.substring(along + 2, along + 4));
        }
        return messageDLT;
    }

    //解析16进制回复值
    public static String receiverVlue(String datainfo) {
        String send = "";
        long x = 0;
        long y = Long.parseLong("33", 16);
        int start = 2;
        int end = 0;
        int len = datainfo.length() / 2;
//        System.out.println("len==" + len);
        for (int i = 0; i < len; i++) {
            if (datainfo.length() >= start) {
                String str = datainfo.substring(datainfo.length() - start, datainfo.length() - end);
                x = Long.parseLong(str, 16);
//                System.out.println("x====" + x);
                String temp = String.valueOf(Long.toHexString(x - y));
//                System.out.println("temp==" + temp);
                if (temp.length() > 2) {
                    send += temp.substring(temp.length() - 2, temp.length());//当ff+33时=132，取后两位
                } else {
                    send += addZeroForNumLeft(temp, 2);
                }
//                System.out.println("send==" + send);

                start += 2;
                end += 2;
            }
        }
        return send;
    }

    /**
     * @Author:xulei
     * @Description:解析错误报文回复
     * @Date: 2018-4-22
     */
    public static String errorMessage(String errormsg) {
        String controlID = errormsg.substring(24, 26);
        String datalong = errormsg.substring(26, 28);
        String data = errormsg.substring(28, 28 + (Integer.valueOf(datalong) * 2));

        String binStr = addZeroForNumLeft(hexString2binaryString(data), 8);

        return binStr;
    }


    //解析数据标 加33 倒序
    public static String dateIDhex(String dataID) {

        String send = "";
        long x = 0;
        long y = Long.parseLong("33", 16);
        int start = 2;
        int end = 0;
        for (int i = 0; i < 4; i++) {
            if (dataID.length() >= start) {
                String str = dataID.substring(dataID.length() - start, dataID.length() - end);
                x = Long.parseLong(str, 16);
                String temp = String.valueOf(Long.toHexString(x + y));
                send += temp.substring(temp.length() - 2, temp.length());//当ff+33时=132，取后两位
                start += 2;
                end += 2;
            }
        }

//        System.out.println("send===" + send);

//		if(dataID.equals("02030100")) {//有功功率
//			dataID = "33343635";
//		}
//		if(dataID.equals("02040100")) {//无功功率
//			dataID = "33343735";
//		}
//		if(dataID.equals("00000000")) {//有功电量
//			dataID = "33333333";
//		}
//		if(dataID.equals("00030000")) {//无功电量
//			dataID = "33333633";
//		}
//		if(dataID.equals("02010100")) {//电压
//			dataID = "33343435";
//		}
//		if(dataID.equals("02020100")) {//电流
//			dataID = "33343535";
//		}
//		if(dataID.equals("02060100")) {//功率因数
//			dataID = "33343935";
//		}
//		if(dataID.equals("02800002")) {//频率
//			dataID = "3533B335";
//		}
//		if(dataID.equals("04000101")) {//时期及星期
//			dataID = "34343337";
//		}

        return send;
    }

    //组合设备地址 倒序
    public static String machineAddressHex(String address) {

        String addressHex = "";
        int start = 2;
        int end = 0;
        for (int i = 0; i < 6; i++) {
            if (address.length() >= start) {
                String str = address.substring(address.length() - start, address.length() - end);
                addressHex += str;
//                System.out.println(addressHex);
                start += 2;
                end += 2;
            }
        }

        addressHex = addZeroForNumFRight(addressHex, 12);
        return addressHex;
    }

    //组合设备地址 正序
    public static String machineAddressHexOpposite(String address) {

        String addressHex = "";
        int start = 2;
        int end = 0;
        for (int i = 0; i < 6; i++) {
            if (address.length() >= start) {
                String str = address.substring(address.length() - start, address.length() - end);
                addressHex += str;
                start += 2;
                end += 2;
            }
        }

        addressHex = addZeroForNumLeft(addressHex, 12);
        return addressHex;
    }


    public static String addZeroForNumFRight(String str, int strLength) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
//		    sb.append("0").append(str);//左补0
                sb.append(str).append("0");//右补0
                str = sb.toString();
                strLen = str.length();
            }
        }

        return str;
    }

    public static String addZeroForNumLeft(String str, int strLength) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                sb.append("0").append(str);//左补0
//		    sb.append(str).append("0");//右补0
                str = sb.toString();
                strLen = str.length();
            }
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

    private static String toHexString(String str, int start, int end) {
        return Long.valueOf(str.substring(start, end), 16).toString();
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


}
