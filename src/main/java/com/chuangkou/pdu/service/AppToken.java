package com.chuangkou.pdu.service;

import com.chuangkou.pdu.entity.User;
import com.chuangkou.pdu.entity.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 用户token验证
 * 刘哲
 * 18/4/11
 */
@Component
public class AppToken {
    @Autowired
    private UserService userService;

    public Token apptokenyanzheng(String token1) {
        Token token = new Token();
        //分解 token
        String[] tokens = token1.split(",");
        //token 中 获取用户名
        String userName = tokens[0].toString();
        //token 中 获取密码
        String md5Password = tokens[1].toString();
        //根据 用户名查询用户数据
        User userByUsername = userService.findUserByUsername(userName);
        //取出数据库中密码
        String s = userByUsername.getPassword();
        //取出数据库中token
        String token2 = userByUsername.getToken();
        //token 值 和密码都相等时 token  验证通过
        if ( token2.equals(token1)&&s.equals(md5Password) == true) {
            token.setUsername(userName);
            token.setJieguo(true);
            token.setMd5Password(md5Password);
            return token;
        } else {
            token.setUsername("");
            token.setJieguo(false);
            token.setMd5Password("");
            return token;
        }

    }
}


