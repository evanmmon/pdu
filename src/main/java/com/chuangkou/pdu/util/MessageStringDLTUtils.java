package com.chuangkou.pdu.util;

import com.chuangkou.pdu.entity.MessageDLT;
import com.chuangkou.pdu.entity.PduInfoTemp;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.text.SimpleDateFormat;

/**
 * @Author:xulei
 * @Description:645鍗忚鎶ユ枃宸ュ叿绫�
 * @Date:Created in 10:36 2018/4/22
 */
public class MessageStringDLTUtils {

    /**
     * @Author:xulei
     * @Description:涓荤珯璇绘姤鏂囩粍鍚堛�佸啓鎶ユ枃缁勫悎
     * @Date: 2018-05-02
     */
    public static String messageToHex(MessageDLT message, String action) {

        String sendmes = "";

        try {
            String machineAddress = message.getMachineAddress();
            machineAddress = addZeroForNumLeft(machineAddress, 12);
            machineAddress = machineAddressHex(machineAddress);

            if (action.equals("read")) {//涓荤珯璇绘暟鎹�
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

            if (action.equals("write")) {//涓荤珯鍐欐暟鎹�
                MessageDLT messageBean = new MessageDLT();
                String dataTab = dateIDhex(message.getDataTab());
                String dataStr = dateIDhex(message.getDataStr());

                messageBean.setStartFrame("68");
                messageBean.setMachineAddress(machineAddress);
                messageBean.setStartFrame2("68");
                messageBean.setControl(message.getControl());

                String data = message.getDataTab() + message.getPassword() + message.getAuth() + message.getDataStr();
                //鏁版嵁闀垮害闇�瑕佽浆鎴�16杩涘埗
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
     * @param :鏃ц澶囧湴鍧�銆佹棫鏁版嵁鏍囥�佸洖澶嶆暟鎹�
     * @return :鍥炲鎶ユ枃
     * @Author:xulei
     * @Description:645涓嬩綅鏈轰笂鍙戠殑鎶ユ枃锛岃繘琛屽洖澶�
     * @Date: 208-05-02
     */
    public static String receiveMessageToHex(MessageDLT message) {

        String receiveMsg = "";

        MessageDLT messageBean = new MessageDLT();

        String machineAddress = message.getMachineAddress();//鏃у湴鍧�
        machineAddress = machineAddressHex(machineAddress);
        String dataTab = message.getDataTab();//鏃ф暟鎹爣

        String dataStr = dataTab + dateIDhex(message.getDataStr());
        String dateLong = addZeroForNumLeft(String.valueOf(dataStr.length() / 2), 2);
        String control = "91";

        String datesum = makeChecksum(makeChecksum("68" + machineAddress + "68" + control + dateLong + dataStr));
        String head = "FEFEFEFE";//鍥炲鏁版嵁澶�
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
     * @param :鏃ц澶囧湴鍧�銆佹棫鏁版嵁鏍囥�佸洖澶嶆暟鎹�
     * @return :鍥炲鎶ユ枃
     * @Author:xulei
     * @Description:645涓嬩綅鏈轰笂鍙戠殑鎶ユ枃锛岃繘琛屽洖澶�
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
        String head = "FEFEFEFE";//鍥炲鏁版嵁澶�
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
     * @Description:瑙ｆ瀽鎶ユ枃鏁版嵁,杩斿洖鎶ユ枃涓殑鍊�
     * @Date: 2018-4-22
     * @param锛氭敹鍒扮殑鎶ユ枃銆佹棫璁惧鍦板潃銆佹棫鏁版嵁鏍�
     * @return锛氳繑鍥炶В鏋愬悗鐨勬暟鎹��
     */

    public static String receiveMessageToDate(String receiveMessage, MessageDLT sendMessage) {

        String msg = "";
        String receiveHead = receiveMessage.substring(0, 8);
        String receiveEnd = receiveMessage.substring(receiveMessage.length() - 2, receiveMessage.length());
//        if (receiveHead.equals("FEFEFEFE") && receiveEnd.equals("16")) {//鍏堝垽鏂姤鏂囧ご鍜屽熬鏄惁姝ｇ‘
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
        //鍒ゆ柇鐨勮繑鍥炴姤鏂囧鏋滄槸寮傚父鏁版嵁鏍煎紡锛屽垯杩斿洖浜岃繘鍒跺紓甯�
        String controlID = receiveMessage.substring(24, 25);
        if (!controlID.equals("D")) {
            oldaddress = machineAddressHex(sendMessage.getMachineAddress());//鍦板潃鍊掑簭
            olddatastr = dateIDhex(sendMessage.getDataStr());//鏁版嵁鏍囧�掑簭
            if (messageDLT.getMachineAddress().equals(oldaddress)) {//楠岃瘉杩斿洖鐨勮澶嘔D鏄惁鐩稿悓
                String dataStr = messageDLT.getDataStr();
                if(dataStr.length() > 8) {
//                    System.out.println("olddatastr===" + olddatastr);
//                    System.out.println("dataStr===" + dataStr.substring(0, 8));
                    if (olddatastr.equals(dataStr.substring(0, 8))) {//鍒ゆ柇鏁版嵁鏍囨槸鍚︿竴鑷�
                        //鑾峰彇杩斿洖鏁版嵁鍊�
                        String datainfo = dataStr.substring(8, dataStr.length());

                        //瑙ｆ瀽鍗佸叚杩涘埗杩斿洖鏁版嵁锛�
                        msg = receiverVlue(datainfo);

                    } else {
                        System.out.println("杩斿洖鏁版嵁鏍囧紓甯革紒");
                    }
                }
            } else {

                System.out.println("杩斿洖璁惧ID寮傚父!");
            }

        } else {
            //瑙ｆ瀽閿欒鎶ユ枃
            String errors = errorMessage(receiveMessage);
            System.out.println("杩斿洖閿欒寮傚父!閿欒浠ｇ爜锛�" + errors);

        }

//        }
        return msg;

    }

    /**
     * @Author:xulei
     * @Description:閫氱敤645鎶ユ枃瑙ｆ瀽
     * @Date: 208-05-2
     * @param锛氭敹鍒扮殑鎶ユ枃
     * @return锛氳В鏋愬悗鐨勬姤鏂囩被
     */

    public static MessageDLT receiveMessageToBean(String receiveMessage) {
        MessageDLT messageDLT = new MessageDLT();

//        String receiveHead = receiveMessage.substring(0, 8);
//        String receiveEnd = receiveMessage.substring(receiveMessage.length() - 2, receiveMessage.length());
//        if (receiveHead.equals("FEFEFEFE") && receiveEnd.equals("16")) {//鍏堝垽鏂姤鏂囧ご鍜屽熬鏄惁姝ｇ‘
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

                    if (datalong >= 4) {//渚嬪 鎷夐椄銆佸悎闂搞�侀璀︿竴浜涙姤鏂囨病鏈夎繑鍥炴爣璁扮殑
                        messageDLT.setDataTab(receiveMessage.substring(28, 28 + 8));
                        messageDLT.setDataStr(receiveMessage.substring(28 + 8, along));
                    }

                    if (datalong > 0 && datalong < 4) {
                        messageDLT.setDataTab("");
                        messageDLT.setDataStr(receiveMessage.substring(28, along));
                    }

                    if (datalong == 0) {//鎷夐椄鐨勬椂鍊欏洖杩斿洖鏁版嵁闀垮害涓�0
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
     * @Description:645璁惧涓婄嚎鍙戦�佹姤鏂�
     * @Date: 208-05-2
     * @param锛氭敹鍒扮殑鎶ユ枃
     * @return锛氳В鏋愬悗鐨勬姤鏂囩被
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


            if (datalong >= 4) {//渚嬪 鎷夐椄銆佸悎闂搞�侀璀︿竴浜涙姤鏂囨病鏈夎繑鍥炴爣璁扮殑
//                System.out.println("onlineMessage2====" + receiveMessage);
                messageDLT.setDataTab(receiveMessage.substring(20, 20 + 8));
                messageDLT.setDataStr(receiveMessage.substring(20 + 8, along));
            }

            if (datalong > 0 && datalong < 4) {
                messageDLT.setDataTab("");
                messageDLT.setDataStr(receiveMessage.substring(20, along));
            }

            if (datalong == 0) {//鎷夐椄鐨勬椂鍊欏洖杩斿洖鏁版嵁闀垮害涓�0
                messageDLT.setDataTab("");
                messageDLT.setDataStr("");
            }

            messageDLT.setDatasum(receiveMessage.substring(along, along + 2));
            messageDLT.setEndStr(receiveMessage.substring(along + 2, along + 4));
        }
        return messageDLT;
    }

    //瑙ｆ瀽16杩涘埗鍥炲鍊�
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
                    send += temp.substring(temp.length() - 2, temp.length());//褰揻f+33鏃�=132锛屽彇鍚庝袱浣�
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
     * @Description:瑙ｆ瀽閿欒鎶ユ枃鍥炲
     * @Date: 2018-4-22
     */
    public static String errorMessage(String errormsg) {
        String controlID = errormsg.substring(24, 26);
        String datalong = errormsg.substring(26, 28);
        String data = errormsg.substring(28, 28 + (Integer.valueOf(datalong) * 2));

        String binStr = addZeroForNumLeft(hexString2binaryString(data), 8);

        return binStr;
    }


    //瑙ｆ瀽鏁版嵁鏍� 鍔�33 鍊掑簭
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
                send += temp.substring(temp.length() - 2, temp.length());//褰揻f+33鏃�=132锛屽彇鍚庝袱浣�
                start += 2;
                end += 2;
            }
        }

//        System.out.println("send===" + send);

//		if(dataID.equals("02030100")) {//鏈夊姛鍔熺巼
//			dataID = "33343635";
//		}
//		if(dataID.equals("02040100")) {//鏃犲姛鍔熺巼
//			dataID = "33343735";
//		}
//		if(dataID.equals("00000000")) {//鏈夊姛鐢甸噺
//			dataID = "33333333";
//		}
//		if(dataID.equals("00030000")) {//鏃犲姛鐢甸噺
//			dataID = "33333633";
//		}
//		if(dataID.equals("02010100")) {//鐢靛帇
//			dataID = "33343435";
//		}
//		if(dataID.equals("02020100")) {//鐢垫祦
//			dataID = "33343535";
//		}
//		if(dataID.equals("02060100")) {//鍔熺巼鍥犳暟
//			dataID = "33343935";
//		}
//		if(dataID.equals("02800002")) {//棰戠巼
//			dataID = "3533B335";
//		}
//		if(dataID.equals("04000101")) {//鏃舵湡鍙婃槦鏈�
//			dataID = "34343337";
//		}

        return send;
    }

    //缁勫悎璁惧鍦板潃 鍊掑簭
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

    //缁勫悎璁惧鍦板潃 姝ｅ簭
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
//		    sb.append("0").append(str);//宸﹁ˉ0
                sb.append(str).append("0");//鍙宠ˉ0
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
                sb.append("0").append(str);//宸﹁ˉ0
//		    sb.append(str).append("0");//鍙宠ˉ0
                str = sb.toString();
                strLen = str.length();
            }
        }

        return str;
    }

    //16杩涘埗绱姞鍜�
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
         * 鐢�256姹備綑鏈�澶ф槸255锛屽嵆16杩涘埗鐨凢F
         */
        int mod = total % 256;
        String hex = Integer.toHexString(mod);
        len = hex.length();
        // 濡傛灉涓嶅鏍￠獙浣嶇殑闀垮害锛岃ˉ0,杩欓噷鐢ㄧ殑鏄袱浣嶆牎楠�
        if (len < 2) {
            hex = "0" + hex;
        }
        return hex;
    }


    /**
     * @Author:
     * @Description:瀛楃涓茶浆鍗佸叚杩涘埗瀛楃涓�
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

    //鍗佸叚杩涘埗瀛楃涓茶浆浜岃繘鍒跺瓧绗︿覆
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
