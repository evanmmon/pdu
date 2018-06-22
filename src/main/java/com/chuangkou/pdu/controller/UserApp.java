package com.chuangkou.pdu.controller;


import com.chuangkou.pdu.bean.MsgBean;
import com.chuangkou.pdu.entity.User;
import com.chuangkou.pdu.entity.Token;
import com.chuangkou.pdu.service.AppToken;
import com.chuangkou.pdu.service.PduGroupRelationService;
import com.chuangkou.pdu.service.PduWarningService;
import com.chuangkou.pdu.service.UserService;
import com.chuangkou.pdu.util.LogUtil;
import com.chuangkou.pdu.util.MD5Utils;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;


@Controller
public class UserApp{
    @Autowired
    private PduWarningService pduWarningService;
    @Autowired
    private PduGroupRelationService pduGroupRelationService;
    @Autowired
    private UserService userService;
    @Autowired
    private AppToken appToken;

    /**
     * 登陆接口
     * 刘哲
     */
    @RequestMapping("/user/login")
    public void login(Model model, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");

//        System.out.println("被访问了！");
        String username;
        String password;
        //读取“姓名”
        username = req.getParameter("account");
        //读取“密码”
        password = req.getParameter("password");
        try {

            User userByUsername = userService.selectOneByUserName(username);
            if (null == userByUsername) {
                String s = MsgBean.getFalseInstance("帐号不存在").toJsonString();
                PrintWriter out = resp.getWriter();
                out.print(s);
                out.flush();
                out.close();
            } else if (password.equals(userByUsername.getPassword()) == false) {
//                System.out.println(MD5Utils.encode("888888"));
                String s = MsgBean.getFalseInstance("密码错误").toJsonString();

                PrintWriter out = resp.getWriter();
                out.print(s);
                out.flush();
                out.close();
            } else {
//            User cuurentUser = userService.findUserByUsername(username);
//            req.getSession().setAttribute("user",cuurentUser);
                //帐号 密码 当前时间秒数
                String token = username + "," + password + "," + System.currentTimeMillis();
                User user1 = new User();
                user1.setUsername(username);
                user1.setToken(token);
                userService.update5(user1);
                //添加日志
                LogUtil.addLog(username, "登录系统");
                PrintWriter ut = resp.getWriter();
                PrintWriter out = resp.getWriter();
                JSONObject json = new JSONObject();
                JSONObject data = new JSONObject();
//                System.out.println(token);
                data.put("access_token", token);
                data.put("nick_name", userByUsername.getNickname());
                data.put("phone_number", userByUsername.getPhone());
                data.put("avatar", userByUsername.getAvatar());
                data.put("permission_level", userByUsername.getMpermissions().getId());
                data.put(" create_time", userByUsername.getCreate_time());
                json.put("data", data);
                json.put("code", 0);
                json.put("msg", "登陆成功");
                out.print(json);
                out.flush();
                out.close();

            }
        } catch (Exception e) {
            PrintWriter out = resp.getWriter();
            MsgBean msgBean = MsgBean.getFalseInstance("登录失败");
            out.print(msgBean.toJsonString());
            out.flush();
            out.close();

        }
    }

