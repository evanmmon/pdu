package com.chuangkou.pdu.controller;

import com.chuangkou.pdu.util.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
/**
 *登录
 * 刘哲
 *
 */
@ControllerAdvice
public class DoExceptionController {

    @ExceptionHandler
    public ModelAndView doException(Exception e){
        if(e instanceof MyException){
            MyException ex = (MyException)e;
            if(ex.getMessage().equals("E1001")){
                ModelAndView mv = new ModelAndView("/login");
                mv.addObject("msg", "用户名或密码错误");
                return mv;
            }else {
                ModelAndView mv = new ModelAndView("/error");
                return mv;
            }
        }else{
            ModelAndView mv = new ModelAndView("/error");
            return mv;
        }
    }
}
