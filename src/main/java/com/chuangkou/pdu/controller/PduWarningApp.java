package com.chuangkou.pdu.controller;

import com.chuangkou.pdu.bean.*;
import com.chuangkou.pdu.entity.*;
import com.chuangkou.pdu.service.*;
import com.chuangkou.pdu.thread.PduActionThread;
import com.chuangkou.pdu.thread.PduWarningSetThread;
import com.chuangkou.pdu.thread.ThreadUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PduWarningApp {
    @Autowired
    private PduWarningService pduWarningService;
    @Autowired
    private PduWarningSetService pduWarningSetService;
    @Autowired
    private PduGroupRelationService pduGroupRelationService;
    @Autowired
    private AppToken appToken;

    @Autowired
    private PduService pduService;

    /**
     * 设置预警处理状态
     * 刘哲
     * 18/3/21
     */
    @RequestMapping("/warning/set_warning_processing_state")
    public void SelectPduGrouplist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        System.out.println("设置预警状态 ！");
        String access_token;
        String warning_id;
        try {
            PduWarning pduWarning = new PduWarning();
            //读取“token”
            String token = req.getHeader("access_token");
            Token apptokenyanzheng = appToken.apptokenyanzheng(token);
            if (!apptokenyanzheng.getJieguo()) {
                PrintWriter out = resp.getWriter();
                MsgBean msgBean = MsgBean.getFalseInstance("token失效");
                msgBean.setCode(403);
                out.print(msgBean.toJsonString());
                out.flush();
                out.close();
            } else {
                //读取“预警id”
                warning_id = req.getParameter("warning_id");
                int WarningId = Integer.parseInt(warning_id);


                pduWarning.setId(WarningId);
                //获取预警状态
                pduWarning.setState(req.getParameter("warning_state"));
                PrintWriter out = resp.getWriter();
                //根据预警id 和 状态更新
                pduWarningService.update(pduWarning);
                out.print(MsgBean.getInstance().toJsonString());
                out.flush();
                out.close();
//        }


            }
        } catch (Exception e) {
            PrintWriter out = resp.getWriter();
            MsgBean msgBean = MsgBean.getFalseInstance("设置失败");
            out.print(msgBean.toJsonString());
            out.flush();
            out.close();

        }
    }

    /**
     * 设置预警开关
     * 刘哲
     * 18/4/12
     */
    @RequestMapping("/warning/set_device_warning_switch_state")
    public void SelectPduGroup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        System.out.println("预警开关 ！");
        String access_token;
        String warning_id;
        try {
            PduWarningSet pduWarningSet = new PduWarningSet();

            //读取“token”
            String token = req.getHeader("access_token");
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
                //读取“设备id”
                pduWarningSet.setPduid(Integer.parseInt(req.getParameter("device_id")));
                //读取“设备状态”
                int type = Integer.parseInt(req.getParameter("type"));
                String state = req.getParameter("state");
                //  预警类型,0漏电,1断路,2功率,3电压,4过流

                PduWarningSet device_id = pduWarningSetService.findPduWarningSetById(Integer.parseInt(req.getParameter("device_id")));

                if (null == device_id) {
                    switch (type) {
                        case 0:
                            pduWarningSet.setElectricleakage(state);
                            pduWarningSetService.savePduWarningSetr(pduWarningSet);
                            break;
                        case 1:
                            pduWarningSet.setCircuitbreaker(state);
                            pduWarningSetService.savePduWarningSetr(pduWarningSet);
                            break;
                        case 2:
                            pduWarningSet.setPowerremind(state);

                            pduWarningSetService.savePduWarningSetr(pduWarningSet);
                            break;
                        case 3:
                            pduWarningSet.setOvervoltage(state);
                            pduWarningSet.setUndervoltage(state);
                            pduWarningSetService.savePduWarningSetr(pduWarningSet);
                            break;
                        case 5:
                            pduWarningSet.setOvercurrent(state);
                            pduWarningSetService.savePduWarningSetr(pduWarningSet);
                            break;
                        case 6:
                            pduWarningSet.setElectricityRemind(Integer.valueOf(state));
                            pduWarningSetService.savePduWarningSetr(pduWarningSet);
                            break;
                        case 7:
                            pduWarningSet.setTemperatureRemind(Integer.valueOf(state));
                            pduWarningSetService.savePduWarningSetr(pduWarningSet);
                            break;
                        case 8:
                            pduWarningSet.setPoorContactRemind(Integer.valueOf(state));
                            pduWarningSetService.savePduWarningSetr(pduWarningSet);
                            break;

                    }
                } else {
                    PduWarningSet pduWarningSetUpdate = new PduWarningSet();
                    pduWarningSetUpdate = pduWarningSetService.selectByPduWarningSet(pduWarningSet.getPduid());
                    switch (type) {
                        case 0:
                            pduWarningSetUpdate.setElectricleakage(state);
                            pduWarningSetService.update(pduWarningSetUpdate);
                            break;
                        case 1:
                            pduWarningSetUpdate.setCircuitbreaker(state);
                            pduWarningSetService.update(pduWarningSetUpdate);
                            break;
                        case 2:
                            pduWarningSetUpdate.setPowerremind(state);

                            pduWarningSetService.update(pduWarningSetUpdate);
                            break;
                        case 3:
                            pduWarningSetUpdate.setOvervoltage(state);
                            pduWarningSetUpdate.setUndervoltage(state);
                            pduWarningSetService.update(pduWarningSetUpdate);
                            break;
                        case 5:
                            pduWarningSetUpdate.setOvercurrent(state);
                            pduWarningSetService.update(pduWarningSetUpdate);
                            break;

                        case 6:
                            pduWarningSetUpdate.setElectricityRemind(Integer.valueOf(state));
                            pduWarningSetService.update(pduWarningSetUpdate);
                            break;
                        case 7:
                            pduWarningSetUpdate.setTemperatureRemind(Integer.valueOf(state));
                            pduWarningSetService.update(pduWarningSetUpdate);
                            break;
                        case 8:
                            pduWarningSetUpdate.setPoorContactRemind(Integer.valueOf(state));
                            pduWarningSetService.update(pduWarningSetUpdate);
                            break;
//            case 5:
//                pduWarningSet.setOvercurrent(state);
//                pduWarningSetService.update(pduWarningSet);
//                break;

                    }
                }

                PrintWriter out = resp.getWriter();
                out.print(MsgBean.getInstance().toJsonString());
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            PrintWriter out = resp.getWriter();
            MsgBean msgBean = MsgBean.getFalseInstance("设置失败");
            out.print(msgBean.toJsonString());
            out.flush();
            out.close();

        }

    }

    /**
     * 设置预警值
     * 刘哲
     * 18/4/12
     */
    @RequestMapping("/warning/set_device_warning_value")
    public void SelectPdu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        System.out.println("预警值  ！");
        String access_token;
        String warning_id;

        try {
            PduWarningSet pduWarningSet = new PduWarningSet();

            //读取“token”
//            String token = req.getHeader("access_token");
//            Token apptokenyanzheng = appToken.apptokenyanzheng(token);
//            if (!apptokenyanzheng.getJieguo()) {
//                PrintWriter out = resp.getWriter();
//                MsgBean msgBean = MsgBean.getFalseInstance("token失效");
//                msgBean.setCode(403);
//                out.print(msgBean.toJsonString());
//                out.flush();
//                out.close();
//            } else {

            //读取“设备id”
            pduWarningSet.setPduid(Integer.parseInt(req.getParameter("device_id")));
            //读取“设备状态”
            int type = Integer.parseInt(req.getParameter("type"));
            String state = req.getParameter("value");
            float v = Float.parseFloat(state);
            boolean status = state.contains(".");
            int vtime = 0;
            if (!status) {
                vtime = Integer.parseInt(state);
            }
            //  预警类型,【0漏电,1断路】,2功率,3电压,4过流

            PduWarningSet device_id = pduWarningSetService.findPduWarningSetById(Integer.parseInt(req.getParameter("device_id")));

            if (null == device_id) {
                switch (type) {
                    case 2://功率
                        pduWarningSet.setWattamplitude(v);
                        pduWarningSetService.savePduWarningSetr(pduWarningSet);
                        break;
                    case 3://过欠压
                        pduWarningSet.setSetingvoltage(v);
                        pduWarningSetService.savePduWarningSetr(pduWarningSet);
                        break;
                    case 5://过流
                        pduWarningSet.setCurrentamplitude(v);
                        pduWarningSetService.savePduWarningSetr(pduWarningSet);
                        break;

                    //新增预警设置
                    case 6:  // 功率启动延时时间
                        pduWarningSet.setPowerStartDelay((int) v);
                        pduWarningSetService.savePduWarningSetr(pduWarningSet);
                        break;
                    case 7:// 	功率恢复延时
                        pduWarningSet.setPowerResumeDelay((int) v);
                        pduWarningSetService.savePduWarningSetr(pduWarningSet);
                        break;
                    case 8:// 	过欠压启动延时
                        pduWarningSet.setVoltageStartDelay((int) v);
                        pduWarningSetService.savePduWarningSetr(pduWarningSet);
                        break;
                    case 9:// 	过欠压恢复延时
                        pduWarningSet.setVoltageResumedDelay((int) v);
                        pduWarningSetService.savePduWarningSetr(pduWarningSet);
                        break;

                    case 10:// 	电流启动延时
                        pduWarningSet.setCurrentStartDelay((int) v);
                        pduWarningSetService.savePduWarningSetr(pduWarningSet);
                        break;
                    case 11:// 	电流恢复延时
                        pduWarningSet.setCurrentResumeDelay((int) v);
                        pduWarningSetService.savePduWarningSetr(pduWarningSet);
                        break;
                    case 12:// 	电量提醒
                        pduWarningSet.setLowerLimitQuantity(v);
                        pduWarningSetService.savePduWarningSetr(pduWarningSet);
                        break;

                    case 13:// 	线温阀值
                        pduWarningSet.setTemperatureAmplitude(v);
                        pduWarningSetService.savePduWarningSetr(pduWarningSet);
                        break;
                    case 14:// 	线温启动延时
                        pduWarningSet.setTemperatureStartDelay(vtime);
                        pduWarningSetService.savePduWarningSetr(pduWarningSet);
                        break;
                    case 15:// 	线温恢复延时
                        pduWarningSet.setTemperatureResumeDelay(vtime);
                        pduWarningSetService.savePduWarningSetr(pduWarningSet);
                        break;
                    case 16:// 	接触不良探查间隔
                        pduWarningSet.setPoorContact((int) v);
                        pduWarningSetService.savePduWarningSetr(pduWarningSet);
                        break;
                }


            } else {
                PduWarningSet pduWarningSetUpdate = new PduWarningSet();
                pduWarningSetUpdate = pduWarningSetService.selectByPduWarningSet(pduWarningSet.getPduid());

                switch (type) {
                    case 2:
                        pduWarningSetUpdate.setWattamplitude(v);
                        pduWarningSetService.update(pduWarningSetUpdate);
                        break;
                    case 3:
                        pduWarningSetUpdate.setSetingvoltage(v);
                        pduWarningSetService.update(pduWarningSetUpdate);
                        break;
                    case 5:
                        pduWarningSetUpdate.setCurrentamplitude(v);
                        pduWarningSetService.update(pduWarningSetUpdate);
                        break;

                    //新增预警设置
                    case 6:  // 功率启动延时时间

                        pduWarningSetUpdate.setPowerStartDelay((int) v);
                        pduWarningSetService.update(pduWarningSetUpdate);
                        break;
                    case 7:// 	功率恢复延时
                        pduWarningSetUpdate.setPowerResumeDelay((int) v);
                        pduWarningSetService.update(pduWarningSetUpdate);
                        break;
                    case 8:// 	过欠压启动延时
                        pduWarningSetUpdate.setVoltageStartDelay((int) v);
                        pduWarningSetService.update(pduWarningSetUpdate);
                        break;
                    case 9:// 	过欠压恢复延时
                        pduWarningSetUpdate.setVoltageResumedDelay((int) v);
                        pduWarningSetService.update(pduWarningSetUpdate);
                        break;
                    case 10:// 	电流启动延时
                        pduWarningSetUpdate.setCurrentStartDelay((int) v);
                        pduWarningSetService.update(pduWarningSetUpdate);
                        break;
                    case 11:// 	电流恢复延时
                        pduWarningSetUpdate.setCurrentResumeDelay((int) v);
                        pduWarningSetService.update(pduWarningSetUpdate);
                        break;
                    case 12:// 	电量下限
                        pduWarningSetUpdate.setLowerLimitQuantity(v);
                        pduWarningSetService.update(pduWarningSetUpdate);
                        break;

                    case 13:// 	线温阀值
                        pduWarningSetUpdate.setTemperatureAmplitude(v);
                        pduWarningSetService.update(pduWarningSetUpdate);
                        break;
                    case 14:// 	线温启动延时
                        pduWarningSetUpdate.setTemperatureStartDelay(vtime);
                        pduWarningSetService.update(pduWarningSetUpdate);
                        break;
                    case 15:// 	线温恢复延时
                        pduWarningSetUpdate.setTemperatureResumeDelay(vtime);
                        pduWarningSetService.update(pduWarningSetUpdate);
                        break;
                    case 16:// 	接触不良探查间隔
                        pduWarningSetUpdate.setPoorContact((int) v);
                        pduWarningSetService.update(pduWarningSetUpdate);
                        break;
                }
            }

            PrintWriter out = resp.getWriter();
            out.print(MsgBean.getInstance().toJsonString());
            out.flush();
            out.close();

            //设置设备预警信息 新建一个线程
            Pdu warningPdu = new Pdu();
            warningPdu = pduService.selectByPrimaryKey(pduWarningSet.getPduid());
            String ip = warningPdu.getIp();
            Socket connection = null;
            connection = (Socket) BaseController.SubPolmap.get(ip); //获取socket连接对象

            Thread pduOnOffthread = new Thread(new PduWarningSetThread(connection, pduWarningSet, type));
            pduOnOffthread.start();
            pduOnOffthread.join();

//        }
//            }
        } catch (Exception e) {
            e.printStackTrace();
            PrintWriter out = resp.getWriter();
            MsgBean msgBean = MsgBean.getFalseInstance("设置失败");
            out.print(msgBean.toJsonString());
            out.flush();
            out.close();

        }

    }

    /**
     * 获取设备预警设置
     * 刘哲
     * 18/4/12
     */
    @RequestMapping("/warning/get_device_warning_options")
    public void Select(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");

        String access_token;
        String device_id;
        //读取“token”
        String token = req.getHeader("access_token");
//        String token = req.getHeader("access_token");
//        token = "admin,21232F297A57A5A743894A0E4A801FC3,1528077189636";
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

                //读取“设备id”
                int id = Integer.parseInt(req.getParameter("device_id"));
                //查询数据

                PduWarningSet warningSetById = pduWarningSetService.findPduWarningSetById(id);
                PduWarningBean pduWarningBean = new PduWarningBean();
                MsgBean<PduWarningBean> pduWarningBeanMsgBean = new MsgBean<PduWarningBean>();
                if (warningSetById != null) {
                    pduWarningBean.setElectric_leakage_remind(warningSetById.getElectricleakage());//漏电
                    pduWarningBean.setOpen_circuit_remind(warningSetById.getCircuitbreaker());//断路
                    pduWarningBean.setPower_reference(warningSetById.getWatt());//功率参考值
//            pduWarningBean.setPower_type(warningSetById.getPowertype());
                    pduWarningBean.setVoltage_reference(warningSetById.getVoltage());//电压参考值
                    pduWarningBean.setCurrent_reference(warningSetById.getCurrent());//电流参考值

                    pduWarningBean.setPower_remind(warningSetById.getPowerremind()); //功率提醒
                    pduWarningBean.setVoltage_remind(warningSetById.getOvervoltage());//过压提醒
                    pduWarningBean.setCurrent_remind(warningSetById.getOvercurrent());//过流提醒

                    pduWarningBean.setPower_range(warningSetById.getWattamplitude());   //功率幅度
                    pduWarningBean.setPower_start_delay(warningSetById.getPowerStartDelay());//延时启动时间
                    pduWarningBean.setPower_resume_delay(warningSetById.getPowerResumeDelay());//延时恢复时间

                    pduWarningBean.setVoltage_range(warningSetById.getSetingvoltage()); //电压幅度
                    pduWarningBean.setVoltage_start_delay(warningSetById.getVoltageStartDelay());
                    pduWarningBean.setVoltage_resume_delay(warningSetById.getVoltageResumedDelay());

                    pduWarningBean.setCurrent_range(warningSetById.getCurrentamplitude());  //电流幅度
                    pduWarningBean.setCurrent_start_delay(warningSetById.getCurrentStartDelay());
                    pduWarningBean.setCurrent_resume_delay(warningSetById.getCurrentResumeDelay());

                    pduWarningBean.setElectricity_remind(warningSetById.getElectricityRemind());
                    pduWarningBean.setElectricity_value((double) warningSetById.getLowerLimitQuantity());

                    pduWarningBean.setTemperature_remind(warningSetById.getTemperatureRemind());
//                    pduWarningBean.setTemperature_range(Math.round(warningSetById.getTemperatureAmplitude()));
                    pduWarningBean.setTemperature_range(0);
                    pduWarningBean.setTemperature_start_delay(warningSetById.getTemperatureStartDelay());
                    pduWarningBean.setTemperature_resume_delay(warningSetById.getTemperatureResumeDelay());

                    pduWarningBean.setPoor_contact_remind(warningSetById.getPoorContactRemind());
                    pduWarningBean.setPoor_contact_delay(warningSetById.getPoorContact());

                    pduWarningBeanMsgBean.setData(pduWarningBean);
                    pduWarningBeanMsgBean.setMsg("获取成功");
                } else {
                    pduWarningBean.setElectric_leakage_remind("0");//漏电
                    pduWarningBean.setOpen_circuit_remind("0");//断路
                    pduWarningBean.setPower_reference("0.0");//功率参考值
//            pduWarningBean.setPower_type(warningSetById.getPowertype());
                    pduWarningBean.setVoltage_reference("0.0");//电压参考值
                    pduWarningBean.setCurrent_reference("0.0");//电流参考值
                    pduWarningBean.setPower_remind("0"); //功率提醒
                    pduWarningBean.setVoltage_remind("0");//过压提醒
                    pduWarningBean.setCurrent_remind("0");//过流提醒

                    pduWarningBean.setPower_range(Float.valueOf("0.0"));   //功率幅度
                    pduWarningBean.setPower_start_delay(0);
                    pduWarningBean.setPower_resume_delay(0);

                    pduWarningBean.setVoltage_range(Float.valueOf("0.0")); //电压幅度
                    pduWarningBean.setVoltage_start_delay(0);
                    pduWarningBean.setVoltage_resume_delay(0);

                    pduWarningBean.setCurrent_range(Float.valueOf("0.0"));  //电流幅度
                    pduWarningBean.setCurrent_start_delay(0);
                    pduWarningBean.setCurrent_resume_delay(0);

                    pduWarningBean.setElectricity_remind(0);
                    pduWarningBean.setElectricity_value(0.0);

                    pduWarningBean.setTemperature_remind(0);
                    pduWarningBean.setTemperature_range(0);
                    pduWarningBean.setTemperature_start_delay(0);
                    pduWarningBean.setTemperature_resume_delay(0);

                    pduWarningBean.setPoor_contact_remind(0);
                    pduWarningBean.setPoor_contact_delay(0);

//            pduWarningBean.setElectric_leakage_remind("0");
//            pduWarningBean.setShort_circuit_remind("0");
//            pduWarningBean.setPower_remind("0");
//            pduWarningBean.setPower_type("0");
//            pduWarningBean.setPower_value("0");
//            pduWarningBean.setOver_voltage_remind("");
//            pduWarningBean.setOver_voltage_value(Float.valueOf("0.0"));
//            pduWarningBean.setUnder_voltage_remind("0");
//            pduWarningBean.setUnder_voltage_value(Float.valueOf("0.0"));
//            pduWarningBean.setOver_current_remind("0");
//            pduWarningBean.setOver_current_value(Float.valueOf("0.0"));

                    pduWarningBeanMsgBean.setData(pduWarningBean);
                    pduWarningBeanMsgBean.setMsg("设备预警初始化");
                }

                PrintWriter out = resp.getWriter();
                pduWarningBeanMsgBean.setData(pduWarningBean);
                pduWarningBeanMsgBean.setMsg("获取成功");

                out.print(pduWarningBeanMsgBean.toJsonString());
                out.flush();
                out.close();
//        }

            }
        } catch (Exception e) {
            PrintWriter out = resp.getWriter();
            MsgBean msgBean = MsgBean.getFalseInstance("获取失败");
            out.print(msgBean.toJsonString());
            out.flush();
            out.close();
            e.printStackTrace();
        }
    }

    /**
     * 搜索预警
     * 刘哲
     * 18/4/13
     */
    @RequestMapping("/warning/search_warning")
    public void SelectSNAME(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");

        //   List<PduGroupRelation> selectsmane = pduGroupRelationService.selectsmane("过压");
//        System.out.println(selectsmane.size());

        String KeyWord = req.getParameter("keyword");
        String token = req.getHeader("access_token");
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


                List<Mohuchaxun> pduWarningList = pduGroupRelationService.selectsmane(KeyWord);
                if (0 == pduWarningList.size()) {
                    String s = MsgBean.getFalseInstance("搜索失败").toJsonString();
                    PrintWriter out = resp.getWriter();
                    out.print(s);
                    out.flush();
                    out.close();

                } else {

                    PduWarningForName warningForName = new PduWarningForName();
                    List<PduWarningForName.WarningListBean> warning_list = new ArrayList<PduWarningForName.WarningListBean>();
                    //   List<PduWarningForName.WarningListBean> warning_list = new PduWarningForName().getWarning_list();

                    for (int i = 0; i < pduWarningList.size(); i++) {
                        PduWarningForName.WarningListBean warningListBean = new PduWarningForName.WarningListBean();
                        warningListBean.setWarning_id(pduWarningList.get(i).getId());
                        warningListBean.setDevice_id(pduWarningList.get(i).getPduid());
                        warningListBean.setDevice_name(pduWarningList.get(i).getName());
                        warningListBean.setGroup_id(pduWarningList.get(i).getPdugroupid());
                        warningListBean.setGroup_name(pduWarningList.get(i).getGroupname());
                        warningListBean.setType(Integer.parseInt(pduWarningList.get(i).getWarningtype()));
                        warningListBean.setDatetime(pduWarningList.get(i).getWarningtime());
                        warningListBean.setState(pduWarningList.get(i).getState());
                        warning_list.add(warningListBean);

                    }

                    PrintWriter out = resp.getWriter();
                    Gson gson = new Gson();
//             JSONObject data = new JSONObject();
//        String s = gson.toJson(group_list);
                    MsgBean<PduWarningForName> pduWarningForNameMsgBean = new MsgBean<PduWarningForName>();

                    pduWarningForNameMsgBean.setData(new PduWarningForName(warning_list));
                    String result = gson.toJson(pduWarningForNameMsgBean);

                    out.print(result);
                    out.flush();
                    out.close();
                }


            }
        } catch (Exception e) {
            PrintWriter out = resp.getWriter();
            MsgBean msgBean = MsgBean.getFalseInstance("搜索失败");
            out.print(msgBean.toJsonString());
            out.flush();
            out.close();

        }


    }


