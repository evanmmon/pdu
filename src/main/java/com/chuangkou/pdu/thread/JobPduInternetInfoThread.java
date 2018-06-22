package com.chuangkou.pdu.thread;

import com.chuangkou.pdu.controller.BaseController;
import com.chuangkou.pdu.entity.MessageDLT;
import com.chuangkou.pdu.entity.Pdu;
import com.chuangkou.pdu.entity.PduRelation;
import com.chuangkou.pdu.service.PduRelationService;
import com.chuangkou.pdu.service.PduService;
import com.chuangkou.pdu.util.MessageStringDLTUtils;
import com.chuangkou.pdu.util.SocketUtils;

import java.io.IOException;
import java.net.Socket;
import java.util.*;

/**
 * @Author:
 * @Description:
 * @Date:Created in 17:49 2018/6/13
 */
public class JobPduInternetInfoThread implements Runnable {


    private static PduService pduService = (PduService) SpringApplicationContextHolder.getSpringBean("pduService");

    private static PduRelationService pduRelationService = (PduRelationService) SpringApplicationContextHolder.getSpringBean("pduRelationService");


    List<Pdu> pduListBreaker = new ArrayList<Pdu>();

    public JobPduInternetInfoThread(List<Pdu> pduListBreaker) {
        this.pduListBreaker = pduListBreaker;
        run();
    }

