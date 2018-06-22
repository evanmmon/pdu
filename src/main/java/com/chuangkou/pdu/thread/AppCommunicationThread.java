package com.chuangkou.pdu.thread;

import com.chuangkou.pdu.controller.BaseController;
import com.chuangkou.pdu.util.DeviceEvent;
import com.chuangkou.pdu.util.MessageStringDLTUtils;
import com.chuangkou.pdu.util.StringUtil;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;

/**
 * @Author:
 * @Description:
 * @Date:Created in 17:41 2018/5/14
 */
public class AppCommunicationThread implements Runnable {

    Socket socket = null;

    public AppCommunicationThread(Socket conSocket) throws Exception {
        this.socket = conSocket;
//        this.call();
    }


    @Override
    public void run() {

        try {
            String msg = "";

            //获取连接APP 发送来的数据  用户名、token
            msg = ThreadUtils.readMessageFromClient(socket.getInputStream());

            try {
                InputStream is = socket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line = br.readLine();
                StringBuilder sb = null;
                while (line != null) {
                    if (line.equals(DeviceEvent.START_TAG)) {
//                        Log.i(TAG, "检测到消息头");
                        System.out.println("检测到消息头");
                        sb = new StringBuilder();
                    } else if (line.equals(DeviceEvent.END_TAG)) {
//                        Log.i(TAG, "检测到消息尾");
                        System.out.println("检测到消息尾");
                        if (sb != null) {
                            String jsonString = sb.toString();
                            DeviceEvent deviceEvent = DeviceEvent.parseToObject(jsonString);

                            //已连接的用户ID 作为连接对象的KEY
                            String device_name = deviceEvent.getDevice_name();
                            System.out.println("新用户上线====" + device_name);
                            BaseController.APPSubPolmap.put(device_name, socket);

                            //发送消息通知处理该消息
//                            EventBus.getDefault().post(deviceEvent);
                        }
                    } else {
//                        Log.i(TAG, "消息:" + line);
                        if (sb != null)
                            sb.append(line);
                    }
                    line = br.readLine();
                }


            } catch (IOException e) {
                socket.close();
                e.printStackTrace();
            } finally {
//                socket.close();
//                closeSocket(socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
