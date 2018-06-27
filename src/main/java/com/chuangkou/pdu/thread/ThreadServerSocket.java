package com.chuangkou.pdu.thread;

import com.chuangkou.pdu.controller.BaseController;
import com.chuangkou.pdu.entity.Message;
import com.chuangkou.pdu.entity.MessageDLT;
import com.chuangkou.pdu.entity.Pdu;
import com.chuangkou.pdu.entity.PduWarning;
import com.chuangkou.pdu.service.PduInfoService;
import com.chuangkou.pdu.service.PduService;
import com.chuangkou.pdu.service.PduWarningService;
import com.chuangkou.pdu.util.DeviceEvent;
import com.chuangkou.pdu.util.MessageStringDLTUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;

/**
 * @Author:
 * @Description:
 * @Date:Created in 14:34 2018/6/11
 */
public class ThreadServerSocket implements Runnable {

    private Socket connection;

    private int BUFFER_SIZE = 1024;


    private static PduWarningService pduWarningService = (PduWarningService) SpringApplicationContextHolder.getSpringBean("pduWarningService");

    private static PduInfoService pduInfoService = (PduInfoService) SpringApplicationContextHolder.getSpringBean("pduInfoService");

    private static PduService pduService = (PduService) SpringApplicationContextHolder.getSpringBean("pduService");


