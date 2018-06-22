package com.chuangkou.pdu.util;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import static java.lang.Thread.sleep;

//import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
/**
 * @Author:
 * @Description:
 * @Date:Created in 10:06 2018/3/2
 */
//@Controller
//@RequestMapping("/socket")
public class ServerSocketPoolTest implements ServletContextListener {

//    public static void main(String[] args) {
//        testCommon();
//    }

    /**
     * 1.测试普通的server
     * @author zzj
     */
//    public static void testCommon(){
//        ServerSocket serverSocket=null;
//        //定义一个容量为50的线程
//        ExecutorService service = Executors.newFixedThreadPool(50);
//        try {
//            serverSocket = new ServerSocket(8888);
//            while(true){
//                System.out.println("wait receive message from client...");
//                //接收客户端连接的socket对象
//                Socket connection =null;
//                //接收客户端传过来的数据，会阻塞
//                connection=serverSocket.accept();
//                System.out.println("ip=="+connection.getInetAddress()+":"+connection.getPort());
//                service.submit(new SubPolThread(connection));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally{
//            if (serverSocket!=null) {
//                try {
//                    serverSocket.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServerSocket serverSocket=null;
        //定义一个容量为50的线程
//        ExecutorService service = Executors.newFixedThreadPool(50);
//        try {
//            serverSocket = new ServerSocket(8888);
////            while(true){
//                System.out.println("wait receive message from client...");
//                //接收客户端连接的socket对象
//                Socket connection =null;
//                //接收客户端传过来的数据，会阻塞
//                connection=serverSocket.accept();
//                System.out.println("ip=="+connection.getInetAddress()+":"+connection.getPort());
//
//                service.submit(new SubPolThread(connection));
////            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally{
//            if (serverSocket!=null) {
//                try {
//                    serverSocket.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}