package com.chuangkou.pdu.util;

//import com.chuangkou.pdu.entity.PduSearch;
import com.chuangkou.pdu.entity.PduTemporary;
import com.chuangkou.pdu.service.PduService;
import com.chuangkou.pdu.service.PduTemporaryService;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:xulei
 * @Description:socket线程
 * @Date:Created in 9:55 2018/2/7
 */
public class ServerThread extends Thread {

    Socket socket = null;//和本线程相关的Socket

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    private PduTemporaryService pduTemporaryService;

    public ServerThread() {

    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    List list = new ArrayList();

    @Override
    public void run() {
//        InputStream is = null;
//        InputStreamReader isr = null;
//        BufferedReader br = null;
//        OutputStream os = null;
//        PrintWriter pw = null;
//
//        try {
//            //与客户端建立通信，获取输入流，读取取客户端提供的信息
//            is = socket.getInputStream();
//            isr = new InputStreamReader(is,"GBK");
//            br = new BufferedReader(isr);
//            String data = null;
//
//            //获取输出流，响应客户端的请求
//            os = socket.getOutputStream();
//            pw = new PrintWriter(os);
//            while((data=br.readLine()) != null){//循环读取客户端的信息
//                System.out.println("我是服务器，客户端提交信息为："+data);
////                list.add(data);
////                addPdu(data);
//
//                pw.write("999999999\n");
////                pw.write("收到的数据是："+ data);
//                pw.flush();
//            }
//            socket.shutdownInput();//关闭输入流
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            //关闭资源即相关socket
//            try {
//                if(pw!=null)
//                    pw.close();
//                if(os!=null)
//                    os.close();
//                if(br!=null)
//                    br.close();
//                if(isr!=null)
//                    isr.close();
//                if(is!=null)
//                    is.close();
//                if(socket!=null)
//                    socket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
    }

    //获取客户端信息后，添加到数据库设备临时表
    public void addPdu(String data) throws Exception{
//        List<PduTemporary> searchList = new ArrayList();

        JSONObject json = JSONObject.fromObject(data);

        PduTemporary pduTemporary = new PduTemporary();

        try {
            pduTemporary.setIp(json.getString("ip"));
            pduTemporary.setQrcode(json.getString("qrcode"));
            pduTemporary.setMachineid(json.getString("machineid"));
            pduTemporary.setType(json.getString("type"));
            pduTemporary.setState("2");

//        searchList.add(pduTemporary);
            pduTemporaryService.insert(pduTemporary);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static byte[] readStream(InputStream inStream) throws Exception {
        int count = 0;
        while (count == 0) {
            count = inStream.available();
        }
        byte[] b = new byte[count];
        inStream.read(b);
        return b;
    }

}