    /**
     * 获取用户信息
     * 刘哲
     * 18/3/20
     */
    @RequestMapping("/user/get_user_info")
    public void Select(Model model, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        String token = req.getHeader("access_token");
//        String token = req.getHeader("access_token");
//        System.out.println(token + "-6666");
        try {
        Token apptokenyanzheng = appToken.apptokenyanzheng(token);
        if (!apptokenyanzheng.getJieguo()) {
            PrintWriter out = resp.getWriter();
            MsgBean msgBean = MsgBean.getFalseInstance("token失效");
            msgBean.setCode(403);
            out.print(msgBean.toJsonString());
            out.flush();
            out.close();
        } else {
            String username = apptokenyanzheng.getUsername();
            User userByUsername = userService.findUserByUsername(username);
            PrintWriter out = resp.getWriter();
            JSONObject json = new JSONObject();
            JSONObject data = new JSONObject();

            data.put("create_time", userByUsername.getCreate_time());
            data.put("nick_name", userByUsername.getNickname());
            data.put("phone_number", userByUsername.getPhone());
            data.put("avatar", userByUsername.getAvatar());
            data.put("permission_level", userByUsername.getMpermissions().getId());
            json.put("data", data);
            json.put("code", 0);
            json.put("msg", "");
            out.print(json);
            out.flush();
            out.close();
//            System.out.println("-------------------------------");
//            System.out.println(json);
//            System.out.println("-------------------------------");

        }}catch (Exception e){
            PrintWriter out = resp.getWriter();
            MsgBean msgBean = MsgBean.getFalseInstance("获取用户信息失败");
            out.print(msgBean.toJsonString());
            out.flush();
            out.close();

        }

//        String[] tokens =token.split(",");
//       String userName= tokens[0].toString();
//       String md5Password= tokens[1].toString();
//        System.out.println(userName);
//        System.out.println(md5Password);

        //读取“token”

//        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//        PrintWriter ut = resp.getWriter();
//        System.out.println("UserNamme:"+username);
//        System.out.println("Password:"+password);


    }


    /**
     * 退出登录
     * 刘哲
     * 18/3/22
     */
    @RequestMapping("/user/logout")
    public void SelectPduGroup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
//        System.out.println(" ！");
        String warning_id;
        String token1 = req.getHeader("access_token");
        //验证并解析token

        try {

            Token token = appToken.apptokenyanzheng(token1);
            if (token.getJieguo()) {
                //添加日志
                User user1 = new User();
                user1.setUsername(token.getUsername());
                user1.setToken("" + "," + "" + "," + "");
                userService.update5(user1);
                LogUtil.addLog(token.getUsername(), "退出系统");
                PrintWriter out = resp.getWriter();
                MsgBean msgBean = MsgBean.getInstance();
                out.print(msgBean.toJsonString());
                out.flush();
                out.close();
            } else {
                PrintWriter out = resp.getWriter();
                MsgBean msgBean = MsgBean.getFalseInstance("token失效");
                msgBean.setCode(403);
                out.print(msgBean.toJsonString());
                out.flush();
                out.close();

            }
        } catch (Exception e) {
            PrintWriter out = resp.getWriter();
            MsgBean msgBean = MsgBean.getFalseInstance("退出登录失败");
            out.print(msgBean.toJsonString());
            out.flush();
            out.close();

        }

    }


    /**
     * 修改密码
     * 刘哲
     * 18/3/23
     */
    @RequestMapping("/user/change_password")
    public void SelectP(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        String password = req.getParameter("password");
        String new_password = req.getParameter("new_password");
        String token = req.getHeader("access_token");
//        String token = req.getHeader("access_token");
        try {
        Token apptokenyanzheng = appToken.apptokenyanzheng(token);
        if (!apptokenyanzheng.getJieguo()) {
            PrintWriter out = resp.getWriter();
            JSONObject json = new JSONObject();
            MsgBean msgBean = MsgBean.getFalseInstance("token失效");
            msgBean.setCode(403);
            out.print(msgBean.toJsonString());
            out.flush();
            out.close();
        } else if (apptokenyanzheng.getMd5Password().equals(password) == false) {
            PrintWriter out = resp.getWriter();
            JSONObject json = new JSONObject();
            MsgBean msgBean = MsgBean.getFalseInstance("密码错误");
            out.print(msgBean.toJsonString());
            out.flush();
            out.close();
        } else {
            String username = apptokenyanzheng.getUsername();
            User user1 = new User();
            user1.setUsername(username);
            user1.setPassword(new_password);
            userService.updatePassword(user1);
            //添加日志
            LogUtil.addLog(username, "修改密码");
            PrintWriter out = resp.getWriter();
            JSONObject json = new JSONObject();
            MsgBean msgBean = MsgBean.getInstance();
            out.print(msgBean.toJsonString());
            out.flush();
            out.close();
        }}catch (Exception e){
            PrintWriter out = resp.getWriter();
            MsgBean msgBean = MsgBean.getFalseInstance("修改密码失败");
            out.print(msgBean.toJsonString());
            out.flush();
            out.close();

        }
    }

    /**
     * 头像上传
     * 刘哲
     * 18/4/17
     */
    @RequestMapping("/user/upload_avatar")
    public void upload_avatar(HttpServletRequest req, HttpServletResponse resp, MultipartFile avatar) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        //resp.setContentType("application/json;charset=UTF-8");
