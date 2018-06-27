package com.chuangkou.pdu.controller;

import com.chuangkou.pdu.bean.*;
import com.chuangkou.pdu.entity.*;
import com.chuangkou.pdu.service.*;
import com.chuangkou.pdu.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


@Controller
public class IndexApp {
    @Autowired
    private PduWarningService pduWarningService;
    @Autowired
    private PduGroupRelationService pduGroupRelationService;
    @Autowired
    private PduService pduService;
    @Autowired
    private PduInfoService pduInfoService;
    @Autowired
    private PduGroupService pduGroupService;
    @Autowired
    private AppToken appToken;
    @Autowired
    private RolePduRelationService rolePduRelationService;


    /**
     * 首页接口
     * 刘哲
     * 18/3/20
     */
    @RequestMapping("/device/overview")
    public void SelectPduGrouplist(Model model, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
//        System.out.println("首页！");
        String access_token;

        //读取“token”
        String token = req.getHeader("access_token");
//        token = "admin,21232F297A57A5A743894A0E4A801FC3,1527560139428";
        try {
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
                PrintWriter out = resp.getWriter();
                //获取数据库分组
                Date date = new Date();
                date.getTime();
                IndexBean indexBean = new IndexBean();


                List<IndexBean.GroupListBean> listBeans = new ArrayList<IndexBean.GroupListBean>();
                List<Pdu> pdus = pduService.selectAll();
                //设备总数
                int szongshu = pduService.selectzongshu();
                //没有设备时
                if (0 == szongshu) {
                    indexBean.setDevice_number(0);
                    indexBean.setTotal_power(0);
                    indexBean.setNormal_number(0);
                    indexBean.setClosed_number(0);
                    indexBean.setFault_number(0);
                    indexBean.setOffline_number(0);
                    indexBean.setGroup_list(listBeans);
                    MsgBean<IndexBean> msgBean = MsgBean.getInstance();
                    msgBean.setData(indexBean);
                    out.print(msgBean.toJsonString());
                    out.flush();
                    out.close();
                } else {      //设备总数
                    indexBean.setDevice_number(szongshu);
                    //总功率 1 取出所有设备id   2根据id 查询最新的功率  3 累计相加

                    float sum = 0.0f;
                    for (int i = 0; i < pdus.size(); i++) {
                        String watt = pduInfoService.wattzongshu(pdus.get(i).getId());
                        if (null == watt || "0".equals(pdus.get(i).getOnlinestate()) == true || "2".equals(pdus.get(i).getOnlinestate()) == true) {
                            sum += 0;
                        } else {
                            sum += Float.valueOf(watt);
                        }
                    }

//                double d  = sum;
//                DecimalFormat df  = new DecimalFormat("######0.0");
//                d =Double.valueOf(df.format(d));
                    int d = (Integer) Math.round(sum);
                    //分组总功率
                    indexBean.setTotal_power(d);
                    //运行中设备总数
                    indexBean.setNormal_number(pduService.yunxingzhong());
                    //已关闭
                    indexBean.setClosed_number(pduService.yiguanbi());
                    //故障
                    indexBean.setFault_number(pduService.guzhang());
                    //离线
                    indexBean.setOffline_number(pduService.lixian());

                    int air_switch_number = 0;
                    int socket_number = 0;
                    int d45_number = 0;

                    //分类设备数量 xulei add 2018-05-14
                    for (int s = 0; s < pdus.size(); s++) {
                        Pdu pdufenlei = new Pdu();
                        pdufenlei = pdus.get(s);
                        String pdutype = pdufenlei.getType();

                        if (pdutype.equals("180")) air_switch_number++;
                        if (pdutype.equals("0001")) socket_number++;
                        if (pdutype.equals("0000")) d45_number++;
                    }

                    //空开设备数量
                    indexBean.setAir_switch_number(air_switch_number);
                    //插座设备数量
                    indexBean.setSocket_number(socket_number);
                    //45设备数量
                    indexBean.setD45_number(d45_number);

                    //group list 填充
                    //查询所有分组
                    List<PduGroup> pduGroupList = pduGroupService.selectALL();
                    for (int i = 0; i < pduGroupList.size(); i++) {
                        IndexBean.GroupListBean groupListBean = new IndexBean.GroupListBean();
                        //分组id  pduGroupList.get(i).getId()
                        groupListBean.setGroup_id(Integer.toString(pduGroupList.get(i).getId()));
                        //分组名称
                        groupListBean.setGroup_name(pduGroupList.get(i).getGroupname());
                        //分组内总功率
                        // 1 获取分组内 state 为1的设备id
                        // 2 根据id 到 info 中查询每个pdu 设备的最近一条功率值
                        // 3 获取后累计相加

                        List<PduGroupRelation> pduGroupRelations = pduGroupRelationService.selectPduIdByGroupId(pduGroupList.get(i).getId());
                        List<PduGroupRelation> pduGroupRelations2 = pduGroupRelationService.selectByIds(pduGroupList.get(i).getId());
                        float sum1 = 0.0f;
                        for (int j = 0; j < pduGroupRelations2.size(); j++) {
                            String watt = pduInfoService.wattzongshu(pduGroupRelations.get(j).getPduid());
                            if (null == watt || "0".equals(pduGroupRelations2.get(j).getPdus().get(0).getOnlinestate()) == true || "2".equals(pduGroupRelations2.get(j).getPdus().get(0).getOnlinestate()) == true) {
                                sum1 += 0;
                            } else {
                                sum1 += Float.valueOf(watt);
                            }
                        }
//                    double d1  = sum1;
//                    DecimalFormat df1  = new DecimalFormat("######0.0");
//                    d1 =Double.valueOf(df1.format(d1));
                        int d1 = (Integer) Math.round(sum1);
                        //分组总功率
                        groupListBean.setTotal_power(d1);
                        //分组设备总数
                        groupListBean.setNormal_number(pduGroupRelations.size());
                        //分组各种状态设备数量

                        int yunxingzhong = 0;
                        int yiguangbi = 0;
                        int guzhang = 0;
                        int lixian = 0;
                        // 查询出每条设备的状态
                        List<PduGroupRelation> pduGroupRelations1 = pduGroupRelationService.selectByIds(pduGroupList.get(i).getId());
                        for (int z = 0; z < pduGroupRelations1.size(); z++) {
//                String actionState1 = pduGroupRelations1.get(z).getPdu().getActionState();
                            if (null == pduGroupRelations1.get(z).getPdu().getOnlinestate()) {

//                        pduGroupRelations1.get(z).getPdu().getActionState()
                                int a = 0;

                                switch (a) {
                                    case 0:
                                        yiguangbi = yiguangbi + 1;
                                        break;
                                    case 1:
                                        yunxingzhong = yunxingzhong + 1;
                                        break;
                                    case 2:
                                        lixian = lixian + 1;
                                        break;
                                    case 3:
                                        guzhang = guzhang + 1;
                                        break;
                                }
                            } else {
                                String actionState = pduGroupRelations1.get(z).getPdu().getOnlinestate();
                                int b = Integer.parseInt(actionState);
                                switch (b) {
                                    case 0:
                                        yiguangbi = yiguangbi + 1;
                                        break;
                                    case 1:
                                        yunxingzhong = yunxingzhong + 1;
                                        break;
                                    case 2:
                                        lixian = lixian + 1;
                                        break;
                                    case 3:
                                        guzhang = guzhang + 1;
                                        break;
                                }
                            }
                        }
                        groupListBean.setNormal_number(yunxingzhong);
                        groupListBean.setClosed_number(yiguangbi);
                        groupListBean.setFault_number(guzhang);
                        groupListBean.setOffline_number(lixian);

                        //分组状态判断 根据运行中设备总数判断
                        if (yunxingzhong > 0) {
                            groupListBean.setGroup_state(1);
                        } else {
                            groupListBean.setGroup_state(0);
                        }
                        listBeans.add(groupListBean);
                    }
                    indexBean.setGroup_list(listBeans);
                    MsgBean<IndexBean> msgBean = MsgBean.getInstance();
                    msgBean.setData(indexBean);
                    out.print(msgBean.toJsonString());
                    out.flush();
                    out.close();
                }
            }
        } catch (Exception e) {
            PrintWriter out = resp.getWriter();
            MsgBean msgBean = MsgBean.getFalseInstance("获取失败");
            out.print(msgBean.toJsonString());
            out.flush();
            out.close();

        }

//        }

