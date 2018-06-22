package com.chuangkou.pdu.controller;

import com.chuangkou.pdu.thread.ThreadServerSocket;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author:
 * @Description:
 * @Date:Created in 14:31 2018/6/11
 */
@Controller
public class PduReadMessageController extends BaseController {


    private static final int PORT = 8899;
    //private static final int BUFFER_SIZE = 1024;


    @RequestMapping("/jsonPdutest")
    public static void jsonPdutest() {
        // TODO Auto-generated method stub
        int counter = 1;
        try {
            ServerSocket ss = new ServerSocket(PORT);
            while (true) {
                Socket connection = ss.accept();
                System.out.println("第 " + (counter++) + " 个连接");

                String conName = connection.getInetAddress().toString();
                conName = conName.substring(1, conName.length());
                BaseController.SubPolmap.put(conName, connection);
                executorService.submit(new ThreadServerSocket(connection));
//                Thread t = new Thread(new ThreadServerSocket(connection));
//                t.start();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
