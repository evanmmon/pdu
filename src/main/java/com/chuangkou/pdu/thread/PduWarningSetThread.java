package com.chuangkou.pdu.thread;

import com.chuangkou.pdu.controller.BaseController;
import com.chuangkou.pdu.entity.MessageDLT;
import com.chuangkou.pdu.entity.Pdu;
import com.chuangkou.pdu.entity.PduWarningSet;
import com.chuangkou.pdu.service.PduService;
import com.chuangkou.pdu.service.PduWarningSetService;
import com.chuangkou.pdu.util.MessageStringDLTUtils;

import java.io.IOException;
import java.net.Socket;
import java.text.DecimalFormat;

/**
 * @Author:
 * @Description:
 * @Date:Created in 18:03 2018/5/16
 */
public class PduWarningSetThread implements Runnable {

    Socket socket = null;//和本线程相关的Socket
    PduWarningSet pduWarningSet = null;
    int type;

    private static PduService pduService = (PduService) SpringApplicationContextHolder.getSpringBean("pduService");

    private static PduWarningSetService pduWarningSetService = (PduWarningSetService) SpringApplicationContextHolder.getSpringBean("pduWarningSetService");


    public PduWarningSetThread(Socket socket, PduWarningSet pduWarningSet, int type) {
        this.socket = socket;
        this.pduWarningSet = pduWarningSet;
        this.type = type;
//        run();
    }

