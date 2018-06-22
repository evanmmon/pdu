package com.chuangkou.pdu.controller;

import com.chuangkou.pdu.entity.PduTemporary;
import com.chuangkou.pdu.service.PduTemporaryService;
import com.chuangkou.pdu.thread.SpringApplicationContextHolder;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @Author:
 * @Description:
 * @Date:Created in 16:12 2018/4/9
 */

@Controller
public class PduTemporaryController extends BaseController{

//    @Resource(name = "pduTemporaryService")
private static  PduTemporaryService pduTemporaryService=(PduTemporaryService) SpringApplicationContextHolder.getSpringBean("pduTemporaryService");

    public static PduTemporary selectByPduTemporary(String machineID){
        PduTemporary pduTemporary = new PduTemporary();
        try {
            pduTemporary = pduTemporaryService.selectByPduTemporary(machineID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pduTemporary;
    }


    public static void addPduTemporary(PduTemporary pduTemporary){
//        PduTemporary pduTemporary = new PduTemporary();
        try {
            pduTemporaryService.insert(pduTemporary);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
