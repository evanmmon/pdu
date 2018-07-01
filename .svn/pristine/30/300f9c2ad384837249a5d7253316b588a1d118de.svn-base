package com.chuangkou.pdu.thread;

import com.chuangkou.pdu.controller.BaseController;
import com.chuangkou.pdu.entity.MessageDLT;
import com.chuangkou.pdu.entity.Pdu;
import com.chuangkou.pdu.entity.PduInfo;
import com.chuangkou.pdu.entity.PduRelation;
import com.chuangkou.pdu.service.*;
import com.chuangkou.pdu.util.MessageStringDLTUtils;
import com.chuangkou.pdu.util.SocketUtils;
import org.springframework.jdbc.datasource.AbstractDriverBasedDataSource;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author:
 * @Description:
 * @Date:Created in 11:40 2018/6/6
 */
public class JobPduPlugThread implements Runnable {


    private static PduInfoService pduInfoService = (PduInfoService) SpringApplicationContextHolder.getSpringBean("pduInfoService");

    private static PduService pduService = (PduService) SpringApplicationContextHolder.getSpringBean("pduService");

    private static PduWarningSetService pduWarningSetService = (PduWarningSetService) SpringApplicationContextHolder.getSpringBean("pduWarningSetService");

    private static SceneService sceneService = (SceneService) SpringApplicationContextHolder.getSpringBean("sceneService");

    private static PduAutomaticService pduAutomaticService = (PduAutomaticService) SpringApplicationContextHolder.getSpringBean("pduAutomaticService");

    private static PduWarningService pduWarningService = (PduWarningService) SpringApplicationContextHolder.getSpringBean("pduWarningService");

    private static PduRelationService pduRelationService = (PduRelationService) SpringApplicationContextHolder.getSpringBean("pduRelationService");


    private static Socket plugThreadSocket = null;//和本线程相关的Socket
    private static Pdu pdu = null;

    public JobPduPlugThread(Socket socket, Pdu pdu) {
        this.plugThreadSocket = socket;
        this.pdu = pdu;
//        run();
    }


