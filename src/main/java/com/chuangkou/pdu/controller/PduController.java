package com.chuangkou.pdu.controller;

import com.chuangkou.pdu.entity.*;
import com.chuangkou.pdu.service.*;
import com.chuangkou.pdu.thread.*;
import com.chuangkou.pdu.util.DeviceEvent;
import com.chuangkou.pdu.util.LogUtil;
import com.chuangkou.pdu.util.NetUtil;
import com.google.gson.Gson;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
*描述：PDU设备对象功能
*作者：徐磊
* 创建：2018-2-6
* */
@Controller
public class PduController extends BaseController {

    //    protected Logger logger = Logger.getLogger(this.getClass());
//    private static final long serialVersionUID = 6357869213649815390L;
    @Resource(name = "pduService")
    private PduService pduService;

    @Resource(name = "pduGroupService")
    private PduGroupService pduGroupService;

    @Resource(name = "pduInfoService")
    private PduInfoService pduInfoService;

    @Resource(name = "roleService")
    private RoleService roleService;

    @Resource(name = "pduTemporaryService")
    private PduTemporaryService pduTemporaryService;

    @Resource(name = "pduGroupRelationService")
    private PduGroupRelationService pduGroupRelationService;

    @Resource(name = "pduRelationService")
    private PduRelationService pduRelationService;

    private Pdu pdu = new Pdu();

    public Pdu getPdus() {
        return pdu;
    }

    public void setPdus(Pdu pdu) {
        this.pdu = pdu;
    }

    private String threadRsult = "";

