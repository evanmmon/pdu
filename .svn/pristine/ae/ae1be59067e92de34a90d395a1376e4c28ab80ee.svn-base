package com.chuangkou.pdu.controller;

import com.chuangkou.pdu.entity.Pdu;
import com.chuangkou.pdu.entity.PduTemporary;
import com.chuangkou.pdu.entity.PduWarningSet;
import com.chuangkou.pdu.service.PduService;
import com.chuangkou.pdu.service.PduTemporaryService;
import com.chuangkou.pdu.service.PduWarningSetService;
import com.chuangkou.pdu.thread.JobPduSetThread;
import com.chuangkou.pdu.util.LogUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:xulei
 * @Description:预警管理
 * @Date:Created in 15:28 2018/2/26
 */
@Controller
public class WarningController extends BaseController {

    Pdu pdu = new Pdu();
    PduWarningSet pduWarningSet = new PduWarningSet();

    @Resource(name = "pduService")
    private PduService pduService;

    @Resource(name = "pduTemporaryService")
    private PduTemporaryService pduTemporaryService;

    @Resource(name = "pduWarningSetService")
    private PduWarningSetService pduWarningSetService;

    public Pdu getPdu() {
        return pdu;
    }

    public void setPdu(Pdu pdu) {
        this.pdu = pdu;
    }

    public PduWarningSet getPduWarningSet() {
        return pduWarningSet;
    }

    public void setPduWarningSet(PduWarningSet pduWarningSet) {
        this.pduWarningSet = pduWarningSet;
    }

