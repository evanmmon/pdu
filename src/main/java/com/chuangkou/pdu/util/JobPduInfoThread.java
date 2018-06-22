package com.chuangkou.pdu.util;

import com.chuangkou.pdu.entity.Message;
import com.chuangkou.pdu.entity.Pdu;
import com.chuangkou.pdu.entity.PduInfo;
import com.chuangkou.pdu.service.PduInfoService;
import com.chuangkou.pdu.service.PduService;

import javax.annotation.Resource;
import java.io.*;
import java.net.Socket;

/**
 * @Author:
 * @Description:设备信息采集线程
 * @Date:Created in 16:27 2018/3/27
 */
public class JobPduInfoThread extends Thread {


    Socket socket = null;//和本线程相关的Socket
    Pdu jobpdu = null;

    @Resource(name = "pduInfoService")
    private PduInfoService pduInfoService;
    @Resource(name = "pduService")
    private PduService pduService;

    public JobPduInfoThread(Socket socket, Pdu jobpdu) {
        this.socket = socket;
        this.jobpdu = jobpdu;
    }

    @Override
    public void run() {

        String sendmsg = "AAB2A2";
        String recivemsg = "";
        try {
            //发送采集设备信息请求
            writeMsgToClient(socket.getOutputStream(), sendmsg);


            //接收设备发来设备信息
            recivemsg = readMessageFromClient(socket.getInputStream());
            Message message = new Message();
            PduInfo pduInfo = new PduInfo();
            if(!recivemsg.equals("")){
                //解析设备实时数据报文
                message =  StringUtil.receiveMessage2(recivemsg);
                jobpdu =  pduService.selectByMachineID(message.getSendMachineID());

                pduInfo.setPduid(jobpdu.getId());
                String time = message.getYear() + message.getMonth() + message.getDay() + message.getHour() +
                        message.getMinute() + message.getSecond();
                pduInfo.setCollectiontime(time);
                pduInfo.setVoltage(message.getVoltage());
                pduInfo.setWatt(message.getWatt());
                pduInfo.setCurrent(message.getCurrent());
                pduInfo.setOvervoltage(message.getOvervoltage());
                pduInfo.setUndervoltage(message.getUndervoltage());
                pduInfo.setOvercurrent(message.getOvercurrent());
                pduInfo.setCircuitbreaker(message.getCircuitbreaker());
                pduInfo.setElectricleakage(message.getElectricleakage());
                pduInfo.setTemperature(message.getTemperature());
                pduInfo.setSmoke(message.getSmoke());
                pduInfo.setWaterLevel(message.getWaterLevel());

                pduInfoService.insert(pduInfo);//添加采集信息
                sendmsg = "AAB2A0";
                writeMsgToClient(socket.getOutputStream(),sendmsg);  //返回收到设备信息并添加成功
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //发送报文
    private static void writeMsgToClient(OutputStream outputStream, String string) throws IOException {
        Writer writer = new OutputStreamWriter(outputStream);
        writer.append(string);
        writer.flush();
//        writer.close();
    }

    private static String readMessageFromClient(InputStream inputStream) throws IOException {
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(reader);
        String a = null;
        String message = "";

        //循环接收报文
        while ((a = br.readLine()) != null) {
            message += a;
            return message;
        }
        return message;
    }
}
