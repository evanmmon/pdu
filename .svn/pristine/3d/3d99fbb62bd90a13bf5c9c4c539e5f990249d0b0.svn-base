package com.chuangkou.pdu.util;

import com.chuangkou.pdu.entity.Message;
import com.chuangkou.pdu.entity.Pdu;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

/**
 * @Author:
 * @Description:
 * @Date:Created in 15:27 2018/3/22
 */
public class RelayThread extends Thread {

    Socket socket = null;//和本线程相关的Socket
    Pdu relayPdu = null;
    String relay = "";

    public RelayThread(Socket socket,Pdu relayPdu,String relay) {
        this.socket = socket;
        this.relayPdu = relayPdu;
        this.relay = relay;
    }

    @Override
    public void run() {

        Message sendmsg = new Message();
        try {
            //组合继电器控制报文
            sendmsg.setControlType("2");
            sendmsg.setCommand("3");
            String messageID = StringUtil.randomStr(); //随机生成4位字符和数字的组合
            sendmsg.setMessageID(messageID);

            String pduType = relayPdu.getMachineid().substring(1,3);
            String machineID = relayPdu.getMachineid().substring(3,8);
            String randomID = relayPdu.getMachineid().substring(3,8);

            sendmsg.setReceivePduType(pduType);
            sendmsg.setReceiveMachineID(pduType);
            sendmsg.setReceiveRandomID(randomID);
            sendmsg.setIp(relayPdu.getIp());

            sendmsg.setRelayState(relay);

            String mes = StringUtil.sendMessage(sendmsg);
            writeMsgToClient(socket.getOutputStream(), mes);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void writeMsgToClient(OutputStream outputStream, String string) throws IOException {
        Writer writer = new OutputStreamWriter(outputStream);
        writer.append(string);
        writer.flush();
//        writer.close();
    }

}
