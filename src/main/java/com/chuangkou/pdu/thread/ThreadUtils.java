package com.chuangkou.pdu.thread;

import com.chuangkou.pdu.controller.BaseController;
import com.chuangkou.pdu.controller.PduController;
import com.chuangkou.pdu.controller.PduTemporaryController;
import com.chuangkou.pdu.entity.*;
import com.chuangkou.pdu.service.*;
import com.chuangkou.pdu.util.DeviceEvent;
import com.chuangkou.pdu.util.MessageStringDLTUtils;
import com.chuangkou.pdu.util.StringUtil;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author:
 * @Description:
 * @Date:Created in 17:00 2018/4/24
 */
public class ThreadUtils {
    private static PduAutomaticService pduAutomaticService = (PduAutomaticService) SpringApplicationContextHolder.getSpringBean("pduAutomaticService");
    private static SceneService sceneService = (SceneService) SpringApplicationContextHolder.getSpringBean("sceneService");
    private static PduWarningSetService pduWarningSetService = (PduWarningSetService) SpringApplicationContextHolder.getSpringBean("pduWarningSetService");

    private static PduWarningService pduWarningService = (PduWarningService) SpringApplicationContextHolder.getSpringBean("pduWarningService");
    private static PduService pduService = (PduService) SpringApplicationContextHolder.getSpringBean("pduService");
    private static PduTemporaryService pduTemporaryService = (PduTemporaryService) SpringApplicationContextHolder.getSpringBean("pduTemporaryService");
    private static PduInfoService pduInfoService = (PduInfoService) SpringApplicationContextHolder.getSpringBean("pduInfoService");

    private static PduGroupRelationService pduGroupRelationService = (PduGroupRelationService) SpringApplicationContextHolder.getSpringBean("pduGroupRelationService");


    //发送报文
    public static void writeMsgToClient(OutputStream outputStream, String string) throws IOException {
        try {
            byte[] ss = StringUtil.hexStringToByteArray(string);
//            System.out.println("发送工作状态查询报文：" + ss.toString());
            outputStream.write(ss);
            outputStream.flush();
        } catch (IOException e) {
            System.out.println("发送报文异常！");
            e.printStackTrace();
        }
    }

