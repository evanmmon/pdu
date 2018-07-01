package com.chuangkou.pdu.controller;

import com.chuangkou.pdu.entity.Log;
import com.chuangkou.pdu.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 操作日志管理
 */
@Controller
public class LogController {
    @Autowired
    private LogService logService;

    /**
     * 查询日志列表
     * @return
     */
    @RequestMapping("/logList")
    public String findLogList(Model model){
        try {
            List<Log> logList = logService.findAll();
            model.addAttribute("logList", logList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "log";
    }
}