    /**
     * 方法描述：后台管理－设备列表　search
     * 作者：徐磊
     * 创建：2016-2-6
     */

//    @RequiresPermissions(value = {"sys:product:search"})
    @RequestMapping("/pdusearch")
    public String search(Model model, HttpServletRequest request, HttpServletResponse resp) {
//        ModelAndView mv = new ModelAndView();

        try {
            //获取已经添加的设备list
            List<Pdu> pduList = pduService.selectAll();

            //搜索没有添加的设备列表socket服务，可以优化，长时间启动线程保存新设备到数据库，直接读取数据库的内容
//            SocketServer socketServer = new SocketServer();
//            socketServer.socketSearchList();//启动socket通信线程；
//      List<PduSearch> pduSearchList = tempPdu();//获取搜索的设备列表
            List<PduTemporary> pduSearchList = pduTemporaryService.selectAll();//获取搜索的设备列表

//      Thread.currentThread().sleep(5000);

            //获取当前用户的操作权限
//        List<Role> roleList = roleService.RoleList();
//            mv.setViewName("/pdu_list");
//            mv.addObject("pduList", pduList);//返回已添加设备列表
//            mv.addObject("pduSearchList", pduSearchList); //返回socket搜索设备
//        mv.addObject("roleList", roleList);//返回用户角色列表
//        mv.addObject(Const.SESSION_QX, this.getHC());    //按钮权限

            model.addAttribute("pduList", pduList);
            model.addAttribute("pduSearchList", pduSearchList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "pdu_list";
    }


    //json解析信息数据
    public Map jsonInfo(JSONObject obj) {
        JSONObject json = JSONObject.fromObject(obj);
        Map map = new HashMap();

        //json解析返回的pdu新设备信息数据
//    public List<PduSearch> tempPdu() {
//        ServerThread serverThread = new ServerThread();
//        List<PduSearch> searchList = new ArrayList();
//        List tempList = new ArrayList(serverThread.getList());
//
//        for (int i = 0; i < tempList.size(); i++) {
//            String data = (String) tempList.get(i);
//            JSONObject json = JSONObject.fromObject(data);
//            PduSearch pduSearch = new PduSearch();
//            pduSearch.setIp(json.getString("ip"));
//            pduSearch.setQrcode(json.getString("qrcode"));
//            pduSearch.setType(json.getString("type"));
//            searchList.add(pduSearch);
//        }
//        return searchList;
//    }

        map.put("access_token", json.getString("access_token"));
        map.put("group_id", json.getString("group_id"));
        map.put("devices_list", json.getString("devices_list"));

        return map;
    }


    /**
     * 方法描述：后台管理－设备新增　ｓａｖｅ
     * 作者：徐磊
     * 创建：2016-2-6
     * 修改：阚远锋 2018/4/10
     */
//    @RequiresPermissions(value = {"sys:pdu:save"})
    @RequestMapping("/pdusave")
    public void save(Model model, HttpServletRequest request, HttpServletResponse response, String ids) {

        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            System.out.println(df.format(System.currentTimeMillis()));
//        ModelAndView mv = this.getModelAndView();

            //添加一条或多条设备信息
//        PageData pd = new PageData();
//        pd = this.getPageData();
//            System.out.println("ids====" + ids);
//            String IDS = (String) request.getAttribute("ids");
//        String IDS = pd.getString("IDS");

            if (null != ids && !"".equals(ids)) { //新增新设备信息至pdu表

                String[] idsList = ids.split(",");
                //循环插入一条或多条设备记录
//                System.out.println("idslist==" + idsList.length);
                for (int i = 0; i < idsList.length; i++) {
                    String pduMachineID = idsList[i].toString();
//                    System.out.println("pduMachineID===" + pduMachineID);

                    //判断设备是否已经添加过,如果添加过则只是修改状态为1
                    pdu = pduService.selectByMachineID(pduMachineID);

                    if (null != pdu) {

                        pduService.updateStateView(pdu);//修改设备表状态为1 正常
                        pduTemporaryService.updateStateAdd(pduMachineID); //修改临时设备表状态为1 已添加
                        //添加该条设备到默认分组
                        PduGroupRelation pduGroupRelation = new PduGroupRelation();
                        pduGroupRelation.setPdugroupid(1);
                        pduGroupRelation.setPduid(pdu.getId());
                        pduGroupRelationService.savePduGroupRelation(pduGroupRelation);
                        //添加日志
                        LogUtil.addLog(request, "添加设备" + pdu.getName() + "到" + pduGroupService.findPduGroupById(1).getGroupname());

                    } else {//该设备没有添加过，作为新设备进行添加管理

                        //根据机器machineID 查询出设备其他的信息；
                        PduTemporary pduTemporary = new PduTemporary();
                        pduTemporary = pduTemporaryService.selectByPduTemporary(pduMachineID);

                        //插入到将新设备插入到pdu表中
                        Pdu pudNew = new Pdu();
                        /*pudNew.setMachineid(pduTemporary.getMachineid().toString() != "" ? pduTemporary.getMachineid().toString() : "null");
                        pudNew.setQrcode(pduTemporary.getQrcode().toString() != "" ? pduTemporary.getQrcode().toString() : "null");
                        pudNew.setIp(pduTemporary.getIp().toString() != "" ? pduTemporary.getIp().toString() : "null");
                        pudNew.setType(pduTemporary.getType().toString() != "" ? pduTemporary.getType().toString() : "null");*/
                        pudNew.setMachineid((null != pduTemporary.getMachineid() && StringUtils.isNotBlank(pduTemporary.getMachineid())) ? pduTemporary.getMachineid() : "null");
                        pudNew.setQrcode((null != pduTemporary.getQrcode() && StringUtils.isNotBlank(pduTemporary.getQrcode())) ? pduTemporary.getQrcode() : "null");
                        pudNew.setIp((null != pduTemporary.getIp() && StringUtils.isNotBlank(pduTemporary.getIp())) ? pduTemporary.getIp() : "null");
                        pudNew.setType((null != pduTemporary.getType() && StringUtils.isNotBlank(pduTemporary.getType())) ? pduTemporary.getType() : "null");
                        pudNew.setState("1");
                        pudNew.setCreateTime(df.format(System.currentTimeMillis()));
                        //主键返回
                        pduService.insert(pudNew);
                        //添加该条设备到默认分组
                        PduGroupRelation pduGroupRelation = new PduGroupRelation();
                        pduGroupRelation.setPdugroupid(1);
                        pduGroupRelation.setPduid(pudNew.getId());
                        pduGroupRelationService.savePduGroupRelation(pduGroupRelation);
                        //更新临时设备表
                        pduTemporaryService.updateStateAdd(pduMachineID);
                    }

                }
            }

            //刷新设备搜索表
            List<Pdu> pduList = pduService.selectAll();
            //刷新设备表显示新记录
            List<PduTemporary> pduSearchList = pduTemporaryService.selectAll();

            Map data = new HashMap();
            data.put("result", "success");
            Gson gson = new Gson();
            String gsonString = gson.toJson(data);
            response.getWriter().write(gsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        mv.addObject("pduList", pduList);//返回已添加设备列表
//        mv.addObject("pduSearchList", pduSearchList); //返回socket搜索设备

//        return mv;
//        return null;
    }

    /**
     * 方法描述：后台管理－设备删除　ｄｅｌｅｔｅ
     * 作者：徐磊
     * 创建：2016-2-6
     */
    @RequestMapping("/pdudelete")
    public void delete(HttpServletRequest request, HttpServletResponse response, String id) throws Exception {

        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            System.out.println(df.format(System.currentTimeMillis()));

            int ID = Integer.valueOf(id);
            pdu = pduService.selectByPrimaryKey(ID);
//        Pdu pdu = new Pdu();
            pduService.delete(pdu);
            pduTemporaryService.delete(pdu.getMachineid().toString());

            //刷新设备搜索表
            List<Pdu> pduList = pduService.selectAll();
            //刷新设备表显示新记录
            List<PduTemporary> pduSearchList = pduTemporaryService.selectAll();

            Map data = new HashMap();
            data.put("result", "success");
            Gson gson = new Gson();
            String gsonString = gson.toJson(data);
            response.getWriter().write(gsonString);
            //添加日志
            LogUtil.addLog(request, "删除设备：" + pdu.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 方法描述：后台管理－设备修改ｕｐｄａｔｅ
     * 作者：徐磊
     * 创建：2018-02-08
     */
    @RequestMapping("/pduupdate")
    public String update(HttpServletRequest request, Model model, Pdu pdu, Integer pdugroupid) throws Exception {
//        System.out.println(pdu.getId());
        try {
            pduService.update(pdu);
            //修改设备分组
            PduGroupRelation pduGroupRelation = pduGroupRelationService.findPduGroupRelationBypduId(pdu.getId());
            pduGroupRelation.setPdugroupid(pdugroupid);
            pduGroupRelationService.updateByPrimaryKeySelective(pduGroupRelation);
            //刷新设备搜索表
            List<Pdu> pduList = pduService.selectAll();
            //刷新设备表显示新记录
            List<PduTemporary> pduSearchList = pduTemporaryService.selectAll();

            model.addAttribute("pduList", pduList);
            model.addAttribute("pduSearchList", pduSearchList);
            //添加日志
            LogUtil.addLog(request, "修改设备：" + pdu.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "pdu_list";
    }

    /**
     * @Author:xulei
     * @Description:设备详情
     * @Date: 2018-02-24
     */
    @RequestMapping("/pduinfo")
    public String pduInfo(Model model, HttpServletRequest request, HttpServletResponse response, String id) throws Exception {

        int ID = Integer.valueOf(id);
        pdu = pduService.selectByPrimaryKey(ID);
        //根据设备id查找设备所在分组
        PduGroup pduGroup = pduGroupRelationService.findBypduId(ID).getPdugroup();
        //查找所有的分组
        List<PduGroup> pduGroupList = pduGroupService.selectALL();
        model.addAttribute("pdu", pdu);
        model.addAttribute("pduGroup", pduGroup);
        model.addAttribute("pduGroupList", pduGroupList);
        return "pdu_edit";
    }


    /**
     * @Author:xulei
     * @Description:设备控制，继电器开关控制 stock
     * @Date:2018-02-27
     */
    @RequestMapping("/pduOnOffAction")
    public String pduOnOffAction(HttpServletRequest request, HttpServletResponse response, String id, String actype) {

        //根据设备ID查找设备信息
        int ID = Integer.valueOf(id);
        pdu = pduService.selectByPrimaryKey(ID);
        String ip = pdu.getIp();

        Map data = new HashMap();
        Socket connection = null;
        try {
            //用户权限判断，是否有控制权限

            connection = (Socket) SubPolmap.get(ip); //获取socket连接对象
            //创建新的线程，发送报文到设备
            Thread pduOnOffthread = new Thread(new PduActionThread(connection, pdu, actype));
            pduOnOffthread.start();
            pduOnOffthread.join();
//            PduActionThread.pduRelayOffOn(connection, pdu, actype);

//            String actiontype = StringUtil.actionMessage(result,messageID,pdu);

            //判断是否控制成功
            if (pdu.getActionState().equals("1")) {//继电器状态0表示关闭 1表示开启
                pdu.setActionState("0");//如果原来的状态是1 就修改为0
            }

            if (pdu.getActionState().equals("0")) {
                pdu.setActionState("1");//如果原来的状态是0 就修改为1
            }

            if (actype.equals("1")) {
                System.out.println("发送继电器操作指令成功！");
                pduService.update(pdu);
                data.put("result", "success");
                //添加日志
                if (pdu.getActionState().equals("0")) {
                    LogUtil.addLog(request, "关闭设备：" + pdu.getName());
                } else if (pdu.getActionState().equals("1")) {
                    LogUtil.addLog(request, "打开设备：" + pdu.getName());
                }
            } else {
                System.out.println("发送继电器操作指令失败！");
                data.put("result", "error");
            }

            Gson gson = new Gson();
            String gsonString = gson.toJson(data);
            response.getWriter().write(gsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @Author:xulei
     * @Description:根据ID获取设备基本信息
     * @Date:208-03-06
     */
    @RequestMapping("/pduInfoRun")
    public String pduInfoRun(Model model, HttpServletRequest request, HttpServletResponse response, String id) throws Exception {
        //获取设备基本信息
        int ID = Integer.valueOf(id);
        pdu = pduService.selectByPrimaryKey(ID);
        model.addAttribute("pdu", pdu);

        //获取设备的运行数据
        return "pdu_info";
    }

    /**
     * @Author:xulei
     * @Description:加载设备运行状态
     * @Date:208-03-06
     */
    @RequestMapping("/pduInfoCharts")
    @ResponseBody
    public Object pduInfoCharts(Model model, HttpServletRequest request, HttpServletResponse response, String id) throws Exception {
        JSONObject json = null;
        int ID = Integer.valueOf(id);
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        //获取设备的运行数据
        List<PduInfo> infoList = new ArrayList<PduInfo>();
        infoList = pduInfoService.selectAllByPrimaryKey(ID);
        return infoList;
    }

    /**
     * @Author:xulei
     * @Description:APP设备修改接口
     * @Date: 2018-03-09
     */
    @RequestMapping("/jsonPduUpdate")
    @ResponseBody
    public JSONObject jsonPduUpdate(HttpServletRequest request, HttpServletResponse response, Pdu pdu) throws
            Exception {
        JSONObject json = null;
//        System.out.println(pdu.getId());
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        PrintWriter out = response.getWriter();
        json = new JSONObject();
//        JSONObject data = new JSONObject();

        try {
            pduService.update(pdu);
            json.put("code", 0);
            json.put("msg", "设备修改成功！");
            json.put("data", "null");
//            System.out.println(json);

            out.print(json);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }


    /**
     * @Author:xulei
     * @Description：触发场景，关闭设备继电器
     * @Date:2018-04-11
     */
    @RequestMapping("/pduOffAction")
    public static void pduOffAction(String id) {

        //根据设备ID查找设备信息
        int ID = Integer.valueOf(id);
        Pdu pduOff = new Pdu();
        PduService pduServiceTWO = (PduService) SpringApplicationContextHolder.getSpringBean("pduService");
        pduOff = pduServiceTWO.selectByPrimaryKey(ID);
        String ip = pduOff.getIp();

        Map data = new HashMap();
        Socket connection = null;
        try {
            //用户权限判断，是否有控制权限

            connection = (Socket) SubPolmap.get(ip); //获取socket连接对象
            //创建新的线程，发送报文到设备
            Thread thread = new Thread(new PduActionThread(connection, pduOff, "0"));
//            PduActionThread.pduRelayOffOn(connection, pdu, "0");
//            String actiontype = StringUtil.actionMessage(result,messageID,pdu);
            thread.start();
            thread.join();
            pduOff.setActionState("0");
            pduServiceTWO.update(pduOff);

            LogUtil.addLog("关闭设备:" + pduOff.getName());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @Author:xulei
     * @Description:空开网络拓扑结构计算，在第一次配置的过程中，通过控制插座继电器，计算出空开与插座的关联关系
     * @Date:2018-05-08
     * @param：设备列表
     * @return: 关系记录表  插座设备ID  空开设备ID
     * 前提：应用于空开和插座都是同一产品，设备部署完毕，空开和插座设备已经连接，并且全部是可控状态，该功能只能有系统管理员操作；
     */

    @RequestMapping("/jsonPduRelationShip")
    public void jsonPduRelationShip() throws InterruptedException {
        //第1步：监测一遍所有设备的通信状态，是否能收到心跳包，或者是采集信息操作
        List<Pdu> pduList = new ArrayList<Pdu>();
        pduList = pduService.selectAllByPduOnline();//
        int pdunum = pduList.size();
        int num = 0;
        Socket connection = null;
        PduController receiveFileStr = new PduController();
        for (int i = 0; i < pdunum; i++) {
            Pdu pdu = new Pdu();
            pdu = pduList.get(i);

            String ip = pdu.getIp();
            connection = (Socket) BaseController.SubPolmap.get(ip);

//            boolean result = true;
            Thread relationShipthread = new Thread(new PduRelationShipThread(connection, pdu, receiveFileStr));
//            Future resule = executorService.submit(new PduRelationShipThread(connection, pdu));


            relationShipthread.start();

            if (threadRsult.equals("")) {
                num++;
            } else {
                System.out.println("设备编号：" + threadRsult + "接收不到通信指令，请检查！");
            }
            relationShipthread.join();
        }

        if (num == pduList.size()) {
            System.out.println("所有上电设备通信正常！");


            //第2步：关闭所有空开

            for (int i = 0; i < pdunum; i++) {
                Pdu pdu = new Pdu();
                pdu = pduList.get(i);

                if (pdu.getType().equals("180")) {
                    String ip = pdu.getIp();
                    connection = (Socket) SubPolmap.get(ip); //获取socket连接对象
                    //创建新的线程，发送报文到设备
                    Thread pduOffthread = new Thread(new PduActionThread(connection, pdu, "0"));
                    pduOffthread.start();
                    pduOffthread.join();
                }
            }

            //第3步：开启一个空开  线路下得插座都会上电

            for (int i = 0; i < pdunum; i++) {
                Pdu pduOne = new Pdu();
                pduOne = pduList.get(i);

                if (pduOne.getType().equals("180")) {
                    String ip = pduOne.getIp();
                    Socket switchConnection = (Socket) SubPolmap.get(ip); //获取socket连接对象
                    //创建新的线程，发送报文到设备
                    Thread pduswitchOnthread = new Thread(new PduActionThread(switchConnection, pduOne, "1"));
                    pduswitchOnthread.start();
                    pduswitchOnthread.join();

                    //第4步：开启所有插座设备命令
                    Pdu notSwitchPdu = new Pdu();
                    notSwitchPdu.setType("180");

                    List<Pdu> pduUnitList = new ArrayList<Pdu>();
                    pduUnitList = pduService.selectAllByNotSwitch(notSwitchPdu);//返回所有的插座

                    for (int s = 0; s <= pduUnitList.size(); s++) {
                        Pdu pduTwo = new Pdu();
                        pduTwo = pduUnitList.get(s);

//                        String ip = pduTwo.getIp();
                        Socket PduOnconnection = (Socket) SubPolmap.get(pduTwo.getIp()); //获取socket连接对象
                        //创建新的线程，发送报文到设备
                        Thread pduOnthread = new Thread(new PduActionThread(PduOnconnection, pdu, "1"));
                        pduOnthread.start();
                        pduOnthread.join();

                    }

                    //第5步：筛选设备在线状态为1  并且继电器状态为1 的插座设备
                    List<Pdu> heartOnList = new ArrayList<Pdu>();
                    pduUnitList = pduService.selectAllByNotSwitch(notSwitchPdu);//返回所有的插座

                    for (int y = 0; y <= pduUnitList.size(); y++) {
                        Pdu pduThree = new Pdu();
                        pduThree = pduUnitList.get(y);

                        if (pduThree.getOnlinestate().equals("1") && pduThree.getActionState().equals("1")) {
                            heartOnList.add(pduThree);
                        }
                    }

//                    for(int s = 0 ; s <= pduUnitList.size(); s++) {
//                        Pdu pduThree = new Pdu();
//                        pduThree = pduUnitList.get(s);
//
//                        Socket heartConnection = (Socket) SubPolmap.get(pduThree.getIp()); //获取socket连接对象
//                        //创建新的线程，发送报文到设备
//                        Thread pduHeartBeatThread = new Thread(new PduHeartBeatThread(heartConnection,pdu,receiveFileStr));
//                        pduHeartBeatThread.start();
//
//                        if(!threadRsult.equals("")){
//                            heartOnList.add(pduThree);
//                        }
//
//                        pduHeartBeatThread.join();
//
//                    }
                    //第6步： 收到命令的插座设备,则可以保存为关联的插座设备，数据库插入关联设备；
                    for (int h = 0; h < heartOnList.size(); h++) {
                        PduRelation pduRelation = new PduRelation();
                        Pdu pduFour = new Pdu();
                        pduFour = heartOnList.get(h);

                        pduRelation.setPduID(pduFour.getId());//插座设备ID
                        pduRelation.setSwitchPduID(pduOne.getId());//空开设备ID

                        //判断关系表是否存在插座ID  一个插座只能属于一个空开
                        PduRelation pduRelationOne = pduRelationService.selectByPrimaryKey(pduFour.getId());

                        if (pduRelationOne != null) {

                            //修改设备关系
                            pduRelationService.updateByPrimaryKey(pduRelation);
                        } else {

                            //新增设备关系信息
                            pduRelationService.insert(pduRelation);
                        }

                    }

                    //第7步：关闭空开
                    Thread pduswitchOffthread = new Thread(new PduActionThread(switchConnection, pduOne, "0"));
                    pduswitchOffthread.start();
                    pduswitchOffthread.join();
                }
            }


            //第8步：循环开启下一个空开

            //重复第4、5、6、7步的操作
        }
    }

    public String receiveFileStr(String result) {
//        StringBuffer sb = new StringBuffer();
//        sb.append(result );
//        System.out.println(sb.toString());
        threadRsult = result;
        return threadRsult;
    }


    /**
     * @Author:xulei
     * @Description:APP移动端通信Socket
     * @Date:2018-05-14
     */

    @RequestMapping("/jsonAPPSocket")
    public static void jsonAPPSocket() {
        ServerSocket serverSocket = null;
//        定义一个容量为50的线程
        ExecutorService executorService = Executors.newFixedThreadPool(50);

        boolean ifport = NetUtil.isLoclePortUsing(10888);
        if (!ifport) {
            try {
                serverSocket = new ServerSocket(10888);

                while (true) {
                    System.out.println("开启APP连接");
                    //接收客户端连接的socket对象
                    Socket connectionApp = null;
                    //接收客户端传过来的数据，会阻塞
                    connectionApp = serverSocket.accept();
//                    System.out.println("ip==" + connectionApp.getInetAddress() + ":" + connectionApp.getPort());
                    String connectionIp = connectionApp.getInetAddress().toString();

                    if (!connectionIp.equals("/127.0.0.1")) {
                        System.out.println("处理连接请求");
                        //map保存设备连接对象
//                    String conName = connectionApp.getInetAddress().toString();
//                    conName = conName.substring(1, conName.length());
//                        Thread threadsApp = new Thread(new AppCommunicationThread(connectionApp));
//                        long threadid = threadsApp.getId();
//                    SubPolmap.put(threadid, connectionApp);
                        executorServiceAppSocket.submit(new AppCommunicationThread(connectionApp));
//                        threadsApp.start();
//                    threads.join();
//                    Thread.sleep(2000);

                    } else {
//                connection.close();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
//                if (serverSocket != null) {
//                    try {
//                        serverSocket.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        }
//        return null;
    }


    /**
     * @Author:
     * @Description:开启线程池，接收设备的链接请求；
     * @Date:
     */
    /**
//    @RequestMapping("/jsonPdutest")
//    @Scheduled(fixedDelay = 1000*5)
    public static void jsonPdutest() {
        ServerSocket serverSocket = null;
//        定义一个容量为50的线程
//        ExecutorService executorService = Executors.newFixedThreadPool(50);
//        boolean ifport = NetUtil.isLoclePortUsing(8899);
//        if (!ifport) {
        try {
            serverSocket = new ServerSocket(8899);
            int counter = 1;
            while (true) {
                //接收客户端连接的socket对象
                Socket connection = null;
                //接收客户端传过来的数据，会阻塞
                connection = serverSocket.accept();

                String connectionIp = connection.getInetAddress().toString();
//                    BaseController.SubPolmap.put(connectionIp.substring(1,connectionIp.length()), connection);
                if (!connectionIp.equals("/127.0.0.1")) {
                    System.out.println("第 " + (counter++) + " 个连接" + " ip==" + connection.getInetAddress());
                    //map保存设备连接对象
                    String conName = connection.getInetAddress().toString();
                    conName = conName.substring(1, conName.length());
                    BaseController.SubPolmap.put(conName, connection);
                    Thread threads = new Thread(new SubPolThread(connection));

                    threads.start();
//                    executorService.submit(new SubPolThread(connection));

                } else {
//                connection.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
**/





}