    @Override
    public void run() {
        try {
            Map startMap = new HashMap();
            Map endMap = new HashMap();

            String startBreakerCurrent = "0";
            String endBreakerCurrent = "0";
            String startPduCurrent = "0";
            String endPduCurrent = "0";

            //当前插座电流
            Thread.sleep(500);
            startPduCurrent = pduPlugCurrent(pdu, plugThreadSocket,"out");
            System.out.println("当前插座电流==" + startPduCurrent);

            Pdu pduBreaker = new Pdu();
            pduBreaker.setType("180");
            List<Pdu> pduListTwo = pduService.selectAllBySearch(pduBreaker);

            for (int y = 0; y < pduListTwo.size(); y++) {
                Thread.sleep(10000);
                Pdu pduBreakerTwo = new Pdu();
                pduBreakerTwo = pduListTwo.get(y);

                String conName = pduBreakerTwo.getIp();
                Socket breakerCon = null;
                //获取连接对象

                breakerCon = (Socket) BaseController.SubPolmap.get(conName);
                //获取空开当前的电流
//                Thread.sleep(500);
                startBreakerCurrent = pduPlugCurrent(pduBreakerTwo, breakerCon,"out");
                System.out.println("当前空开电流==" + startBreakerCurrent);

                //打开插座
//                relayPdu = pduService.selectByPrimaryKey(pdu.getId());
//                String ip = pdu.getIp();
//                Socket relayCon = null;
//                //获取连接对象
//                relayCon = (Socket) BaseController.SubPolmap.get(ip);
                long starttime = System.currentTimeMillis();
//                System.out.println("开始时间=================" + System.currentTimeMillis());
                Thread.sleep(100);
                Thread threadPlug = new Thread(new PduActionThread(plugThreadSocket, pdu, "1"));
                threadPlug.start();
                threadPlug.join();
                pdu.setActionState("1");
                pduService.update(pdu);
                System.out.println("已开启插座设备" + pdu.getMachineid());

                //再次获取插座电流
                Thread.sleep(1000);
                endPduCurrent = pduPlugCurrent(pdu, plugThreadSocket,"out");
                System.out.println("插座增加负载后的电流值==" + endPduCurrent);

                Float pduCurrentDifference = Float.parseFloat(endPduCurrent) - Float.parseFloat(startPduCurrent);
                System.out.println("插座设备电流差值==" + pduCurrentDifference);

                Thread.sleep(1000);
                endBreakerCurrent = pduPlugCurrent(pduBreakerTwo, breakerCon,"out");
                System.out.println("空开增加负载后的电流值==" + endBreakerCurrent);

                Float breakerCurrentDifference = Float.parseFloat(endBreakerCurrent) - Float.parseFloat(startBreakerCurrent);
                System.out.println("空开设备电流差值==" + breakerCurrentDifference);

                //关闭插座
                Thread.sleep(100);
                Thread threadPlugOff = new Thread(new PduActionThread(plugThreadSocket, pdu, "0"));
                threadPlugOff.start();
                threadPlugOff.join();
                pdu.setActionState("1");
                pduService.update(pdu);
                System.out.println("已关闭插座设备" + pdu.getMachineid());
                System.out.println("总共用时=================" + (System.currentTimeMillis() - starttime));

                //判断空开的差值与插座的差值是否在0.1的范围内
                Float difference = Math.abs(pduCurrentDifference - breakerCurrentDifference);
                if (difference < 0.1) {
                    //如果差值与插座差值相同，则保存空开与插座的关系
                    PduRelation pduRelation = new PduRelation();
                    pduRelation.setPduID(pdu.getId());
                    pduRelation.setSwitchPduID(pduBreakerTwo.getId());
                    pduRelation.setParent_id("");
                    System.out.println("记录插座与空开关系");

                    PduRelation pduRelationTwo = new PduRelation();
                    pduRelationTwo = pduRelationService.selectByPrimaryKey(pdu.getId());

                    if (pduRelationTwo == null) {
                        System.out.println("parentID===" + pduRelation.getParent_id());
                        pduRelationService.insert(pduRelation);

                    } else {
                        System.out.println("parentID===" + pduRelation.getParent_id());
                        pduRelationService.updateByPrimaryKeySelective(pduRelation);
                    }
                    System.out.println("匹配合适的空开！！！！！！！！！！！！！！！！！！！！" + pduBreakerTwo.getId());
                    break;
                } else {
                    System.out.println("****************************************************" + pduBreakerTwo.getId());

                }

            }

        } catch (IOException e) {

            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @Author:
     * @Description:循环获取空开的电流
     * @Date:
     */

    public static Map allBreakerCurrent(String type, Socket socket) {
        Map startMap = new HashMap();
        String breakerCurrent = "0";
        try {
            //循环所有空开,并且获取空开的电流
            Pdu pduBreaker = new Pdu();
            pduBreaker.setType("180");
            List<Pdu> pduListTwo = pduService.selectAllBySearch(pduBreaker);

            for (int y = 0; y < pduListTwo.size(); y++) {
                Pdu pduBreakerTwo = new Pdu();
                pduBreakerTwo = pduListTwo.get(y);

                String machineID = pduBreakerTwo.getMachineid();

                MessageDLT messageDLTBreaker = new MessageDLT();
                messageDLTBreaker.setMachineAddress(machineID);

                messageDLTBreaker.setDataStr("02020100");
                String currentreadmesg = "";
                //拼接报文
                String currentsendmesg = MessageStringDLTUtils.messageToHex(messageDLTBreaker, "read");

                SocketUtils.writeMsgToClient(socket.getOutputStream(), currentsendmesg);//发送报文

                Thread.sleep(1000);
                currentreadmesg = SocketUtils.readMessageFromClient(socket.getInputStream());//收到回复

                System.out.println("返回电流报文：" + currentreadmesg);

                PduInfo pduInfoBreaker = new PduInfo();
                if (!currentreadmesg.equals("")) {
                    if (currentreadmesg.substring(0, 8).equals("FEFEFEFE")) {
                        //解析报文 获取设备当前电流值
                        String current = MessageStringDLTUtils.receiveMessageToDate(currentreadmesg, messageDLTBreaker);
                        System.out.println("电流值===" + current);
                        if (!current.equals("")) {
                            float currentF = Float.parseFloat(current);
                            currentF = (float) Math.round(currentF) / 1000;
                            if (currentF > 0) {
                                breakerCurrent = String.valueOf(currentF);
                            }
                        }
                    }
                }

//                //所有空开的电流
//                startMap.put(pduBreakerTwo.getId(), pduInfoBreaker.getCurrent());

                //开启插座
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return startMap;
    }

    /**
     * @Author:
     * @Description:匹配计算电流差值与插座相同的空开
     * @Date:
     */
    public static int allBreakerCurrentMatching(String type, Socket socket, Map startMap, String pduCurrentDifference) {

        int breakerID = 0;
        String pduCurrent = "0";

//       Map startMap = new HashMap();
        try {
            //循环所有空开,并且获取空开的电流
            Pdu pduBreaker = new Pdu();
            pduBreaker.setType("180");
            List<Pdu> pduListTwo = pduService.selectAllBySearch(pduBreaker);

            for (int y = 0; y < pduListTwo.size(); y++) {
                Pdu pduBreakerTwo = new Pdu();
                pduBreakerTwo = pduListTwo.get(y);

                String machineID = pduBreakerTwo.getMachineid();

                MessageDLT messageDLTBreaker = new MessageDLT();
                messageDLTBreaker.setMachineAddress(machineID);

                messageDLTBreaker.setDataStr("02020100");
                String currentreadmesg = "";
                //拼接报文
                String currentsendmesg = MessageStringDLTUtils.messageToHex(messageDLTBreaker, "read");

                SocketUtils.writeMsgToClient(socket.getOutputStream(), currentsendmesg);//发送报文
                Thread.sleep(1000);
                currentreadmesg = SocketUtils.readMessageFromClient(socket.getInputStream());//收到回复

                System.out.println("返回电流报文：" + currentreadmesg);

                PduInfo pduInfoBreaker = new PduInfo();
                if (!currentreadmesg.equals("")) {
                    if (currentreadmesg.substring(0, 8).equals("FEFEFEFE")) {
                        //解析报文 获取设备当前电流值
                        String current = MessageStringDLTUtils.receiveMessageToDate(currentreadmesg, messageDLTBreaker);
                        System.out.println("电流值===" + current);
                        if (!current.equals("")) {
                            float currentF = Float.parseFloat(current);
                            currentF = (float) Math.round(currentF) / 1000;
                            if (currentF > 0) {
                                pduCurrent = String.valueOf(currentF);
                            }
                        }
                    }
                }

                String currentValue = (String) startMap.get(pduBreakerTwo.getId());
                Float breakerCurrentDifference = Float.parseFloat(pduCurrent) - Float.parseFloat(currentValue);

                //判断空开的差值与插座的差值是否一致
                if (pduCurrentDifference.equals(breakerCurrentDifference)) {
                    //如果一致则返回当前空开的ID
                    breakerID = pduBreakerTwo.getId();
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return breakerID;
    }

    /**
     * @Author:
     * @Description:获取插座电流
     * @Date:
     */

    public static String pduPlugCurrent(Pdu pdu, Socket socket,String action) {

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