//    @RequestMapping("/analysis/get_analysis_data")
//    public void Selec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//        resp.setContentType("application/json;charset=UTF-8");
//        System.out.println("获取预警设置  ！");
//        String access_token;
//        //读取“token”
//        access_token = req.getHeader("access_token");
//
//        //查询数据
//
//        PduWarningSet warningSetById = pduWarningSetService.findPduWarningSetById(1);
//        PduWarningBean pduWarningBean = new PduWarningBean();
//        pduWarningBean.setElectric_leakage_remind(warningSetById.getElectricleakage());
//        pduWarningBean.setShort_circuit_remind(warningSetById.getCircuitbreaker());
//        pduWarningBean.setPower_remind(warningSetById.getPowerremind());
//        pduWarningBean.setPower_type(warningSetById.getPowertype());
//        pduWarningBean.setPower_value(warningSetById.getWatt());
//        pduWarningBean.setOver_voltage_remind(warningSetById.getOvervoltage());
//        pduWarningBean.setOver_voltage_value(warningSetById.getOvervoltagevalue());
//        pduWarningBean.setUnder_voltage_remind(warningSetById.getUndervoltage());
//        pduWarningBean.setUnder_voltage_value(warningSetById.getUndervoltagevalue());
//        pduWarningBean.setOver_current_remind(warningSetById.getOvercurrent());
//        pduWarningBean.setOver_current_value(warningSetById.getOvercurrentvalue());
//
//        MsgBean<PduWarningBean> pduWarningBeanMsgBean = new MsgBean<PduWarningBean>();
//
//        PrintWriter out = resp.getWriter();
//        pduWarningBeanMsgBean.setData(pduWarningBean);
//        pduWarningBeanMsgBean.setMsg("获取成功");
//
//        out.print(pduWarningBeanMsgBean.toJsonString());
//        out.flush();
//        out.close();
////        }