    /**
     * @Author:xulei
     * @Description:设备预警信息值获取
     * @Date: 2018-02-26
     */
    @RequestMapping("/pduWarninginfo")
    public String pduWarninginfo(Model model, HttpServletRequest request, HttpServletResponse response, String id) {

        try {
            //根据设备的ID，获取设备的预警设置信息，如果没有预警设置信息，则没有信息返回，跳转到空白页面；
            int ID = Integer.valueOf(id);
            PduWarningSet pduWarningSet = new PduWarningSet();
            pduWarningSet = pduWarningSetService.selectByPduWarningSet(ID);

            pdu = pduService.selectByPrimaryKey(ID);

            //判断是否已经设置了设备预警值记录
            if (null == pduWarningSet) {
                PduWarningSet pduWarningSetTemp = new PduWarningSet();
                pduWarningSetTemp.setPduid(pdu.getId());
                pduWarningSetTemp.setVoltage("");
                pduWarningSetTemp.setCurrent("");
                pduWarningSetTemp.setWatt("");
                pduWarningSetTemp.setRelaystate("0");
                pduWarningSetTemp.setOvervoltage("0");
                pduWarningSetTemp.setUndervoltage("0");
                pduWarningSetTemp.setOvercurrent("0");
                pduWarningSetTemp.setCircuitbreaker("0");
                pduWarningSetTemp.setElectricleakage("0");
                model.addAttribute("pduWarningSet", pduWarningSetTemp);
            } else {
                model.addAttribute("pduWarningSet", pduWarningSet);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        model.addAttribute("pdu", pdu);
        return "pdu_warning_set";
    }

    /**
     * @Author:xulei
     * @Description:设备预警设置
     * @Date: 2018-02-26
     */
    @RequestMapping("/pduWarningSet")
    public String pduWarningSet(Model model, HttpServletRequest request, HttpServletResponse response, PduWarningSet pduWarningSet) {

        //根据设备的ID，保存预警设置值；
        try {


            int ID = pduWarningSet.getPduid();
            //发送预警设置报文
            sendWarningSet(pduWarningSet);

            PduWarningSet pduWarningSetTemp = new PduWarningSet();
            pduWarningSetTemp = pduWarningSetService.selectByPduWarningSet(ID);

            if (null == pduWarningSetTemp) {

                //如果没有设置过的设备，添加一条新记录
                int r = pduWarningSetService.insertSelective(pduWarningSet);
                //添加日志
                LogUtil.addLog(request,"添加设备预警");
                System.out.println("设备预警设置添加成功！");
            } else {

                //如果是已经设置过的设备，更新改记录
                pduWarningSetService.updateByPduWarningSet(pduWarningSet);
                //添加日志
                LogUtil.addLog(request,"修改设备预警");
                System.out.println("设备预警设置修改成功！");
            }
            //获取已经添加的设备list
            List<Pdu> pduList = pduService.selectAll();
            List<PduTemporary> pduSearchList = pduTemporaryService.selectAll();

            model.addAttribute("pduList", pduList);
            model.addAttribute("pduSearchList", pduSearchList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "pdu_list";
    }

    /**
     * @Author:
     * @Description:
     * @Date:
     */
    public void sendWarningSet(PduWarningSet pduWarningSet) {

        Pdu pduset = new Pdu();
        pduset = pduService.selectByPrimaryKey(pduWarningSet.getPduid());

        String machineID = pduset.getMachineid();
        String conName = pduset.getIp();
        Socket pduinfoCon = null;
        //获取连接对象
        pduinfoCon = (Socket) SubPolmap.get(conName);

        if (pduinfoCon != null) {
            //创建一个线程，发送报文
            Thread thread = new Thread(new JobPduSetThread(pduinfoCon, pduWarningSet,pduset));

        }

    }

    /**
     * @Author:xulei
     * @Description:APP接口 获取设备预警设置
     * @Date:2018-02-27
     */
    @RequestMapping("/jsonPduWarningSetInfo")
    @ResponseBody
    public JSONObject jsonPduWarningSetInfo(HttpServletRequest request, HttpServletResponse response, String pduid) throws Exception {
        JSONObject json = null;
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=UTF-8");

            int ID = Integer.valueOf(pduid);
            pduWarningSet = new PduWarningSet();
            pduWarningSet = pduWarningSetService.selectByPduWarningSet(ID);

            PrintWriter out = response.getWriter();
            json = new JSONObject();
            JSONObject data = new JSONObject();

            //判断是否已经设置了设备预警值记录
            if (pduWarningSet == null) {
                PduWarningSet pduWarningSetTemp = new PduWarningSet();
                pduWarningSetTemp.setPduid(pdu.getId());
                pduWarningSetTemp.setVoltage("");
                pduWarningSetTemp.setCurrent("");
                pduWarningSetTemp.setWatt("");
                pduWarningSetTemp.setRelaystate("0");
                pduWarningSetTemp.setOvervoltage("0");
                pduWarningSetTemp.setUndervoltage("0");
                pduWarningSetTemp.setOvercurrent("0");
                pduWarningSetTemp.setCircuitbreaker("0");
                pduWarningSetTemp.setElectricleakage("0");
                data.put("pduWarningSet", pduWarningSetTemp);
            } else {
                data.put("pduWarningSet", pduWarningSet);
            }

//            data.put("pduWarningSet", pduWarningSet);

            json.put("code", 0);
            json.put("msg", "可添加设备列表！");
            json.put("data", data);

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
     * @Author: xulei
     * @Description: app接口 新增修改设备预警设置
     * @Date:2018-02-27
     */
    @RequestMapping("/jsonPduWarningSetUpdate")
    @ResponseBody
    public JSONObject jsonPduWarningSetUpdate(HttpServletRequest request, HttpServletResponse response, String PduWarningSetinfo) throws Exception {

        JSONObject json = null;

        JSONObject objs = new JSONObject();
        objs.put("access_token", "7141B356967436617B3B6D0C9690D7A9");
        objs.put("group_id", "0");
        objs.put("WarningSetinfo", "{'pduid':'4','voltage':'220','current':'10','watt':'800','relayState':'0','overvoltage':'1','undervoltage':'1','overcurrent':'1','circuitbreaker':'1','electricleakage':'1'}");

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(df.format(System.currentTimeMillis()));

        Map map = new HashMap();
        map = jsonInfo(objs);//返回json数据

        String access_token = map.get("access_token").toString();
        String group_id = map.get("group_id").toString();
        String WarningSetinfo = map.get("WarningSetinfo").toString();

        JSONArray jsonArray = JSONArray.fromObject(WarningSetinfo);
        int pduid = Integer.valueOf(jsonArray.getJSONObject(0).getString("pduid"));

        pduWarningSet.setPduid(pduid);
        pduWarningSet.setVoltage(jsonArray.getJSONObject(0).getString("voltage"));
        pduWarningSet.setCurrent(jsonArray.getJSONObject(0).getString("current"));
        pduWarningSet.setWatt(jsonArray.getJSONObject(0).getString("watt"));
        pduWarningSet.setRelaystate(jsonArray.getJSONObject(0).getString("relayState"));
        pduWarningSet.setOvervoltage(jsonArray.getJSONObject(0).getString("overvoltage"));
        pduWarningSet.setUndervoltage(jsonArray.getJSONObject(0).getString("undervoltage"));
        pduWarningSet.setOvercurrent(jsonArray.getJSONObject(0).getString("overcurrent"));
        pduWarningSet.setCircuitbreaker(jsonArray.getJSONObject(0).getString("circuitbreaker"));
        pduWarningSet.setElectricleakage(jsonArray.getJSONObject(0).getString("electricleakage"));

        PduWarningSet pduWarningSetTemp = new PduWarningSet();
        pduWarningSetTemp = pduWarningSetService.selectByPduWarningSet(pduid);

        if (pduWarningSetTemp == null) {
            //如果没有设置过的设备，添加一条新记录
            int r = pduWarningSetService.insertSelective(pduWarningSet);
            System.out.println("设备预警设置添加成功！");
        } else {
            //如果是已经设置过的设备，更新改记录
            pduWarningSetService.updateByPduWarningSet(pduWarningSet);
            System.out.println("设备预警设置修改成功！");
        }

        json = new JSONObject();
        JSONObject data = new JSONObject();
        PrintWriter out = response.getWriter();

        data.put("WarningSetinfo", WarningSetinfo);
        json.put("code", 0);
        json.put("msg", "添加修改成功！");
        json.put("data", data);

        System.out.println(json);
        out.print(json);
        out.flush();
        out.close();
        return json;
    }

    //json解析信息数据
    public Map jsonInfo(JSONObject obj) {

        JSONObject json = JSONObject.fromObject(obj);
        Map map = new HashMap();

        map.put("access_token", json.getString("access_token"));
        map.put("group_id", json.getString("group_id"));
        map.put("WarningSetinfo", json.getString("WarningSetinfo"));

        return map;
    }

}
