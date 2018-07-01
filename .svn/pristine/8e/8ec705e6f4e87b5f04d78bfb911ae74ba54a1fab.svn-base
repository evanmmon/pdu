package com.chuangkou.pdu.util;


import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

/**
 * @Author:
 * @Description:
 * @Date:Created in 10:06 2018/3/2
 */

//@Component("APPServerSocketPool")
public class APPServerSocketPool implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ServerSocket serverSocket=null;
        //定义一个容量为50的线程
        ExecutorService service = Executors.newFixedThreadPool(50);
        try {
            serverSocket = new ServerSocket(8888);
//            while(true){
                System.out.println("wait receive message from client...");
                //接收客户端连接的socket对象
                Socket connection =null;
                //接收客户端传过来的数据，会阻塞
                connection=serverSocket.accept();
//                System.out.println("ip=="+connection.getInetAddress()+":"+connection.getPort());

//                service.submit(new SubPolThread(connection));
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
//            if (serverSocket!=null) {
//                try {
//                    serverSocket.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        }



    }
}