//    }

    /**
     * 预警分析
     * 刘哲
     * 18/4/13
     */

    @RequestMapping("/analysis/get_analysis_data")
    public void Select1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        System.out.println("获取预警设置  ！");
        String token = req.getHeader("access_token");
//        String token = req.getHeader("access_token");
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
                //   String token =   req.getHeader("access_token");
//        String token = req.getHeader("access_token");

                //查询数据
                PduWarningfenXi pduWarningfenXi = new PduWarningfenXi();
                pduWarningfenXi.setPower(pduWarningService.selectjidianqi());
                pduWarningfenXi.setOvervoltage(pduWarningService.selectguoya());
                pduWarningfenXi.setUndervoltage(pduWarningService.selectqianya());
                pduWarningfenXi.setOvercurrent(pduWarningService.selectguoliu());
                pduWarningfenXi.setOpen_circuit(pduWarningService.selectduanlu());
                pduWarningfenXi.setElectric_leakage(pduWarningService.selectloudian());
                pduWarningfenXi.setUnsolved(pduWarningService.selectweichulishu());
                pduWarningfenXi.setIn_progress(pduWarningService.selectchulizhong());
                pduWarningfenXi.setResolved(pduWarningService.selectyichulishu());
                pduWarningfenXi.setIgnored(pduWarningService.selectyihulve());

                MsgBean<PduWarningfenXi> pduWarningfenXiMsgBean = new MsgBean<PduWarningfenXi>();

                PrintWriter out = resp.getWriter();
                pduWarningfenXiMsgBean.setData(pduWarningfenXi);
                pduWarningfenXiMsgBean.setMsg("");

                out.print(pduWarningfenXiMsgBean.toJsonString());
                out.flush();
                out.close();
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
     * 设置标准值 2功率 3 电压 4 电流
     * 刘哲
     * 18/4/19
     */
    @RequestMapping("/device/set_device_standard_value")
    public void shezhibiaozhunzhi(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        PduWarningSet pduWarningSet = new PduWarningSet();
        //读取“token”
        String token = req.getHeader("access_token");
//        String token = req.getHeader("access_token");
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
                //读取“设备id”
                pduWarningSet.setPduid(Integer.parseInt(req.getParameter("device_id")));
                //读取“预警类型”
                int type = Integer.parseInt(req.getParameter("type"));
                String value = req.getParameter("value");
                PduWarningSet device_id = pduWarningSetService.findPduWarningSetById(Integer.parseInt(req.getParameter("device_id")));
                if (null == device_id) {
                    switch (type) {
                        case 2:
                            pduWarningSet.setWatt(value);
                            pduWarningSetService.savePduWarningSetr(pduWarningSet);
                            break;
                        case 3:
                            pduWarningSet.setVoltage(value);
                            pduWarningSetService.savePduWarningSetr(pduWarningSet);
                            break;
                        case 4:
                            pduWarningSet.setCurrent(value);
                            pduWarningSetService.savePduWarningSetr(pduWarningSet);
                            break;

                    }
                } else {

                    switch (type) {
                        case 2:
                            pduWarningSet.setWatt(value);
                            pduWarningSetService.update(pduWarningSet);
                            break;
                        case 3:
                            pduWarningSet.setVoltage(value);
                            pduWarningSetService.update(pduWarningSet);
                            break;
                        case 4:
                            pduWarningSet.setCurrent(value);
                            pduWarningSetService.update(pduWarningSet);
                            break;
                    }
                }


                PrintWriter out = resp.getWriter();
                out.print(MsgBean.getInstance().toJsonString());
                out.flush();
                out.close();
//        }


            }
        } catch (Exception e) {
            PrintWriter out = resp.getWriter();
            MsgBean msgBean = MsgBean.getFalseInstance("设置失败");
            out.print(msgBean.toJsonString());
            out.flush();
            out.close();

        }
    }
}
