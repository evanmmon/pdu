package com.chuangkou.pdu.controller;


import javax.servlet.http.HttpServletRequest;

import com.chuangkou.pdu.util.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


//import com.fh.util.Const;
//import com.fh.util.Logger;
//import com.fh.util.PageData;
//import com.fh.util.Tools;
//import com.fh.util.UuidUtil;


public class BaseController {

	protected Logger logger = Logger.getLogger(this.getClass());

	private static final long serialVersionUID = 6357869213649815390L;

    public static Map SubPolmap = new HashMap<String,Socket>();
	public static Map APPSubPolmap = new HashMap<String,Socket>();
//	public static Map APPSubPolmap = new HashMap<String,Socket>();
	public static String readmsg = "";

	public static ExecutorService executorService = Executors.newFixedThreadPool(200);

	public static ExecutorService executorServiceAppSocket = Executors.newFixedThreadPool(50);

	public static String DLTpassword = "35434343";
	public static String DLTcontrol = "44444444";

//    public static Map getSubPolmap() {
//        return SubPolmap;
//    }
//
//    public static void setSubPolmap(Map subPolmap) {
//        SubPolmap = subPolmap;
//    }

    /**

	 * 得到ModelAndView
	 */
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}

	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}




	public static void logBefore(Logger logger, String interfaceName){
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}

	public static void logAfter(Logger logger){
		logger.info("end");
		logger.info("");
	}

}
