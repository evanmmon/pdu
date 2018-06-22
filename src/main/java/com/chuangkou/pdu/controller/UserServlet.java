//package com.chuangkou.pdu.controller;
//
//import com.chuangkou.pdu.bean.MsgBean;
//import com.chuangkou.pdu.entity.User;
//import com.chuangkou.pdu.service.UserService;
//import com.chuangkou.pdu.util.MD5;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
///**
// *登陆接口
// * 刘哲
// *
// */
//@Controller
//public class UserServlet
//    @Autowired
//    private UserService userService;
//
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//        doPost(req, resp);
//    }
//
//
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//
//        req.setCharacterEncoding("UTF-8");
//        resp.setContentType("application/json;charset=UTF-8");
//        System.out.println("被访问了！");
//        String username;
//        String password;
//        //读取“姓名”
//        username =req.getParameter("account");
//        //读取“密码”
//        password =req.getParameter("password");
//
//        User userByUsername = userService.findUserByUsername(username);  //selectOneByUserName(username);
//        if (password.equals(userByUsername.getPassword())==false){
//            JSONObject json = new JSONObject();
//            json.put("data","");
//            json.put("code", 1);
//            json.put("msg", "密码错误");
//            PrintWriter out=resp.getWriter();
//            out.print(json);
//            out.flush();
//            out.close();
//        } else {
//        String token = MD5.md5(username + password);
//        PrintWriter ut = resp.getWriter();
//
//        PrintWriter out=resp.getWriter();
//        JSONObject json = new JSONObject();
//        JSONObject data = new JSONObject();
//        data.put("access_token", token);
//        data.put("nick_name", userByUsername.getUsername());
//        data.put("phone_number", userByUsername.getPhone());
//        data.put("avatar", "http://img2.woyaogexing.com/2018/01/25/4bc73467cb1b70b5!400x400_big.jpg");
//        data.put("permission_level", userByUsername.getMpermissions().getId());
//        json.put("data",data);
//        json.put("code", 0);
//        json.put("msg", "登陆成功");
//        out.print(json);
//        out.flush();
//        out.close();
//
//
//
//
//
////        List<User> users = userService.selectALL();
////        for(int i =0;i<users.size();i++){
////            String phone = users.get(i).getPhone();
////            String password1 = users.get(i).getPassword();
////            UsernamePasswordToken token1 = new UsernamePasswordToken(phone, password1);
////            boolean b = token.equals(token1);
////            if (b=true){
////                PrintWriter out=resp.getWriter();
////                JSONObject json = new JSONObject();
////                JSONObject data = new JSONObject();
////                data.put("access_token", token);
////                data.put("nick_name", "Nickname");
////                data.put("phone_number", "18612345678");
////                data.put("avatar", "http://img2.woyaogexing.com/2018/01/25/4bc73467cb1b70b5!400x400_big.jpg");
////                data.put("permission_level", 1);
////                json.put("data",data);
////                json.put("code", 0);
////                json.put("msg", "登陆成功");
////                out.print(json);
////                out.flush();
////                out.close();
////
////            }else {
////                PrintWriter out=resp.getWriter();
////                JSONObject json = new JSONObject();
////                json.put("data","");
////                json.put("code", 1);
////                json.put("msg", "帐号或密码错误");
////                out.print(json);
////                out.flush();
////                out.close();
////            }
//
////
////        }
//    }}
//}
//
