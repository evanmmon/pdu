package com.chuangkou.pdu.util;

import com.chuangkou.pdu.entity.Log;
import com.chuangkou.pdu.entity.User;
import com.chuangkou.pdu.service.LogService;
import com.chuangkou.pdu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class LogUtil {
   @Autowired
   private LogService logService;
    @Autowired
    private UserService userService;

   private static LogUtil logUtil;

   public void setLogService(LogService logService){
       this.logService = logService;
   }

    public void setUserService(UserService logService){
        this.userService = userService;
    }
   @PostConstruct
    public void init() {
        logUtil = this;
        logUtil.logService = this.logService;   // 初使化时将已静态化的logService实例化
       logUtil.userService = this.userService;   // 初使化时将已静态化的userService实例化
    }
    /**
     *  web日志记录
     * @param request(获取session，通过session获取用户)
     * @param action（操作动作）
     */
    public static void addLog(HttpServletRequest request, String action){
        User user = (User) request.getSession().getAttribute("user");
        Date date  = new Date();
        String actiontime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        Log log = new Log(user.getId(), user.getNickname(), action, actiontime);
        logUtil.logService.insert(log);
    }

    /**
     * App日志记录
     * @param username(通过token解析用户名，通过用户名获取用户)
     * @param action（操作动作）
     */
    public static void addLog(String username, String action){
        User user = logUtil.userService.findUserByUsername(username);
        Date date  = new Date();
        String actiontime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
         Log log = new Log(user.getId(), user.getNickname(),action,actiontime);
        logUtil.logService.insert(log);
    }

    public static void addLog(String action){
        Date date  = new Date();
        String actiontime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        Log log = new Log(1, "admin", action, actiontime);
        logUtil.logService.insert(log);
    }
}
