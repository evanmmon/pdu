package com.chuangkou.pdu.controller;

import com.chuangkou.pdu.entity.*;
import com.chuangkou.pdu.service.MpermissionsService;
import com.chuangkou.pdu.service.PduService;
import com.chuangkou.pdu.service.RolePduRelationService;
import com.chuangkou.pdu.service.RoleService;
import com.chuangkou.pdu.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MpermissionsService mpermissionsService;
    @Autowired
    private PduService pduService;
    @Autowired
    private RolePduRelationService rolePduRelationService;

    /**
     * 查询所有角色展示列表
     *刘哲
     * @return
     * @throws Exception
     */
    @RequestMapping("/rolelist")
    public String selectUserlist(Model model, HttpServletRequest request, HttpServletResponse resp) {
        try {
            List<Mpermissions> RoleList = mpermissionsService.selectALL();
            model.addAttribute("Rolelist", RoleList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "role";
    }


    //选择设备
    @RequestMapping("/rolepdus")
    public String selectPdulist(Model model, HttpServletRequest request, HttpServletResponse resp, int id) {
        try {
            List<Pdu> pdus = pduService.selectAll();
            List<PduPermissions> selectquanxian = rolePduRelationService.selectALL(id);

            if (selectquanxian.size() == 0) {
                model.addAttribute("pdus", pdus);
                model.addAttribute("id", id);

            } else {
                ArrayList<Integer> string1 = new ArrayList<Integer>();
                ArrayList<Integer> string2 = new ArrayList<Integer>();

                for (int i =0 ;i<pdus.size();i++){
                    string1.add(pdus.get(i).getId());
                }
                for (int j =0 ;j<selectquanxian.size();j++){
                    string2.add(selectquanxian.get(j).getId());
                }
                    //去重
                    string1.removeAll(string2);
                List<Pdu> list = new ArrayList<Pdu>();
                    for (int z=0;z<string1.size();z++){
                   list.add(pduService.selectByIp(string1.get(z)))  ;

                    }

//                List<Pdu> list = new ArrayList<Pdu>();
//                for (int i=0;i<selectquanxian.size();i++){
//               list.add(pduService.selectByIp(selectquanxian.get(i).getIp()));
//
//                }
//
////                List<Pdu> pdus1=new ArrayList<Pdu>();
//                pdus.removeAll(list);


//                for (int i = 0; i < pdus.size(); i++) {
//                    for (int j = 0; j < selectquanxian.size(); j++) {
//                        if (pdus.get(i).getIp().equals(selectquanxian.get(j).getIp()) == false) {
//                            pdus1.add(pdus.get(i));
//                        }
//
//                    }
////            selectquanxian.get(i).getIp();
////            for(int j=0;j< )
////            pdus.get(i).getIp();
//                }
                model.addAttribute("pdus", list);
                model.addAttribute("id", id);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "role_pdu";
    }

    @RequestMapping("/addpdus")
    public String pduPermissison(Model model, HttpServletRequest request, HttpServletResponse resp) {

        String a = request.getParameter("roleid");
        String[] pduIds = request.getParameterValues("pdu");
        if (pduIds == null) {
            return "redirect:/permissionslist?roleid=" + a;
        } else {


            for (int i = 0; i < pduIds.length; i++) {

                RolePduRelation rolePduRelation = new RolePduRelation();
                rolePduRelation.setIfcontrol("0");
                rolePduRelation.setIfview("0");
                rolePduRelation.setRoleid(Integer.parseInt(a));
                rolePduRelation.setMpermissionsid(Integer.parseInt(pduIds[i]));
                rolePduRelationService.saveRolePduRelation(rolePduRelation);


//            System.out.println(a);
//            System.out.println(pduIds.length);
            }
            return "redirect:/permissionslist?roleid=" + a;
//         new ModelAndView("redirect:/permissionslist?roleid="+a);


        }
    }

//    /**
//     * 根据id删除角色
//     *
//     * @param id
//     */
//    @RequestMapping("/delRole")
//    public String deleteRole(HttpServletRequest request, Model model, int id) {
//        try {
//            model.addAttribute("Role", roleService.deleteOne(request, id));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "redirect:/rolelist";
//    }
//
//    /**
//     * 根据id查找角色
//     *
//     * @param id
//     */
//    @RequestMapping("/seleceOneRole")
//    public String getRole(int id, Model model) {
//        try {
//            model.addAttribute("role", roleService.findRoleById(id));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "role_edti";
//    }

    /**
     * 新增角色
     */

    @RequestMapping("/addRole")
    public String addRole(HttpServletRequest request, Model model, Role role) {
        try {
            Role role1 = roleService.saveRole(role);
            //添加日志
            LogUtil.addLog(request,"添加角色："+role.getRoleName());
            model.addAttribute("Role", role1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/rolelist";
    }

    /**
     * 编辑角色
     */

    @RequestMapping("/updateRole")
    public String UpdateRole(HttpServletRequest request, Model model, Role role) {
        try {
            Role role1 = roleService.update(role);
            //添加日志
            LogUtil.addLog(request,"编辑角色："+role.getRoleName());
            model.addAttribute("role", role1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/rolelist";
    }
}