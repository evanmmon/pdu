package com.chuangkou.pdu.controller;


import com.chuangkou.pdu.service.UserService;
import com.chuangkou.pdu.util.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
/**
 *登录
 * 刘哲
 *
 */
@Controller
public class AuthController{
    @Resource
    private UserService userService;

    @RequestMapping("/login")
    public String login(){
        return "/login";
    }

    @RequestMapping("/doLogin")
    public String doLogin(HttpServletRequest request, String username, String password) throws MyException {
        userService.login(request, username, password);
        System.out.print(request.getSession().getServletContext().getRealPath(""));
        return "/index";
    }
}
