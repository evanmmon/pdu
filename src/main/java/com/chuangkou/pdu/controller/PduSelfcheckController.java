package com.chuangkou.pdu.controller;

import com.chuangkou.pdu.entity.Pdu;
import com.chuangkou.pdu.entity.PduSelfcheck;
import com.chuangkou.pdu.entity.PduSelfcheckInfo;
import com.chuangkou.pdu.service.PduSelfcheckInfoService;
import com.chuangkou.pdu.service.PduSelfcheckService;
import com.chuangkou.pdu.service.PduService;
import com.chuangkou.pdu.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class PduSelfcheckController {
    @Autowired
    private PduSelfcheckService pduSelfcheckService;
    @Autowired
    private PduSelfcheckInfoService pduSelfcheckInfoService;
    @Autowired
    private PduService pduService;

    /**
     * 查询所有自检展示列表
     *刘哲
     * @return
     * @throws Exception
     */
    @RequestMapping("/pduSelfchecklist")
    public String selectPduSelfchecklist(Model model, HttpServletRequest request, HttpServletResponse resp) {
        try {
            List<PduSelfcheck> PduSelfcheckList = pduSelfcheckService.selectALL();
            model.addAttribute("PduSelfchecklist", PduSelfcheckList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pdu_selfcheck_list";
    }

    /**
     * 根据id删除
     *
     * @param id
     */
    @RequestMapping("/delPduSelfcheck")
    public String deletePduSelfcheck(HttpServletRequest request, Model model, int id) {
        try {
            PduSelfcheck pduSelfcheck = pduSelfcheckService.selectById(id);
            model.addAttribute("PduSelfcheck", pduSelfcheckService.deleteOne(id));
            //添加日志
            LogUtil.addLog(request,"删除设备自检："+pduSelfcheck.getSelfcheckname());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/pduSelfchecklist";
    }

    /**
     * 新增1
     */

    @RequestMapping("/addPduSelfcheck")
    public String addPduSelfcheck(HttpServletRequest request,Model model) {
        try {
            List<Pdu> pduList = pduService.selectAll();
            model.addAttribute("pduList", pduList);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        PduSelfcheck pduSelfcheck1 = pduSelfcheckService.savePduSelfcheck(pduSelfcheck);
//        model.addAttribute("PduSelfcheck", pduSelfcheck1);
        return "pdu_selfcheck_add";
    }

    @RequestMapping("/addPduSelfcheck2")
    public String addPduSelfcheck2(Model model, HttpServletRequest request, HttpServletResponse resp, PduSelfcheck pduSelfcheck) {
       try{
        String[] pduIds = request.getParameterValues("pdu");
        String selfcheckname = request.getParameter("selfcheckname");
        String createtime = request.getParameter("createtime");
        pduSelfcheck.setCreatetime(createtime);
        pduSelfcheck.setSelfcheckname(selfcheckname);
        PduSelfcheck pduSelfcheck1 = pduSelfcheckService.savePduSelfcheck(pduSelfcheck);
           //添加日志
           LogUtil.addLog(request,"添加设备自检："+selfcheckname);
        int maxId = pduSelfcheck1.getId();

        for (int i = 0; i < pduIds.length; i++) {

//            Map<String,Object> Map = new HashMap<String,Object>();
//            Map.put("selfcheckid",maxId);
//            int a = Integer.parseInt(pduIds[i]);
//            Map.put("pduid",a);
//            Map.put("createtime","createtime");

            PduSelfcheckInfo pduSelfcheckInfo = new PduSelfcheckInfo();
            pduSelfcheckInfo.setSelfcheckid(maxId);
            pduSelfcheckInfo.setCollectiontime(createtime);

            int a = Integer.parseInt(pduIds[i]);
            pduSelfcheckInfo.setPduid(a);
            pduSelfcheckInfoService.savePduSelfcheckInfo(pduSelfcheckInfo);


//           System.out.println(pduIds[i]);
//            System.out.println(selfcheckname);
//            System.out.println(createtime);
//            System.out.println(maxId);
        }
       } catch (Exception e) {
           e.printStackTrace();
       }
        return "redirect:/pduSelfchecklist";
    }
    @RequestMapping("/selectOnePduSelfcheck")
    public String selectOnePduSelfcheck2(int id ,Model model, HttpServletRequest request, HttpServletResponse resp, PduSelfcheck pduSelfcheck) {
        String[] pduIds = request.getParameterValues("pdu");
        return null;
    }



    @RequestMapping("/updatePduSelfcheck")
    public String updatePduSelfcheck2(Model model, HttpServletRequest request, HttpServletResponse resp, PduSelfcheck pduSelfcheck) {
        String[] pduIds = request.getParameterValues("pdu");
        return null;
    }
}