    /**
     * @Override public void run() {
     * <p>
     * try {
     * for (int i = 0; i < pduListBreaker.size(); i++) {
     * Pdu pduBreakerTwo = new Pdu();
     * pduBreakerTwo = pduListBreaker.get(i);//空开信息
     * <p>
     * //获取空开关联的插座
     * List<PduRelation> pduRelationsList = new ArrayList<PduRelation>();
     * pduRelationsList = pduRelationService.selectByPlugs(pduBreakerTwo.getId());
     * <p>
     * //遍历空开关联的插座
     * for (int x = 0; x < pduRelationsList.size(); x++) {
     * PduRelation pduRelation = new PduRelation();
     * pduRelation = pduRelationsList.get(x);
     * <p>
     * int oneId = pduRelation.getPduID();
     * Pdu onePduinfo = pduService.selectByPrimaryKey(oneId);
     * <p>
     * System.out.println("one的ID===" + onePduinfo.getMachineid());
     * <p>
     * String oneConName = onePduinfo.getIp();
     * Socket onePduinfoCon = null;
     * //获取连接对象
     * onePduinfoCon = (Socket) BaseController.SubPolmap.get(oneConName);
     * <p>
     * Thread.sleep(3000);
     * //获取插座的IN值
     * String oneStartPduCurrent = "0";
     * oneStartPduCurrent = pduPlugCurrent(onePduinfo, onePduinfoCon, "in");
     * System.out.println("当前插座one电流in==" + oneStartPduCurrent);
     * <p>
     * //获取插座的out值
     * //                    String oneStartPduCurrentOut = "0";
     * //                    oneStartPduCurrentOut = pduPlugCurrent(onePduinfo, onePduinfoCon, "out");
     * //                    System.out.println("当前插座one电流out==" + oneStartPduCurrentOut);
     * <p>
     * //                    int num = 0;
     * for (int y = 0; y < pduRelationsList.size(); y++) {
     * Thread.sleep(10000);
     * PduRelation pduRelationTwo = new PduRelation();
     * pduRelationTwo = pduRelationsList.get(y);
     * <p>
     * <p>
     * int twoId = pduRelationTwo.getPduID();
     * Pdu twoPduinfo = pduService.selectByPrimaryKey(twoId);
     * <p>
     * System.out.println("22222222222222222222twoPduinfo===" + twoPduinfo.getMachineid());
     * <p>
     * if (!twoPduinfo.getMachineid().equals(onePduinfo.getMachineid())) {
     * <p>
     * String twoConName = twoPduinfo.getIp();
     * Socket twoPduinfoCon = null;
     * //获取连接对象
     * twoPduinfoCon = (Socket) BaseController.SubPolmap.get(twoConName);
     * <p>
     * //获取插座的IN值
     * String twoStartPduCurrent = "0";
     * twoStartPduCurrent = pduPlugCurrent(twoPduinfo, twoPduinfoCon, "in");
     * System.out.println("当前插座two电流in==" + twoStartPduCurrent);
     * <p>
     * //获取插座的OUT值
     * //                            String twoStartPduCurrentOut = "0";
     * //                            twoStartPduCurrentOut = pduPlugCurrent(twoPduinfo, twoPduinfoCon, "out");
     * //                            System.out.println("当前插座two电流out==" + twoStartPduCurrentOut);
     * <p>
     * long starttime = System.currentTimeMillis();
     * System.out.println("开启时间==" + System.currentTimeMillis());
     * <p>
     * //开启one
     * Thread.sleep(100);
     * //                            Thread oneThreadPlug = new Thread(new PduActionThread(onePduinfoCon, onePduinfo, "1"));
     * Thread oneThreadPlug = new Thread(new PduActionThreadPDC(onePduinfoCon, onePduinfo, "1"));
     * oneThreadPlug.start();
     * oneThreadPlug.join();
     * //                        onePduinfo.setActionState("1");
     * //                        pduService.update(onePduinfo);
     * System.out.println("已开启插座one设备" + onePduinfo.getMachineid());
     * <p>
     * <p>
     * //开启two
     * Thread.sleep(100);
     * //                            Thread twoThreadPlug = new Thread(new PduActionThread(twoPduinfoCon, twoPduinfo, "1"));
     * Thread twoThreadPlug = new Thread(new PduActionThreadPDC(twoPduinfoCon, twoPduinfo, "1"));
     * twoThreadPlug.start();
     * twoThreadPlug.join();
     * //                        twoPduinfo.setActionState("1");
     * //                        pduService.update(twoPduinfo);
     * System.out.println("已开启插座Two设备" + twoPduinfo.getMachineid());
     * <p>
     * <p>
     * Thread.sleep(10000);
     * <p>
     * //再次获取one的电流
     * String oneEndPduCurrent = "0";
     * //                            Thread.sleep(500);
     * oneEndPduCurrent = pduPlugCurrent(onePduinfo, onePduinfoCon, "in");
     * System.out.println("插座one " + onePduinfo.getMachineid() + " 开启后的电流in==" + oneEndPduCurrent);
     * <p>
     * <p>
     * //获取插座的out值
     * //                            String oneEndPduCurrentOut = "0";
     * ////                            Thread.sleep(500);
     * //                            oneEndPduCurrentOut = pduPlugCurrent(onePduinfo, onePduinfoCon, "out");
     * //                            System.out.println("插座one "+ onePduinfo.getMachineid() +" 开启后的电流out==" + oneEndPduCurrentOut);
     * <p>
     * //再次获取two的电流in
     * String twoEndPduCurrent = "0";
     * //                            Thread.sleep(500);
     * twoEndPduCurrent = pduPlugCurrent(twoPduinfo, twoPduinfoCon, "in");
     * System.out.println("插座two " + twoPduinfo.getMachineid() + " 开启后的电流==" + twoEndPduCurrent);
     * <p>
     * //再次获取two的电流out
     * //                            String twoEndPduCurrentOut = "0";
     * ////                            Thread.sleep(500);
     * //                            twoEndPduCurrentOut = pduPlugCurrent(twoPduinfo, twoPduinfoCon, "out");
     * //                            System.out.println("插座two "+ twoPduinfo.getMachineid() +" 开启后的电流out==" + twoEndPduCurrentOut);
     * <p>
     * //关闭one、two
     * Thread.sleep(100);
     * //                            oneThreadPlug = new Thread(new PduActionThread(onePduinfoCon, onePduinfo, "0"));
     * oneThreadPlug = new Thread(new PduActionThreadPDC(onePduinfoCon, onePduinfo, "0"));
     * oneThreadPlug.start();
     * oneThreadPlug.join();
     * //                        onePduinfo.setActionState("0");
     * //                        pduService.update(onePduinfo);
     * System.out.println("关闭插座one设备" + onePduinfo.getMachineid());
     * <p>
     * Thread.sleep(100);
     * //                            twoThreadPlug = new Thread(new PduActionThread(twoPduinfoCon, twoPduinfo, "0"));
     * twoThreadPlug = new Thread(new PduActionThreadPDC(twoPduinfoCon, twoPduinfo, "0"));
     * twoThreadPlug.start();
     * twoThreadPlug.join();
     * //                        twoPduinfo.setActionState("1");
     * //                        pduService.update(twoPduinfo);
     * System.out.println("关闭插座two设备" + twoPduinfo.getMachineid());
     * <p>
     * System.out.println("结束时间==" + Long.valueOf(System.currentTimeMillis() - starttime));
     * <p>
     * //比较值进行匹配 公式匹配 IN1 = OUT1+IN2  记录插座之间的关系
     * <p>
     * float oneStartPduCurrentFloat = Float.parseFloat(oneStartPduCurrent);
     * //                            float oneStartPduCurrentOutFloat = Float.parseFloat(oneStartPduCurrentOut);
     * float oneEndPduCurrentFloat = Float.parseFloat(oneEndPduCurrent);
     * //                            float oneEndPduCurrentOutFloat = Float.parseFloat(oneEndPduCurrentOut);
     * System.out.println(onePduinfo.getMachineid() + "初始 in===" + oneStartPduCurrentFloat);
     * //                            System.out.println(onePduinfo.getMachineid() + "初始 out===" + oneStartPduCurrentOutFloat);
     * System.out.println(onePduinfo.getMachineid() + "加载后 in===" + oneEndPduCurrentFloat);
     * //                            System.out.println(onePduinfo.getMachineid() + "加载后 out===" + oneEndPduCurrentOutFloat);
     * <p>
     * <p>
     * float twoStartPduCurrentFloat = Float.parseFloat(twoStartPduCurrent);
     * //                            float twoStartPduCurrentOutFloat = Float.parseFloat(twoStartPduCurrentOut);
     * float twoEndPduCurrentFloat = Float.parseFloat(twoEndPduCurrent);
     * //                            float twoEndPduCurrentOutFloat = Float.parseFloat(twoEndPduCurrentOut);
     * System.out.println(twoPduinfo.getMachineid() + "初始 in===" + twoStartPduCurrentFloat);
     * //                            System.out.println(twoPduinfo.getMachineid() + "初始 out===" + twoStartPduCurrentOutFloat);
     * System.out.println(twoPduinfo.getMachineid() + "加载后 in===" + twoEndPduCurrentFloat);
     * //                            System.out.println(twoPduinfo.getMachineid() + "加载后 out===" + twoEndPduCurrentOutFloat);
     * <p>
     * <p>
     * //                            float differenceTwoPduCurrent = twoEndPduCurrentFloat - twoStartPduCurrentFloat;
     * //                            System.out.println("插座two开启前后的差值===" + differenceTwoPduCurrent);
     * <p>
     * if (Math.abs(oneEndPduCurrentFloat - twoEndPduCurrentFloat) < 0.1) {
     * System.out.println(onePduinfo.getMachineid() + "与" + twoPduinfo.getMachineid() + "是同级关系");
     * System.out.println("**************************************************************");
     * <p>
     * } else {
     * <p>
     * float s = 0;
     * if (oneEndPduCurrentFloat > twoEndPduCurrentFloat) {
     * s = oneEndPduCurrentFloat - twoEndPduCurrentFloat;
     * float ds = s * 2;
     * <p>
     * if (Math.abs(ds - oneEndPduCurrentFloat) < 0.1) {
     * System.out.println(onePduinfo.getMachineid() + "是" + twoPduinfo.getMachineid() + "的上级");
     * }
     * <p>
     * } else {
     * <p>
     * s = twoEndPduCurrentFloat - oneEndPduCurrentFloat;
     * float ds = s * 2;
     * if (Math.abs(ds - twoEndPduCurrentFloat) < 0.1) {
     * System.out.println(twoPduinfo.getMachineid() + "是" + onePduinfo.getMachineid() + "的上级");
     * }else {
     * <p>
     * System.out.println("数据误差太大==========================================");
     * }
     * }
     * }
     * <p>
     * <p>
     * if (differenceTwoPduCurrent > 0) {
     * float inOne = oneEndPduCurrentOutFloat + twoEndPduCurrentFloat;
     * System.out.println("公式out1 + in2===" + inOne);
     * float difference = Math.abs(inOne - oneEndPduCurrentFloat);
     * System.out.println("公式误差==" + difference);
     * if (difference < 0.1) {
     * //如果差值与插座差值相同，则保存空开与插座的关系
     * PduRelation pduRelationEnd = new PduRelation();
     * pduRelation.setPduID(twoPduinfo.getId());
     * pduRelation.setParent_id(String.valueOf(onePduinfo.getId()));
     * System.out.println("记录插座与插座关系");
     * <p>
     * PduRelation pduRelationGet = new PduRelation();
     * pduRelationGet = pduRelationService.selectByPrimaryKey(twoPduinfo.getId());
     * <p>
     * if (pduRelationGet == null) {
     * <p>
     * pduRelationService.insert(pduRelation);
     * <p>
     * } else {
     * pduRelationService.updateByPrimaryKeySelective(pduRelation);
     * }
     * System.out.println(onePduinfo.getMachineid() + "与" + twoPduinfo.getMachineid() + "判定存在级联关系");
     * System.out.println("匹配合适的插座$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$" + pduRelation.getPduID());
     * break;
     * } else {
     * <p>
     * System.out.println(onePduinfo.getMachineid() + "与" + twoPduinfo.getMachineid() + "判定没有级联关系");
     * System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + +pduRelation.getPduID());
     * }
     * <p>
     * }
     * }
     * }
     * }
     * <p>
     * }
     * } catch (InterruptedException e) {
     * e.printStackTrace();
     * }
     * <p>
     * }
     ****/
    @Override
    public void run() {


        try {
            for (int i = 0; i < pduListBreaker.size(); i++) {
                Pdu pduBreakerTwo = new Pdu();
                pduBreakerTwo = pduListBreaker.get(i);//空开信息

                //获取空开关联的插座
                List<PduRelation> pduRelationsList = new ArrayList<PduRelation>();
                pduRelationsList = pduRelationService.selectByPlugs(pduBreakerTwo.getId());

                Map<Integer, String> oneMap = new HashMap<Integer, String>();//保存集合

                for (int x = 0; x < pduRelationsList.size(); x++) {
                    PduRelation pduRelation = new PduRelation();
                    pduRelation = pduRelationsList.get(x);


                    //获取所有的插座的当前电流  包括ONE
                    Map<Integer, Float> startMap = new HashMap<Integer, Float>();
                    for (int satrt = 0; satrt < pduRelationsList.size(); satrt++) {
                        PduRelation pduRelationTwo = new PduRelation();
                        pduRelationTwo = pduRelationsList.get(satrt);

                        int twoId = pduRelationTwo.getPduID();
                        Pdu twoPduinfo = pduService.selectByPrimaryKey(twoId);

                        String twoConName = twoPduinfo.getIp();
                        Socket twoPduinfoCon = null;
                        //获取连接对象
                        twoPduinfoCon = (Socket) BaseController.SubPolmap.get(twoConName);

                        //取得插座2当前电流
                        float twoStartPduCurrent = (float) 0.0;
                        twoStartPduCurrent = Float.parseFloat(pduPlugCurrent(twoPduinfo, twoPduinfoCon, "in"));
                        System.out.println("开启前" + twoPduinfo.getMachineid() + "插座电流==" + twoStartPduCurrent);

                        //保存到startMap中
                        startMap.put(twoPduinfo.getId(), twoStartPduCurrent);

                    }

                    //开启当前的插座
                    int oneId = pduRelation.getPduID();
                    Pdu onePduinfo = pduService.selectByPrimaryKey(oneId);
                    System.out.println("one的ID===" + onePduinfo.getMachineid());
                    String oneConName = onePduinfo.getIp();
                    Socket onePduinfoCon = null;
                    //获取连接对象
                    onePduinfoCon = (Socket) BaseController.SubPolmap.get(oneConName);

                    //开启插座1
                    Thread.sleep(100);
                    Thread startThreadPlug = new Thread(new PduActionThreadPDC(onePduinfoCon, onePduinfo, "1"));
                    startThreadPlug.start();
                    startThreadPlug.join();
                    System.out.println("开启插座。。。。。。。。。。。。" + onePduinfo.getMachineid());

                    Thread.sleep(10000);

                    //读取开启后的所有插座电流
                    Map<Integer, Float> endMap = new HashMap<Integer, Float>();
                    for (int end = 0; end < pduRelationsList.size(); end++) {
                        PduRelation pduRelationThree = new PduRelation();
                        pduRelationThree = pduRelationsList.get(end);

                        int threeId = pduRelationThree.getPduID();
                        Pdu threePduinfo = pduService.selectByPrimaryKey(threeId);

                        String threeConName = threePduinfo.getIp();
                        Socket threePduinfoCon = null;
                        //获取连接对象
                        threePduinfoCon = (Socket) BaseController.SubPolmap.get(threeConName);

                        //取得插座2当前电流
                        float threeStartPduCurrent = (float) 0.0;
                        threeStartPduCurrent = Float.parseFloat(pduPlugCurrent(threePduinfo, threePduinfoCon, "in"));
                        System.out.println("开启后 " + threePduinfo.getMachineid()+ " 插座电流==" + threeStartPduCurrent);

                        endMap.put(threePduinfo.getId(), threeStartPduCurrent);
                    }

                    //关闭设备
                    Thread.sleep(100);
                    Thread endThreadPlug = new Thread(new PduActionThreadPDC(onePduinfoCon, onePduinfo, "0"));
                    endThreadPlug.start();
                    endThreadPlug.join();
                    System.out.println("关闭插座。。。。。。。。。。。。" + onePduinfo.getMachineid());


                    //比较前后的电流数据 计算差值
                    Map<Integer, Float> differenceMap = new HashMap<Integer, Float>();
                    //遍历endmmap
                    for (Map.Entry<Integer, Float> endentry : endMap.entrySet()) {
                        System.out.println("Key = " + endentry.getKey() + ", Value = " + endentry.getValue());
                        int endkey = endentry.getKey();
                        float endvalue = endentry.getValue();

                        for (Map.Entry<Integer, Float> startentry : startMap.entrySet()) {
                            int startkey = startentry.getKey();
                            float startvalue = startentry.getValue();

                            if (endkey == startkey) {

                                float differenceValue = endvalue - startvalue;
                                differenceMap.put(endkey, differenceValue);
                                break;
                            }

                        }
                    }
                    //当前插座的差值
                    float onePlugValue = differenceMap.get(onePduinfo.getId());

                    String pduidscollect = "";
                    for (Map.Entry<Integer, Float> oneentry : differenceMap.entrySet()) {

                        int onekey = oneentry.getKey();
                        float onevalue = oneentry.getValue();

                        if(onePduinfo.getId() != onekey){
                            if (Math.abs(onePlugValue - onevalue) < 0.1 && onevalue != 0) {
                                System.out.println("插座" + onekey + "是插座" + onePduinfo.getId() + "的上级");
                                pduidscollect = String.valueOf(onekey) + ",";
                            }
                            oneMap.put(onePduinfo.getId(), pduidscollect);
                        }
                    }

                    Thread.sleep(10000);
                }

                //梳理关系明细

                for (int r = 0; r < pduRelationsList.size(); r++) {
                    //遍历所有的插座的集合
                    PduRelation pduRelationFour = new PduRelation();
                    pduRelationFour = pduRelationsList.get(r);

                    int pduid = pduRelationFour.getPduID();

                    String valueEnd = "";
                    for (Map.Entry<Integer, String> entry : oneMap.entrySet()) {
                        int key = entry.getKey();
                        String value = entry.getValue();

                        if (pduid == key) {
                            String[] str = value.split(",");
                            String[] str2 = str;

                            for (int s = 0; s < str.length; s++) {
                                String id = str[s];
                                if (!id.equals("")) {

                                    for (Map.Entry<Integer, String> entryTwo : oneMap.entrySet()) {
                                        int keytwo = entryTwo.getKey();
                                        String valuetwo = entryTwo.getValue();
                                        String[] strtwo = valuetwo.split(",");
                                        if (Integer.valueOf(id) == keytwo) {
                                            // 数组比较
                                            String v = "";
                                            List<String> list = compare(strtwo, str2);
                                            for (String strArray : list) {
                                                v += strArray + ",";
                                            }
                                            value = v;
                                            str2 = v.split(",");
                                            break;
                                        }
                                    }

                                }
                            }
                            valueEnd = value.replaceAll(",", "");
                            System.out.println("插座" + key + " 的上级是" + valueEnd);
                        }

                        //修改数据库
                        PduRelation pduRelationGet = new PduRelation();
                        pduRelationGet = pduRelationService.selectByPrimaryKey(pduid);

                        PduRelation pduRelationEnd = new PduRelation();
                        pduRelationEnd.setPduID(pduid);
                        pduRelationEnd.setSwitchPduID(pduBreakerTwo.getId());
                        pduRelationEnd.setParent_id(valueEnd);

                        if (pduRelationGet == null) {
                            pduRelationService.insert(pduRelationEnd);
                        } else {
                            pduRelationService.updateByPrimaryKeySelective(pduRelationEnd);
                        }

                    }
                }
            }
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }

    public static <T> List<T> compare(T[] t1, T[] t2) {
        List<T> list1 = Arrays.asList(t1); //将t1数组转成list数组
        List<T> list2 = new ArrayList<T>();//用来存放2个数组中不相同的元素
        for (T t : t2) {
            if (!list1.contains(t)) {
                list2.add(t);
            }
        }
        return list2;
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
