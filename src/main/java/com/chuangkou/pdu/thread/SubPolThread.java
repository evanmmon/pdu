package com.chuangkou.pdu.thread;

import com.chuangkou.pdu.controller.BaseController;
import com.chuangkou.pdu.controller.PduTemporaryController;
import com.chuangkou.pdu.entity.*;
import com.chuangkou.pdu.service.*;
import com.chuangkou.pdu.util.DeviceEvent;
import com.chuangkou.pdu.util.MessageStringDLTUtils;
import com.chuangkou.pdu.util.StringUtil;
import net.sf.json.JSONObject;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Callable;

/**
 * @Author:
 * @Description:
 * @Date:Created in 10:07 2018/3/2
 */

//public class SubPolThread implements Callable<Boolean> {
public class SubPolThread implements Runnable {
    //    @Resource(name = "pduTemporaryService")
//    private static PduTemporaryService pduTemporaryService2;
//PduTemporaryService pduTemporaryService=(PduTemporaryService) SpringApplicationContextHolder.getSpringBean("pduTemporaryService");
//    @Resource(name = "pduService")
//    private static PduService pduService;
//
//    @Resource(name = "pduInfoService")
//    private static PduInfoService pduInfoService;
//
//    @Resource(name = "pduWarningSetService")
//    private static PduWarningSetService pduWarningSetService;
//
//    @Resource(name = "pduWarningService")
//    private static PduWarningService pduWarningService;

    private static Socket connection;
    public static String readMsg;
    boolean run = true;
    int num = 0;


    private static PduWarningSetService pduWarningSetService = (PduWarningSetService) SpringApplicationContextHolder.getSpringBean("pduWarningSetService");

    private static PduWarningService pduWarningService = (PduWarningService) SpringApplicationContextHolder.getSpringBean("pduWarningService");

    private static PduInfoService pduInfoService = (PduInfoService) SpringApplicationContextHolder.getSpringBean("pduInfoService");

    private static PduService pduService = (PduService) SpringApplicationContextHolder.getSpringBean("pduService");

    private static PduTemporaryService pduTemporaryService = (PduTemporaryService) SpringApplicationContextHolder.getSpringBean("pduTemporaryService");

    private static PduGroupRelationService pduGroupRelationService = (PduGroupRelationService) SpringApplicationContextHolder.getSpringBean("pduGroupRelationService");

    public SubPolThread(Socket conSocket) throws Exception {
        this.connection = conSocket;
//        this.call();
//        run();
    }