//        ServletInputStream inputStream = req.getInputStream();
        String token = req.getHeader("access_token");
        try {
        Token apptokenyanzheng = appToken.apptokenyanzheng(token);
        if (!apptokenyanzheng.getJieguo()) {
            PrintWriter out = resp.getWriter();
            JSONObject json = new JSONObject();
            MsgBean msgBean = MsgBean.getFalseInstance("token失效");
            msgBean.setCode(403);
            out.print(msgBean.toJsonString());
            out.flush();
            out.close();
        } else {
            String Filename = avatar.getOriginalFilename();
            String newFilename = System.currentTimeMillis() + Filename;//防止名字相同
            String username = apptokenyanzheng.getUsername();
            User user1 = new User();
            user1.setUsername(username);
            String realPath = req.getSession().getServletContext().getRealPath("/");
            //图片存储的路径
            String pic_path = realPath+"img\\";
//            user1.setAvatar("http://192.168.0.10:8080/img/" + newFilename);
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            //数据库储存地址
            user1.setAvatar("http://"+hostAddress+":8080/img/" + newFilename);


            //新的图片
            File newfile = new File(pic_path + newFilename);
            avatar.transferTo(newfile);
            userService.update3(user1);
            //添加日志
            LogUtil.addLog(apptokenyanzheng.getUsername(), "上传头像");
            PrintWriter out = resp.getWriter();

            JSONObject json = new JSONObject();
            JSONObject data = new JSONObject();
            data.put("avatar", user1.getAvatar());
            json.put("data", data);
            json.put("code", 0);
            json.put("msg", "");
            out.print(json);
            out.flush();
            out.close();

        }}catch (Exception e){
            PrintWriter out = resp.getWriter();
            MsgBean msgBean = MsgBean.getFalseInstance("上传头像失败");
            out.print(msgBean.toJsonString());
            out.flush();
            out.close();

        }
    }

    /**
     * 修改用户名
     * 刘哲
     * 18/4/19
     */
    @RequestMapping("/user/modify_nickname")
    public void upload_nickname(HttpServletRequest req, HttpServletResponse resp, MultipartFile avatar) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        //resp.setContentType("application/json;charset=UTF-8");
//        ServletInputStream inputStream = req.getInputStream();
        String token = req.getHeader("access_token");
        String nickname = req.getParameter("nickname");
        try {
        Token apptokenyanzheng = appToken.apptokenyanzheng(token);
        if (!apptokenyanzheng.getJieguo()) {
            PrintWriter out = resp.getWriter();
            JSONObject json = new JSONObject();
            MsgBean msgBean = MsgBean.getFalseInstance("token失效");
            msgBean.setCode(403);
            out.print(msgBean.toJsonString());
            out.flush();
            out.close();
        } else {
            String username = apptokenyanzheng.getUsername();
            User user1 = new User();
            user1.setNickname(nickname);
            user1.setUsername(username);
            userService.update4(user1);
            PrintWriter out = resp.getWriter();
            MsgBean msgBean = MsgBean.getInstance();
            out.print(msgBean.toJsonString());
            out.flush();
            out.close();

        }}catch (Exception e){
            PrintWriter out = resp.getWriter();
            MsgBean msgBean = MsgBean.getFalseInstance("修改用户名失败");
            out.print(msgBean.toJsonString());
            out.flush();
            out.close();

        }

    }
}