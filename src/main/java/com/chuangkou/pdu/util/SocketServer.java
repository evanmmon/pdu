package com.chuangkou.pdu.util;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * @Author:xulei
 * @Description:socekt服务操作
 * @Date:Created in 9:51 2018/2/7
 */
//@Controller
//@RequestMapping("/socket")
//public class SocketServer implements ServletContextListener{
    public class SocketServer {

    private ServerThread serverThread;

//    @RequestMapping("/socketAction")
//    public void socketSearchList() throws Exception {
        public static void main(String[] args) {
            try {
                //创建一个服务器端的Socket，即ServerSocket,绑定需要监听的端口
                ServerSocket serverSocket = new ServerSocket(8888);
                Socket socket = null;
                //记录连接过服务器的客户端数量
                int count = 0;
                System.out.println("***服务器即将启动，等待客户端的连接***");
                while (true) {//循环侦听新的客户端的连接
                    //调用accept（）方法侦听，等待客户端的连接以获取Socket实例
                    socket = serverSocket.accept();
                    //创建新线程
                    Thread thread = new Thread(new ServerThread(socket));
                    thread.setName(socket.getInetAddress().toString());//定义线程名字
                    thread.start();

                    count++;
                    System.out.println("服务器端被连接过的次数：" + count);
                    InetAddress address = socket.getInetAddress();
                    System.out.println("当前客户端的端口为：" + socket.getPort());
                    System.out.println("当前客户端的Name为：" + address.getHostName());
                    System.out.println("当前客户端的IP为：" + address.getHostAddress());

                }
                //serverSocket.close();一直循环监听，不用关闭连接
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    /**
     *@Author:xulei
     *@Description:启动socket
     *@Date:  2018-02-27
     */
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        try {
//            //创建一个服务器端的Socket，即ServerSocket,绑定需要监听的端口
//            ServerSocket serverSocket = new ServerSocket(8888);
//            Socket socket = null;
//            //记录连接过服务器的客户端数量
//            int count = 0;
//            System.out.println("***服务器即将启动，等待客户端的连接***");
//            while (true) {//循环侦听新的客户端的连接
//                //调用accept（）方法侦听，等待客户端的连接以获取Socket实例
//                socket = serverSocket.accept();
//                //创建新线程
//                Thread thread = new Thread(new ServerThread(socket));
//                thread.setName(socket.getInetAddress().toString());//定义线程名字
//                thread.start();
//                count++;
//                System.out.println("服务器端被连接过的次数：" + count);
//                InetAddress address = socket.getInetAddress();
//
//                System.out.println("当前客户端的IP为：" + address.getHostAddress());
//            }
//            //serverSocket.close();一直循环监听，不用关闭连接
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     *@Author:xulei
//     *@Description: 关闭socket
//     *@Date:2018-02-27
//     */
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        if(serverThread != null && serverThread.isInterrupted()){
//            serverThread.interrupt();
//        }
//    }
}
