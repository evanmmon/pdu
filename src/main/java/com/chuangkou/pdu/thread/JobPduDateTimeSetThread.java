package com.chuangkou.pdu.thread;

import com.chuangkou.pdu.controller.BaseController;
import com.chuangkou.pdu.entity.Message;
import com.chuangkou.pdu.entity.MessageDLT;
import com.chuangkou.pdu.entity.Pdu;
import com.chuangkou.pdu.util.MessageStringDLTUtils;
import com.chuangkou.pdu.util.SocketUtils;
import com.chuangkou.pdu.util.StringUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Author:xulei
 * @Description:校时广播报文
 * @Date:Created in 15:03 2018/4/20
 */
public class JobPduDateTimeSetThread implements Runnable {

    Socket socket = null;//和本线程相关的Socket
    List<Pdu> pduList = null;
    String machineId = null;

    //    public JobPduDateTimeSetThread(Socket socket, List<Pdu> pduList) {
    public JobPduDateTimeSetThread(Socket socket, String machineId) {
        this.socket = socket;
        this.machineId = machineId;
//        this.pduList = pduList;
//        run();
    }

    @Override
    public void run() {
        machineId = MessageStringDLTUtils.addZeroForNumLeft(machineId, 12);
        System.out.println("machineId==" + machineId);
//        machineId = MessageStringDLTUtils.machineAddressHex(machineId);
        String end = "0D0A";
        int num = 0;
        try {
//            for (int i = 0; i < pduList.size(); i++) {
//                Pdu pduTime = new Pdu();
//                pduTime = pduList.get(i);
//            machineId = MessageStringDLTUtils.addZeroForNumLeft(machineId, 12);
//            machineId = MessageStringDLTUtils.machineAddressHex(machineId);


            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEEE");
            String datetime = df.format(System.currentTimeMillis());
            int y = Integer.parseInt("33", 16);

            int year = Integer.valueOf(datetime.substring(2, 4));
            int month = Integer.valueOf(datetime.substring(5, 7));
            int day = Integer.valueOf(datetime.substring(8, 10));
            int hour = Integer.valueOf(datetime.substring(11, 13));
            int minute = Integer.valueOf(datetime.substring(14, 16));
            int second = Integer.valueOf(datetime.substring(17, 19));
            String whatday = getWeekOfDate(datetime.substring(20, 23));//返回的是星期一 需要装换成数字
            int whatdayInt = Integer.valueOf(whatday);


            //时间日期是先转换成16进制，再加十六进制33
//            String yearhex = StringUtil.addZeroForNum(Integer.toHexString(year + y), 2);
//            String monthhex = StringUtil.addZeroForNum(Integer.toHexString(month + y), 2);
//            String dayhex = StringUtil.addZeroForNum(Integer.toHexString(day + y), 2);
//            String hourhex = StringUtil.addZeroForNum(Integer.toHexString(hour + y), 2);
//            String minutehex = StringUtil.addZeroForNum(Integer.toHexString(minute + y), 2);
//            String secondhex = StringUtil.addZeroForNum(Integer.toHexString(second + y), 2);
//            String whatdayhex = StringUtil.addZeroForNum(Integer.toHexString(whatdayInt + y), 2);


            String yearhex = StringUtil.addZeroForNum(String.valueOf(year), 2);
            String monthhex = StringUtil.addZeroForNum(String.valueOf(month), 2);
            String dayhex = StringUtil.addZeroForNum(String.valueOf(day), 2);
            String hourhex = StringUtil.addZeroForNum(String.valueOf(hour), 2);
            String minutehex = StringUtil.addZeroForNum(String.valueOf(minute), 2);
            String secondhex = StringUtil.addZeroForNum(String.valueOf(second), 2);
            String whatdayhex = StringUtil.addZeroForNum(String.valueOf(whatdayInt), 2);

            String dateTab = "04000101"; //设置日期数据标
            //日期校时数据
            String data = yearhex + monthhex + dayhex + whatdayhex;

            MessageDLT messageDLTdata = new MessageDLT();
            messageDLTdata.setMachineAddress(machineId);
            messageDLTdata.setControl("14");
//            messageDLTdata.setDataLong("10");
            messageDLTdata.setDataTab(dateTab);
            messageDLTdata.setPassword(BaseController.DLTpassword);
            messageDLTdata.setAuth(BaseController.DLTcontrol);
            messageDLTdata.setDataStr(data);

            //组合写入对时设置报文
            String datahex = MessageStringDLTUtils.messageToHex(messageDLTdata, "write");


//            String datenum = StringUtil.makeChecksum("1410" + data);
//                String machineAddress = pduTime.getMachineid();
//                String address = "999999999999";//广播报文就用999999999999
//
//            String datahex = "68" + machineId + "681410" + data + datenum + "16";

            if (!machineId.substring(0, 3).equals("180")) {
                datahex = datahex + end;//非空开设备发送消息加0D0A
            }
            System.out.println("发送日期报文===" + datahex);
            writeMsgToClient(socket.getOutputStream(), datahex);//发送日期报文

            Thread.sleep(1000);
            String datahexreadmesg = SocketUtils.readMessageFromClient(socket.getInputStream());//收到回复
//            String datahexreadmesg = BaseController.readmsg;
            System.out.println("datahexreadmesg==" + datahexreadmesg);
            while (datahexreadmesg.equals("")) {
                System.out.println("重新校对日期");
//                System.out.println("datahex====" + datahex);
                writeMsgToClient(socket.getOutputStream(), datahex);//重新发送报文
                Thread.sleep(500);
                datahexreadmesg = SocketUtils.readMessageFromClient(socket.getInputStream());//再次接受报文
                num++;
                if (num == 5) {
                    num = 0;
                    break;
                }
            }

            if (!datahexreadmesg.equals("")) {
                MessageDLT messageDLT = MessageStringDLTUtils.onlineMessage(datahexreadmesg);
                String newddress = MessageStringDLTUtils.machineAddressHexOpposite(messageDLT.getMachineAddress());
                if (messageDLT.getControl().equals("94") && machineId.equals(newddress)) {
                    System.out.println("日期校时成功！");
                }
            }


            String datetimeTab = "04000102"; //设置时间数据标
            //时间校时数据
            String datatime = hourhex + minutehex + secondhex;

            MessageDLT messageDLTdatatime = new MessageDLT();
            messageDLTdatatime.setMachineAddress(machineId);
            messageDLTdatatime.setControl("14");
//            messageDLTdata.setDataLong("10");
            messageDLTdatatime.setDataTab(datetimeTab);
            messageDLTdatatime.setPassword(BaseController.DLTpassword);
            messageDLTdatatime.setAuth(BaseController.DLTcontrol);
            messageDLTdatatime.setDataStr(datatime);

            //组合写入对时设置报文
            String datatiemhex = MessageStringDLTUtils.messageToHex(messageDLTdatatime, "write");

//            String datatimenum = StringUtil.makeChecksum("140F" + data);
//            String datatiemhex = "68" + machineId + "68140F" + datatime + datatimenum + "16";

            if (!machineId.substring(0, 3).equals("180")) {
                datatiemhex = datatiemhex + end;//非空开设备发送消息加0D0A
            }
            writeMsgToClient(socket.getOutputStream(), datatiemhex);//发送时间报文

            Thread.sleep(1000);
            String datatiemhexreadmesg = SocketUtils.readMessageFromClient(socket.getInputStream());//收到回复
//            String datatiemhexreadmesg = BaseController.readmsg;
            System.out.println("datatiemhexreadmesg==" + datatiemhexreadmesg);
            while (datatiemhexreadmesg.equals("")) {
                System.out.println("重新校对时间");
//                System.out.println("datatiemhex====" + datatiemhex);

                writeMsgToClient(socket.getOutputStream(), datatiemhex);//重新发送时间报文
                Thread.sleep(500);
                datatiemhexreadmesg = SocketUtils.readMessageFromClient(socket.getInputStream());//重新接受回复
                num++;
                if (num == 5) {
                    num = 0;
                    break;
                }
            }
            if (!datahexreadmesg.equals("")) {
                MessageDLT messageDLT = MessageStringDLTUtils.onlineMessage(datatiemhexreadmesg);
                String newddress = MessageStringDLTUtils.machineAddressHexOpposite(messageDLT.getMachineAddress());
                if (messageDLT.getControl().equals("94") && machineId.equals(newddress)) {
                    System.out.println("时间校时成功！");
                }
            }
//            68 01 00 14 03 18 00 68 14 10  34 34 33 37  35 43 43 43  44 44 44 44 37 43 38 4B 01 16  日期校时
//            68 01 00 14 03 18 00 68 14 10  34 34 33 37  35 43 43 43  44 44 44 44 37 43 38 4B 01 16
//            34 34 33 37 数据标
//            35 43 43 43 密码
//            44 44 44 44 操作者代码
//            37 43 38 4B 数据值 （解出来是 18 05 10 04  ） 04表示星期四
//
//            FE FE FE FE 68 01 00 14 03 18 00 68 94 00 94 16
//            68 01 00 14 03 18 00 68 14 0F  35 34 33 37  35 43 43 43  44 44 44 44  88 3B 48 0F 16  //时间校时
//            FE FE FE FE 68 01 00 14 03 18 00 68 94 00 94 16

//            Thread.sleep(1000);
//            for(int i = 0 ; i <= pduList.size();i++) {
//                writeMsgToClient(socket.getOutputStream(), s);
//            }
//            }
            //协议规定广播报文没有回复
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送信息
     *
     * @param outputStream
     * @param string
     */
    private static void writeMsgToClient(OutputStream outputStream, String string) throws IOException {

        try {
            byte[] ss = StringUtil.hexStringToByteArray(string);
//            System.out.println("校时报文===" + ss.toString());
            outputStream.write(ss);
            outputStream.flush();
        } catch (IOException e) {
            System.out.println("发送报文异常！");
            outputStream.close();
            e.printStackTrace();
        }

    }

    public static String getWeekOfDate(String dt) {

        String whatday = "";
        if (dt.equals("星期日")) whatday = "0";
        if (dt.equals("星期一")) whatday = "1";
        if (dt.equals("星期二")) whatday = "2";
        if (dt.equals("星期三")) whatday = "3";
        if (dt.equals("星期四")) whatday = "4";
        if (dt.equals("星期五")) whatday = "5";
        if (dt.equals("星期六")) whatday = "6";

        return whatday;
    }
}
