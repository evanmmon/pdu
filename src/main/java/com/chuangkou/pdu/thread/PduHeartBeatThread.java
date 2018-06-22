package com.chuangkou.pdu.thread;

import com.chuangkou.pdu.controller.BaseController;
import com.chuangkou.pdu.controller.PduController;
import com.chuangkou.pdu.entity.Message;
import com.chuangkou.pdu.entity.MessageDLT;
import com.chuangkou.pdu.entity.Pdu;
import com.chuangkou.pdu.service.PduService;
import com.chuangkou.pdu.util.MessageStringDLTUtils;

import java.io.IOException;
import java.net.Socket;

/**
 * @Author:
 * @Description:
 * @Date:Created in 11:22 2018/5/9
 */
public class PduHeartBeatThread implements Runnable {

    Socket socket = null;//和本线程相关的Socket
    Pdu jobpdu = null;
    PduController result;
    private static PduService pduService = (PduService) SpringApplicationContextHolder.getSpringBean("pduService");


    public PduHeartBeatThread(Socket socket, Pdu jobpdu,PduController result) {
        this.socket = socket;
        this.jobpdu = jobpdu;
        this.result = result;
    }

    @Override
    public void run() {

        String msg = "";
        boolean ifcontinue = true;
        Message message = new Message();
        MessageDLT messageDLT = new MessageDLT();

        String addressip = socket.getLocalAddress().toString();
        addressip = addressip.substring(1, addressip.length());


        try {
            while (ifcontinue) {

                msg = ThreadUtils.readMessageFromClient(socket.getInputStream());
                System.out.println("收到报文：" + msg);
                if (!msg.equals("")) {
                    break;
                }

                messageDLT = MessageStringDLTUtils.onlineMessage(msg);//解析报文

                if (messageDLT.getDataTab().equals("FFFFFFFF")) {//心跳包类型
                    //修改心跳状态和设备IP地址
                    String updateAction = ThreadUtils.updatePduOnline(messageDLT, this.socket);
                    System.out.println("心跳记录成功......");
                    result.receiveFileStr(messageDLT.getMachineAddress());
                }else{

                    result.receiveFileStr("");
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
