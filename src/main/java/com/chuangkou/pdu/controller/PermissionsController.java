package com.chuangkou.pdu.controller;

import com.chuangkou.pdu.entity.Pdu;
import com.chuangkou.pdu.entity.PduPermissions;
import com.chuangkou.pdu.entity.RolePduRelation;
import com.chuangkou.pdu.service.PduService;
import com.chuangkou.pdu.service.RolePduRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PermissionsController {
    @Autowired
    private PduService pduService;
    @Autowired
    private RolePduRelationService rolePduRelationService;


    /**
     * 查询角色设备权限
     * 刘哲
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/permissionslist")
    public String selectpermissionslist(Model model, HttpServletRequest request, HttpServletResponse resp, String roleid) {
        String a = request.getParameter("roleid");
        int i1 = Integer.parseInt(a);

        try {
            List<PduPermissions> selectquanxian = rolePduRelationService.selectALL(i1);

            for (int i = 0; i < selectquanxian.size(); i++) {
                selectquanxian.get(i).setPduifiewradio(selectquanxian.get(i).getMachineid() + "ifview");
                selectquanxian.get(i).setPduifcontrolradio(selectquanxian.get(i).getMachineid() + "ifcontrol");
            }
            model.addAttribute("selectquanxian", selectquanxian);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "permissions";
    }

    /**
     * 更新角色设备权限
     * 刘哲
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/updatepermissionslist")
    public String updatepermissionslist(Model model, HttpServletRequest request, HttpServletResponse resp, String roleid) {
        try {
        String[] pduIds = request.getParameterValues("roleId");
        String[] pduIds1 = request.getParameterValues("mpermissionsId");
        //获取数据库全部数据
        List<PduPermissions> selectquanxian = rolePduRelationService.selectALL(Integer.parseInt(pduIds[0]));
//        List<RolePduRelation> list = new ArrayList<RolePduRelation>();
        for (int i=0;i<selectquanxian.size();i++){
            RolePduRelation rolePduRelation = new RolePduRelation();
            //获取jsp name值
            selectquanxian.get(i).setPduifiewradio(selectquanxian.get(i).getMachineid() + "ifview");
            selectquanxian.get(i).setPduifcontrolradio(selectquanxian.get(i).getMachineid()+ "ifcontrol");
            //获取值
            String ifview = request.getParameter(selectquanxian.get(i).getPduifiewradio());
            String ifcontrol =request.getParameter(selectquanxian.get(i).getPduifcontrolradio());
            rolePduRelation.setRoleid(Integer.parseInt(pduIds[i]));
            rolePduRelation.setMpermissionsid(Integer.parseInt(pduIds1[i]));
            rolePduRelation.setIfview(ifview);
            rolePduRelation.setIfcontrol(ifcontrol);
            rolePduRelationService.update(rolePduRelation);
//            list.add(rolePduRelation);
        }
        //更新

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/rolelist";
    }
}