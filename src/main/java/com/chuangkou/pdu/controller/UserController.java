package com.chuangkou.pdu.controller;

import com.chuangkou.pdu.entity.Organization;
import com.chuangkou.pdu.entity.Role;
import com.chuangkou.pdu.entity.User;
import com.chuangkou.pdu.service.OrganizationService;
import com.chuangkou.pdu.service.RoleService;
import com.chuangkou.pdu.service.UserService;
import com.chuangkou.pdu.util.LogUtil;
import com.chuangkou.pdu.util.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@Controller
//@RequestMapping("/users")
public class UserController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;
    @Autowired
    private OrganizationService organizationService;

    /**
     * 查询所有用户展示列表
     *刘哲
     * @return
     * @throws Exception
     */
    @RequestMapping("/userlist")
    public String selectUserlist(Model model, HttpServletRequest request, HttpServletResponse resp) {
        try {
            List<User> UserList = userService.selectALL();
            model.addAttribute("Userlist", UserList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "user";
    }

    /**
     * 根据id删除用户
     *
     * @param id
     */
    @RequestMapping("/delUser")
    public String deleteUser(HttpServletRequest request, Model model, int id) {
        try {
            model.addAttribute("User", userService.deleteOne(request,id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/userlist";
    }

    /**
     * 根据id查找用户
     *
     * @param id
     */
    @RequestMapping("/seleceOneUser")
    public String getUser(int id, Model model) {
        try {
            List<Role> RoleList = roleService.selectALL();
            model.addAttribute("RoleList", RoleList);
            List<Organization> OrganizationList = organizationService.selectALL();
            model.addAttribute("OrganizationLists", OrganizationList);
            model.addAttribute("user", userService.findUserById(id));
        } catch (
                Exception e){
            e.printStackTrace();
        }
        return "user_edti";
    }

    /**
     * 新增用户
     */
    @RequestMapping("/addUser1")
    public String addUser1(HttpServletRequest request, Model model, User user) {
        try {
            List<Role> RoleList = roleService.selectALL();
            model.addAttribute("RoleList", RoleList);
            List<Organization> OrganizationList = organizationService.selectALL();
            model.addAttribute("OrganizationLists", OrganizationList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "user_add";
    }
    @RequestMapping("/addUser")
    public String addUser(HttpServletRequest request, HttpServletResponse resp, Model model, User user) {
        User user1 = new User();
        user1.setUsername(request.getParameter("username"));
        user1.setPassword(MD5Utils.encode(request.getParameter("password")));
        user1.setNickname(request.getParameter("nickname"));
        user1.setPartyid(Integer.parseInt(request.getParameter("parentid")));
        user1.setEmail(request.getParameter("email"));
        user1.setPhone(request.getParameter("phone"));
        user1.setRemark(request.getParameter("remark"));
        user1.setState(1);
//        String date = new Date().toString();
//        user1.setCreateTime(Long.valueOf(date));
        Date date = new Date();
        Long time =date.getTime();
        user1.setCreate_time(time);
        userService.saveUser(request,user1);

        Role role = new Role();
        role.setRoleName(request.getParameter("roleName"));
        role.setUsername(request.getParameter("username"));
        roleService.saveRole(role);

        //        try {
//            User user1 = userService.saveUser(request,user);
//            model.addAttribute("User", user1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return "redirect:/userlist";
    }

    /**
     * 编辑用户
     */


    @RequestMapping("/updateUser")
    public String UpdateUser(HttpServletRequest request, Model model, User user) {
        User user1 = new User();
        user1.setNickname(request.getParameter("nickname"));
        user1.setPartyid(Integer.parseInt(request.getParameter("parentid")));
        user1.setEmail(request.getParameter("email"));
        user1.setPhone(request.getParameter("phone"));
        user1.setRemark(request.getParameter("remark"));
        user1.setState(1);
        user1.setId(user.getId());
        userService.update(request,user1);

        Role role = new Role();
        role.setRoleName(request.getParameter("roleName"));
        role.setUsername(request.getParameter("username"));
        roleService.update1(role);
        return "redirect:/userlist";


    }
    //退出登录
    //刘哲
    //2018/4/12
    @RequestMapping("/out")
    public String loginOut(HttpServletRequest request, Model model, User user) {
        LogUtil.addLog(request,"退出系统");
        SecurityUtils.getSubject().logout();
            request.getSession().removeAttribute("user");
            return "login";
    }
    /**
     * @Author: kanyuanfeng
     * @Date: 2018/4/17

     * @Description:修改密码
     *
     */
    @RequestMapping("/updatePassword")
    public String updatePassword(HttpServletRequest request) {
        try {
            User user = (User)request.getSession().getAttribute("user");
            String s = request.getParameter("password");
            user.setPassword(MD5Utils.encode(request.getParameter("password")));
            userService.updatePassword(user);
            LogUtil.addLog(request,"修改密码");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "login";
    }
    /**
     * @Author: kanyuanfeng
     * @Date: 2018/4/17
     * @Description:获取用户信息
     *
     */
    @RequestMapping("/userInfo")
    public void userInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        //从seesion中获取用户
        User user = (User)request.getSession().getAttribute("user");
        //获取角色名
        Role role = roleService.selectByUsername(user.getUsername());
        //获取用户部门信息
        Organization organization = organizationService.findOrganizationById(user.getPartyid());
        String organizationName = "";
        if(null != organization){
            organizationName = organization.getName();
        }
        //组装个人信息json
        //"{"name":"name","name":"name"}"
        String json = "{\"username\":\""+user.getNickname()+"\",\"phone\":\""+user.getPhone()+"\",\"remark\":\""+user.getRemark()+"\",\"email" +
                "\":\""+user.getEmail()+"\",\"role\":\""+role.getRoleName()+"\",\"organization\":\""+organizationName+"\"}";
        PrintWriter pw = response.getWriter();
        pw.write(json);
        pw.flush();
        pw.close();
    }

    /**
     * @Author: kanyuanfeng
     * @Date: 2018/4/20
     * @Description:验证用户是否已存在
     *
     */
    @RequestMapping("/checkUsername")
    @ResponseBody
    public User checkUsername(String username){
        User user = userService.findUserByUsername(username);
        return user;
    }
}




//    @RequiresPermissions(value = {"sys:product:add"})
//    @RequestMapping("/list")
//    public String list(){
//        return "/list";
//    }
//// 登录
//@Resource
//private UserService userService;
//    // 直接跳转到login.jsp页面
//    @RequestMapping("/login")
//    public String login(){
//        return "login.jsp";
//    }
//
//    // 直接跳转到index.jsp页面
//    @RequestMapping("/index")
//    public String home(){
//        return "index.jsp";
//    }
//
//
//@RequestMapping("/Login")
//public String Login(String phone, String password, HttpSession session, Model model){
//    User user= UserService.login(phone, password);
//    if (user != null){
//        session.setAttribute("user", user);
//        return "/users/index";
//    }else {
//        model.addAttribute("msg", "用户名或密码错误");
//        return "/users/login";
//    }
//}