    /* (non-Javadoc)
     * @see java.util.concurrent.Callable#call()
     */
    @Override
//    public Boolean call() throws Exception {
    public void run() {


        try {
            while (true) {
                //                Thread.sleep(1000);
                String msg = "";
                msg = ThreadUtils.readMessageFromClient(connection.getInputStream());
                //                System.out.println("鏀跺埌鎶ユ枃==" + msg);
                //                setReadMsg(msg);

                Message message = new Message();
                MessageDLT messageDLT = new MessageDLT();

                String dataTab = "06EE0100";//棰勮鎶ユ枃
                dataTab = MessageStringDLTUtils.dateIDhex(dataTab);

                if (!msg.equals("")) {

                    messageDLT = MessageStringDLTUtils.onlineMessage(msg);
                    String macheineID = messageDLT.getMachineAddress();
                    macheineID = MessageStringDLTUtils.machineAddressHexOpposite(macheineID);//姝ｅ簭

                    if (msg.substring(0, 8).equals("FEFEFEFE")) {
                        BaseController.readmsg = msg;
                        System.out.println("鏀跺埌鍥炲鐨勬姤鏂�==" + BaseController.readmsg);
                        //                        Thread.sleep(1000);
                        break;
                    }

                    //鍒ゆ柇鏄績璺冲寘
                    if (messageDLT.getDataTab().equals("3C3C3239") && messageDLT.getDataStr().equals("55555555")) {//璁惧涓婄數鍙戦�佹姤鏂囷紝鏍稿IP鍦板潃鍜岃澶嘔D
                        System.out.println("杩欐槸蹇冭烦鍖咃紒");

                        messageDLT.setControl("2E");
                        messageDLT.setMachineAddress(macheineID);

                        String heartmsg = getHeartMessage(macheineID);

                        System.out.println("鍙戦�佸績璺�==" + heartmsg);

                        ThreadUtils.writeMsgToClient(connection.getOutputStream(), heartmsg);

                        String threadIp = this.connection.getInetAddress().toString();
                        message.setIp(threadIp.substring(1, threadIp.length()));
                        String ip = message.getIp();

                        //                        String machineID = messageDLT.getMachineAddress();
                        //                        machineID = MessageStringDLTUtils.machineAddressHex(machineID);

                        //璁惧涓婄數瀵规牎鏃�
                        Thread.sleep(2000);
                        Thread threadTime = new Thread(new JobPduDateTimeSetThread(connection, messageDLT.getMachineAddress()));
                        threadTime.start();
                        threadTime.join();

                        Pdu searchPdu = new Pdu();
                        //                        searchPdu.setMachineid(machineID);
                        System.out.println("macheineID== " + macheineID);
                        searchPdu = pduService.selectByMachineID(macheineID);

                        if (searchPdu != null) {

                            //鍒ゆ柇IP鍦板潃涓嶅悓 鐘舵�佷负绂荤嚎鐨� 淇敼璁惧淇℃伅
                            if (!searchPdu.getIp().equals(ip) || searchPdu.getOnlinestate().equals("2")) {

                                System.out.println("璁惧瀛樺湪锛屼絾鏄疘P鍦板潃鏈夊彉鍖�");
                                String updateAction = ThreadUtils.updatePduIpDLT(messageDLT, ip, this.connection);
                                Pdu pduaddress = pduService.selectByMachineID(macheineID);


                                //璁惧璁惧涓婄嚎 鎺ㄩ�佹秷鎭彁閱�
                                System.out.println("璁惧涓婄嚎鎺ㄩ�佹秷鎭�====");
                                int event_type = DeviceEvent.TYPE_ONLINE;//涓嬬嚎浜嬩欢绫诲瀷
                                String appMessage = ThreadUtils.sendDeviceEvent(pduaddress.getId(), event_type);
                                ThreadUtils.connectionMapMessageSend(BaseController.APPSubPolmap, appMessage);

                            } else {

                                //                                BaseController.SubPolmap.put(addressip, connection);
                            }
                        }

                        if (searchPdu == null) {

                            //鏂拌澶�
                            String updateAction = ThreadUtils.updatePduIpDLT(messageDLT, ip, this.connection);
                            //                            BaseController.SubPolmap.put(addressip, connection);

                            Pdu pduaddress = pduService.selectByMachineID(macheineID);

                            //璁惧涓婄數瀵规牎鏃�
                            //                            System.out.println("璁惧涓婄數瀵规牎鏃�====");
                            threadTime = new Thread(new JobPduDateTimeSetThread(connection, pduaddress.getMachineid()));
                            threadTime.start();
                            threadTime.join();

                            //璁惧璁惧涓婄嚎 鎺ㄩ�佹秷鎭彁閱�
                            System.out.println("璁惧涓婄嚎鎺ㄩ�佹秷鎭�====");
                            int event_type = DeviceEvent.TYPE_ONLINE;//涓嬬嚎浜嬩欢绫诲瀷
                            String appMessage = ThreadUtils.sendDeviceEvent(pduaddress.getId(), event_type);
                            ThreadUtils.connectionMapMessageSend(BaseController.APPSubPolmap, appMessage);

                        }
                        //                        }

                    }


                    //鍒ゆ柇鏄璀︿俊鎭�
                    if (messageDLT.getDataTab().equals(dataTab)) {
                        PduWarning pduWarning = new PduWarning();
                        System.out.println("鏀跺埌棰勮淇℃伅锛佺紪鍙凤細" + macheineID);
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
                            pduWarningService.insert(pduWarning);//鎻掑叆涓婃姤鐨勯璀︿俊鎭�

                            Pdu updatePdu = new Pdu();
                            System.out.println("warningPdu.getId()===" + warningPdu.getId());
                            updatePdu.setId(warningPdu.getId());
                            updatePdu.setOnlinestate("3");
                            pduService.update(updatePdu);//淇敼璁惧鐘舵�佷负 鍙戠敓鏁呴殰

                            //璁惧鏁呴殰 鎺ㄩ�佹秷鎭彁閱� 鑷矨PP
                            int event_type = 100;
                            switch (Integer.valueOf(warningType)) {
                                case 0:
                                    event_type = DeviceEvent.TYPE_ELECTRIC_LEAKAGE;//婕忕數浜嬩欢绫诲瀷
                                    break;
                                case 1:
                                    event_type = DeviceEvent.TYPE_OPEN_CIRCUIT;//鏂矾浜嬩欢绫诲瀷
                                    break;
                                case 2:
                                    event_type = DeviceEvent.TYPE_POWER_ABNORMAL;//鍔熺巼浜嬩欢绫诲瀷
                                    break;
                                case 3:
                                    event_type = DeviceEvent.TYPE_OVER_VOLTAGE;//杩囧帇浜嬩欢绫诲瀷
                                    break;
                                case 4:
                                    event_type = DeviceEvent.TYPE_UNDER_VOLTAGE;//娆犲帇浜嬩欢绫诲瀷
                                    break;
                                case 5:
                                    event_type = DeviceEvent.TYPE_OVER_CURRENT;//杩囨祦浜嬩欢绫诲瀷
                                    break;
                                case 6:
                                    event_type = DeviceEvent.TYPE_SMOKE_OPEN;//鐑熼浘浜嬩欢绫诲瀷
                                    break;
                                case 7:
                                    event_type = DeviceEvent.TYPE_TEMPERATURE_OPEN;//娓╁害浜嬩欢绫诲瀷
                                    break;
                                case 8:
                                    event_type = DeviceEvent.TYPE_WATERLEVEL_OPEN;//娑蹭綅浜嬩欢绫诲瀷
                                    break;
                            }

                            String appMessage = ThreadUtils.sendDeviceEvent(warningPdu.getId(), event_type);
                            ThreadUtils.connectionMapMessageSend(BaseController.APPSubPolmap, appMessage);
                        } else {
                            System.out.println("璁惧锛�" + macheineID + "涓嶅瓨鍦�");
                        }
                        //                        BaseController.SubPolmap.put(addressip, connection);
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


/**
 String addressip = connection.getInetAddress().toString();
 addressip = addressip.substring(1, addressip.length());
 int pduid = 0;
 int num = 0;
 try {
 //            while (run) {
 Thread.sleep(4000);
 //璇诲彇瀹㈡埛绔紶杩囨潵鐨勬暟鎹姤鏂�
 String msg = "";
 Message message = new Message();
 MessageDLT messageDLT = new MessageDLT();

 //                while(msg.equals("")){
 msg = ThreadUtils.readMessageFromClient(connection.getInputStream());
 //                }


 System.out.println(connection.getInetAddress().toString() + "===" + msg);


 //                System.out.println("鏀跺埌鎶ユ枃==" + msg);
 String dataTab = "06EE0100";//棰勮鎶ユ枃
 dataTab = MessageStringDLTUtils.dateIDhex(dataTab);

 if (!msg.equals("")) {

 messageDLT = MessageStringDLTUtils.onlineMessage(msg);
 String macheineID = messageDLT.getMachineAddress();
 macheineID = MessageStringDLTUtils.machineAddressHexOpposite(macheineID);//姝ｅ簭

 //鍒ゆ柇鏄績璺冲寘
 if (messageDLT.getDataTab().equals("3C3C3239") && messageDLT.getDataStr().equals("55555555")) {//璁惧涓婄數鍙戦�佹姤鏂囷紝鏍稿IP鍦板潃鍜岃澶嘔D
 System.out.println("杩欐槸蹇冭烦鍖咃紒");

 messageDLT.setControl("2E");
 messageDLT.setMachineAddress(macheineID);

 String heartmsg = getHeartMessage(macheineID);

 //                        String heartmsg = msg.replaceAll("11", "2E");
 //                        String sum = MessageStringDLTUtils.makeChecksum(heartmsg.substring(0, heartmsg.length() - 4));
 //                        heartmsg = heartmsg.substring(0, heartmsg.length() - 4) + sum + "160D0A";

 System.out.println("鍙戦�佸績璺�==" + heartmsg);

 ThreadUtils.writeMsgToClient(connection.getOutputStream(), heartmsg);

 String readheart = ThreadUtils.readMessageFromClient(connection.getInputStream());

 //                        while (readheart.equals("")) {
 //                            System.out.println("閲嶅彂蹇冭烦===" + heartmsg);
 //                            ThreadUtils.writeMsgToClient(connection.getOutputStream(), heartmsg);
 //
 //                            readheart = ThreadUtils.readMessageFromClient(connection.getInputStream());
 //                            System.out.println("readheart===" + readheart);
 //                        }

 //                        if (readheart.equals("")) {
 //                            messageDLT = MessageStringDLTUtils.onlineMessage(readheart);
 //                            String newddress = MessageStringDLTUtils.machineAddressHexOpposite(messageDLT.getMachineAddress());
 //                            if (messageDLT.getControl().equals("91") && macheineID.equals(newddress)) {
 //                                System.out.println("鏀跺埌蹇冭烦锛�");
 //                            }


 String threadIp = this.connection.getInetAddress().toString();
 message.setIp(threadIp.substring(1, threadIp.length()));
 String ip = message.getIp();

 String machineID = messageDLT.getMachineAddress();
 machineID = MessageStringDLTUtils.machineAddressHex(machineID);

 //璁惧涓婄數瀵规牎鏃�
 //                                System.out.println("璁惧涓婄數瀵规牎鏃�====");
 Thread threadTime = new Thread(new JobPduDateTimeSetThread(connection, machineID));
 threadTime.start();
 threadTime.join();


 Pdu searchPdu = new Pdu();
 //                        searchPdu.setMachineid(machineID);
 searchPdu = pduService.selectByMachineID(machineID);

 if (searchPdu != null) {

 //鍒ゆ柇IP鍦板潃涓嶅悓 鐘舵�佷负绂荤嚎鐨� 淇敼璁惧淇℃伅
 if (!searchPdu.getIp().equals(ip) || searchPdu.getOnlinestate().equals("2")) {

 System.out.println("璁惧瀛樺湪锛屼絾鏄疘P鍦板潃鏈夊彉鍖�");
 String updateAction = ThreadUtils.updatePduIpDLT(messageDLT, ip, this.connection);
 //                                BaseController.SubPolmap.put(addressip, connection);

 Pdu pduaddress = pduService.selectByMachineID(macheineID);



 //璁惧璁惧涓婄嚎 鎺ㄩ�佹秷鎭彁閱�
 System.out.println("璁惧涓婄嚎鎺ㄩ�佹秷鎭�====");
 int event_type = DeviceEvent.TYPE_ONLINE;//涓嬬嚎浜嬩欢绫诲瀷
 String appMessage = ThreadUtils.sendDeviceEvent(pduaddress.getId(), event_type);
 ThreadUtils.connectionMapMessageSend(BaseController.APPSubPolmap, appMessage);

 } else {

 //                                BaseController.SubPolmap.put(addressip, connection);
 }
 }

 if (searchPdu == null) {

 //鏂拌澶�
 String updateAction = ThreadUtils.updatePduIpDLT(messageDLT, ip, this.connection);
 //                            BaseController.SubPolmap.put(addressip, connection);

 Pdu pduaddress = pduService.selectByMachineID(macheineID);

 //璁惧涓婄數瀵规牎鏃�
 //                            System.out.println("璁惧涓婄數瀵规牎鏃�====");
 threadTime = new Thread(new JobPduDateTimeSetThread(connection, pduaddress.getMachineid()));
 threadTime.start();
 threadTime.join();

 //璁惧璁惧涓婄嚎 鎺ㄩ�佹秷鎭彁閱�
 System.out.println("璁惧涓婄嚎鎺ㄩ�佹秷鎭�====");
 int event_type = DeviceEvent.TYPE_ONLINE;//涓嬬嚎浜嬩欢绫诲瀷
 String appMessage = ThreadUtils.sendDeviceEvent(pduaddress.getId(), event_type);
 ThreadUtils.connectionMapMessageSend(BaseController.APPSubPolmap, appMessage);

 }
 //                        }

 }


 //鍒ゆ柇鏄璀︿俊鎭�
 if (messageDLT.getDataTab().equals(dataTab)) {
 PduWarning pduWarning = new PduWarning();

 Pdu warningPdu = pduService.selectByMachineID(messageDLT.getMachineAddress());

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
 pduWarningService.insert(pduWarning);//鎻掑叆涓婃姤鐨勯璀︿俊鎭�

 Pdu updatePdu = new Pdu();
 updatePdu.setId(warningPdu.getId());
 updatePdu.setOnlinestate("3");
 pduService.update(updatePdu);//淇敼璁惧鐘舵�佷负 鍙戠敓鏁呴殰

 //璁惧鏁呴殰 鎺ㄩ�佹秷鎭彁閱� 鑷矨PP
 int event_type = 100;
 switch (Integer.valueOf(warningType)) {
 case 0:
 event_type = DeviceEvent.TYPE_ELECTRIC_LEAKAGE;//婕忕數浜嬩欢绫诲瀷
 break;
 case 1:
 event_type = DeviceEvent.TYPE_OPEN_CIRCUIT;//鏂矾浜嬩欢绫诲瀷
 break;
 case 2:
 event_type = DeviceEvent.TYPE_POWER_ABNORMAL;//鍔熺巼浜嬩欢绫诲瀷
 break;
 case 3:
 event_type = DeviceEvent.TYPE_OVER_VOLTAGE;//杩囧帇浜嬩欢绫诲瀷
 break;
 case 4:
 event_type = DeviceEvent.TYPE_UNDER_VOLTAGE;//娆犲帇浜嬩欢绫诲瀷
 break;
 case 5:
 event_type = DeviceEvent.TYPE_OVER_CURRENT;//杩囨祦浜嬩欢绫诲瀷
 break;
 case 6:
 event_type = DeviceEvent.TYPE_SMOKE_OPEN;//鐑熼浘浜嬩欢绫诲瀷
 break;
 case 7:
 event_type = DeviceEvent.TYPE_TEMPERATURE_OPEN;//娓╁害浜嬩欢绫诲瀷
 break;
 case 8:
 event_type = DeviceEvent.TYPE_WATERLEVEL_OPEN;//娑蹭綅浜嬩欢绫诲瀷
 break;
 }

 String appMessage = ThreadUtils.sendDeviceEvent(warningPdu.getId(), event_type);
 ThreadUtils.connectionMapMessageSend(BaseController.APPSubPolmap, appMessage);

 //                        BaseController.SubPolmap.put(addressip, connection);
 }

 //鍒ゆ柇鏄澶囨墜鍔ㄦ媺闂搞�佸悎闂�
 if (messageDLT.getControl().equals("1C") && messageDLT.getDataTab().equals("35434343")) {

 String datastr = messageDLT.getDataStr();
 //鍒ゆ柇鎷夐椄
 if (datastr.indexOf("4D") != -1) {
 //鏀跺埌鎵嬪姩鎷夐椄鐨勬秷鎭紝淇敼璁惧鐨勭户鐢靛櫒鐘舵��
 System.out.println("鏀跺埌鎵嬪姩鎷夐椄鐨勬秷鎭�,鏀硅澶囩殑缁х數鍣ㄧ姸鎬�");
 Pdu actionPdu = new Pdu();
 actionPdu = pduService.selectByMachineID(macheineID);
 actionPdu.setActionState("0");
 actionPdu.setOnlinestate("0");

 pduService.update(actionPdu);
 }

 //鍒ゆ柇鍚堥椄
 if (datastr.indexOf("4E") != -1) {
 System.out.println("鏀跺埌鎵嬪姩鍚堥椄鐨勬秷鎭紝淇敼璁惧鐨勭户鐢靛櫒鐘舵��");
 Pdu actionPdu = new Pdu();
 actionPdu = pduService.selectByMachineID(macheineID);
 actionPdu.setActionState("1");
 actionPdu.setOnlinestate("1");
 pduService.update(actionPdu);
 }

 }

 }

 if (msg.equals("") && num == 0) {
 Pdu pduinfo = new Pdu();
 pduinfo.setIp(addressip);
 List<Pdu> list = pduService.selectAllBySearch(pduinfo);

 if (list != null && list.size() > 0) {
 Pdu pduaddressResult = new Pdu();
 pduaddressResult = list.get(0);

 if (pduaddressResult.getType().equals("180") && msg.equals("") && pduaddressResult.getMachineid().length() == 10) {//鍒ゆ柇鏄┖寮�璁惧

 //                            BaseController.SubPolmap.put(addressip, connection);
 //闆嗕腑鍣ㄥ簲璇ョ粰澶氫釜璁惧 杩涜鏍℃
 for (int i = 0; i < list.size(); i++) {
 pduaddressResult = list.get(i);
 //                                System.out.println("绌哄紑鏍℃椂====");
 Thread threadTime = new Thread(new JobPduDateTimeSetThread(connection, pduaddressResult.getMachineid()));
 threadTime.start();
 threadTime.join();
 }
 num++;
 //                        break;
 } else {
 //                            connection.close();
 //                            break;
 }

 } else {
 //                        connection.close();
 //                        break;
 }

 }
 //            }
 //                connection.close();
 } catch(Exception e){
 //                BaseController.SubPolmap.remove(addressip);
 //                System.out.println(connection.getInetAddress() + "杩炴帴寮傚父锛屽凡鏂紑杩炴帴锛�");
 //                杩炴帴寮傚父 鎺ㄩ�佹秷鎭彁閱�
 //                int event_type = DeviceEvent.TYPE_OFFLINE;//涓嬬嚎浜嬩欢绫诲瀷
 //                String appMessage = ThreadUtils.sendDeviceEvent(pduid, event_type);
 //                ThreadUtils.connectionMapMessageSend(BaseController.APPSubPolmap, appMessage);

 e.printStackTrace();
 //                break;
 }
 //        return true;
 //        }

 **/
}


        /**
         * 璇诲彇瀹㈡埛绔俊鎭�
         *
         * @param inputStream
         */

    private static String readMessageFromClient(InputStream inputStream) throws IOException {
//        Reader reader = new InputStreamReader(inputStream);
//        BufferedReader br = new BufferedReader(reader);
//        String a = null;
//        String message = "";
//
//        //寰幆鎺ユ敹鎶ユ枃
//        while ((a = br.readLine()) != null) {
//            message += a;
//            System.out.println(message);
//            return message;
//        }
//        System.out.println("宸叉帴鏀跺埌瀹㈡埛绔繛鎺�");
//        BufferedReader bufferedReader = new BufferedReader(reader);//鍔犲叆缂撳啿鍖�
//        String temp = null;
//        String info = "";

//        while ((temp = bufferedReader.readLine()) != null) {
//            info += temp;
//            System.out.println("宸叉帴鏀跺埌瀹㈡埛绔繛鎺�");
//            System.out.println("鏈嶅姟绔帴鏀跺埌瀹㈡埛绔俊鎭細" + info );
//        }

        String message = "";
        try {
            byte[] bytes = null;
            int bufflenth = inputStream.available();
            if (bufflenth > 0) {
                while (bufflenth != 0) {
                    // 鍒濆鍖朾yte鏁扮粍涓篵uffer涓暟鎹殑闀垮害
                    bytes = new byte[bufflenth];
                    inputStream.read(bytes);
                    bufflenth = inputStream.available();
                }
                message = StringUtil.str2HexStr(bytes);
                System.out.println("鎺ユ敹鍒版柊鎶ユ枃锛�" + message);
            }
        } catch (Exception e) {
            System.out.println("鎺ユ敹鎶ユ枃寮傚父锛�");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return message;

    }

    /**
     * 鍝嶅簲瀹㈡埛绔俊鎭�
     *
     * @param outputStream
     * @param string
     */
    private static void writeMsgToClient(OutputStream outputStream, String string) throws IOException {
//        Writer writer = new OutputStreamWriter(outputStream);
//        writer.append(string);
//        writer.flush();
//        writer.close();
        try {
//            System.out.println("鍙戦�佺殑鎶ユ枃涓猴細" + StringUtil.hexToBytes(string));
//            outputStream.write(StringUtil.hexToBytes(string));//蹇呴』鍗佸叚杩涘埗杞琤yte绫诲瀷鎵嶈兘杩涜鎺у埗
//            outputStream.flush();
            byte[] ss = StringUtil.hexStringToByteArray(string);
            System.out.println("鍥炲鐨勬姤鏂囦负===" + ss.toString());
            outputStream.write(ss);
            outputStream.flush();
        } catch (IOException e) {
            System.out.println("鍙戦�佹姤鏂囧紓甯革紒");
            e.printStackTrace();
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

    public static String getReadMsg() {
        return readMsg;
    }

    public static void setReadMsg(String readMsg) {
        SubPolThread.readMsg = readMsg;
    }
}
