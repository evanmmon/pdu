package com.chuangkou.pdu.thread;

import com.chuangkou.pdu.entity.Message;
import com.chuangkou.pdu.entity.Pdu;
import com.chuangkou.pdu.entity.PduWarningSet;
import com.chuangkou.pdu.util.StringUtil;

import java.io.*;
import java.net.Socket;

/**
 * @Author:
 * @Description:预计值设置线程
 * @Date:Created in 14:16 2018/3/28
 */
public class JobPduSetThread extends Thread {

    Socket socket = null;//和本线程相关的Socket
    PduWarningSet pduWarningSet = null;
    Pdu pduset = null;

    public JobPduSetThread(Socket socket, PduWarningSet pduWarningSet, Pdu pduset) {
        this.socket = socket;
        this.pduWarningSet = pduWarningSet;
        this.pduset = pduset;

    }

    @Override
    public void run() {

        //根据参数组合报文
        Message message = new Message();
        String receivemsg = "";

        try {
            while (!receivemsg.equals("AAB1A3")) {
                message.setControlType("2");//下行
                message.setCommand("3");//控制类型报文

                message.setIp(pduset.getIp());
                String pdumachine = pduset.getMachineid();

                message.setReceivePduType(pdumachine.substring(0, 3));
                message.setReceiveMachineID(pdumachine.substring(3, 8));
                message.setReceiveRandomID(pdumachine.substring(8, 11));

                message.setSetingVoltage(pduWarningSet.getVoltage());
                message.setCurrent(pduWarningSet.getCurrent());
                message.setWatt(pduWarningSet.getWatt());
                //设备电压、电流、功率幅度
                message.setVoltageAmplitude("00");
                message.setCurrentAmplitude("00");
                message.setWattAmplitude("00");

                //组合报文
                String msg = StringUtil.sendMessage3(message);

                //发送报文
                writeMsgToClient(socket.getOutputStream(), msg);

                //接收回复是否成功
                receivemsg = readMessageFromClient(socket.getInputStream());

                if (receivemsg.equals("AAB1A3")) {
                    System.out.println("预警值设置成功！");
                }
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
