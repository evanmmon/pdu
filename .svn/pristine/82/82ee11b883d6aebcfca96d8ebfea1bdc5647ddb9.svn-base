package com.chuangkou.pdu.util;

import com.chuangkou.pdu.entity.Message;
import com.chuangkou.pdu.entity.Pdu;

import java.io.*;
import java.net.Socket;

/**
 * @Author:
 * @Description:继电器控制线程
 * @Date:Created in 11:07 2018/3/28
 */
public class PduActionThread extends Thread {

    Socket socket = null;//和本线程相关的Socket
    Pdu jobpdu = null;
    String actype = "";

    public PduActionThread(Socket socket, Pdu jobpdu, String actype) {
        this.socket = socket;
        this.jobpdu = jobpdu;
        this.actype = actype;
    }


    @Override
    public void run() {

        //根据参数组合报文
//        Message message = new Message();
        String receivemsg = "";
        try {
//            while (!receivemsg.equals("AAB1A3")) {
//                message.setControlType("2");//下行
//                message.setCommand("3");//控制类型报文
//
//                message.setIp(jobpdu.getIp());
//                String pdumachine = jobpdu.getMachineid();
//
//                message.setReceivePduType(pdumachine.substring(0, 3));
//                message.setReceiveMachineID(pdumachine.substring(3, 8));
//                message.setReceiveRandomID(pdumachine.substring(8, 11));
//
//                message.setRelayState(actype);
//
//                //组合报文
//                String msg = StringUtil.sendMessage2(message);
                 String msg ="AAB2A3";
                //发送报文
                writeMsgToClient(socket.getOutputStream(), msg);

                //接收回复是否成功
                receivemsg = readMessageFromClient(socket.getInputStream());

                if (receivemsg.equals("AAB1A3")) {
                    System.out.println("修改继电器状态成功！");
                }
//            }
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
