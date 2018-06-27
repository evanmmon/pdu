package com.chuangkou.pdu.controller.interceptor;

import com.chuangkou.pdu.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: Kanyuanfeng
 * @Description: 登录拦截
 * @Date: 2018/4/19
 * @Modified By:
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取Session
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        if(null != user){
            return true;
        }
        //不符合条件的，跳转到登录界面
        request.getRequestDispatcher("/login.jsp").forward(request, response);

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
