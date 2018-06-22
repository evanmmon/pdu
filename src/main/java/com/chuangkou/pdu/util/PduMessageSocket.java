package com.chuangkou.pdu.util;

import com.chuangkou.pdu.entity.Pdu;

import java.io.*;
import java.net.Socket;

/**
 * @Author:
 * @Description:
 * @Date:Created in 11:28 2018/3/16
 */
public class PduMessageSocket {
    private Socket connection;
    private String data;
    private String messageID;
    private Pdu pdu;

    public static String sendMessage(Socket conSocket, String data, String messageID, Pdu pdu) throws Exception {
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        OutputStream os = null;
        PrintWriter pw = null;

        String pduMessage = "";
        String pdureply = "";
        try {

            //创建一个输入流，用于接收消息
            is = conSocket.getInputStream();
            isr = new InputStreamReader(is, "GBK");
            br = new BufferedReader(isr);

            //创建一个输出流，发送消息给
            os = conSocket.getOutputStream();
            pw = new PrintWriter(os);
            pw.write(data);

            String shortData = null;
            while ((shortData = br.readLine()) != null) {//循环读取客户端的信息
                pduMessage += shortData;
            }

            //解析返回的数据，并返回结果
//            String actiontype = StringUtil.actionMessage(pduMessage,messageID,pdu);//先注释
            if(pduMessage.equals("")){
                return pdureply;
            }else {
                pdureply = StringUtil.actionMessage(messageID);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    return pdureply;
    }
}
