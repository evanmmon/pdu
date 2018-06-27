package com.chuangkou.pdu.controller;

import com.chuangkou.pdu.entity.Organization;
import com.chuangkou.pdu.service.OrganizationService;
import com.chuangkou.pdu.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;
    /**
     *组织管理
     * 刘哲
     *
     */
    @RequestMapping("/organizationlist")
    public String selectlist(Model model, HttpServletRequest request, HttpServletResponse resp) {
        try {
            List<Organization> OrganizationList = organizationService.selectALL();
            model.addAttribute("OrganizationLists", OrganizationList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "organization";
    }
    /**
     * 根据id删除用户
     *
     * @param id
     */
    @RequestMapping("/delOrganization")
    public String deleteUser(HttpServletRequest request,Model model, int id) {
        try {
            Organization organization = organizationService.findOrganizationById(id);
            model.addAttribute("Organization", organizationService.deleteOne(id));
            //添加日志
            LogUtil.addLog(request,"删除部门："+organization.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/organizationlist";
    }
    /**
     * 根据id查找用户
     *
     * @param id
     * 修改：kanyuanfeng
     * 时间：2018/4/12
     */
    @RequestMapping("/seleceOneOrganization")
    public String getUser(int id, Model model) {
        try {
            List<Organization> organizationList = organizationService.selectALL();
            //从部门集合中移除改该部门本身
            for (Organization organization:organizationList ) {
                if(id == organization.getId()){
                    organizationList.remove(organization);
                    break;
                }
            }
            model.addAttribute("OrganizationLists", organizationList);
            model.addAttribute("organization", organizationService.findOrganizationById(id));
        } catch (
                Exception e){
            e.printStackTrace();
        }
        return "organization_edti";
    }
    /**
     * 进入新增部门页面
     */
    @RequestMapping("/addOrganization1")
    public String addUser1(Model model,  Organization organization) {

        try {
            List<Organization> OrganizationList = organizationService.selectALL();
            model.addAttribute("OrganizationLists", OrganizationList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "organization_add1";
    }

    /**
     * 新增部门
     * 修改：kanyuanfeng
     * 时间：2018/4/12
     */

    @RequestMapping("/addOrganization")
    public String addUser(HttpServletRequest request,Model model,  Organization organization) {
        try {
            Organization organization1 =  organizationService.saveOrganization(organization);
            //判断是否为一级部门。若为一级部门parent设置为id
            if (null == organization.getParentid()){
                organization.setParentid(organization1.getId());
                organizationService.update(organization);
            }
            //新增部门
            LogUtil.addLog(request,"新增部门:"+organization.getName());
            model.addAttribute("Organization",organization1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/organizationlist";
    }

    /**
     * 编辑用户
     */


    @RequestMapping("/updateOrganization")
    public String UpdateUser(HttpServletRequest request,Model model, Organization organization) {
        try {
            Organization organization1 = organizationService.update(organization);
            model.addAttribute("organization", organization1);
            //添加日志
            LogUtil.addLog(request,"修改部门："+organization.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/organizationlist";


    }
}
