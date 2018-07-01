package com.chuangkou.pdu.thread;

import com.chuangkou.pdu.controller.PduController;
import com.chuangkou.pdu.entity.MessageDLT;
import com.chuangkou.pdu.entity.Pdu;
import com.chuangkou.pdu.service.PduService;
import com.chuangkou.pdu.util.MessageStringDLTUtils;
import com.chuangkou.pdu.util.SocketUtils;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Callable;

/**
 * @Author:
 * @Description:
 * @Date:Created in 18:02 2018/5/8
 */
public class PduRelationShipThread implements Runnable {

    private static PduService pduService = (PduService) SpringApplicationContextHolder.getSpringBean("pduService");

//    private boolean result;

    Socket socket = null;
    Pdu pdu = null;
    PduController result;

    public PduRelationShipThread(Socket socket, Pdu pdu,PduController result) {
        this.socket = socket;
        this.pdu = pdu;
        this.result =  result;
    }

    @Override
    public void run() {
//        public Boolean  call() throws Exception {

        String end = "0D0A";

        try {
            //发送一个测试报文
            MessageDLT messageDLT = new MessageDLT();
            messageDLT.setMachineAddress(pdu.getMachineid());
            messageDLT.setDataStr("AAAAAAAA");//电压
            //拼接报文
            //            num += 1;
            //            System.out.println("num====" + num);
            String sendmesg = MessageStringDLTUtils.messageToHex(messageDLT, "read");
            SocketUtils.writeMsgToClient(socket.getOutputStream(), sendmesg + end);//发送报文
            String readmesg = SocketUtils.readMessageFromClient(socket.getInputStream());//收到回复
            messageDLT = MessageStringDLTUtils.onlineMessage(readmesg);

            if (messageDLT.getDataStr().equals("AA")) {
                System.out.println("测试设备成功！");
//                result = true;


            }else{
                result.receiveFileStr(messageDLT.getMachineAddress());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        return result;
    }

//    public boolean isResult() {
//        return result;
//    }
//
//    public void setResult(boolean result) {
//        this.result = result;
//    }
}
