package com.chuangkou.pdu.controller;

import com.chuangkou.pdu.entity.PduWarning;
import com.chuangkou.pdu.service.PduWarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class PduWarningController {
    @Autowired
    private PduWarningService pduWarningService;

    /**
     * 查询所有
     *刘哲
     * @return
     * @throws Exception
     */
    @RequestMapping("/PduWarninglist")
    public String selectpduwarninglistlist(Model model, HttpServletRequest request, HttpServletResponse resp) {
        try {
            List<PduWarning> PduWarningList = pduWarningService.selectALL();
            model.addAttribute("PduWarninglist", PduWarningList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pdu_warning_list";
    }
    @RequestMapping("/PduWarning")
    public String PduWarningfenxi(Model model, HttpServletRequest request, HttpServletResponse resp) {
       try {
           int a = pduWarningService.selectduanlu();
           int b = pduWarningService.selectguoliu();
           int c = pduWarningService.selectguoya();
           int e = pduWarningService.selectzongshu();
           int f = pduWarningService.selectjidianqi();
           int g = pduWarningService.selectloudian();
           int h = pduWarningService.selectqianya();
           int i = pduWarningService.selectweichulishu();
           int j = pduWarningService.selectyichulishu();
           int k = pduWarningService.selectchulizhong();
           int l = pduWarningService.selectyihulve();
           model.addAttribute("duanlu", a);
           model.addAttribute("guoliu", b);
           model.addAttribute("guoya", c);
           model.addAttribute("zongshu", e);
           model.addAttribute("jidianqi", f);
           model.addAttribute("loudian", g);
           model.addAttribute("qianya", h);
           model.addAttribute("weichulishu", i);
           model.addAttribute("yichulishu", j);
           model.addAttribute("chulizhong", k);
           model.addAttribute("yihulue", l);
       }catch (Exception e){
           e.printStackTrace();
       }
        return "pduwarning";
    }
}