    @Override
    public void run() {
        try {
            Pdu warningPdu = new Pdu();
            warningPdu = pduService.selectByPrimaryKey(pduWarningSet.getPduid());
            pduWarningSet = pduWarningSetService.selectByPduWarningSet(pduWarningSet.getPduid());
            String warningSetMessage = "";

            MessageDLT messageDLT = new MessageDLT();
            MessageDLT receiverMessageDLT = new MessageDLT();

            messageDLT.setMachineAddress(warningPdu.getMachineid());
            String machineid = MessageStringDLTUtils.addZeroForNumLeft(messageDLT.getMachineAddress(), 12);
            messageDLT.setControl("14");
            messageDLT.setPassword(BaseController.DLTpassword);
            messageDLT.setAuth(BaseController.DLTcontrol);
            String end = "";
            if(!warningPdu.getType().equals("180")){
                end = "0D0A";
            }

            float dataStr;
            String receiverDataTab = "";
            String newaddress = "";
            switch (type) {
                case 2://设置功率阀值
                    float wattamplitude = pduWarningSet.getWattamplitude();
                    float setWatt = Float.valueOf(pduWarningSet.getWatt());

                    dataStr = (setWatt * wattamplitude) + setWatt;
                    String wattStr = new DecimalFormat("##,##0.0").format(dataStr);
                    wattStr = wattStr.replace(".", "");
//                    wattStr = MessageStringDLTUtils.dateIDhex(wattStr);

                    messageDLT.setDataStr(wattStr);

                    messageDLT.setDataTab("06FF0100");
                    warningSetMessage = MessageStringDLTUtils.messageToHex(messageDLT, "write");
                    //先发送功率阀值报文
                    ThreadUtils.writeMsgToClient(socket.getOutputStream(), warningSetMessage+end);
//                    warningSetMessage = ThreadUtils.readMessageFromClient(socket.getInputStream());
                    warningSetMessage = SubPolThread.getReadMsg();
                    //收到回复
//                    while (warningSetMessage.equals("")) {
//                        System.out.println("重新设置功率阀值");
//                        ThreadUtils.writeMsgToClient(socket.getOutputStream(), warningSetMessage);
//                        warningSetMessage = ThreadUtils.readMessageFromClient(socket.getInputStream());
//
//                    }
                    receiverMessageDLT = MessageStringDLTUtils.onlineMessage(warningSetMessage);
                    newaddress = MessageStringDLTUtils.machineAddressHexOpposite(receiverMessageDLT.getMachineAddress());
                    //判断返回是否正确
                    if (receiverMessageDLT.getControl().equals("94") && machineid.equals(newaddress)) {
                        System.out.println("设置功率阀值成功！");
                    }

                    break;
                case 3://设置电压阀值

//                    messageDLT.setMachineAddress(warningPdu.getMachineid());
//                    messageDLT.setControl("14");
//                    messageDLT.setPassword(BaseController.DLTpassword);
//                    messageDLT.setAuth(BaseController.DLTcontrol);

                    //计算过压阀值数据

                    float voltageAmplitude = pduWarningSet.getSetingvoltage();
                    float setVoltage = Float.parseFloat(pduWarningSet.getVoltage());
                    dataStr = (setVoltage * voltageAmplitude) + setVoltage;
                    String overVoltageStr = new DecimalFormat("##,##0.0").format(dataStr);
                    overVoltageStr = overVoltageStr.replace(".", "");

                    messageDLT.setDataStr(overVoltageStr);

                    messageDLT.setDataTab("04FF0101");
                    warningSetMessage = MessageStringDLTUtils.messageToHex(messageDLT, "write");

                    System.out.println(warningSetMessage);
                    //先发送过压阀值
                    ThreadUtils.writeMsgToClient(socket.getOutputStream(), warningSetMessage+end);
                    //收到回复
//                    warningSetMessage = ThreadUtils.readMessageFromClient(socket.getInputStream());
                    warningSetMessage = SubPolThread.getReadMsg();
//                    while (warningSetMessage.equals("")) {
//                        System.out.println("重新设置过压阀值");
//                        ThreadUtils.writeMsgToClient(socket.getOutputStream(), warningSetMessage);
//                        warningSetMessage = ThreadUtils.readMessageFromClient(socket.getInputStream());
//
//                    }
                    receiverMessageDLT = MessageStringDLTUtils.onlineMessage(warningSetMessage);
                    newaddress = MessageStringDLTUtils.machineAddressHexOpposite(receiverMessageDLT.getMachineAddress());
                    //判断返回是否正确
                    if (receiverMessageDLT.getControl().equals("94") && machineid.equals(newaddress)) {
                        System.out.println("设置过压阀值成功！");
                    }


                    //欠压阀值报文
                    messageDLT.setDataTab("04FF0201");
                    dataStr = (setVoltage * voltageAmplitude) - setVoltage;
                    String unVoltageStr = new DecimalFormat("##,##0.0").format(dataStr);
                    unVoltageStr = unVoltageStr.replace(".", "");
//                    System.out.println("unVoltageStr==" + unVoltageStr);
                    messageDLT.setDataStr(unVoltageStr);
                    warningSetMessage = MessageStringDLTUtils.messageToHex(messageDLT, "write");

                    System.out.println("warningSetMessage==" + warningSetMessage);
                    ThreadUtils.writeMsgToClient(socket.getOutputStream(), warningSetMessage+end);
                    //收到回复
//                    warningSetMessage = ThreadUtils.readMessageFromClient(socket.getInputStream());
                    warningSetMessage = SubPolThread.getReadMsg();

//                    while (warningSetMessage.equals("")) {
//                        System.out.println("重新设置欠压阀值");
//                        ThreadUtils.writeMsgToClient(socket.getOutputStream(), warningSetMessage);
//                        warningSetMessage = ThreadUtils.readMessageFromClient(socket.getInputStream());
//
//                    }
                    receiverMessageDLT = MessageStringDLTUtils.onlineMessage(warningSetMessage);
                    newaddress = MessageStringDLTUtils.machineAddressHexOpposite(receiverMessageDLT.getMachineAddress());
                    //判断返回是否正确
                    if (receiverMessageDLT.getControl().equals("94") && machineid.equals(newaddress)) {
                        System.out.println("设置欠压阀值成功！");
                    }
                    break;
                case 5:

                    //过流阀值报文
                    messageDLT.setDataTab("04FF0502");
                    float currentAmplitude = pduWarningSet.getCurrentamplitude();
                    float setCurrent = Float.valueOf(pduWarningSet.getCurrent());
                    dataStr = (setCurrent * currentAmplitude) + setCurrent;
                    String overCurrentStr = new DecimalFormat("##0.000").format(dataStr);
                    overCurrentStr = overCurrentStr.replace(".", "");
                    overCurrentStr = MessageStringDLTUtils.addZeroForNumLeft(overCurrentStr, 8);


                    messageDLT.setDataStr(overCurrentStr);
                    warningSetMessage = MessageStringDLTUtils.messageToHex(messageDLT, "write");
                    ThreadUtils.writeMsgToClient(socket.getOutputStream(), warningSetMessage + end);
                    //收到回复
//                    warningSetMessage = ThreadUtils.readMessageFromClient(socket.getInputStream());
                    warningSetMessage = SubPolThread.getReadMsg();
//                    while (warningSetMessage.equals("")) {
//                        System.out.println("重新设置过流阀值");
//                        ThreadUtils.writeMsgToClient(socket.getOutputStream(), warningSetMessage);
//                        warningSetMessage = ThreadUtils.readMessageFromClient(socket.getInputStream());
//
//                    }
                    receiverMessageDLT = MessageStringDLTUtils.onlineMessage(warningSetMessage);
                    newaddress = MessageStringDLTUtils.machineAddressHexOpposite(receiverMessageDLT.getMachineAddress());
                    //判断返回是否正确
                    if (receiverMessageDLT.getControl().equals("94") && machineid.equals(newaddress)) {
                        System.out.println("设置过流阀值成功！");
                    }

                    break;
                case 6:
                    //功率过大启动延时报文
                    messageDLT.setDataTab("06FF0101");
//                    messageDLT.setDataStr("10");
                    String powerStartDelay = MessageStringDLTUtils.addZeroForNumLeft(String.valueOf(pduWarningSet.getPowerStartDelay()),4);
                    messageDLT.setDataStr(powerStartDelay);
                    warningSetMessage = MessageStringDLTUtils.messageToHex(messageDLT, "write");

                    ThreadUtils.writeMsgToClient(socket.getOutputStream(), warningSetMessage + end);
                    //收到回复
//                    warningSetMessage = ThreadUtils.readMessageFromClient(socket.getInputStream());
                    warningSetMessage = SubPolThread.getReadMsg();
//                    while (warningSetMessage.equals("")) {
//                        System.out.println("重新设置功率过大启动延时");
//                        ThreadUtils.writeMsgToClient(socket.getOutputStream(), warningSetMessage);
//                        warningSetMessage = ThreadUtils.readMessageFromClient(socket.getInputStream());
//
//                    }
                    receiverMessageDLT = MessageStringDLTUtils.onlineMessage(warningSetMessage);
                    newaddress = MessageStringDLTUtils.machineAddressHexOpposite(receiverMessageDLT.getMachineAddress());
                    //判断返回是否正确
                    if (receiverMessageDLT.getControl().equals("94") && machineid.equals(newaddress)) {
                        System.out.println("设置功率过大启动延时成功！");
                    }
                    break;
                case 7:
                    //功率过大回复延时时间
                    messageDLT.setDataTab("06FF0102");
//                    messageDLT.setDataStr("30");
                    String powerResumeDelay = MessageStringDLTUtils.addZeroForNumLeft(String.valueOf(pduWarningSet.getPowerResumeDelay()),4);
                    messageDLT.setDataStr(powerResumeDelay);
                    warningSetMessage = MessageStringDLTUtils.messageToHex(messageDLT, "write");
                    ThreadUtils.writeMsgToClient(socket.getOutputStream(), warningSetMessage + end);
                    //收到回复
//                    warningSetMessage = ThreadUtils.readMessageFromClient(socket.getInputStream());
                    warningSetMessage = SubPolThread.getReadMsg();
//                    while (warningSetMessage.equals("")) {
//                        System.out.println("重新设置功率过大回复延时时间");
//                        ThreadUtils.writeMsgToClient(socket.getOutputStream(), warningSetMessage);
//                        warningSetMessage = ThreadUtils.readMessageFromClient(socket.getInputStream());
//
//                    }
                    receiverMessageDLT = MessageStringDLTUtils.onlineMessage(warningSetMessage);
                    newaddress = MessageStringDLTUtils.machineAddressHexOpposite(receiverMessageDLT.getMachineAddress());
                    //判断返回是否正确
                    if (receiverMessageDLT.getControl().equals("94") && machineid.equals(newaddress)) {
                        System.out.println("设置功率过大回复延时时间成功！");
                    }
                    break;
                case 8:
                    //发送过压延时启动时间报文
                    messageDLT.setDataTab("04FF0102");
//                    messageDLT.setDataStr("30");
                    String VoltageStartDelay = MessageStringDLTUtils.addZeroForNumLeft(String.valueOf(pduWarningSet.getVoltageStartDelay()),4);
                    messageDLT.setDataStr(VoltageStartDelay);

//                    String datasatr = Integer.toHexString(Integer.valueOf(messageDLT.getDataStr()));
//                    datasatr = MessageStringDLTUtils.addZeroForNumLeft(datasatr, 4);
//                    messageDLT.setDataStr(datasatr);
                    warningSetMessage = MessageStringDLTUtils.messageToHex(messageDLT, "write");
                    System.out.println("warningSetMessage==" + warningSetMessage);

                    ThreadUtils.writeMsgToClient(socket.getOutputStream(), warningSetMessage + end);
                    //收到回复
//                    warningSetMessage = ThreadUtils.readMessageFromClient(socket.getInputStream());
                    warningSetMessage = SubPolThread.getReadMsg();
//                    while (warningSetMessage.equals("")) {
//                        System.out.println("重新过压延时启动时间");
//                        ThreadUtils.writeMsgToClient(socket.getOutputStream(), warningSetMessage);
//                        warningSetMessage = ThreadUtils.readMessageFromClient(socket.getInputStream());
//
//                    }

                    receiverMessageDLT = MessageStringDLTUtils.onlineMessage(warningSetMessage);

                    newaddress = MessageStringDLTUtils.machineAddressHexOpposite(receiverMessageDLT.getMachineAddress());

                    //判断返回是否正确
                    if (receiverMessageDLT.getControl().equals("94") && machineid.equals(newaddress)) {
                        System.out.println("设置过压延时启动时间成功！");
                    }


                    //发送欠压延时启动时间报文
                     messageDLT.setDataTab("04FF0202");
//                    messageDLT.setDataStr("30");
                    VoltageStartDelay = MessageStringDLTUtils.addZeroForNumLeft(String.valueOf(pduWarningSet.getVoltageResumedDelay()),4);
                     messageDLT.setDataStr(VoltageStartDelay);
//                    datasatr = Integer.toHexString(Integer.valueOf(messageDLT.getDataStr()));
//                    datasatr = MessageStringDLTUtils.addZeroForNumLeft(datasatr, 4);
//                    messageDLT.setDataStr(datasatr);
                    warningSetMessage = MessageStringDLTUtils.messageToHex(messageDLT, "write");
                    ThreadUtils.writeMsgToClient(socket.getOutputStream(), warningSetMessage  + end);
                    //收到回复
//                    warningSetMessage = ThreadUtils.readMessageFromClient(socket.getInputStream());
                    warningSetMessage = SubPolThread.getReadMsg();
//                    while (warningSetMessage.equals("")) {
//                        System.out.println("重发欠压延时启动时间");
//                        ThreadUtils.writeMsgToClient(socket.getOutputStream(), warningSetMessage);
//                        warningSetMessage = ThreadUtils.readMessageFromClient(socket.getInputStream());
//
//
//                    }
                    receiverMessageDLT = MessageStringDLTUtils.onlineMessage(warningSetMessage);
                    newaddress = MessageStringDLTUtils.machineAddressHexOpposite(receiverMessageDLT.getMachineAddress());

                    //判断返回是否正确
                    if (receiverMessageDLT.getControl().equals("94") && machineid.equals(newaddress)) {
                        System.out.println("设置欠压延时启动时间成功！");
                    }
                    break;
                case 9:
                    //发送过压恢复时间报文
                    messageDLT.setDataTab("04FF0109");
//                    messageDLT.setDataStr("30");

                    String voltageResumedDelay = MessageStringDLTUtils.addZeroForNumLeft(String.valueOf(pduWarningSet.getVoltageResumedDelay()),4);
                    messageDLT.setDataStr(voltageResumedDelay);
                    warningSetMessage = MessageStringDLTUtils.messageToHex(messageDLT, "write");
                    ThreadUtils.writeMsgToClient(socket.getOutputStream(), warningSetMessage + end);
                    //收到回复
//                    warningSetMessage = ThreadUtils.readMessageFromClient(socket.getInputStream());
                    warningSetMessage = SubPolThread.getReadMsg();
//                    while (warningSetMessage.equals("")) {
//                        System.out.println("重发过压恢复时间");
//                        ThreadUtils.writeMsgToClient(socket.getOutputStream(), warningSetMessage);
//                        warningSetMessage = ThreadUtils.readMessageFromClient(socket.getInputStream());
//
//
//                    }
                    receiverMessageDLT = MessageStringDLTUtils.onlineMessage(warningSetMessage);
                    newaddress = MessageStringDLTUtils.machineAddressHexOpposite(receiverMessageDLT.getMachineAddress());

                    //判断返回是否正确
                    if (receiverMessageDLT.getControl().equals("94") && machineid.equals(newaddress)) {
                        System.out.println("设置过压恢复时间成功！");
                    }

                    //发送欠压恢复时间报文
                    messageDLT.setDataTab("04FF0203");
//                    messageDLT.setDataStr("30");
                    voltageResumedDelay = MessageStringDLTUtils.addZeroForNumLeft(String.valueOf(pduWarningSet.getVoltageResumedDelay()),4);
                    messageDLT.setDataStr(voltageResumedDelay);
                    warningSetMessage = MessageStringDLTUtils.messageToHex(messageDLT, "write");
                    ThreadUtils.writeMsgToClient(socket.getOutputStream(), warningSetMessage + end);
                    //收到回复
//                    warningSetMessage = ThreadUtils.readMessageFromClient(socket.getInputStream());
                    warningSetMessage = SubPolThread.getReadMsg();
//                    while (warningSetMessage.equals("")) {
//                        System.out.println("重发欠压恢复时间");
//                        ThreadUtils.writeMsgToClient(socket.getOutputStream(), warningSetMessage);
//                        warningSetMessage = ThreadUtils.readMessageFromClient(socket.getInputStream());
//
//
//                    }
                    receiverMessageDLT = MessageStringDLTUtils.onlineMessage(warningSetMessage);
                    newaddress = MessageStringDLTUtils.machineAddressHexOpposite(receiverMessageDLT.getMachineAddress());

                    //判断返回是否正确
                    if (receiverMessageDLT.getControl().equals("94") && machineid.equals(newaddress)) {
                        System.out.println("设置欠压恢复时间成功！");
                    }
                    break;
                case 10:
                    //发送过流启动延时时间
                    messageDLT.setDataTab("04FF0503");

//                    messageDLT.setDataStr("30");

                   String currentStartDelay = MessageStringDLTUtils.addZeroForNumLeft(String.valueOf(pduWarningSet.getCurrentStartDelay()),4);
                    messageDLT.setDataStr(currentStartDelay);

                    warningSetMessage = MessageStringDLTUtils.messageToHex(messageDLT, "write");
                    ThreadUtils.writeMsgToClient(socket.getOutputStream(), warningSetMessage + end);
                    //收到回复
//                    warningSetMessage = ThreadUtils.readMessageFromClient(socket.getInputStream());
                    warningSetMessage = SubPolThread.getReadMsg();
//                    while (warningSetMessage.equals("")) {
//                        System.out.println("重发过流启动延时时间");
//                        ThreadUtils.writeMsgToClient(socket.getOutputStream(), warningSetMessage);
//                        warningSetMessage = ThreadUtils.readMessageFromClient(socket.getInputStream());
//
//                    }
                    receiverMessageDLT = MessageStringDLTUtils.onlineMessage(warningSetMessage);
                    newaddress = MessageStringDLTUtils.machineAddressHexOpposite(receiverMessageDLT.getMachineAddress());

                    //判断返回是否正确
                    if (receiverMessageDLT.getControl().equals("94") && machineid.equals(newaddress)) {
                        System.out.println("设置过流启动延时时间成功！");
                    }
                    break;
                case 11:
                    //发送过流启动延时时间
                    messageDLT.setDataTab("04FF0504");
//                    messageDLT.setDataStr("30");
                    String currentResumeDelay = MessageStringDLTUtils.addZeroForNumLeft(String.valueOf(pduWarningSet.getCurrentResumeDelay()),4);
                    messageDLT.setDataStr(currentResumeDelay);

//                    datasatr = Integer.toHexString(Integer.valueOf(messageDLT.getDataStr()));
//                    datasatr = MessageStringDLTUtils.addZeroForNumLeft(datasatr, 4);
//                    messageDLT.setDataStr(datasatr);

                    warningSetMessage = MessageStringDLTUtils.messageToHex(messageDLT, "write");
                    ThreadUtils.writeMsgToClient(socket.getOutputStream(), warningSetMessage + end);
                    //收到回复
//                    warningSetMessage = ThreadUtils.readMessageFromClient(socket.getInputStream());
                    warningSetMessage = SubPolThread.getReadMsg();
//                    while (warningSetMessage.equals("")) {
//                        System.out.println("重发过流启动延时时间");
//                        ThreadUtils.writeMsgToClient(socket.getOutputStream(), warningSetMessage);
//                        warningSetMessage = ThreadUtils.readMessageFromClient(socket.getInputStream());
//
//                    }
                    receiverMessageDLT = MessageStringDLTUtils.onlineMessage(warningSetMessage);
                    newaddress = MessageStringDLTUtils.machineAddressHexOpposite(receiverMessageDLT.getMachineAddress());

                    //判断返回是否正确
                    if (receiverMessageDLT.getControl().equals("94") && machineid.equals(newaddress)) {
                        System.out.println("设置过流恢复延时时间成功！");
                    }
                    break;
                case 12:
                    //电量下限
                    messageDLT.setDataTab("04FF0702");

//                    dataStr = (float) 300;
                    float quantity = (float) pduWarningSet.getLowerLimitQuantity();
//                    messageDLT.setDataStr(currentResumeDelay);

//                    dataStr = pduWarningSet.getLowerLimitQuantity();
                    String lowerLimitQuantity = new DecimalFormat("#00.00").format(quantity);
                    lowerLimitQuantity = lowerLimitQuantity.replace(".", "");
                    lowerLimitQuantity = MessageStringDLTUtils.addZeroForNumLeft(lowerLimitQuantity, 8);
                    messageDLT.setDataStr(lowerLimitQuantity);

                    warningSetMessage = MessageStringDLTUtils.messageToHex(messageDLT, "write");
                    System.out.println("warningSetMessage===" + warningSetMessage);
                    ThreadUtils.writeMsgToClient(socket.getOutputStream(), warningSetMessage + end);
                    //收到回复
//                    warningSetMessage = ThreadUtils.readMessageFromClient(socket.getInputStream());
                    warningSetMessage = SubPolThread.getReadMsg();
//                    while (warningSetMessage.equals("")) {
//                        System.out.println("重发电量下限");
//                        ThreadUtils.writeMsgToClient(socket.getOutputStream(), warningSetMessage);
//                        warningSetMessage = ThreadUtils.readMessageFromClient(socket.getInputStream());
//
//                    }
                    receiverMessageDLT = MessageStringDLTUtils.onlineMessage(warningSetMessage);
                    newaddress = MessageStringDLTUtils.machineAddressHexOpposite(receiverMessageDLT.getMachineAddress());

                    //判断返回是否正确
                    if (receiverMessageDLT.getControl().equals("94") && machineid.equals(newaddress)) {
                        System.out.println("设置电量下限成功！");
                    }
                    break;
                case 13:
                    //线温阀值
                    messageDLT.setDataTab("06FF0301");

                    dataStr = (float) 300;//测试值
//                    dataStr = pduWarningSet.getTemperatureAmplitude();
                    String temperatureAmplitude = new DecimalFormat("##0.00").format(dataStr);
                    temperatureAmplitude = temperatureAmplitude.replace(".", "");
                    temperatureAmplitude = MessageStringDLTUtils.addZeroForNumLeft(temperatureAmplitude, 8);
                    messageDLT.setDataStr(temperatureAmplitude);

                    warningSetMessage = MessageStringDLTUtils.messageToHex(messageDLT, "write");
                    System.out.println("warningSetMessage===" + warningSetMessage);
                    ThreadUtils.writeMsgToClient(socket.getOutputStream(), warningSetMessage + end);
                    //收到回复
//                    warningSetMessage = ThreadUtils.readMessageFromClient(socket.getInputStream());
                    warningSetMessage = SubPolThread.getReadMsg();
//                    while (warningSetMessage.equals("")) {
//                        System.out.println("重发线温阀值");
//                        ThreadUtils.writeMsgToClient(socket.getOutputStream(), warningSetMessage);
//                        warningSetMessage = ThreadUtils.readMessageFromClient(socket.getInputStream());
//
//                    }
                    receiverMessageDLT = MessageStringDLTUtils.onlineMessage(warningSetMessage);
                    newaddress = MessageStringDLTUtils.machineAddressHexOpposite(receiverMessageDLT.getMachineAddress());

                    //判断返回是否正确
                    if (receiverMessageDLT.getControl().equals("94") && machineid.equals(newaddress)) {
                        System.out.println("设置线温阀值成功！");
                    }
                    break;


                case 16:
                    //发送接触不良启动时间
                    messageDLT.setDataTab("06FF0201");
//                    messageDLT.setDataStr("30");
                    String poorContact = MessageStringDLTUtils.addZeroForNumLeft(String.valueOf(pduWarningSet.getPoorContact()),4);
                    messageDLT.setDataStr(poorContact);

//                    datasatr = Integer.toHexString(Integer.valueOf(messageDLT.getDataStr()));
//                    datasatr = MessageStringDLTUtils.addZeroForNumLeft(datasatr, 4);
//                    messageDLT.setDataStr(datasatr);

                    warningSetMessage = MessageStringDLTUtils.messageToHex(messageDLT, "write");
                    ThreadUtils.writeMsgToClient(socket.getOutputStream(), warningSetMessage + end);
                    //收到回复
//                    warningSetMessage = ThreadUtils.readMessageFromClient(socket.getInputStream());
                    warningSetMessage = SubPolThread.getReadMsg();
//                    while (warningSetMessage.equals("")) {
//                        System.out.println("重发过流启动延时时间");
//                        ThreadUtils.writeMsgToClient(socket.getOutputStream(), warningSetMessage);
//                        warningSetMessage = ThreadUtils.readMessageFromClient(socket.getInputStream());
//
//                    }
                    receiverMessageDLT = MessageStringDLTUtils.onlineMessage(warningSetMessage);
                    newaddress = MessageStringDLTUtils.machineAddressHexOpposite(receiverMessageDLT.getMachineAddress());

                    //判断返回是否正确
                    if (receiverMessageDLT.getControl().equals("94") && machineid.equals(newaddress)) {
                        System.out.println("设置接触不良时间成功！");
                    }
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
