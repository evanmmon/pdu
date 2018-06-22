package com.chuangkou.pdu.controller;

import com.chuangkou.pdu.bean.MsgBean;
import com.chuangkou.pdu.bean.SaveGroupList;
import com.chuangkou.pdu.entity.Pdu;
import com.chuangkou.pdu.entity.PduGroup;
import com.chuangkou.pdu.entity.PduGroupRelation;
import com.chuangkou.pdu.entity.Token;
import com.chuangkou.pdu.service.AppToken;
import com.chuangkou.pdu.service.PduGroupRelationService;
import com.chuangkou.pdu.service.PduGroupService;
import com.chuangkou.pdu.service.PduService;
import com.chuangkou.pdu.util.LogUtil;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PduGroupApp {
    @Autowired
    private PduGroupService pduGroupService;
    @Autowired
    private PduGroupRelationService pduGroupRelationService;
    @Autowired
    private PduService pduService;
    @Autowired
    private AppToken appToken;


    /**
     * 保存修改分组接口
     * 刘哲
     * 18/3/15
     */
    @RequestMapping("/group/save_group_list")
    public void SavePduGrouplist(Model model, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        System.out.println("开始操作设备分组！");
        String access_token;
        String group_list;
        //读取“token”
        String token = req.getHeader("access_token");
//        String token = req.getHeader("access_token");
        try{
        Token apptokenyanzheng = appToken.apptokenyanzheng(token);
        if (!apptokenyanzheng.getJieguo()) {
            PrintWriter out = resp.getWriter();
            MsgBean msgBean = MsgBean.getFalseInstance("token失效");
            msgBean.setCode(403);
            out.print(msgBean.toJsonString());
            out.flush();
            out.close();
        } else {
//        String token = req.getHeader("access_token");
            //读取“分组列表”
            group_list = req.getParameter("group_list");
            PrintWriter out = resp.getWriter();

            Gson gson = new Gson();
            String jsonString = group_list;
            SaveGroupList appGroupList = gson.fromJson(jsonString, SaveGroupList.class);
            List<SaveGroupList.GroupListBean> group_list1 = appGroupList.getGroup_list();
//        appGroupList.getGroup_list().get(0).getGroup_id();

            List<PduGroup> sqlGroupList = pduGroupService.selectALL();
//        List<PduGroup> appGroupList = APPgrouplist;

            boolean findSameGroup = false;
            //遍历数据库分组列表
            for (int i = 0; i < sqlGroupList.size(); i++) {
                findSameGroup = false;
                //第i个分组对象
                PduGroup sqlPduGroup = sqlGroupList.get(i);
                //判断这个分组对象是否在appList中
                for (int j = 0; j < group_list1.size(); j++) {
                    SaveGroupList.GroupListBean groupListBean = group_list1.get(j);
                    //判断applist中的分组对象ID是否和SQL数据库中的分组ID相同
//                findSameGroup = sqlPduGroup.id.equals(appPduGroup.id);
                    findSameGroup = sqlPduGroup.getId().equals(groupListBean.getGroup_id());
                    if (findSameGroup) {
                        //如果名字不相等
                        if (!sqlPduGroup.getGroupname().equals(groupListBean.getGroup_name())) {
                            //更新分组名称
                            sqlPduGroup.setId(groupListBean.getGroup_id());
                            sqlPduGroup.setGroupname(groupListBean.getGroup_name());
                            pduGroupService.update(sqlPduGroup);
                            //添加日志
//                        LogUtil.addLog(req,"修改分组："+groupListBean.getGroup_name());
                            LogUtil.addLog(apptokenyanzheng.getUsername(),"修改分组");
                            // TODO: 2018/3/15 0015  upgrageGroupNameFromMySql(sqlPduGroup.id,sqlPduGroupm.name);
                        }
                        break;
                    }
                }
                //遍历完APP列表之后没找到这个分组,那么就删除这个分组 ,将该分组设备添加到默认分组
                if (!findSameGroup) {
                    List<PduGroupRelation> pduGroupRelations = pduGroupRelationService.selectPduIdByGroupId(sqlPduGroup.getId());
                    for (int l =0;l<pduGroupRelations.size();l++){
                        PduGroupRelation pduGroupRelation = new PduGroupRelation();
                        pduGroupRelation.setPduid(pduGroupRelations.get(l).getPduid());
                        pduGroupRelation.setPdugroupid(1);
                        pduGroupRelationService.savePduGroupRelation(pduGroupRelation);
                    }
                    PduGroup group = pduGroupService.findPduGroupById(sqlPduGroup.getId());
                    //删除分组sqlPduGroup.id;
                    pduGroupService.deleteOne(sqlPduGroup.getId());
                    //添加日志
//               LogUtil.addLog(req,"删除分组："+group.getGroupname());
                    LogUtil.addLog(apptokenyanzheng.getUsername(),"删除分组");
                    // TODO: 2018/3/15 0015  deleteGroupFromMySql(sqlPduGroup.id);

                }
            }
            //增加新的分组
            for (int i = 0; i < group_list1.size(); i++) {
                findSameGroup = false;
                //当前循环第i次的app分组列表项
                SaveGroupList.GroupListBean appPduGroup = group_list1.get(i);
                //遍历服务器中的分组列表
                for (int j = 0; j < sqlGroupList.size(); j++) {
                    //服务器分组列表中第j项分组对象
                    PduGroup sqlPduGroup = sqlGroupList.get(j);
                    findSameGroup = sqlPduGroup.getId().equals(appPduGroup.getGroup_id());
                    if (findSameGroup) {
                        break;
                    }
                }
                if (!findSameGroup) {
                    //添加APP这一条服务器中不存在的分组 appGroupItem.id;
                    // TODO: 2018/3/15 0015  insertGroupToMySql(appGroupItem.name);
                    PduGroup a = new PduGroup();
                    a.setId(appPduGroup.getGroup_id());
                    a.setGroupname(appPduGroup.getGroup_name());
                    pduGroupService.savePduGroup(a);
                    //添加日志
//                LogUtil.addLog(req,"添加分组："+a.getGroupname());
                    LogUtil.addLog(apptokenyanzheng.getUsername(),"添加新分组");
                }
            }

            JSONObject json = new JSONObject();
            MsgBean msgBean = MsgBean.getInstance();
            out.print(msgBean.toJsonString());
            out.flush();
            out.close();

        }
        }catch (Exception e){
            e.printStackTrace();
            PrintWriter out = resp.getWriter();
            MsgBean msgBean = MsgBean.getFalseInstance("设置失败");
            out.print(msgBean.toJsonString());
            out.flush();
            out.close();

        }
    }

    /**
     * 读取分组接口
     * 刘哲
     * 18/4/16
     */

    @RequestMapping("/group/get_group_list")
    public void SelectPduGrouplist(Model model, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        String token = req.getHeader("access_token");
//        String token = req.getHeader("access_token");
        try{
        Token apptokenyanzheng = appToken.apptokenyanzheng(token);
        if (!apptokenyanzheng.getJieguo()) {
            PrintWriter out = resp.getWriter();
            MsgBean msgBean = MsgBean.getFalseInstance("token失效");
            msgBean.setCode(403);
            out.print(msgBean.toJsonString());
            out.flush();
            out.close();
        } else {
            //读取“分组列表”

            PrintWriter out = resp.getWriter();
            //获取数据库分组
            List<PduGroup> pduGroupList = pduGroupService.selectALL();
            // 创建app 分组
            SaveGroupList appGroupList = new SaveGroupList();
            List<SaveGroupList.GroupListBean> group_list = new ArrayList<SaveGroupList.GroupListBean>();

            //将数据库的值给app
            for (int i = 0; i < pduGroupList.size(); i++) {
                SaveGroupList.GroupListBean groupListBean = new SaveGroupList.GroupListBean();
                groupListBean.setGroup_id(pduGroupList.get(i).getId());
                groupListBean.setGroup_name(pduGroupList.get(i).getGroupname());
                group_list.add(groupListBean);


            }
//        if(group_list2 != null){
            Gson gson = new Gson();
//             JSONObject data = new JSONObject();
//        String s = gson.toJson(group_list);
            MsgBean<SaveGroupList> saveGroupListMsgBean = new MsgBean<SaveGroupList>();
            saveGroupListMsgBean.setData(new SaveGroupList(group_list));
            String result = gson.toJson(saveGroupListMsgBean);

            out.print(result);
            out.flush();
            out.close();
//        }

        }}catch (Exception e){
            PrintWriter out = resp.getWriter();
            MsgBean msgBean = MsgBean.getFalseInstance("读取失败");
            out.print(msgBean.toJsonString());
            out.flush();
            out.close();

        }
    }

    /**
     * 修改分组开关状态(开关整个分组内的设备)
     * 刘哲
     * 18/4/16
     */
    @RequestMapping("/group/switch_group_relay")
    public void switch_group_relay(Model model, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        String token = req.getHeader("access_token");
        String group_id = req.getParameter("group_id");
        String relay = req.getParameter("relay");
//      String token = req.getHeader("access_token");

//        String token = req.getHeader("access_token");
        try{
        Token apptokenyanzheng = appToken.apptokenyanzheng(token);
        if (!apptokenyanzheng.getJieguo()) {
            PrintWriter out = resp.getWriter();
            MsgBean msgBean = MsgBean.getFalseInstance("token失效");
            msgBean.setCode(403);
            out.print(msgBean.toJsonString());
            out.flush();
            out.close();
        } else {
            //根据分组id 找出设备id
            List<PduGroupRelation> pduGroupRelations = pduGroupRelationService.selectPduIdByGroupId(Integer.parseInt(group_id));
            // 根据修改状态进行操作
            // 如果修改为1 则将分组内所以设备 onlinestate 将0更新为1  其余( 2,3) 不做操作
            if (relay.equals("1") == true) {
//                System.out.println(1);
                for (int i = 0; i < pduGroupRelations.size(); i++) {
                    Integer pduid = pduGroupRelations.get(i).getPduid();
                    Pdu pdu = pduService.selectByPrimaryKey(pduid);
                    if (pdu.getOnlinestate().equals("0") == true) {
                        pdu.setOnlinestate("1");
                        pduService.updateById(pdu);
                        //添加日志
                        LogUtil.addLog(req,"修改设备"+pdu.getName()+"在线状态为：运行");
                    }
                }
            } else {
                for (int i = 0; i < pduGroupRelations.size(); i++) {
                    Integer pduid = pduGroupRelations.get(i).getPduid();
                    Pdu pdu = pduService.selectByPrimaryKey(pduid);
                    if (pdu.getOnlinestate().equals("1") == true) {
                        pdu.setOnlinestate("0");
                        pduService.updateById(pdu);
                        //添加日志
                        LogUtil.addLog(req,"修改设备"+pdu.getName()+"在线状态为：关闭");
                    }
                }
            }
            PrintWriter out = resp.getWriter();
            MsgBean msgBean = MsgBean.getInstance();
            out.print(msgBean.toJsonString());
            out.flush();
            out.close();
        }}catch (Exception e){
            PrintWriter out = resp.getWriter();
            MsgBean msgBean = MsgBean.getFalseInstance("修改失败");
            out.print(msgBean.toJsonString());
            out.flush();
            out.close();

        }
    }

}