        PduController.jsonAPPSocket();//启动手机APPsocket连接服务
    }

    /**
     * 首页预警列表接口
     * 刘哲
     * 18/3/20
     */
    @RequestMapping("/warning/get_warning_list")
    public void SelectPduWarning(Model model, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
//        System.out.println("首页进入预警界面！");
        String access_token;

        //读取“token”
        String token = req.getHeader("access_token");
//        String token = req.getHeader("access_token");

//        token = "admin,21232F297A57A5A743894A0E4A801FC3,1527560139428";
        try {
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
                //创建 data
                IndexWarningBean warningBean = new IndexWarningBean();
                // 内部list集合
                ArrayList<IndexWarningBean.WarningListBean> warningListBeans = new ArrayList<IndexWarningBean.WarningListBean>();
                //单个 list

                List<PduWarning> pduWarnings = pduWarningService.selectALL2();

                for (int j = 0; j < pduWarnings.size(); j++) {
                    IndexWarningBean.WarningListBean warningListBean = new IndexWarningBean.WarningListBean();

                    PduWarning pduWarning = pduWarnings.get(j);
//            PduGroupRelation bypduId = new PduGroupRelation();
                    PduGroupRelation bypduId = pduGroupRelationService.findBypduId(pduWarning.getPduid());
                    warningListBean.setDatetime(pduWarning.getWarningtime());
                    warningListBean.setDevice_id(Integer.toString(pduWarning.getPduid()));
                    if (null == bypduId) {
                        warningListBean.setDevice_name("");
                        warningListBean.setGroup_name("");
                        warningListBean.setGroup_id(0);
                    } else {
                        warningListBean.setDevice_name(bypduId.getPdus().get(0).getName().equals("null") ? "" : bypduId.getPdus().get(0).getName());
                        warningListBean.setGroup_name(bypduId.getPdugroup().getGroupname());
                        warningListBean.setGroup_id(bypduId.getPdugroup().getId());
                    }

                    warningListBean.setState(Integer.parseInt(pduWarning.getState()));
                    String a = pduWarning.getWarningtype();
                    warningListBean.setType(Integer.parseInt(a));
//            boolean b = "漏电".equals(a);
//            boolean c = "断路".equals(a);
//            boolean d = "功率".equals(a);
//            boolean e = "过压".equals(a);
//            boolean f = "欠压".equals(a);
//            boolean g = "过流".equals(a);
//
//            if (b = true) {
//                warningListBean.setType(1);
//            } else if (c = true) {
//                warningListBean.setType(2);
//            } else if (d = true) {
//                warningListBean.setType(3);
//            } else if (e = true) {
//                warningListBean.setType(4);
//            } else if (f = true) {
//                warningListBean.setType(5);
//            } else {
//                warningListBean.setType(6);


//            warningListBean.setType(Integer.parseInt(pduWarning.getWarningtype()));
                    warningListBean.setWarning_id(pduWarning.getId());
                    warningListBeans.add(warningListBean);
                }
//        //单个 填充
//        warningListBean.setDatetime("2018-03-20 15:30");
//        warningListBean.setDevice_id("1");
//        warningListBean.setDevice_name("研发部1号");
//        warningListBean.setGroup_id(1);
//        warningListBean.setGroup_name("研发部");
//        warningListBean.setState(0);
//        warningListBean.setType(1);
//        warningListBean.setWarning_id(1);
//
//
//        warningListBean1.setDatetime("2018-03-21 14:30");
//        warningListBean1.setDevice_id("2");
//        warningListBean1.setDevice_name("研发部2号");
//        warningListBean1.setGroup_id(1);
//        warningListBean1.setGroup_name("研发部");
//        warningListBean1.setState(0);
//        warningListBean1.setType(1);
//        warningListBean1.setWarning_id(8);
                //添加单个到 集合


                //将新建的Arraylist 放入 bean中的list
                warningBean.setWarning_list(warningListBeans);
                //调用msgbean 转换格式
                MsgBean<IndexWarningBean> msgBean = MsgBean.getInstance();
                msgBean.setData(warningBean);
                out.print(msgBean.toJsonString());
                out.flush();
                out.close();

//        IndexBean.GroupListBean groupListBean = new IndexBean.GroupListBean();
//
//        List<IndexBean.GroupListBean> listBeans=new ArrayList<IndexBean.GroupListBean>();
//
//        indexBean.setClosed_number(30);
//        indexBean.setFault_number(1);
//        indexBean.setDevice_number(30);
//        indexBean.setNormal_number(25);
//        indexBean.setOffline_number(2);
//        indexBean.setTotal_power(12927.1);
//
//
//        groupListBean .setGroup_id("18000");
//        groupListBean .setGroup_name("办公室");
//        groupListBean .setTotal_power(9000.1);
//        groupListBean .setNormal_number(5);
//        groupListBean .setClosed_number(0);
//        groupListBean .setFault_number(0);
//        groupListBean .setOffline_number(0);
//        groupListBean .setGroup_state(1);
//        groupListBean .setGroup_type(1);
//        listBeans.add(groupListBean);
//        indexBean.setGroup_list(listBeans);
////        indexBean.setGroup_list().add(0,groupListBean);
////        if(group_list2 != null){
//        Gson gson = new Gson();
////        String data =gson.toJson(indexBean);
//        MsgBean<IndexBean> msgBean = new MsgBean<IndexBean>();
//        msgBean.setCode(0);
//        msgBean.setMsg("数据获取成功");
//        msgBean.setData(indexBean);
//        String s = gson.toJson(msgBean);
////
////        JSONObject json = new JSONObject();
////        json.put("code", 0);
////        json.put("msg", "数据获取成功");
////        json.put("data",data);
//        out.print(s);
//        out.flush();
//        out.close();
//        }


            }
        } catch (Exception e) {
            PrintWriter out = resp.getWriter();
            MsgBean msgBean = MsgBean.getFalseInstance("获取失败");
            out.print(msgBean.toJsonString());
            out.flush();
            out.close();

        }
    }

    /**
     * @Author:xulei
     * @Description:版本自动更新接口
     * @Date: 2018-06-01
     */
    @RequestMapping("/get_new_version")
    @ResponseBody
    public void jsonPduNewVersion(HttpServletRequest request, HttpServletResponse response) throws
            Exception {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String access_token = request.getHeader("access_token");
//        NewVersionBean newVersionBean = null;
        PrintWriter out = response.getWriter();

//        access_token = "admin,21232F297A57A5A743894A0E4A801FC3,1527560139428";
        try {
            //验证并解析token
            Token token = TokenUtil.apptokenyanzheng(access_token);
            if (token.getJieguo()) {

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbFactory.newDocumentBuilder();
                Document document = null;
                //            List<NewVersionBean> newVersionBean =  new ArrayList<NewVersionBean>();
                NewVersionBean newVersionBean = null;
                //                将给定 URI 的内容解析为一个 XML 文档,并返回Document对象
                String path = request.getSession().getServletContext().getRealPath("");
                document = (Document) db.parse(path+"/app/version.xml");
//                document = (Document) db.parse("D:\\tomcat\\apache-tomcat-7.0.82\\app\\version.xml");
                NodeList bookList = document.getElementsByTagName("data");


                //遍历XML内容 data
                for (int i = 0; i < bookList.getLength(); i++) {
                    newVersionBean = new NewVersionBean();

                    //获取第i个data结点
                    org.w3c.dom.Node node = bookList.item(0);
                    //                    //获取第i个book的所有属性
                    //                    NamedNodeMap namedNodeMap = node.getAttributes();

                    //获取book结点的子节点,包含了Test类型的换行
                    NodeList cList = node.getChildNodes();//System.out.println(cList.getLength());9

                    //将一个book里面的属性加入数组
                    ArrayList<String> contents = new ArrayList<String>();
                    for (int j = 1; j < cList.getLength(); j += 2) {

                        org.w3c.dom.Node cNode = cList.item(j);
                        String content = cNode.getFirstChild().getTextContent();
                        contents.add(content);
                        //System.out.println(contents);
                    }

                    newVersionBean.setDownload_url(contents.get(0));
                    newVersionBean.setVersion_code(Integer.valueOf(contents.get(1)));
                    newVersionBean.setVersion_name(contents.get(2));
                    newVersionBean.setDescription(contents.get(3));
                    newVersionBean.setLength(Long.valueOf(contents.get(4)));
                    newVersionBean.setUpdate_time(Long.valueOf(contents.get(5)));

                }
                //调用msgbean 转换格式
                MsgBean<NewVersionBean> msgBean = MsgBean.getInstance();
                msgBean.setData(newVersionBean);
                out.print(msgBean.toJsonString());
                out.flush();
                out.close();

            } else {
                out = response.getWriter();
                MsgBean msgBean = MsgBean.getFalseInstance("获取失败");
                out.print(msgBean.toJsonString());
                out.flush();
                out.close();

            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DOMException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }


    /**
     * @Author:xulei
     * @Description: 权限列表接口
     * @Date:2018-06-19
     */
    @RequestMapping("/user/get_permissions_details")
    @ResponseBody
    public void jsonGetPermissionsDetails(HttpServletRequest request, HttpServletResponse response) throws
            Exception {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String access_token = request.getHeader("access_token");

        String account = request.getParameter("account");
//        String password = request.getHeader("password");

        PrintWriter out = response.getWriter();
        access_token = "admin,21232F297A57A5A743894A0E4A801FC3,1529381604227";
        try {
            //验证并解析token
            Token token = TokenUtil.apptokenyanzheng(access_token);
            if (token.getJieguo()) {

                //获取用户权限
                List<RolePduRelation> rolePduRelationList = new ArrayList<RolePduRelation>();
                rolePduRelationList =  rolePduRelationService.getRolePduRelationList(account);

                GetPermissionsDetailsBean getPermissionsDetailsBean = new GetPermissionsDetailsBean();

                List<GetPermissionsDetailsBean.PermissionListBean> permissionListBeanList = new ArrayList<GetPermissionsDetailsBean.PermissionListBean>();


                for(int i= 0 ; i < rolePduRelationList.size(); i++){
                    RolePduRelation rolePduRelation = new RolePduRelation();
                    rolePduRelation = rolePduRelationList.get(i);

                    GetPermissionsDetailsBean.PermissionListBean permissionListBean = new GetPermissionsDetailsBean.PermissionListBean();

                    permissionListBean.setDevice_id(rolePduRelation.getMpermissionsid());
                    permissionListBean.setDevice_type(rolePduRelation.getType());
                    permissionListBean.setDevice_statu(rolePduRelation.getIfview());
                    permissionListBean.setDevice_operation(rolePduRelation.getIfcontrol());

                    permissionListBeanList.add(permissionListBean);
                }
                getPermissionsDetailsBean.setPermission_list(permissionListBeanList);

                MsgBean<GetPermissionsDetailsBean> msgBean = MsgBean.getInstance();
                msgBean.setData(getPermissionsDetailsBean);
                out.print(msgBean.toJsonString());
                out.flush();
                out.close();
            } else {
                out = response.getWriter();
                MsgBean msgBean = MsgBean.getFalseInstance("获取失败");
                out.print(msgBean.toJsonString());
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