    public ThreadServerSocket(Socket i) {
        // TODO Auto-generated constructor stub
        connection = i;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
//            InputStream in = server.getInputStream();
//            OutputStream out = server.getOutputStream();
            byte[] recData = null;
            while (true) {

                String msg = "";
                msg = ThreadUtils.readMessageFromClient(connection.getInputStream());

                Message message = new Message();
                MessageDLT messageDLT = new MessageDLT();

                String dataTab = "06EE0100";//预警报文
                dataTab = MessageStringDLTUtils.dateIDhex(dataTab);

                if (!msg.equals("")) {

                    messageDLT = MessageStringDLTUtils.onlineMessage(msg);
                    String macheineID = messageDLT.getMachineAddress();
                    macheineID = MessageStringDLTUtils.machineAddressHexOpposite(macheineID);//正序


                    if (msg.substring(0, 8).equals("FEFEFEFE")) {
                        BaseController.readmsg = msg;
                        System.out.println("收到回复的报文==" + BaseController.readmsg);
                    }

                    //判断是心跳包
                    if (messageDLT.getDataTab().equals("3C3C3239") && messageDLT.getDataStr().equals("55555555")) {//设备上电发送报文，核对IP地址和设备ID
                        System.out.println("这是心跳包！");

                        messageDLT.setControl("2E");
                        messageDLT.setMachineAddress(macheineID);

                        String heartmsg = getHeartMessage(macheineID);

                        System.out.println("发送心跳==" + heartmsg);

                        ThreadUtils.writeMsgToClient(connection.getOutputStream(), heartmsg);

                        String threadIp = this.connection.getInetAddress().toString();
                        message.setIp(threadIp.substring(1, threadIp.length()));
                        String ip = message.getIp();

                        //                        String machineID = messageDLT.getMachineAddress();
                        //                        machineID = MessageStringDLTUtils.machineAddressHex(machineID);

                        //设备上电对校时
                        Thread.sleep(2000);
                        Thread threadTime = new Thread(new JobPduDateTimeSetThread(connection, messageDLT.getMachineAddress()));
                        threadTime.start();
                        threadTime.join();

                        Pdu searchPdu = new Pdu();
                        //                        searchPdu.setMachineid(machineID);
                        System.out.println("macheineID== " + macheineID);
                        searchPdu = pduService.selectByMachineID(macheineID);

                        if (searchPdu != null) {

                            //判断IP地址不同 状态为离线的 修改设备信息
                            if (!searchPdu.getIp().equals(ip) || searchPdu.getOnlinestate().equals("2")) {

                                System.out.println("设备存在，但是IP地址有变化");
                                String updateAction = ThreadUtils.updatePduIpDLT(messageDLT, ip, this.connection);
                                Pdu pduaddress = pduService.selectByMachineID(macheineID);

                                //单个设备拓扑结构
//                                System.out.println("匹配拓扑节点");
//                                Thread.sleep(5000);
//                                Thread threadPduInfo = new Thread(new JobPduPlugThreadTwo(this.connection, pduaddress));
//                                threadPduInfo.start();
//                                threadPduInfo.join();

                                //设备设备上线 推送消息提醒
                                System.out.println("设备上线推送消息====");
                                int event_type = DeviceEvent.TYPE_ONLINE;//下线事件类型
                                String appMessage = ThreadUtils.sendDeviceEvent(pduaddress.getId(), event_type);
                                ThreadUtils.connectionMapMessageSend(BaseController.APPSubPolmap, appMessage);


                            } else {
                                Pdu pduaddress = pduService.selectByMachineID(macheineID);
                                //单个设备拓扑结构
//                                System.out.println("匹配拓扑节点");
//                                Thread.sleep(5000);
//                                Thread threadPduInfo = new Thread(new JobPduPlugThreadTwo(this.connection, pduaddress));
//                                threadPduInfo.start();
//                                threadPduInfo.join();

                                //设备设备上线 推送消息提醒
                                System.out.println("设备上线推送消息====");
                                int event_type = DeviceEvent.TYPE_ONLINE;//下线事件类型
                                String appMessage = ThreadUtils.sendDeviceEvent(pduaddress.getId(), event_type);
                                ThreadUtils.connectionMapMessageSend(BaseController.APPSubPolmap, appMessage);

                            }
                        }

                        if (searchPdu == null) {

                            //新设备
                            String updateAction = ThreadUtils.updatePduIpDLT(messageDLT, ip, this.connection);
                            //                            BaseController.SubPolmap.put(addressip, connection);

                            Pdu pduaddress = pduService.selectByMachineID(macheineID);

                            //设备上电对校时
                            //                            System.out.println("设备上电对校时====");
                            threadTime = new Thread(new JobPduDateTimeSetThread(connection, pduaddress.getMachineid()));
                            threadTime.start();
                            threadTime.join();

                            //设备设备上线 推送消息提醒
                            System.out.println("设备上线推送消息====");
                            int event_type = DeviceEvent.TYPE_ONLINE;//下线事件类型
                            String appMessage = ThreadUtils.sendDeviceEvent(pduaddress.getId(), event_type);
                            ThreadUtils.connectionMapMessageSend(BaseController.APPSubPolmap, appMessage);


                        }

                    }


                    //判断是预警信息
                    if (messageDLT.getDataTab().equals(dataTab)) {
                        PduWarning pduWarning = new PduWarning();
                        System.out.println("收到预警信息！编号：" + macheineID);
                        Pdu warningPdu = pduService.selectByMachineID(macheineID);

                        if (warningPdu != null) {
                            //                            String dataStr = messageDLT.getDataStr();
                            //                            String warningType = dataStr.substring(8, dataStr.length());
                            String warningType = messageDLT.getDataStr();
                            warningType = MessageStringDLTUtils.receiverVlue(warningType);

                            pduWarning.setWarningtype(warningType);
                            pduWarning.setPduid(warningPdu.getId());
                            pduWarning.setState("0");

                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String datetime = df.format(System.currentTimeMillis());
                            pduWarning.setWarningtime(datetime);
                            pduWarningService.insert(pduWarning);//插入上报的预警信息

                            Pdu updatePdu = new Pdu();
                            System.out.println("warningPdu.getId()===" + warningPdu.getId());
                            updatePdu.setId(warningPdu.getId());
                            updatePdu.setOnlinestate("3");
                            pduService.update(updatePdu);//修改设备状态为 发生故障

                            //设备故障 推送消息提醒 至APP
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

                            String appMessage = ThreadUtils.sendDeviceEvent(warningPdu.getId(), event_type);
                            ThreadUtils.connectionMapMessageSend(BaseController.APPSubPolmap, appMessage);
                        } else {
                            System.out.println("设备：" + macheineID + "不存在");
                        }
                        //                        BaseController.SubPolmap.put(addressip, connection);
                    }

                    //判断是设备手动拉闸、合闸
                    if (messageDLT.getControl().equals("1C") && messageDLT.getDataTab().equals("35434343")) {

                        String datastr = messageDLT.getDataStr();
                        //判断拉闸
                        if (datastr.indexOf("4D") != -1) {
                            //收到手动拉闸的消息，修改设备的继电器状态
                            System.out.println("收到手动拉闸的消息,改设备的继电器状态");
                            Pdu actionPdu = new Pdu();
                            actionPdu = pduService.selectByMachineID(macheineID);
                            actionPdu.setActionState("0");
                            if (!actionPdu.getOnlinestate().equals("3")) {
                                actionPdu.setOnlinestate("0");
                            }
                            pduService.update(actionPdu);
                        }

                        //判断合闸
                        if (datastr.indexOf("4E") != -1) {
                            System.out.println("收到手动合闸的消息，修改设备的继电器状态");
                            Pdu actionPdu = new Pdu();
                            actionPdu = pduService.selectByMachineID(macheineID);
                            actionPdu.setActionState("1");
                            if (!actionPdu.getOnlinestate().equals("3")) {
                                actionPdu.setOnlinestate("1");
                            }
                            pduService.update(actionPdu);
                        }

                    }

                }

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("关闭连接！！！！");
                connection.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }


    public static String getHeartMessage(String machineAddress) {
        String mesg = "";
        machineAddress = MessageStringDLTUtils.addZeroForNumLeft(machineAddress, 12);
        machineAddress = MessageStringDLTUtils.machineAddressHex(machineAddress);
        mesg = "68" + machineAddress + "682E083C3C323955555555";

        String datesum = MessageStringDLTUtils.makeChecksum(mesg);
        mesg = mesg + datesum + "160D0A";

        return mesg;

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
                message = str2HexStr(bytes);
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


    public static String str2HexStr(byte[] bs) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
//        byte[] bs = str.getBytes();
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
}
