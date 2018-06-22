package com.chuangkou.pdu.thread;

import com.chuangkou.pdu.controller.BaseController;
import com.chuangkou.pdu.entity.MessageDLT;
import com.chuangkou.pdu.entity.Pdu;
import com.chuangkou.pdu.entity.PduInfo;
import com.chuangkou.pdu.service.PduInfoService;
import com.chuangkou.pdu.service.PduService;
import com.chuangkou.pdu.util.MessageStringDLTUtils;
import com.chuangkou.pdu.util.SocketUtils;

import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Author:
 * @Description:
 * @Date:Created in 10:13 2018/4/22
 */
public class JobPduInfoClockTaskThread implements Runnable {

    Socket socket = null;//和本线程相关的Socket
    Pdu pdu = null;

    private static int num = 0;


    private static PduInfoService pduInfoService = (PduInfoService) SpringApplicationContextHolder.getSpringBean("pduInfoService");
    private static PduService pduService = (PduService) SpringApplicationContextHolder.getSpringBean("pduService");


    public JobPduInfoClockTaskThread(Socket socket, Pdu pdu) {
        this.socket = socket;
        this.pdu = pdu;
//        run();
    }


    @Override
    public void run() {
//    public static void jobPduInfoClock(Socket socket, List<Pdu> pduList) {
        //循环所有的设备，如果不属于此链接下的设备，将不会执行命令
//        for (int i = 0; i < pduList.size(); i++) {
//            Pdu pdu = new Pdu();
        PduInfo pduInfo = new PduInfo();
//            pdu = pduList.get(i);
        String machineID = pdu.getMachineid();
//        String machineID = "1803140001";
//            pdu = pduService.selectByMachineID(machineID);
//        System.out.println("machineID===" + machineID);
        String pdutype = pdu.getType();
        String end = "";
        if (machineID.length() > 10) {
            end = "0d0a";
        }
        pduInfo.setPduid(pdu.getId());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        pduInfo.setCollectiontime(df.format(System.currentTimeMillis()));

        try {
            MessageDLT messageDLT = new MessageDLT();
            messageDLT.setMachineAddress(machineID);
            messageDLT.setDataStr("02010100");//电压
            //拼接报文
            num += 1;
//            System.out.println("num====" + num);
            String voltagesendmesg = MessageStringDLTUtils.messageToHex(messageDLT, "read");
            System.out.println(num + "发送电压报文：" + voltagesendmesg + end);
            String voltagereadmesg = "";
//            Thread.sleep(1000);
//            System.out.println("socket地址：===" + socket.getInetAddress().toString());
            SocketUtils.writeMsgToClient(socket.getOutputStream(), voltagesendmesg + end);//发送报文
            Thread.sleep(1000);
            voltagereadmesg = BaseController.readmsg;
            BaseController.readmsg = "";
//            voltagereadmesg = SocketUtils.readMessageFromClient(socket.getInputStream());//收到回复
//            while (voltagereadmesg.equals("")) {
//
//                System.out.println("返回电压报文为空重新获取");
//                SocketUtils.writeMsgToClient(socket.getOutputStream(), voltagesendmesg + end);
//                Thread.sleep(500);
//                voltagereadmesg = SocketUtils.readMessageFromClient(socket.getInputStream());
//            }

            System.out.println("返回电压报文：" + voltagereadmesg);

            //解析报文 获取设备当前电压值
            System.out.println("旧数据标===" + MessageStringDLTUtils.dateIDhex(messageDLT.getDataStr()));

            if (!voltagereadmesg.equals("")) {
//                if (voltagereadmesg.substring(0, 2).equals("68")) {//接收到设备上电报文，则进行上电处理
//                    onlineAction(socket, voltagereadmesg);
//                }

                if (voltagereadmesg.substring(0, 8).equals("FEFEFEFE")) {
                    String voltage = MessageStringDLTUtils.receiveMessageToDate(voltagereadmesg, messageDLT);
                    System.out.println("电压值===" + voltage);

                    if (!voltage.equals("")) {
                        float voltageF = Float.parseFloat(voltage);
                        voltageF = (float) Math.round(voltageF) / 10;

                        if (voltageF > 0) {
                            pduInfo.setVoltage(String.valueOf(voltageF));
                        } else {
                            pduInfo.setVoltage("0");
                        }
                    } else {
                        pduInfo.setVoltage("0");
                    }
                } else {
                    pduInfo.setVoltage("");
                }
            } else {
                pduInfo.setVoltage("");
            }


            Thread.sleep(1000);
            //获取电流数据
            messageDLT.setDataStr("02020100");
            String currentreadmesg = "";
            //拼接报文
            String currentsendmesg = MessageStringDLTUtils.messageToHex(messageDLT, "read");
            System.out.println("发送电流报文：" + currentsendmesg);
            SocketUtils.writeMsgToClient(socket.getOutputStream(), currentsendmesg + end);//发送报文

            Thread.sleep(1000);
            currentreadmesg = BaseController.readmsg;
            BaseController.readmsg = "";

//            currentreadmesg = SocketUtils.readMessageFromClient(socket.getInputStream());//收到回复

//            while (currentreadmesg.equals("")) {
//                System.out.println("返回电流报文为空重新获取");
//                SocketUtils.writeMsgToClient(socket.getOutputStream(), currentsendmesg + end);
//                Thread.sleep(500);
//                currentreadmesg = SocketUtils.readMessageFromClient(socket.getInputStream());
//            }
            System.out.println("返回电流报文：" + currentreadmesg);

            if (!currentreadmesg.equals("")) {
//                if (currentreadmesg.substring(0, 2).equals("68")) {//接收到设备上电报文，则进行上电处理
//                    onlineAction(socket, currentreadmesg);
//                }
                if (currentreadmesg.substring(0, 8).equals("FEFEFEFE")) {
                    //解析报文 获取设备当前电流值
                    String current = MessageStringDLTUtils.receiveMessageToDate(currentreadmesg, messageDLT);
                    System.out.println("电流值===" + current);
                    if (!current.equals("")) {
                        float currentF = Float.parseFloat(current);
                        currentF = (float) Math.round(currentF) / 1000;
                        if (currentF > 0) {
                            pduInfo.setCurrent(String.valueOf(currentF));
                        } else {
                            pduInfo.setCurrent("0");
                        }
                    } else {
                        pduInfo.setCurrent("0");
                    }
                } else {
                    pduInfo.setCurrent("");
                }
            } else {
                pduInfo.setCurrent("");
            }


            Thread.sleep(1000);
            //获取有功功率数据
            messageDLT.setDataStr("02030100");
            String wattreadmesg = "";
            //拼接报文
            String wattsendmesg = MessageStringDLTUtils.messageToHex(messageDLT, "read");
            System.out.println("发送功率报文：" + wattsendmesg + end);
            SocketUtils.writeMsgToClient(socket.getOutputStream(), wattsendmesg + end);//发送报文

            Thread.sleep(1000);
            wattreadmesg = BaseController.readmsg;
            BaseController.readmsg = "";

//            wattreadmesg = SocketUtils.readMessageFromClient(socket.getInputStream());//收到回复

//            while (wattreadmesg.equals("")) {
//                System.out.println("返回功率报文为空重新获取");
//                SocketUtils.writeMsgToClient(socket.getOutputStream(), wattsendmesg + end);
//                Thread.sleep(500);
//                wattreadmesg = SocketUtils.readMessageFromClient(socket.getInputStream());//收到回复
//            }
            System.out.println("返回功率报文：" + wattreadmesg);
            if (!wattreadmesg.equals("")) {
//                if (wattreadmesg.substring(0, 2).equals("68")) {//接收到设备上电报文，则进行上电处理
//                    onlineAction(socket, wattreadmesg);
//                }
                if (wattreadmesg.substring(0, 8).equals("FEFEFEFE")) {
                    //解析报文 获取设备当前功率值
                    String watt = MessageStringDLTUtils.receiveMessageToDate(wattreadmesg, messageDLT);
                    System.out.println("功率值===" + watt);

                    if (!watt.equals("")) {
                        float wattF = Float.parseFloat(watt) / 10;
//                            int wattI = (Integer) Math.round(wattF);
                        if (wattF > 0) {
                            pduInfo.setWatt(String.valueOf(wattF));
                        } else {
                            pduInfo.setWatt("0");
                        }
                    } else {
                        pduInfo.setWatt("0");
                    }
                } else {
                    pduInfo.setWatt("");
                }
            } else {
                pduInfo.setWatt("");
            }

//            System.out.println("设备校时");
//            Thread threadTime = new Thread(new JobPduDateTimeSetThread(socket, machineID));
//            threadTime.start();
//            threadTime.join();

            //采集实时电量
//            if (pdutype.equals("180")) {//目前暂时只采集空开设备的用电量
            messageDLT.setDataStr("00000000");
//                        messageDLT.setDataStr("00000000");//空开设备的电量数据表
            String quantityreadmesg = "";
            //拼接报文
            String quantitysendmesg = MessageStringDLTUtils.messageToHex(messageDLT, "read");
            System.out.println("发送电量报文：" + quantitysendmesg);
//                        Thread.sleep(500);
            SocketUtils.writeMsgToClient(socket.getOutputStream(), quantitysendmesg + end);//发送报文

            Thread.sleep(1000);
            quantityreadmesg = BaseController.readmsg;
            BaseController.readmsg = "";

//                quantityreadmesg = SocketUtils.readMessageFromClient(socket.getInputStream());//收到回复


//                while (quantityreadmesg.equals("")) {
//                    System.out.println("重新获取电量！");
//                    SocketUtils.writeMsgToClient(socket.getOutputStream(), quantitysendmesg + end);
//                    Thread.sleep(500);
//                    quantityreadmesg = SocketUtils.readMessageFromClient(socket.getInputStream());
//                }

//                        System.out.println("返回电量报文：" + quantityreadmesg);
//                        System.out.println("旧数据标===" + MessageStringDLTUtils.dateIDhex(messageDLT.getDataStr()));
            if (!quantityreadmesg.equals("")) {
//                            if (quantityreadmesg.substring(0, 2).equals("68")) {//接收到设备上电报文，则进行上电处理
//                                onlineAction(socket, quantityreadmesg);
//                            }
                if (quantityreadmesg.substring(0, 8).equals("FEFEFEFE")) {
                    //解析报文 获取设备当前电量
                    String quantity = MessageStringDLTUtils.receiveMessageToDate(quantityreadmesg, messageDLT);
                    System.out.println("电量值===" + quantity);

                    if (!quantity.equals("")) {
                        float quantityF = 0;
                        if (!quantity.equals("00000000")) {
                            quantityF = Float.parseFloat(quantity) / 10;
                        }
                        if (quantityF > 0) {
                            pduInfo.setQuantity(String.valueOf(quantityF));
                        } else {
                            pduInfo.setQuantity("0");
                        }
                    } else {
                        pduInfo.setQuantity("0");
                    }
                } else {
                    pduInfo.setQuantity("");
                }
            } else {
                pduInfo.setQuantity("");
            }
//            }


            if (!pduInfo.getVoltage().equals("") && !pduInfo.getCurrent().equals("") && !pduInfo.getWatt().equals("")) {

                //判断设备预警状态，与设备预警值比较
                PduInfo pduInfoWarning = new PduInfo();
                pduInfoWarning = ThreadUtils.getJudgementWarning(pduInfo);

                pduInfo.setOvervoltage(pduInfoWarning.getOvervoltage());
                pduInfo.setUndervoltage(pduInfoWarning.getUndervoltage());
                pduInfo.setOvercurrent(pduInfoWarning.getOvercurrent());
                pduInfo.setCircuitbreaker("0");
                pduInfo.setElectricleakage("0");
                pduInfo.setTemperature("0");
                pduInfo.setSmoke("0");
                pduInfo.setWaterLevel("0");


                pduInfoService.insertSelective(pduInfo);//添加采集信息
                System.out.println("设备:" + pdu.getId() + " 信息采集成功！");

                //主要针对空开设备，将集中器的IP地址赋值给空开设备

                ThreadUtils.updatePduIpDLT(messageDLT, socket);

                pduInfo.setPduid(pdu.getId());
//                if(pduInfo.getPduid().equals("34")) {
                //判断设备状态是否符合场景要求，如果符合出发断电操作
                ThreadUtils.scentJudgement(pduInfo);
//                }
            }
//            socket1.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static void jobPduInfoClockOne(Socket socket, Pdu pdu) {
        //循环所有的设备，如果不属于此链接下的设备，将不会执行命令
//        for (int i = 0; i <= pduList.size(); i++) {
//        Pdu pdu = new Pdu();
        PduInfo pduInfo = new PduInfo();
//            pdu = pduList.get(i);
        String machineID = pdu.getMachineid();
//        String machineID = "1803140001";
//        pdu = pduService.selectByMachineID(machineID);
        String pdutype = pdu.getType();
        if (!pdutype.equals("0000")) {
            pduInfo.setPduid(pdu.getId());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            pduInfo.setCollectiontime(df.format(System.currentTimeMillis()));

            try {
                MessageDLT messageDLT = new MessageDLT();
                messageDLT.setMachineAddress(machineID);
                messageDLT.setDataStr("02010100");//电压
                //拼接报文
                num += 1;
//                System.out.println("num====" + num);
                String voltagesendmesg = MessageStringDLTUtils.messageToHex(messageDLT, "read");
                System.out.println(num + "发送电压报文：" + voltagesendmesg);
                String voltagereadmesg = "";
                Thread.sleep(500);
                SocketUtils.writeMsgToClient(socket.getOutputStream(), voltagesendmesg);//发送报文
                Thread.sleep(1000);
                voltagereadmesg = SocketUtils.readMessageFromClient(socket.getInputStream());//收到回复
                while (voltagereadmesg.equals("")) {
                    Thread.sleep(1000);
                    System.out.println("返回电压报文为空重新获取");
                    voltagereadmesg = SocketUtils.readMessageFromClient(socket.getInputStream());
                }
                System.out.println("返回电压报文：" + voltagereadmesg);

                //解析报文 获取设备当前电压值
                System.out.println("旧数据标===" + MessageStringDLTUtils.dateIDhex(messageDLT.getDataStr()));
                String voltage = MessageStringDLTUtils.receiveMessageToDate(voltagereadmesg, messageDLT);
                System.out.println("电压值===" + voltage);
                pduInfo.setVoltage(voltage);


                //获取电流数据
                messageDLT.setDataStr("02020100");
                String currentreadmesg = "";
                //拼接报文
                String currentsendmesg = MessageStringDLTUtils.messageToHex(messageDLT, "read");
                System.out.println("发送电流报文：" + currentsendmesg);

                Thread.sleep(500);
                SocketUtils.writeMsgToClient(socket.getOutputStream(), currentsendmesg);//发送报文
                Thread.sleep(1000);
                currentreadmesg = SocketUtils.readMessageFromClient(socket.getInputStream());//收到回复

                while (currentreadmesg.equals("")) {
                    Thread.sleep(1000);
                    System.out.println("返回电流报文为空重新获取");
                    currentreadmesg = SocketUtils.readMessageFromClient(socket.getInputStream());
                }

                System.out.println("返回电流报文：" + currentreadmesg);
                System.out.println("旧数据标===" + MessageStringDLTUtils.dateIDhex(messageDLT.getDataStr()));

                //解析报文 获取设备当前电流值
                String current = MessageStringDLTUtils.receiveMessageToDate(currentreadmesg, messageDLT);
                System.out.println("电流值===" + current);
                pduInfo.setCurrent(current);

                //获取有功功率数据
                messageDLT.setDataStr("02030100");
                String wattreadmesg = "";
                //拼接报文
                String wattsendmesg = MessageStringDLTUtils.messageToHex(messageDLT, "read");
                System.out.println("发送功率报文：" + wattsendmesg);
                Thread.sleep(500);
                SocketUtils.writeMsgToClient(socket.getOutputStream(), wattsendmesg);//发送报文
                Thread.sleep(1000);
                wattreadmesg = SocketUtils.readMessageFromClient(socket.getInputStream());//收到回复
                while (wattreadmesg.equals("")) {
                    Thread.sleep(1000);
                    System.out.println("返回功率报文为空重新获取");
                    wattreadmesg = SocketUtils.readMessageFromClient(socket.getInputStream());//收到回复
                }
                System.out.println("返回功率报文：" + wattreadmesg);
                System.out.println("旧数据标===" + MessageStringDLTUtils.dateIDhex(messageDLT.getDataStr()));
                //解析报文 获取设备当前功率值
                String watt = MessageStringDLTUtils.receiveMessageToDate(wattreadmesg, messageDLT);
                System.out.println("功率值===" + watt);
                pduInfo.setWatt(watt);

                if (!pduInfo.getVoltage().equals("") && !pduInfo.getCurrent().equals("") && !pduInfo.getWatt().equals("")) {

                    //判断设备预警状态，与设备预警值比较
                    PduInfo pduInfoWarning = new PduInfo();
                    pduInfoWarning = ThreadUtils.getJudgementWarning(pduInfo);

                    pduInfo.setOvervoltage(pduInfoWarning.getOvervoltage());
                    pduInfo.setUndervoltage(pduInfoWarning.getUndervoltage());
                    pduInfo.setOvercurrent(pduInfoWarning.getOvercurrent());
                    pduInfo.setCircuitbreaker("0");
                    pduInfo.setElectricleakage("0");
                    pduInfo.setTemperature("0");
                    pduInfo.setSmoke("0");
                    pduInfo.setWaterLevel("0");
                    pduInfo.setRelaystate(pdu.getActionState());

                    pduInfoService.insertSelective(pduInfo);//添加采集信息
                    System.out.println("设备:" + pdu.getId() + " 信息采集成功！");

//                    pduService.updateByPrimaryKey(pdu);//修改主表设备的继电器状态；
                    //判断设备状态是否符合场景要求，如果符合出发断电操作
                    ThreadUtils.scentJudgement(pduInfo);

                }
//            socket1.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        }


    }

    public static void onlineAction(Socket socket, String voltagereadmesg) throws IOException {

        MessageDLT messageDLTOnline = new MessageDLT();
        messageDLTOnline = MessageStringDLTUtils.onlineMessage(voltagereadmesg);

        if (messageDLTOnline.getDataTab().equals("3C3C3239") && messageDLTOnline.getDataStr().equals("55555555")) {
            //修改IP地址
            String ip = socket.getInetAddress().toString();
            ip = ip.substring(1, ip.length());
            String updateAction = ThreadUtils.updatePduIpDLT(messageDLTOnline, ip, socket);

            //回复修改地址成功
            if (updateAction.equals("ok")) {
//                    String receivemessage = MessageStringDLTUtils.receiveUpdateToIp(messageDLTOnline);
//                    System.out.println("回复DLT报文：" + receivemessage);
//                    ThreadUtils.writeMsgToClient(socket.getOutputStream(), receivemessage);
            }
        }


    }

}
