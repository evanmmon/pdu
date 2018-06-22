package com.chuangkou.pdu.controller;

import com.chuangkou.pdu.entity.*;
//import com.chuangkou.pdu.service.PduService;
import com.chuangkou.pdu.service.*;
import com.chuangkou.pdu.thread.*;
//import com.chuangkou.pdu.util.PropertiesUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author:
 * @Description:
 * @Date:Created in 15:25 2018/4/20
 */
@Component
@EnableScheduling
public class JobTask extends BaseController {
    @Resource(name = "pduService")
    private PduService pduService;

    @Resource(name = "pduClockService")
    private PduClockService pduClockService;

    @Resource(name = "pduOldLineService")
    private PduOldLineService pduOldLineService;

    @Resource(name = "pduRelationService")
    private PduRelationService pduRelationService;

    @Resource(name = "pduInfoService")
    private PduInfoService pduInfoService;

//    private static PduService pduService = (PduService) SpringApplicationContextHolder.getSpringBean("pduService");

    /**
     * @Author:xulei
     * @Description:定时获取设备运行信息数据 线程操作  针对45网络版
     * @Date: 2018-03-27
     */
//    @RequestMapping("/jobPduInfo")
//    @Scheduled(cron="0/30 * * * * ?") // 间隔1分钟执行一次
    public void jobPduInfo() {

        try {
            //获取所有设备的基本信息(ip machine)
            List<Pdu> pduList = pduService.selectAll();

            //循环发送设备信息上报请求，并收到回传消息，添加到数据库
            for (int i = 0; i < pduList.size(); i++) {
                Pdu jobpdu = new Pdu();
                jobpdu = pduList.get(i);
                String pduType = jobpdu.getType();

                if (pduType.equals("0000") && jobpdu.getMachineid().length() > 10) {
                    String conName = jobpdu.getIp();
                    Socket pduinfoCon = null;
                    //获取连接对象
                    pduinfoCon = (Socket) SubPolmap.get(conName);

                    if (pduinfoCon != null) {
                        System.out.println("创建一个新线程，发送报文......");
                        //创建一个线程，发送报文
                        Thread threadPduInfo = new Thread(new JobPduInfoThread(pduinfoCon, jobpdu));
                        //                    JobPduInfoThread.jobPduInfo(pduinfoCon, jobpdu);
                        threadPduInfo.start();
                        threadPduInfo.join();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //    @Scheduled(cron="0/10 * * * * ?")
    public void testtime() throws IOException {//定时任务测试
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("**************" + df.format(System.currentTimeMillis()));

//        String voltage = "2217";
//        float voltageF = Float.parseFloat(voltage);
//        DecimalFormat dfVoltage = new DecimalFormat("000.0");
//        dfVoltage.setRoundingMode(RoundingMode.HALF_UP);
//        voltage = dfVoltage.format(voltageF);

//          voltageF   =  (float)Math.round(voltageF)/10;
//        System.out.println("电压为==" + voltageF);
    }


    /**
     * @Author:xulei
     * @Description:定时任务每隔1小时进行一次对时 645协议报文  (暂时不用)
     * @Date: 2018-04-20
     */
//    @RequestMapping("/jobPduDateTimeSet")
//    @Scheduled(cron = "0 0 * * * ?") // 间隔1小时执行一次
    public void jobPduDateTimeSet() {

        //获取所有的TCP连接
        int mapNum = SubPolmap.size();

        try {
//            Map entry = new HashMap<String,Socket>();
            //循环所有连接
            Map map = new HashMap();
            Iterator entries = SubPolmap.entrySet().iterator();

            List<Pdu> pduList = pduService.selectAll();

            while (entries.hasNext()) {
                Map.Entry entry = (Map.Entry) entries.next();
                String key = (String) entry.getKey();
                Socket connection = (Socket) entry.getValue();//获取连接
                System.out.println("Key = " + key);

                //创建新的线程，发送报文到设备
//                Thread thread = new Thread(new JobPduDateTimeSetThread(connection, pduList));
//                thread.start();
//                thread.join();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @Author:xulei
     * @Description: 645协议 每隔5-10分钟采集设备信息
     * @Date:2018-0-4-22
     */
//    @Scheduled(cron = "0/30 * * * * ?") // 间隔5分钟执行一次
    public void jobPduInfoClockTask() {

        //获取所有的TCP连接
        int mapNum = SubPolmap.size();

        try {
            if (mapNum > 0) {
//            Map entry = new HashMap<String,Socket>();
                //循环所有连接Map map = new HashMap();
                Iterator entries = SubPolmap.entrySet().iterator();

                List<Pdu> pduList = pduService.selectAll();

                while (entries.hasNext()) {
                    Map.Entry entry = (Map.Entry) entries.next();
                    String key = (String) entry.getKey();
                    Socket connection = (Socket) entry.getValue();//获取连接
                    System.out.println("Key = " + key);

                    //创建新的线程，发送报文到设备
//                new JobPduInfoClockTaskThread(connection,pduList);
//                JobPduInfoClockTaskThread.jobPduInfoClock(connection,pduList);
//                    Thread PduInfoThread = new Thread(new JobPduInfoClockTaskThread(connection, pduList));
//                    PduInfoThread.start();
//                    PduInfoThread.join();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @Author:xulei
     * @Description: 设备定时任务执行 每分钟巡检一次
     * @Date: 2018-05-03
     */
//    @Scheduled(cron = "0/59 * * * * ?") // 间隔1分钟执行一次
    public void jobClockTask() {
        try {
            int mapNum = SubPolmap.size();

            if (mapNum > 0) {

                List<Pdu> pduList = new ArrayList<Pdu>();

                pduList = pduService.selectAll();

                for (int i = 0; i < pduList.size(); i++) {
                    Pdu pdu = pduList.get(i);
//                List<PduClock> clockList = new ArrayList<PduClock>();
                    PduClock pduClock = new PduClock();
                    pduClock.setPduid(pdu.getId());
                    //拆分时间
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dftime = df.format(System.currentTimeMillis());
                    String clockdate = dftime.substring(0, 10);
                    String clocktime = dftime.substring(11, 16);
//                    System.out.println("定时设备ID：==" + pduClock.getPduid());
//                    System.out.println("定时日期：==" + clockdate);
//                    System.out.println("定时时间：===" + clocktime);

                    pduClock.setClockdate(clockdate);
                    pduClock.setClocktime(clocktime);

                    PduClock pduClockOne = new PduClock();
                    pduClockOne = pduClockService.selectPduClockTask(pduClock);
//                System.out.println(pduClockOne.getId());
                    if (pduClockOne != null) {
                        String ip = pdu.getIp();
                        Socket relayCon = null;
                        //获取连接对象
                        relayCon = (Socket) SubPolmap.get(ip);
                        if (relayCon != null) {
                            //创建一个线程，控制开关
                            Thread threadClockTask = new Thread(new PduActionThread(relayCon, pdu, pduClockOne.getAction()));

                            threadClockTask.start();
                            threadClockTask.join();
                        }
                    }
                }
            } else {
                System.out.println("定时任务没有一个设备在线,无法执行！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //    @Scheduled(cron = "10 */1 * * * ?")
    public void jobPduInfoTask() {

        try {
            //获取所有的TCP连接
            int mapNum = SubPolmap.size();

            if (mapNum > 0) {
                //获取所有设备的基本信息(ip machine)
                List<Pdu> pduList = pduService.selectAll();

                //循环发送设备信息上报请求，并收到回传消息，添加到数据库
                for (int i = 0; i < pduList.size(); i++) {
                    Pdu jobpdu = new Pdu();
                    jobpdu = pduList.get(i);
                    String pduType = jobpdu.getType();
                    String conName = jobpdu.getIp();
                    Socket pduinfoCon = null;
                    //获取连接对象
                    pduinfoCon = (Socket) SubPolmap.get(conName);
                    if (pduinfoCon != null) {
                        if (pduType.equals("0000") && jobpdu.getMachineid().length() > 10) {

//                            System.out.println("jobPduInfoTask：创建一个新线程，发送报文采集设备信息......");
//                            //创建一个线程，发送报文
//                            Thread threadPduInfo = new Thread(new JobPduInfoThread(pduinfoCon, jobpdu));
//                            //                    JobPduInfoThread.jobPduInfo(pduinfoCon, jobpdu);
//                            executorService.submit(threadPduInfo);
//                            threadPduInfo.start();
//                            threadPduInfo.join();

                        } else {
                            Thread.sleep(1000);
                            //采集645设备状态信息
                            System.out.println("jobPduInfoTask：创建一个新线程，发送报文采集645设备信息......");
                            Thread PduInfoThread = new Thread(new JobPduInfoClockTaskThread(pduinfoCon, jobpdu));
                            PduInfoThread.start();
                            PduInfoThread.join();

                        }
                    } else {

                        System.out.println("设备IP:" + jobpdu.getIp() + "没有连线！");

                    }

                }
            } else {

                System.out.println("定时任务没有一个设备在线,无法执行！");

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * @Author:xulei
     * @Description:计算空开设备每一个小时的电阻，用于老化分析
     * @Date:
     */
//    @Scheduled(cron = "0 0 */1 * * ?")
    public void jobPduResistanceTask() {
        //第1步：搜索所有的空开设备
        List<Pdu> pduList = new ArrayList<Pdu>();
        Pdu pduSwitch = new Pdu();
        pduSwitch.setType("180");
        pduList = pduService.selectAllBySearch(pduSwitch);

        if (pduList != null) {
            //第2步：根据空开搜索 关联的插座
            for (int i = 0; i < pduList.size(); i++) {
                Pdu pduSwitchTwo = new Pdu();
                pduSwitchTwo = pduList.get(i);
                List<PduRelation> relationList = new ArrayList<PduRelation>();
                relationList = pduRelationService.selectByPlugs(pduSwitchTwo.getId());//关联的插座

                PduInfo pduInfoSwitch = new PduInfo();
                pduInfoSwitch = pduInfoService.selectByHoursAVG(pduSwitchTwo.getId());
                float switchVoltage = Float.valueOf(pduInfoSwitch.getVoltage());
                float switchCurrent = Float.valueOf(pduInfoSwitch.getCurrent());
                int switchWatt = Integer.valueOf(pduInfoSwitch.getWatt());
                //统计上一个小时的平均功率和电流
                for (int s = 0; s < relationList.size(); s++) {
                    //取得一个设备的ID
                    PduRelation pduRelation = new PduRelation();
                    pduRelation = relationList.get(s);

                    //搜索一个小时的设备平均值
                    PduInfo pduInfoPlug = new PduInfo();
                    pduInfoPlug = pduInfoService.selectByHoursAVG(pduRelation.getPduID());

                    //开始计算电阻
                    int plugWatt = Integer.valueOf(pduInfoPlug.getWatt());
                    switchWatt = switchWatt - plugWatt;
                }

                //计算空开电阻
                float squareSwitchCurrent = switchCurrent * switchCurrent;
                float switchResistance = (switchWatt / squareSwitchCurrent) / 100;
                System.out.println("空开电阻为：" + switchResistance);

                //第4步：保存电阻记录
                PduOldLine pduOldLine = new PduOldLine();
                pduOldLine.setPduID(pduSwitchTwo.getId());

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                pduOldLine.setCollectiontime(df.format(System.currentTimeMillis()));
                pduOldLine.setResistance(String.valueOf(switchResistance));

                pduOldLineService.insert(pduOldLine);//保存空开的电阻记录

            }
        }


    }


    /**
     * @Author:
     * @Description:测试电流时间
     * @Date:
     */
//    @Scheduled(cron = "10 0/1 * * * ?")
    public void jobPduInternetTask() {

        try {
            //获取所有的TCP连接
            int mapNum = SubPolmap.size();

            if (mapNum > 0) {
                //获取所有设备的基本信息(ip machine)
                List<Pdu> pduList = pduService.selectAll();

                //循环发送设备信息上报请求，并收到回传消息，添加到数据库
                System.out.println("总开始时间===" + System.currentTimeMillis());
                for (int i = 0; i < pduList.size(); i++) {
                    Thread.sleep(2000);
                    Pdu jobpdu = new Pdu();
                    jobpdu = pduList.get(i);
                    String pduType = jobpdu.getType();
                    String conName = jobpdu.getIp();
                    Socket pduinfoCon = null;
                    //获取连接对象
                    pduinfoCon = (Socket) SubPolmap.get(conName);
                    if (pduinfoCon != null) {
                        if (pduType.equals("180") && jobpdu.getMachineid().length() == 10) {

//                            System.out.println("jobPduInfoTask：创建一个新线程，发送报文采集设备信息......");
//                            //创建一个线程，采集电流
//                            Thread threadPduInfo = new Thread(new JobPduInfoThread(pduinfoCon, jobpdu));
//                            //                    JobPduInfoThread.jobPduInfo(pduinfoCon, jobpdu);
////                            executorService.submit(threadPduInfo);
//                            threadPduInfo.start();
//                            threadPduInfo.join();


                        } else {
//                            采集645设备状态信息
                            System.out.println("jobPduInfoTask：创建一个新线程，发送报文采集645设备信息......");
//                            Thread PduInfoThread = new Thread(new JobPduInfoThread(pduinfoCon, jobpdu));
//                            PduInfoThread.start();
//                            PduInfoThread.join();
                            Thread PduInfoThreadPDC = new Thread(new JobPduPlugThreadPDC(pduinfoCon, jobpdu));
                            PduInfoThreadPDC.start();
                            PduInfoThreadPDC.join();
                        }

                    } else {

                        System.out.println("设备IP:" + jobpdu.getIp() + "没有连线！");

                    }

                }
                System.out.println("总结束时间===" + System.currentTimeMillis());
            } else {

                System.out.println("定时任务没有一个设备在线,无法执行！");

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * @Author:xulei
     * @Description:一次性检查所有在线插座拓扑结构
     * @Date: 2018-06-06
     */
//    @Scheduled(cron = "20 0/1 * * * ?")
    public void jobPduInternet() {

        //获取所有的TCP连接
        int mapNum = SubPolmap.size();

        try {
            if (mapNum > 0) {

                //获取所有插座
                Pdu pduPlug = new Pdu();
                pduPlug.setType("0001");
                List<Pdu> pduList = pduService.selectAllBySearch(pduPlug);

                //遍历所有插座
                for (int i = 0; i < pduList.size(); i++) {
                    Pdu pduPlugTwo = new Pdu();
                    pduPlugTwo = pduList.get(i);
                    System.out.println("开始查找拓扑网络" + pduPlugTwo.getMachineid());

                    String pduType = pduPlugTwo.getType();
                    String conName = pduPlugTwo.getIp();
                    Socket pduinfoCon = null;
                    //获取连接对象
                    pduinfoCon = (Socket) SubPolmap.get(conName);

                    if (pduinfoCon != null) {
                        System.out.println("jobPduInfoTask：创建一个新线程，检查拓扑......");
                        //创建一个线程,检查拓扑关系
//                        Thread threadPduInfo = new Thread(new JobPduPlugThread(pduinfoCon, pduPlugTwo));
                        //                            executorService.submit(threadPduInfo);
                        Thread threadPduInfo = new Thread(new JobPduPlugThreadPDC(pduinfoCon, pduPlugTwo));
                        threadPduInfo.start();
                        threadPduInfo.join();
                    }

                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author:xulei
     * @Description: 获取所有的插座拓扑结构
     * @Date: 2018-06-12
     */
//    @Scheduled(cron = "20 0/1 * * * ?")
    public void jobPduInternetInfo() {

        try {
            if(BaseController.SubPolmap.size() > 0) {
                //获取空开与插座的关系
//            jobPduInternet();

                //获取所有插座
                Pdu pduPlug = new Pdu();
                pduPlug.setType("0001");
                List<Pdu> pduList = pduService.selectAllBySearch(pduPlug);

                //获取所有空开
                Pdu pduBreaker = new Pdu();
                pduBreaker.setType("180");
                List<Pdu> pduListTwo = pduService.selectAllBySearch(pduBreaker);


                Thread threadPduInfoInternet = new Thread(new JobPduInternetInfoThread(pduListTwo));
                threadPduInfoInternet.start();
                threadPduInfoInternet.join();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
