package com.chuangkou.pdu.thread;

import com.chuangkou.pdu.controller.BaseController;
import com.chuangkou.pdu.controller.PduController;
import com.chuangkou.pdu.entity.*;
import com.chuangkou.pdu.service.*;
import com.chuangkou.pdu.util.MessageStringDLTUtils;
import com.chuangkou.pdu.util.SocketUtils;
import com.chuangkou.pdu.util.StringUtil;

import javax.annotation.Resource;
import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:
 * @Description:设备信息采集线程
 * @Date:Created in 16:27 2018/3/27
 */
public class JobPduInfoThread implements Runnable {


    Socket jobPduInfoSocket = null;//和本线程相关的Socket
    Pdu pdu = null;


    private static PduInfoService pduInfoService = (PduInfoService) SpringApplicationContextHolder.getSpringBean("pduInfoService");

    private static PduService pduService = (PduService) SpringApplicationContextHolder.getSpringBean("pduService");

    private static PduWarningSetService pduWarningSetService = (PduWarningSetService) SpringApplicationContextHolder.getSpringBean("pduWarningSetService");

    private static SceneService sceneService = (SceneService) SpringApplicationContextHolder.getSpringBean("sceneService");

    private static PduAutomaticService pduAutomaticService = (PduAutomaticService) SpringApplicationContextHolder.getSpringBean("pduAutomaticService");

    private static PduWarningService pduWarningService = (PduWarningService) SpringApplicationContextHolder.getSpringBean("pduWarningService");

    public JobPduInfoThread(Socket socket, Pdu pdu) {
        this.jobPduInfoSocket = socket;
        this.pdu = pdu;
//        run();
    }

    @Override
    public void run() {
//    public static void jobPduInfo(Socket socket, Pdu jobpdu){

        //循环所有的设备，如果不属于此链接下的设备，将不会执行命令
//        for (int i = 0; i < pduList.size(); i++) {
//            Pdu pdu = new Pdu();
        PduInfo pduInfo = new PduInfo();
//            pdu = pduList.get(i);
        String machineID = pdu.getMachineid();
//        String machineID = "1803140001";
//            pdu = pduService.selectByMachineID(machineID);
        System.out.println("machineID===" + machineID);
        String pdutype = pdu.getType();
        String end = "";
        if (machineID.length() > 10) {
            end = "0d0a";
        }
        pduInfo.setPduid(pdu.getId());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        pduInfo.setCollectiontime(df.format(System.currentTimeMillis()));
        int num = 0;
        try {
            while (true) {
                System.out.println("===============循环第" + num + "次=======================");
                MessageDLT messageDLT = new MessageDLT();
                messageDLT.setMachineAddress(machineID);

                Pdu pduBreaker = new Pdu();
                pduBreaker.setType("180");
                List<Pdu> pduListTwo = pduService.selectAllBySearch(pduBreaker);

                for (int y = 0; y < pduListTwo.size(); y++) {
                    Thread.sleep(500);
                    String startPduCurrent = pduPlugCurrent(pdu, jobPduInfoSocket, "in");
                    System.out.println("当前插座电流==" + startPduCurrent);

//                    Thread.sleep(10000);
                    Pdu pduBreakerTwo = new Pdu();
                    pduBreakerTwo = pduListTwo.get(y);

                    String conName = pduBreakerTwo.getIp();
                    Socket breakerCon = null;
                    //获取连接对象
                    breakerCon = (Socket) BaseController.SubPolmap.get(conName);
                    //获取空开当前的电流
//                Thread.sleep(500);
                    String startBreakerCurrent = pduPlugCurrent(pduBreakerTwo, breakerCon, "out");
                    System.out.println("当前空开电流==" + startBreakerCurrent);

                    //合闸
                    Thread.sleep(1000);
                    long starttiem = System.currentTimeMillis();
                    System.out.println("合闸时间===" + System.currentTimeMillis());
                    Thread threadPlug = new Thread(new PduActionThread(jobPduInfoSocket, pdu, "1"));
                    threadPlug.start();
                    threadPlug.join();

                    if (num == 0) {
                        Thread.sleep(10000);
                        //关闭插座
                        Thread.sleep(100);
                        Thread threadPlugOff = new Thread(new PduActionThread(jobPduInfoSocket, pdu, "0"));
                        threadPlugOff.start();
                        threadPlugOff.join();
                        System.out.println("拉闸时间===" + (starttiem - Long.valueOf(System.currentTimeMillis())));


                    } else {

                        Thread.sleep(800);
                        String endPduCurrent = pduPlugCurrent(pdu, jobPduInfoSocket, "in");
                        System.out.println("插座增加负载后的电流值==" + endPduCurrent);

                        String endBreakerCurrent = pduPlugCurrent(pduBreakerTwo, breakerCon, "out");
                        System.out.println("空开增加负载后的电流值==" + endBreakerCurrent);

                        //关闭插座
                        Thread.sleep(100);
                        Thread threadPlugOff = new Thread(new PduActionThread(jobPduInfoSocket, pdu, "0"));
                        threadPlugOff.start();
                        threadPlugOff.join();
                        System.out.println("拉闸时间===" + (starttiem - Long.valueOf(System.currentTimeMillis())));

                    }
                }

                num++;

                if (num == 10) break;

                Thread.sleep(10000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * @Author:
     * @Description:获取插座电流
     * @Date:
     */

    public static String pduPlugCurrent(Pdu pdu, Socket socket, String action) {

        String startPduCurrent = "";
        String end = "";
        int num = 0;
        try {
//            startPduCurrent = "0";
            //获取当前设备的电流
            MessageDLT messageDLTPlugStart = new MessageDLT();
            messageDLTPlugStart.setMachineAddress(pdu.getMachineid());
            if (action.equals("in")) {
                messageDLTPlugStart.setDataStr("06FF0500");
            }
            if (action.equals("out")) {
                messageDLTPlugStart.setDataStr("02020100");
            }
//            messageDLTPlugStart.setDataStr("02020100");
            //拼接报文
            String startCurrentSendMesg = MessageStringDLTUtils.messageToHex(messageDLTPlugStart, "read");

            if (pdu.getType().equals("0001")) {
                end = "0D0A";
            }
            String startCurrentReadMesgPlug = "";

            while (startPduCurrent.equals("")) {
                System.out.println("发送电流报文==" + startCurrentSendMesg + end);
                System.out.println("socket IP = " + socket.getInetAddress().toString());
                SocketUtils.writeMsgToClient(socket.getOutputStream(), startCurrentSendMesg + end);//发送报文

                Thread.sleep(1000);
//                startCurrentReadMesgPlug = SocketUtils.readMessageFromClient(socket.getInputStream());//收到回复
                startCurrentReadMesgPlug = BaseController.readmsg;

                System.out.println("电流返回报文==" + startCurrentReadMesgPlug);
                if (startCurrentReadMesgPlug != null && !startCurrentReadMesgPlug.equals("")) {
                    if (startCurrentReadMesgPlug.substring(0, 8).equals("FEFEFEFE")) {
                        //解析报文 获取设备当前电流值
                        String current = MessageStringDLTUtils.receiveMessageToDate(startCurrentReadMesgPlug, messageDLTPlugStart);
                        System.out.println("电流值===" + current);
                        if (!current.equals("")) {
                            float currentF = Float.parseFloat(current);
                            currentF = (float) Math.round(currentF) / 1000;
                            if (currentF >= 0) {
                                startPduCurrent = String.valueOf(currentF);//获取电流
                            }
                        }
                    }
                }
                num++;
                if (num == 3) {
                    startPduCurrent = "0";
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return startPduCurrent;
    }


}
