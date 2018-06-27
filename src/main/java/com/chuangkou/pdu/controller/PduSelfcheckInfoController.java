package com.chuangkou.pdu.controller;

import com.chuangkou.pdu.entity.Pdu;
import com.chuangkou.pdu.entity.PduSelfcheck;
import com.chuangkou.pdu.entity.PduSelfcheckInfo;
import com.chuangkou.pdu.service.PduSelfcheckInfoService;

import com.chuangkou.pdu.service.PduSelfcheckService;
import com.chuangkou.pdu.service.PduService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PduSelfcheckInfoController {
    @Autowired
    private PduSelfcheckInfoService pduSelfcheckInfoService;
    @Autowired
    private PduService pduService;

    /**
     * 查询所有自检详情列表
     *刘哲
     * @return
     * @throws Exception
     * modify:kanyuanfeng 2018/4/16
     * 修改内容：修改自检列表设备的名称显示为id的问题。
     */
    @RequestMapping("/PduSelfcheckInfo")
    public String getUser(int id, Model model) {
        try {
            List<PduSelfcheckInfo> pduSelfcheckInfoList = pduSelfcheckInfoService.selectALL(id);
            if (null != pduSelfcheckInfoList && pduSelfcheckInfoList.size() > 0){
                for (PduSelfcheckInfo pduSelfcheckInfo : pduSelfcheckInfoList) {
                    Pdu pdu = pduService.selectByPrimaryKey(pduSelfcheckInfo.getPduid());
                    pduSelfcheckInfo.setName(pdu.getName());
                }
            }
            model.addAttribute("PduSelfcheckInfolist", pduSelfcheckInfoList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "pdu_selfcheck_info";
    }
}
