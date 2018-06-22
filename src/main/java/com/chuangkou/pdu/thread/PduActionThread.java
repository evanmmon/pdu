package com.chuangkou.pdu.thread;

import com.chuangkou.pdu.controller.BaseController;
import com.chuangkou.pdu.entity.Message;
import com.chuangkou.pdu.entity.MessageDLT;
import com.chuangkou.pdu.entity.Pdu;
import com.chuangkou.pdu.service.PduService;
import com.chuangkou.pdu.util.MessageStringDLTUtils;
import com.chuangkou.pdu.util.SocketUtils;
import com.chuangkou.pdu.util.StringUtil;

import java.io.*;
import java.net.Socket;

/**
 * @Author:
 * @Description:继电器控制线程
 * @Date:Created in 11:07 2018/3/28
 */
public class PduActionThread implements Runnable {

    Socket actionThreadSocket = null;//和本线程相关的Socket
    Pdu jobpdu = null;
    String actype = "";
    private static PduService pduService = (PduService) SpringApplicationContextHolder.getSpringBean("pduService");

    public PduActionThread(Socket socket, Pdu jobpdu, String actype) {
        this.actionThreadSocket = socket;
        this.jobpdu = jobpdu;
        this.actype = actype;//开关
//        run();
    }


    @Override
    public void run() {
//    public static void pduRelayOffOn(Socket socket, Pdu jobpdu, String actype) {
        //根据参数组合报文
        Message message = new Message();
        String receivemsg = "";
        String machineID = jobpdu.getMachineid();
        String pdutype = "";


        try {
            if (machineID.length() > 10) {
                pdutype = machineID.substring(0, 4);
//                if (pdutype.equals("0000")) {
//
//
//                    while (!receivemsg.equals("AAB1A30D0A")) {
//                        message.setControlType("2");//下行
//                        message.setCommand("3");//控制类型报文
//
//                        message.setIp(jobpdu.getIp());
//                        String pdumachine = jobpdu.getMachineid();
//
//                        message.setReceivePduType(pdumachine.substring(0, 3));
//                        message.setReceiveMachineID(pdumachine.substring(3, 8));
//                        message.setReceiveRandomID(pdumachine.substring(8, 11));
//
//                        message.setRelayState(actype);
//                        //组合报文
//                        String msg = StringUtil.sendMessage2(message);
//                        //                 String msg ="AAB2A3";
//                        //发送报文
//                        SocketUtils.writeMsgToClient(socket.getOutputStream(), msg);
//
//                        //接收回复是否成功
//                        receivemsg = SocketUtils.readMessageFromClient(socket.getInputStream());
//
//                        if (receivemsg.equals("AAB1A30D0A")) {
//                            System.out.println("修改继电器状态成功！");
//
//                            //修改继电器成功后，应该修改数据库记录的继电器状态,info新增记录
//                            jobpdu.setActionState(actype);
//                            jobpdu.setOnlinestate(actype);
//                            pduService.update(jobpdu);//修改主表设备的继电器状态；
//                            //                        JobPduInfoClockTaskThread.jobPduInfoClockOne(socket, jobpdu);
//                        }
//                        Thread.sleep(3000);
//                    }
//
//                }

                if (pdutype.equals("0001")) { //645 插座继电器
                    //转换设备地址
                    machineID = MessageStringDLTUtils.addZeroForNumLeft(machineID, 12);
                    String machineAddress = MessageStringDLTUtils.machineAddressHex(machineID);
                    String sendmessage = "";
                    String remessg = "";
                    String off = "681C1035434343444444445D33586B495B374B";//拉闸报文
                    String on =  "681C1035434343444444445E33586B495B374B";//合闸报文
                    //设备控制命令
                    if (actype.equals("0")) {
                        sendmessage = "68" + machineAddress + off;
                        String datesum = MessageStringDLTUtils.makeChecksum(sendmessage);
                        sendmessage = sendmessage + datesum + "16" + "0D0A";


                        System.out.println("发送拉闸报文" + sendmessage);
                        SocketUtils.writeMsgToClient(actionThreadSocket.getOutputStream(), sendmessage);
                        Thread.sleep(1000);
                        //返回控制结果
//                        remessg = SocketUtils.readMessageFromClient(actionThreadSocket.getInputStream());
//                        SubPolThread subPolThread = new SubPolThread(actionThreadSocket);
                        remessg = BaseController.readmsg;

                        System.out.println("返回拉闸报文" + remessg);
                        //FE FE FE FE 68 01 00 14 03 18 00 68 9C 00 9C 16  返回结果如果正确则修改设备主表的继电器状态actionstate
                        MessageDLT messageDLT = new MessageDLT();
                        messageDLT = MessageStringDLTUtils.receiveMessageToBean(remessg);
                        if (messageDLT.getDataStr().equals("4D")) {
                            System.out.println("关闭设备成功！");
                            jobpdu.setActionState(actype);
                            jobpdu.setOnlinestate(actype);
                            pduService.update(jobpdu);//修改主表设备的继电器状态；
                        }

                    }
                    if (actype.equals("1")) {

                        sendmessage = "68" + machineAddress + on;
                        String datesum = MessageStringDLTUtils.makeChecksum(sendmessage);
                        sendmessage = sendmessage + datesum + "16" + "0D0A";

                        System.out.println("发送合闸报文" + sendmessage);
                        SocketUtils.writeMsgToClient(actionThreadSocket.getOutputStream(), sendmessage);

                        Thread.sleep(1000);
//                        remessg = SocketUtils.readMessageFromClient(actionThreadSocket.getInputStream());
                        remessg = BaseController.readmsg;
//                        SubPolThread subPolThread = new SubPolThread(actionThreadSocket);
//                        remessg = SubPolThread.getReadMsg();

                        System.out.println("返回合闸报文" + remessg);
                        //FE FE FE FE 68 01 00 14 03 18 00 68 9C 00 9C 16
                        MessageDLT messageDLT = new MessageDLT();
                        messageDLT = MessageStringDLTUtils.receiveMessageToBean(remessg);
                        if (messageDLT.getDataStr().equals("4E")) {

                            System.out.println("开启设备成功！");
                            jobpdu.setActionState(actype);
                            jobpdu.setOnlinestate(actype);
                            pduService.update(jobpdu);//修改主表设备的继电器状态；
                        }
                    }

                    //                remessg = remessg.substring(remessg.length()-10,remessg.length());
                    //                if(remessg.equals("689C009C16")){
                    //修改继电器成功后，应该修改数据库记录的继电器状态

                    //                    JobPduInfoClockTaskThread.jobPduInfoClockOne(socket, jobpdu);

                    //                }

                }
            }

            if (machineID.length() == 10) {//645报文设备
                //转换设备地址
                machineID = MessageStringDLTUtils.addZeroForNumLeft(machineID, 12);
                String machineAddress = MessageStringDLTUtils.machineAddressHex(machineID);
                String sendmessage = "";
                String remessg = "";
                String off = "681C1035434343444444444D333444444C374B";//拉闸报文
                String on = "681C1035434343444444444E333444444C374B";//合闸报文


                //设备控制命令
                if (actype.equals("0")) {
                    sendmessage = "68" + machineAddress + off;
                    String datesum = MessageStringDLTUtils.makeChecksum(sendmessage);
                    sendmessage = sendmessage + datesum + "16";
                    System.out.println("空开拉闸报文:" + sendmessage);
                    SocketUtils.writeMsgToClient(actionThreadSocket.getOutputStream(), sendmessage);
                    //返回控制结果
//                    remessg = SocketUtils.readMessageFromClient(actionThreadSocket.getInputStream());
                    //FE FE FE FE 68 01 00 14 03 18 00 68 9C 00 9C 16  返回结果如果正确则修改设备主表的继电器状态actionstate
                    Thread.sleep(1000);
                    remessg = BaseController.readmsg;

                    MessageDLT messageDLT = new MessageDLT();
                    messageDLT = MessageStringDLTUtils.onlineMessage(remessg);
                    System.out.println("返回空开拉闸报文" + remessg);
                    if (messageDLT.getControl().equals("9C")) {
                        jobpdu.setActionState(actype);
                        jobpdu.setOnlinestate(actype);
                        pduService.update(jobpdu);//修改主表设备的继电器状态；
                        System.out.println("关闭设备成功！");
                    }
                }
                if (actype.equals("1")) {
                    sendmessage = "68" + machineAddress + on;
                    String datesum = MessageStringDLTUtils.makeChecksum(sendmessage);
                    sendmessage = sendmessage + datesum + "16";
                    System.out.println("空开合闸报文:" + sendmessage);
                    SocketUtils.writeMsgToClient(actionThreadSocket.getOutputStream(), sendmessage);
//                    remessg = SocketUtils.readMessageFromClient(actionThreadSocket.getInputStream());
                    //FE FE FE FE 68 01 00 14 03 18 00 68 9C 00 9C 16
                    Thread.sleep(1000);
                    remessg = BaseController.readmsg;

                    MessageDLT messageDLT = new MessageDLT();
                    messageDLT = MessageStringDLTUtils.onlineMessage(remessg);
                    System.out.println("返回空开合闸报文" + remessg);
                    if (messageDLT.getControl().equals("9C")) {
                        jobpdu.setActionState(actype);
                        jobpdu.setOnlinestate(actype);
                        pduService.update(jobpdu);//修改主表设备的继电器状态；
                        System.out.println("开启设备成功！");
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //发送报文
    private static void writeMsgToClient(OutputStream outputStream, String string) throws IOException {
        Writer writer = new OutputStreamWriter(outputStream);
        writer.append(string);
        writer.flush();
//        writer.close();
    }

    private static String readMessageFromClient(InputStream inputStream) throws IOException {
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(reader);
        String a = null;
        String message = "";

        //循环接收报文
        while ((a = br.readLine()) != null) {
            message += a;
            System.out.println(message);
            return message;
        }
        System.out.println("已接收到客户端连接");
        BufferedReader bufferedReader = new BufferedReader(reader);//加入缓冲区
        String temp = null;
        String info = "";

        while ((temp = bufferedReader.readLine()) != null) {
            info += temp;
            System.out.println("已接收到客户端连接");
            System.out.println("服务端接收到客户端信息：" + info);
        }

        return message;

    }

}