    public static String readMessageFromClient(InputStream inputStream) throws IOException {
        String message = "";
        try {
            byte[] bytes = null;
            int bufflenth = inputStream.available();
            if (bufflenth > 0) {
                while (bufflenth != 0) {
                    // 初始化byte数组为buffer中数据的长度
                    bytes = new byte[bufflenth];
                    inputStream.read(bytes);
                    bufflenth = inputStream.available();
                }
                message = StringUtil.str2HexStr(bytes);
//                System.out.println("接收到报文：" + message);
            }
        } catch (Exception e) {
            System.out.println("接收报文异常！");
//            inputStream.close();
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return message;
    }


    /**
     * @Author:
     * @Description:判断是否有预警情况
     * @Date: 2018-04-22
     */
    public static PduInfo getJudgementWarning(PduInfo pduInfo) {
        PduInfo pduInfoNew = new PduInfo();
        Pdu pduIfWraning = new Pdu();
        PduWarningSet pduWarningSet = new PduWarningSet();
        pduWarningSet = pduWarningSetService.selectByPduWarningSet(pduInfo.getPduid());
        try {
            if (pduWarningSet != null) {
                Float powervalue = pduWarningSet.getWattamplitude(); //功率预警阀值
                Float currentvalue = pduWarningSet.getCurrentamplitude();//电流阀值
                Float voltagevalue = pduWarningSet.getSetingvoltage();//电压阀值

                Float power = Float.valueOf(pduWarningSet.getWatt());
                Float current = Float.valueOf(pduWarningSet.getCurrent());
                Float voltage = Float.valueOf(pduWarningSet.getVoltage());

                Float nowpower = Float.valueOf(pduInfo.getWatt());
                Float nowcurrent = Float.valueOf(pduInfo.getCurrent());
                Float nowvoltage = Float.valueOf(pduInfo.getVoltage());

                if (nowcurrent >= current + (current * currentvalue) && current > 0) {//判断过流状态
                    pduInfoNew.setOvercurrent("1");
                    pduInfoNew.setState("1");
                    //进行过流预警记录
                    PduWarning pduWarning = new PduWarning();

                    pduWarning.setPduid(pduInfo.getPduid());
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    pduWarning.setWarningtime(df.format(System.currentTimeMillis()));
                    pduWarning.setWarningtype("5");//电流异常
                    pduWarning.setState("0");//0:待处理

                    pduWarningService.insert(pduWarning);//插入预警信息

                    //修改主表状态为故障中
                    pduIfWraning.setOnlinestate("3");
                    pduIfWraning.setId(pduInfo.getPduid());
                    pduService.update(pduIfWraning);

                    //推送预警信息
                    int event_type = warningChange(Integer.valueOf(pduWarning.getWarningtype()));
                    String appMessage = ThreadUtils.sendDeviceEvent(pduInfo.getPduid(), event_type);
                    ThreadUtils.connectionMapMessageSend(BaseController.APPSubPolmap, appMessage);

                } else {
                    pduInfoNew.setOvercurrent("0");
                    pduInfoNew.setState("0");
                }

                if (nowvoltage >= voltage + (voltage * voltagevalue) && voltage > 0) {
                    pduInfoNew.setOvervoltage("1");
                    pduInfoNew.setState("1");

                    //进行过压预警记录
                    PduWarning pduWarning = new PduWarning();

                    pduWarning.setPduid(pduInfo.getPduid());
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    pduWarning.setWarningtime(df.format(System.currentTimeMillis()));
                    pduWarning.setWarningtype("3");//过压异常
                    pduWarning.setState("0");//0:待处理

                    pduWarningService.insert(pduWarning);//插入预警信息

                    //修改主表状态为故障中
                    pduIfWraning.setOnlinestate("3");
                    pduIfWraning.setId(pduInfo.getPduid());
                    pduService.update(pduIfWraning);

                    //推送预警信息
                    int event_type = warningChange(Integer.valueOf(pduWarning.getWarningtype()));
                    String appMessage = ThreadUtils.sendDeviceEvent(pduInfo.getPduid(), event_type);
                    ThreadUtils.connectionMapMessageSend(BaseController.APPSubPolmap, appMessage);

                } else {
                    pduInfoNew.setOvervoltage("0");
                    pduInfoNew.setState("0");
                }

                if (nowvoltage <= voltage - (voltage * voltagevalue) && voltage > 0) {
                    pduInfoNew.setUndervoltage("1");
                    pduInfoNew.setState("1");

                    //进行欠压预警记录
                    PduWarning pduWarning = new PduWarning();

                    pduWarning.setPduid(pduInfo.getPduid());
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    pduWarning.setWarningtime(df.format(System.currentTimeMillis()));
                    pduWarning.setWarningtype("4");//欠压异常
                    pduWarning.setState("0");//0:待处理

                    pduWarningService.insert(pduWarning);//插入预警信息

                    //修改主表状态为故障中
                    pduIfWraning.setOnlinestate("3");
                    pduIfWraning.setId(pduInfo.getPduid());
                    pduService.update(pduIfWraning);

                    //推送预警信息
                    int event_type = warningChange(Integer.valueOf(pduWarning.getWarningtype()));
                    String appMessage = ThreadUtils.sendDeviceEvent(pduInfo.getPduid(), event_type);
                    ThreadUtils.connectionMapMessageSend(BaseController.APPSubPolmap, appMessage);

                } else {
                    pduInfoNew.setUndervoltage("0");
                    pduInfoNew.setState("0");
                }

                if (nowpower <= power + (power * powervalue) && power > 0) {
                    pduInfoNew.setElectricleakage("1");
                    pduInfoNew.setState("1");

                    //进行过载预警记录
                    PduWarning pduWarning = new PduWarning();

                    pduWarning.setPduid(pduInfo.getPduid());
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    pduWarning.setWarningtime(df.format(System.currentTimeMillis()));
                    pduWarning.setWarningtype("0");//过载异常
                    pduWarning.setState("0");//0:待处理

                    pduWarningService.insert(pduWarning);//插入预警信息

                    //修改主表状态为故障中
                    pduIfWraning.setOnlinestate("3");
                    pduIfWraning.setId(pduInfo.getPduid());
                    pduService.update(pduIfWraning);

                    //推送预警信息
                    int event_type = warningChange(Integer.valueOf(pduWarning.getWarningtype()));
                    String appMessage = ThreadUtils.sendDeviceEvent(pduInfo.getPduid(), event_type);
                    ThreadUtils.connectionMapMessageSend(BaseController.APPSubPolmap, appMessage);

                } else {
                    pduInfoNew.setElectricleakage("0");
                    pduInfoNew.setState("0");
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pduInfoNew;
    }

    /**
     * @Author:xulei
     * @Description:判断设备状态是否符合触发场景要求，如果符合则对设备进行继电器关闭操作
     * @Date: 2018-04-11
     */
    public static void scentJudgement(PduInfo pduInfo) {
        List<PduAutomatic> pduAutomaticList = new ArrayList<PduAutomatic>();

        try {
            //根据设备查询设备场景设置
            pduAutomaticList = pduAutomaticService.selectBySceneId(pduInfo.getPduid());
            PduWarningSet pduWarningSet = pduWarningSetService.selectByPduWarningSet(pduInfo.getPduid());
            if (pduAutomaticList != null && pduAutomaticList.size() > 0) {
                Float nowpower = Float.valueOf(pduInfo.getWatt());//当前的设备功率
                Float nowcurrent = Float.valueOf(pduInfo.getCurrent());//当前的设备电流
                Float nowvoltage = Float.valueOf(pduInfo.getVoltage());//当前的设备电压

                for (int i = 0; i < pduAutomaticList.size(); i++) {
                    PduAutomatic pduAutomatic = new PduAutomatic();
                    Scene scene = new Scene();
                    pduAutomatic = pduAutomaticList.get(i);
                    scene = sceneService.selectByPrimaryKey(pduAutomatic.getSceneid());


                    if (scene.getState().equals("1")) { //场景状态为开启
                        Float power = Float.valueOf(pduWarningSet.getWatt());//设备标准功率
                        Float current = Float.valueOf(pduWarningSet.getCurrent());//设备标准电流
                        Float voltage = Float.valueOf(pduWarningSet.getVoltage());//设备标准电压

                        Float scenepower = Float.valueOf(scene.getWatt());//场景中设置的功率阀值
                        Float sceneOverCurrent = Float.valueOf(scene.getOvercurrent());//场景中设置的过流阀值
                        Float sceneOvervoltage = Float.valueOf(scene.getOvervoltage());//场景中设置的过压阀值
                        Float sceneUndervoltage = Float.valueOf(scene.getUndervoltage());//场景中设置的欠压阀值
                        String ifele = scene.getElectricleakage();

                        PduController pduController = new PduController();

                        if (sceneOverCurrent > 0) {
                            if (nowcurrent >= current + (current * sceneOverCurrent) && current > 0) {
                                System.out.println("电流达到场景值，触发关闭设备操作");
                                //调用触发器设备关闭操作
                                pduController.pduOffAction(pduInfo.getPduid().toString());
                                break;
                            }
                        }

                        if (sceneOvervoltage > 0) {
                            if (nowvoltage >= voltage + (voltage * sceneOvervoltage) && voltage > 0) {
                                System.out.println("存在过压情况，触发关闭设备操作");
                                //调用触发器设备关闭操作
                                pduController.pduOffAction(pduInfo.getPduid().toString());

                                break;
                            }
                        }

                        if (sceneOvervoltage > 0) {
                            if (nowvoltage <= voltage - (voltage * sceneUndervoltage) && voltage > 0) {
                                System.out.println("存在欠压情况，触发关闭设备操作");
                                //调用触发器设备关闭操作
                                pduController.pduOffAction(pduInfo.getPduid().toString());
                                break;
                            }
                        }

                        if (ifele.equals("1")) {//设置了过载拉闸场景
                            if (pduWarningSet != null) {
                            Float powervalue = pduWarningSet.getWattamplitude(); //功率预警阀值

                                if (nowpower >= power + (power * powervalue) && power > 0) {
                                    System.out.println("存在过载情况情况，触发关闭设备操作");
                                    //调用触发器设备关闭操作
                                    pduController.pduOffAction(pduInfo.getPduid().toString());
                                    break;
                                }
                            }
                        }
                        if (scenepower > 0) {//设置了过载拉闸场景
                            if (pduWarningSet != null) {

                                if (nowpower >= power + (power * scenepower) && power > 0) {
                                    System.out.println("存在过载情况情况，触发关闭设备操作");
                                    //调用触发器设备关闭操作
                                    pduController.pduOffAction(pduInfo.getPduid().toString());

                                    break;
                                }
                            }
                        }

                    }
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }


    /**
     * @Author:
     * @Description:
     * @Date:
     */
    public static void sendAction(Message message, Socket connection) throws Exception {

        String msg1 = "AAB1A10D0A"; //对时报文、设备配网报文下发
        String msg2 = "AAB2A20D0A"; //工作状态查询
        String msg3 = "AAB1A30D0A"; //控制命令下发
        String msg4 = "AAB2A40D0A";//故障告警信息

//        String controlType = Integer.toHexString(Integer.valueOf(message.getControlType().toString())); //上传还是下行
//        String command = Integer.toHexString(Integer.valueOf(message.getCommand().toString()));//命令类型
        String controlType = message.getControlType().toString();
        String command = message.getCommand().toString();
        if (controlType.equals("B1") && command.equals("A1")) {//判断上报类型报文、报文命令类型是网络信息上报

            String msg = "";
            while (!msg.equals(msg1)) {
                //跳转到设备添加方法
                System.out.println("如果是B1A1类型报文，则要添加设备信息或重新设备设备对时信息");
                addPdu(message, connection);
                Thread.sleep(3000);//接收数据的时候要延时2秒，这样才能保证每次发送和返回的正确接收，不丢包
                msg = readMessageFromClient(connection.getInputStream());
//            Message message2 = new Message();
//            //解析报文信息返回设备临时信息
//            message2 = StringUtil.receiveMessage(msg);
//            String actype = message2.getActionType();
                if (msg.equals(msg1)) {
                    System.out.println("新上线设备添加并对时成功！");
                    //如果是新设备 添加成功后，发起校时报文
//                pduTimeSet(message2);
                } else {
                    System.out.println("新上线设备添加对时失败！");
                }
            }
        }

        if (controlType.equals("B1") && command.equals("A2")) {//判断是工作状态信息上报
            //添加设备工作状态信息
            addPduInfo(message);

            //返回报文，设备工作状态接收成功
            message.setControlType("1");
            message.setActionType("1");
            String mes = StringUtil.sendMessage(message);//组合报文
            writeMsgToClient(connection.getOutputStream(), mes);//发送报文

        }

        if (controlType.equals("B1") && command.equals("A3")) {//控制继电器命令回复
            //这里用于控制命令下发后的回复
            String actype = message.getActionType();
            if (actype.equals("1")) {//表示控制设备成功
                Pdu pdu = new Pdu();
                pdu.setActionState(message.getRelayState());
                pduService.update(pdu);
            }
        }

        if (controlType.equals("B1") && command.equals("A4")) {//故障预警上报

            Pdu pdu = new Pdu();
            pdu = pduService.selectByMachineID(message.getReceiveMachineID());
            //根据返回的报文，增加预警故障记录
            String warningtype = pduWarning(message);

            PduWarning pduWarning = new PduWarning();
            pduWarning.setPduid(pdu.getId());

//            String collectiontiem = "20" + message.getYear() + "-" + message.getMonth() + "-" + message.getDay() + " "
//                    + message.getHour() + ":" + message.getMinute() + ":" + message.getSecond();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            pduWarning.setWarningtime(df.format(System.currentTimeMillis()));


            pduWarning.setWarningtype(warningtype);//1:电压异常 2：电流异常。。。。。

            pduWarning.setState("0");//0:待处理
            pduWarningService.insert(pduWarning);//插入预警信息

            String mes = "AAB2A40D0A";
            writeMsgToClient(connection.getOutputStream(), mes);//发送报文

        }

    }


    //获取客户端信息后，添加到数据库设备临时表
    public static void addPdu(Message message, Socket connection) throws Exception {

        Pdu pdu = new Pdu();
//        PduTemporary pduTemporary = new PduTemporary();
        List<PduTemporary> pduTemporaryList = new ArrayList<PduTemporary>();
        try {

//            pdu = pduService.selectByMachineID(message.getReceiveMachineID());
//            pduTemporaryList = pduTemporaryService.selectByPduTemporaryList(message.getReceiveMachineID());
            PduTemporaryController pduTemporaryController = new PduTemporaryController();

//            PduTemporary pduTemporary = pduTemporaryService.selectByPduTemporary2(message.getReceiveMachineID());
            Message sendmsg = new Message();
            pdu = pduService.selectByMachineID(message.getReceiveMachineID());
            String pduMachineID = "";
            System.out.println("设备IP地址：" + message.getIp());
            if (pdu != null) {//表示主表存在该记录，已经添加过
                String pdustate = pdu.getState();
                if (pdustate.equals("1")) {//设备已存在并且状态正常
                    pdu.setIp(message.getIp());
                    pdu.setOnlinestate("0");
                    pdu.setActionState("0");
                    pduService.update(pdu);
                    System.out.println("设备已存在,请勿重复添加!");

                } else {//设备已存在，但是状态是删除
                    pduMachineID = pdu.getMachineid().toString() == null ? "" : pdu.getMachineid().toString();
                    pdu.setIp(message.getIp());
                    pdu.setOnlinestate("0");
                    pdu.setActionState("0");
                    pduService.update(pdu);
                    pduService.updateStateView(pdu);//修改设备表状态为1 正常
                    pduTemporaryService.updateStateAdd(pduMachineID); //修改临时设备表状态为1 已添加

                }


            } else {//该设备存在于临时表中（之前建立过联系），但是没有添加到pdu表管理
                PduTemporary pduTemporary = pduTemporaryController.selectByPduTemporary(message.getReceiveMachineID());
                if (pduTemporary != null) {//如果在设备临时表已有记录，则修改状态为1添加到pdu表
                    Pdu pudNew = new Pdu();
                    pudNew.setMachineid(pduTemporary.getMachineid().toString() == null ? pduTemporary.getMachineid().toString() : "");
                    pudNew.setQrcode(pduTemporary.getQrcode().toString() == null ? pduTemporary.getQrcode().toString() : "");
                    pudNew.setIp(message.getIp());
//                    pudNew.setIp(pduTemporary.getIp().toString() == null ? pduTemporary.getIp().toString() : "");
                    pudNew.setType(pduTemporary.getType().toString() == null ? pduTemporary.getType().toString() : "");
                    pudNew.setName(pduTemporary.getMachineid().toString() == null ? pduTemporary.getMachineid().toString() : "");
                    pudNew.setState("1");
                    pudNew.setOnlinestate("0");
                    pudNew.setActionState("0");
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    pudNew.setCreateTime(df.format(System.currentTimeMillis()));
                    pduService.insert(pudNew);
                    pduTemporaryService.updateStateAdd(pduTemporary.getMachineid().toString() == null ? pduTemporary.getMachineid().toString() : "");

                } else {
                    System.out.println("设备没有添加，新增临时设备数据");
                    PduTemporary pduTemporaryNew = new PduTemporary();
                    pduTemporaryNew.setIp(message.getIp());
                    pduTemporaryNew.setQrcode(message.getReceiveMachineID());
                    pduTemporaryNew.setMachineid(message.getReceiveMachineID());
                    pduTemporaryNew.setType(message.getReceivePduType());
                    pduTemporaryNew.setState("1");
                    System.out.println("开始插入新设备数据....");
                    pduTemporaryController.addPduTemporary(pduTemporaryNew);//将设备加入设备临时表
                    System.out.println("新增临时设备成功....");

                    Pdu pudNew2 = new Pdu();
                    pudNew2.setMachineid(pduTemporaryNew.getMachineid().toString() != "" ? pduTemporaryNew.getMachineid().toString() : "null");
                    pudNew2.setQrcode(pduTemporaryNew.getQrcode().toString() != "" ? pduTemporaryNew.getQrcode().toString() : "null");
                    pudNew2.setIp(message.getIp());
//                    pudNew2.setIp(pduTemporaryNew.getIp().toString() != "" ? pduTemporaryNew.getIp().toString() : "null");
                    pudNew2.setType(pduTemporaryNew.getType().toString() != "" ? pduTemporaryNew.getType().toString() : "null");
                    pudNew2.setName(pduTemporaryNew.getMachineid().toString() != "未命名" ? pduTemporaryNew.getMachineid().toString() : "null");
                    pudNew2.setState("1");
                    pudNew2.setActionState("0");
                    pudNew2.setOnlinestate("0");
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    pudNew2.setCreateTime(df.format(System.currentTimeMillis()));
                    pduService.insert(pudNew2);

                    //添加到默认分组1
                    pdu = pduService.selectByQrcode(pudNew2.getMachineid());
                    PduGroupRelation pduGroupRelation = new PduGroupRelation();
                    pduGroupRelation.setPdugroupid(1);
                    pduGroupRelation.setPduid(pdu.getId());
                    pduGroupRelationService.insertNew(pduGroupRelation);//添加分组关系

//                sendmsg.setPduState("0");//如果是新设备第一次添加要回复
                }
            }


            //重新组织报文，返回已添加设备
            sendmsg.setControlType("2");
            sendmsg.setCommand("1");
            sendmsg.setReceivePduType(message.getReceivePduType());
            sendmsg.setReceiveMachineID(message.getReceiveMachineID());
            sendmsg.setReceiveRandomID(message.getReceiveRandomID());
            sendmsg.setIp(message.getIp());

            String mes = StringUtil.sendMessage1(sendmsg);
            writeMsgToClient(connection.getOutputStream(), mes);//回复报文


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //获取客户端信息后，添加到数据库设备临时表
    public static void addPduDLT(Message message, Socket connection) throws Exception {

        Pdu pdu = new Pdu();
//        PduTemporary pduTemporary = new PduTemporary();
        List<PduTemporary> pduTemporaryList = new ArrayList<PduTemporary>();
        try {

//            pdu = pduService.selectByMachineID(message.getReceiveMachineID());
//            pduTemporaryList = pduTemporaryService.selectByPduTemporaryList(message.getReceiveMachineID());
            PduTemporaryController pduTemporaryController = new PduTemporaryController();

//            PduTemporary pduTemporary = pduTemporaryService.selectByPduTemporary2(message.getReceiveMachineID());
            Message sendmsg = new Message();
            pdu = pduService.selectByMachineID(message.getReceiveMachineID());
            String pduMachineID = "";
            System.out.println("设备IP地址：" + message.getIp());
            if (pdu != null) {//表示主表存在该记录，已经添加过
                String pdustate = pdu.getState();
                if (pdustate.equals("1")) {//设备已存在并且状态正常
                    pdu.setIp(message.getIp());
                    pdu.setActionState("0");
                    pdu.setOnlinestate("0");
                    pduService.update(pdu);
                    System.out.println("设备已存在,修改主表的IP地址");

                } else {//设备已存在，但是状态是删除
                    pduMachineID = pdu.getMachineid().toString() == null ? "" : pdu.getMachineid().toString();
                    pdu.setIp(message.getIp());
                    pdu.setActionState("0");
                    pdu.setOnlinestate("0");
                    pduService.update(pdu);
                    pduService.updateStateView(pdu);//修改设备表状态为1 正常
                    pduTemporaryService.updateStateAdd(pduMachineID); //修改临时设备表状态为1 已添加
                    System.out.println("设备已存在,状态为删除，修改主表IP和状态、以及临时表");

                }

            } else {//该设备存在于临时表中（之前建立过联系），但是没有添加到pdu表管理
                PduTemporary pduTemporary = pduTemporaryController.selectByPduTemporary(message.getReceiveMachineID());
                if (pduTemporary != null) {//如果在设备临时表已有记录，则修改状态为1添加到pdu表
                    Pdu pudNew = new Pdu();
                    pudNew.setMachineid(pduTemporary.getMachineid().toString() != null ? pduTemporary.getMachineid().toString() : "");
//                    pudNew.setQrcode(pduTemporary.getQrcode().toString() != null ? pduTemporary.getQrcode().toString() : "");
                    pudNew.setIp(message.getIp());
//                    pudNew.setIp(pduTemporary.getIp().toString() == null ? pduTemporary.getIp().toString() : "");
                    pudNew.setType(pduTemporary.getType().toString() != null ? pduTemporary.getType().toString() : "");
                    pudNew.setName(pduTemporary.getMachineid().toString() != null ? pduTemporary.getMachineid().toString() : "");
                    pudNew.setState("1");
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    pudNew.setCreateTime(df.format(System.currentTimeMillis()));

                    pudNew.setActionState("0");
                    pudNew.setOnlinestate("0");

                    pduService.insert(pudNew);
                    pduTemporaryService.updateStateAdd(pduTemporary.getMachineid().toString() != null ? pduTemporary.getMachineid().toString() : "");
                    System.out.println("设备主表不存在，添加至主表！");
                } else {
                    System.out.println("设备没有添加过，新增临时设备数据");
                    PduTemporary pduTemporaryNew = new PduTemporary();
                    pduTemporaryNew.setIp(message.getIp());
//                    pduTemporaryNew.setQrcode(message.getReceiveMachineID());
                    pduTemporaryNew.setMachineid(message.getReceiveMachineID());
                    pduTemporaryNew.setType(message.getReceivePduType());
                    pduTemporaryNew.setState("1");
                    System.out.println("临时表 开始插入新设备数据....");
                    pduTemporaryController.addPduTemporary(pduTemporaryNew);//将设备加入设备临时表
                    System.out.println("临时表 新增临时设备成功....");

                    Pdu pudNew2 = new Pdu();
                    pudNew2.setMachineid(pduTemporaryNew.getMachineid().toString() != "" ? pduTemporaryNew.getMachineid().toString() : "null");
//                    pudNew2.setQrcode(pduTemporaryNew.getQrcode().toString() != "" ? pduTemporaryNew.getQrcode().toString() : "null");
                    pudNew2.setIp(message.getIp());
//                    pudNew2.setIp(pduTemporaryNew.getIp().toString() != "" ? pduTemporaryNew.getIp().toString() : "null");
                    pudNew2.setType(pduTemporaryNew.getType().toString() != "" ? pduTemporaryNew.getType().toString() : "null");
                    pudNew2.setName(pduTemporaryNew.getMachineid().toString() != "未命名" ? pduTemporaryNew.getMachineid().toString() : "null");
                    pudNew2.setState("1");
                    pudNew2.setActionState("0");
                    pudNew2.setOnlinestate("0");
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    pudNew2.setCreateTime(df.format(System.currentTimeMillis()));
                    System.out.println("设备主表 开始插入新设备数据....");
                    pduService.insert(pudNew2);
                    System.out.println("设备主表 新增临时设备成功....");

                    //添加到默认分组1
                    pdu = pduService.selectByQrcode(pudNew2.getMachineid());
                    PduGroupRelation pduGroupRelation = new PduGroupRelation();
                    pduGroupRelation.setPdugroupid(1);
                    pduGroupRelation.setPduid(pdu.getId());
                    System.out.println("添加分组关系.....");
                    pduGroupRelationService.insertNew(pduGroupRelation);//添加分组关系
                    System.out.println("添加分组关系完成.....");

                    //添加默认的预警配置
//                    PduWarningSet pduWarningSet = new PduWarningSet();


                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //添加设备运行状态
    public static void addPduInfo(Message message) throws Exception {

        Pdu pdu = new Pdu();
        PduInfo pduInfo = new PduInfo();

        //查询设备信息
        pdu = pduService.selectByMachineID(message.getReceiveMachineID());

        String pdustate = pdu.getState();

        //只接受正常设备的设备运行状态添加
        if (pdustate.equals("1")) {

            pduInfo.setPduid(pdu.getId());

//            String collectiontiem = "20" + message.getYear() + "-" + message.getMonth() + "-" + message.getDay() + " "
//                    + message.getHour() + ":" + message.getMinute() + ":" + message.getSecond();

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            pduInfo.setCollectiontime(df.format(System.currentTimeMillis()));

            pduInfo.setVoltage(message.getVoltage());
            pduInfo.setCurrent(message.getCurrent());
            pduInfo.setWatt(message.getWatt());

            PduInfo pduInfoWarningNew = new PduInfo();
            pduInfoWarningNew = getJudgementWarning(pduInfo);

            pduInfo.setRelaystate(message.getRelayState());
            pduInfo.setOvervoltage(pduInfoWarningNew.getOvervoltage());
            pduInfo.setUndervoltage(pduInfoWarningNew.getUndervoltage());
            pduInfo.setOvercurrent(pduInfoWarningNew.getOvercurrent());
            pduInfo.setCircuitbreaker(message.getCircuitbreaker());
            pduInfo.setElectricleakage(message.getElectricleakage());

            pduInfoService.insert(pduInfo);

//            if (!warning.equals("0")) {//如果超过了预警阈值，则进行预警记录
//                PduWarning pduWarning = new PduWarning();
//                pduWarning.setPduid(pduInfo.getPduid());
//                pduWarning.setWarningtime(pduInfo.getCollectiontime());
//                pduWarning.setWarningtype(warning);//1:电压异常 2：电流异常。。。。。
//
//                pduWarning.setState("0");//0:待处理
//                pduWarningService.insert(pduWarning);//插入预警信息

//            }
        }

    }

    /**
     * @Author:xulei
     * @Description:主动上报预警类型判断
     * @Date: 2018-03-20
     */
    public static String pduWarning(Message mesg) throws Exception {
        String ifwarning = "0";//正常状态

        if (mesg.getElectricleakage().equals("1")) {
            ifwarning = "0";
        }

        if (mesg.getCircuitbreaker().equals("1")) {
            ifwarning = "1";
        }


        if (mesg.getOvervoltage().equals("1")) {
            ifwarning = "3";
        }

        if (mesg.getUndervoltage().equals("1")) {
            ifwarning = "4";
        }

        if (mesg.getOvercurrent().equals("1")) {
            ifwarning = "5";
        }

        if (mesg.getSmoke().equals("1")) {
            ifwarning = "6";
        }

        if (mesg.getTemperature().equals("1")) {
            ifwarning = "7";
        }

        return ifwarning;

    }


    /**
     * @Author:xulei
     * @Description:645插座上电后，修改IP地址
     * @Date:2018-05-03
     */

    public static String updatePduIpDLT(MessageDLT messageDLT, String ip, Socket socket) {
        String r = "";
        try {
            //解析设备地址
            String machineID = messageDLT.getMachineAddress();
//            machineID = MessageStringDLTUtils.addZeroForNumLeft(machineID, 12);
//            machineID = machineID.substring(1, machineID.length());
            System.out.println("设备ID：" + machineID);
            //修改设备IP地址
//            Pdu pduUpdate = new Pdu();
//            pduUpdate = pduService.selectByMachineID(machineID);
//
//            pduUpdate.setMachineid(machineID);
//            pduUpdate.setIp(ip);
//
//            pduService.update(pduUpdate);

            Message message = new Message();
            message.setIp(ip);
            message.setReceiveMachineID(machineID);
            message.setReceivePduType(machineID.substring(0, 4));
            addPduDLT(message, socket);//或添加 或修改设备状态

            r = "ok";

        } catch (Exception e) {
            e.printStackTrace();
        }

        return r;

    }

    /**
     * @Author:xulei
     * @Description:获取空开数据的同时 补充空开的IP地址
     * @Date:2018-05-03
     */
    public static String updatePduIpDLT(MessageDLT messageDLT, Socket socket) {
        String r = "";
        try {
            //解析设备地址
            String machineID = messageDLT.getMachineAddress();
//            machineID = MessageStringDLTUtils.machineAddressHex(machineID);
//            machineID = machineID.substring(1,machineID.length());
            System.out.println("设备ID：" + machineID);
            String ip = socket.getInetAddress().toString();
            ip = ip.substring(1, ip.length());
            System.out.println("修改设备" + machineID + " 的IP地址为：" + ip);
            //修改设备IP地址
            Pdu pduUpdate = new Pdu();
            pduUpdate = pduService.selectByMachineID(machineID);

            pduUpdate.setMachineid(machineID);
            pduUpdate.setIp(ip);

            pduService.update(pduUpdate);

//            Message message = new Message();
//            message.setIp(ip);
//            message.setMessageID(machineID);
//            addPdu(message,socket);//或添加 或修改设备状态

            r = "ok";

        } catch (Exception e) {
            e.printStackTrace();
        }

        return r;

    }


    /**
     * @Author:xulei
     * @Description:心跳包，修改心跳状态和IP地址
     * @Date:2018-05-03
     */
    public static String updatePduOnline(MessageDLT messageDLT, Socket socket) {
        String r = "";
        try {
            //解析设备地址
            String machineID = messageDLT.getMachineAddress();
//            machineID = MessageStringDLTUtils.machineAddressHex(machineID);
//            machineID = machineID.substring(1,machineID.length());
            System.out.println("设备ID：" + machineID);
            String ip = socket.getInetAddress().toString();
            ip = ip.substring(1, ip.length());
            System.out.println("修改设备" + machineID + " 的IP地址为：" + ip);
            //修改设备IP地址
            Pdu pduUpdate = new Pdu();
            pduUpdate = pduService.selectByMachineID(machineID);

            pduUpdate.setMachineid(machineID);
            pduUpdate.setIp(ip);
            pduUpdate.setOnlinestate("1");

            pduService.update(pduUpdate);

//            Message message = new Message();
//            message.setIp(ip);
//            message.setMessageID(machineID);
//            addPdu(message,socket);//或添加 或修改设备状态

            r = "ok";

        } catch (Exception e) {
            e.printStackTrace();
        }

        return r;

    }

    public static String sendDeviceEvent(int pduid, int event_type) {

        DeviceEvent deviceEvent = new DeviceEvent();

        PduInfoTemp pduInfoTemp = new PduInfoTemp();
        pduInfoTemp = pduService.pduInfoByPrimaryKey(pduid);

//        int event_type = deviceEvent.TYPE_OFFLINE;//事件类型
        String device_id = String.valueOf(pduInfoTemp.getPduid());//设备ID
        String device_name = pduInfoTemp.getName();//设备名称
        String group_id = String.valueOf(pduInfoTemp.getPdugroupid());//分组ID
        String group_name = pduInfoTemp.getGroupname();//分组名称
        int visible = 100;
        String desc = "设备" + pduInfoTemp.getName() + "已离线";//消息内容
        switch (event_type) {
            case 0:
                visible = deviceEvent.VISIBILITY_VISIBLE;
                desc = "设备" + pduInfoTemp.getName() + "已上线！";
                break;
            case 1:
                visible = deviceEvent.VISIBILITY_VISIBLE;
                desc = "设备" + pduInfoTemp.getName() + "已离线！";
                break;
            case 2:
                visible = deviceEvent.VISIBILITY_VISIBLE;
                desc = "警告：设备" + pduInfoTemp.getName() + "存在漏电！";
                break;
            case 3:
                visible = deviceEvent.VISIBILITY_VISIBLE;
                desc = "警告：设备" + pduInfoTemp.getName() + "发生短路！";
                break;
            case 4:
                visible = deviceEvent.VISIBILITY_VISIBLE;
                desc = "警告：设备" + pduInfoTemp.getName() + "功率异常！";
                break;
            case 5:
                visible = deviceEvent.VISIBILITY_VISIBLE;
                desc = "警告：设备" + pduInfoTemp.getName() + "过压异常！";
                break;
            case 6:
                visible = deviceEvent.VISIBILITY_VISIBLE;
                desc = "警告：设备" + pduInfoTemp.getName() + "欠压异常！";
                break;
            case 7:
                visible = deviceEvent.VISIBILITY_VISIBLE;
                desc = "警告：设备" + pduInfoTemp.getName() + "过流异常";
                break;
            case 8:
                visible = deviceEvent.VISIBILITY_INVISIBLE;
                desc = "通知：设备" + pduInfoTemp.getName() + "已切断电源！";
                break;
            case 9:
                visible = deviceEvent.VISIBILITY_INVISIBLE;
                desc = "通知：设备" + pduInfoTemp.getName() + "已合闸上电！";
                break;
            case 10:
                visible = deviceEvent.VISIBILITY_VISIBLE;
                desc = "警告：设备" + pduInfoTemp.getName() + "烟雾预警！";
                break;
            case 11:
                visible = deviceEvent.VISIBILITY_VISIBLE;
                desc = "警告：设备" + pduInfoTemp.getName() + "温度预警！";
                break;
            case 12:
                visible = deviceEvent.VISIBILITY_VISIBLE;
                desc = "警告：设备" + pduInfoTemp.getName() + "液位预警！";
                break;

        }


        deviceEvent.setEvent_type(event_type);
        deviceEvent.setDevice_id(device_id);
        deviceEvent.setDevice_name(device_name);
        deviceEvent.setGroup_id(group_id);
        deviceEvent.setGroup_name(group_name);
        deviceEvent.setVisible(visible);
        deviceEvent.setDesc(desc);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(System.currentTimeMillis()); //发生时间
        deviceEvent.setTime(time);

        String appMessage = deviceEvent.toSocketMessage();

        return appMessage;
    }

    public static int warningChange(int warningType) {
        int event_type = 100;
        switch (Integer.valueOf(warningType)) {
            case 0:
                event_type = DeviceEvent.TYPE_ELECTRIC_LEAKAGE;//漏电事件类型
                break;
            case 1:
                event_type = DeviceEvent.TYPE_OPEN_CIRCUIT;//断路事件类型
                break;
            case 2:
                event_type = DeviceEvent.TYPE_POWER_ABNORMAL;//功率事件类型
                break;
            case 3:
                event_type = DeviceEvent.TYPE_OVER_VOLTAGE;//过压事件类型
                break;
            case 4:
                event_type = DeviceEvent.TYPE_UNDER_VOLTAGE;//欠压事件类型
                break;
            case 5:
                event_type = DeviceEvent.TYPE_OVER_CURRENT;//过流事件类型
                break;
            case 6:
                event_type = DeviceEvent.TYPE_SMOKE_OPEN;//烟雾事件类型
                break;
            case 7:
                event_type = DeviceEvent.TYPE_TEMPERATURE_OPEN;//温度事件类型
                break;
            case 8:
                event_type = DeviceEvent.TYPE_WATERLEVEL_OPEN;//液位事件类型
                break;
        }

        return event_type;
    }


    /**
     * @Author:xulei
     * @Description:APP 消息通知推送
     * @Date: 2018-05-16
     */
    private static void appWriteMsgToClient(OutputStream outputStream, String string) throws IOException {
        try {
            OutputStreamWriter outSW = null;
            outSW = new OutputStreamWriter(outputStream, "UTF-8");
            BufferedWriter bw = new BufferedWriter(outSW);
            bw.write(string);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void connectionMapMessageSend(Map map, String message) {

        //获取所有的TCP连接
        int mapNum = map.size();
        String key = "";
        Socket connection = null;
        try {
            if (mapNum > 0) {
//            Map entry = new HashMap<String,Socket>();
                //循环所有连接Map map = new HashMap();
                Iterator entries = map.entrySet().iterator();

                while (entries.hasNext()) {
                    Map.Entry entry = (Map.Entry) entries.next();
                    key = (String) entry.getKey();
                    connection = (Socket) entry.getValue();//获取连接
                    System.out.println("Key = " + key);

                    appWriteMsgToClient(connection.getOutputStream(), message);
                }
            }
        } catch (Exception e) {
            BaseController.APPSubPolmap.remove(key);
            try {
                connection.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

    }

}
