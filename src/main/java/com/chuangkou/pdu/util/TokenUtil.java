package com.chuangkou.pdu.util;

import com.chuangkou.pdu.entity.User;
import com.chuangkou.pdu.entity.Token;
import com.chuangkou.pdu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author: Kanyuanfeng
 * @Description: app token的验证
 * @Date: 2018/4/18
 * @Modified By:
 */
@Component
public class TokenUtil {
    @Autowired
    private UserService userService;

    private static TokenUtil tokenUtil;

    public void setUserService(UserService logService){
        this.userService = userService;
    }
    @PostConstruct
    public void init() {
        tokenUtil = this;
        tokenUtil.userService = this.userService;   // 初使化时将已静态化的logService实例化
    }

    public static Token apptokenyanzheng(String token) {
        Token newToken = new Token();
        String[] tokens = token.split(",");
        String userName = tokens[0].toString();
        String md5Password = tokens[1].toString();
//        System.out.println(userName);
//        System.out.println(md5Password);
        User userByUsername = tokenUtil.userService.findUserByUsername(userName);
        if( null == userByUsername){
            newToken.setUsername("");
            newToken.setJieguo(false);
            newToken.setMd5Password("");
            return newToken;
        }
        String s = userByUsername.getPassword();
        String token1 = userByUsername.getToken();
        System.out.println(s);
        if (token1.equals(token)&&s.equals(md5Password) == true) {
            newToken.setUsername(userName);
            newToken.setJieguo(true);
            newToken.setMd5Password(md5Password);
            return newToken;
        } else {
            newToken.setUsername("");
            newToken.setJieguo(false);
            newToken.setMd5Password("");
            return newToken;
        }
    }
}

