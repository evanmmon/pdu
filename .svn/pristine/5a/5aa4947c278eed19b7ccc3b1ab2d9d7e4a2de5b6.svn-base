package com.chuangkou.pdu.thread;

import com.chuangkou.pdu.controller.BaseController;
import com.chuangkou.pdu.entity.Message;
import com.chuangkou.pdu.entity.MessageDLT;
import com.chuangkou.pdu.entity.Pdu;
import com.chuangkou.pdu.service.PduService;
import com.chuangkou.pdu.util.MessageStringDLTUtils;
import com.chuangkou.pdu.util.SocketUtils;

import java.net.Socket;

/**
 * @Author:
 * @Description:
 * @Date:Created in 13:41 2018/6/20
 */
public class PduActionThreadPDC implements Runnable{

    Socket actionThreadSocket = null;//和本线程相关的Socket
    Pdu jobpdu = null;
    String actype = "";
    private static PduService pduService = (PduService) SpringApplicationContextHolder.getSpringBean("pduService");


    public PduActionThreadPDC(Socket socket, Pdu jobpdu, String actype) {
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
                        if (messageDLT.getDataStr().equals("5D")) {
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
                        if (messageDLT.getDataStr().equals("5E")) {

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
}
