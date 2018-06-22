package com.chuangkou.pdu.controller;

import com.chuangkou.pdu.bean.DeviceBean;
import com.chuangkou.pdu.bean.DeviceWarning;
import com.chuangkou.pdu.bean.MsgBean;
import com.chuangkou.pdu.bean.SceneBean;
import com.chuangkou.pdu.entity.*;
import com.chuangkou.pdu.entity.PduAutomaticExample.Criteria;
import com.chuangkou.pdu.service.PduAutomaticService;
import com.chuangkou.pdu.service.PduService;
import com.chuangkou.pdu.service.PduWarningSetService;
import com.chuangkou.pdu.service.SceneService;
import com.chuangkou.pdu.util.LogUtil;
import com.chuangkou.pdu.util.TokenUtil;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;
@Controller
public class SceneController {
    @Autowired
    private SceneService sceneService;
    @Autowired
    private PduAutomaticService pduAutomaticService;
    @Autowired
    private PduService pduService;
    @Autowired
    private PduWarningSetService pduWarningSetService;

    /**
     * @Author: kanyuanfeng
     * @Date: 2018/3/29
     * @Description:进入场景添加页面
     */
    @RequestMapping("/toAddScene")
    public String toAddScene(Model model){
        //将全部设备传到页面
        //查询出所有所有state为1的设备
        List<Pdu> pduList = pduService.selectAll();
        model.addAttribute("pduList", pduList);
        return "scene_add";
    }
    /**
     * 新增场景
     */
    @RequestMapping("/addScene")
    public String addScene(HttpServletRequest request, Scene scene, int[] pduIds, int[] repeatDays){
        if(null != scene){
            //保存repeatDay
            if(null != repeatDays && repeatDays.length> 0){
                scene.setRepeatday(Arrays.toString(repeatDays));
            }else{
                scene.setRepeatday("[0, 1, 2, 3, 4, 5, 6]");
            }
            String starttime = scene.getStarttime();
            scene.setStarttime((null != starttime && StringUtils.isNotBlank(starttime))? starttime :"00:00");
            String endtime = scene.getEndtime();
            scene.setEndtime((null != endtime && StringUtils.isNotBlank(endtime))? endtime :"23:59");
            //主键返回
            sceneService.insertSelective(scene);
            //添加日志
            LogUtil.addLog(request,"添加场景："+scene.getScenename());
            //保存对应的自动任务表
            if(null != pduIds && pduIds.length> 0){
                for (int id : pduIds) {
                    PduAutomatic pduAutomatic = new PduAutomatic();
                    pduAutomatic.setSceneid(scene.getId());
                    pduAutomatic.setPudid(id);
                    pduAutomaticService.insertSelective(pduAutomatic);
                }
            }
        }
        return "redirect:/sceneList";
    }

    /**
     * 查询所有场景
     */
    @RequestMapping("/sceneList")
    public String sceneList(Model model){
        //查询所有的场景
        SceneExample example = new SceneExample();
        List<Scene> sceneList = sceneService.selectByExample(example);
        model.addAttribute("sceneList", sceneList);
        return "scene";
    }

    /**
     * 根据id查询场景，进行编辑修改
     */
    @RequestMapping("/selectSceneById")
    public String sceneList(Model model, Integer id){
        //根据id查出场景
        Scene scene = sceneService.selectByPrimaryKey(id);
        //根据场景id查出该场景下的设备
        PduAutomaticExample example = new PduAutomaticExample();
        example.createCriteria().andSceneidEqualTo(id);
        List<PduAutomatic> pduAutomaticList = pduAutomaticService.selectByExample(example);
        //将场景设备id组成list，判断场景是否包含该设备。包含便选中
        List<Integer> scenePduIdList = new ArrayList<Integer>();
        //根据设备查出该设备的id
        if(null != pduAutomaticList && pduAutomaticList.size() > 0){
            for (PduAutomatic pduAutomatic : pduAutomaticList) {
                Integer scenePudId = pduAutomatic.getPudid();
                //加入场景设备id集合
                scenePduIdList.add(scenePudId);
            }
        }
        //新建DeviceWarning集合
        List<DeviceWarning> deviceWarningList = new ArrayList<DeviceWarning>();
        //查询出所有所有state为1的设备
        List<Pdu> pduList = pduService.selectAll();
        //查询出state为1的设备的预警参数
        if(null != pduList && pduList.size() > 0 ){
            for (Pdu pdu : pduList) {
                //根据设备id查找设备预警值
                PduWarningSet pduWarningSet = pduWarningSetService.selectByPduWarningSet(pdu.getId());
                DeviceWarning deviceWarning = null;
                if(null != pduWarningSet){
                    //组装到DeviceWarning中
                    deviceWarning = new DeviceWarning(pdu.getId(),pdu.getName(),pduWarningSet.getVoltage(),
                            pduWarningSet.getCurrent());
                }else{
                    deviceWarning = new DeviceWarning(pdu.getId(),pdu.getName(),"","");
                }
                //添加到deviceWarningList中
                deviceWarningList.add(deviceWarning);

            }
        }
        model.addAttribute("scene", scene);
        model.addAttribute("deviceWarningList", deviceWarningList);
        model.addAttribute("scenePduIdList", scenePduIdList);
        return "scene_edit";
    }

    /**
     * 修改场景
     */
    @RequestMapping("updateScene")
    public String updateScene(HttpServletRequest request, Scene scene,int[] repeatDays, int[] pduIds){
        if (null !=repeatDays && repeatDays.length > 0){
            scene.setRepeatday(Arrays.toString(repeatDays));
        }
        sceneService.updateByPrimaryKeySelective(scene);
        //保存自动任务表
        //查找出该场景下的所有自动任务
        PduAutomaticExample pduAutomaticExample = new PduAutomaticExample();
        pduAutomaticExample.createCriteria().andSceneidEqualTo(scene.getId());
        List<PduAutomatic> pduAutomaticList = pduAutomaticService.selectByExample(pduAutomaticExample);
        //判断该场景下是否有自动任务，有就全部删除
        if(null != pduAutomaticList && pduAutomaticList.size() > 0){
            PduAutomaticExample example = new PduAutomaticExample();
            example.createCriteria().andSceneidEqualTo(scene.getId());
            pduAutomaticService.deleteByExample(example);
        }
        //添加自动任务表
        if(null != pduIds && pduIds.length > 0){
            for ( int id : pduIds ) {
                PduAutomatic pduAutomatic = new PduAutomatic();
                pduAutomatic.setSceneid(scene.getId());
                pduAutomatic.setPudid(id);
                pduAutomaticService.insertSelective(pduAutomatic);
            }
        }
        //添加日志
        LogUtil.addLog(request,"修改场景："+scene.getScenename());
        return "redirect:/sceneList";
    }

    /**
     * 根据id删除场景
     */
    @RequestMapping("deleteSceneById")
    public String deleteSceneById(HttpServletRequest request, Integer id){
        Scene scene = sceneService.selectByPrimaryKey(id);
        //先删除关联的自动任务
        PduAutomaticExample example = new PduAutomaticExample();
        example.createCriteria().andSceneidEqualTo(id);
        List<PduAutomatic> pduAutomaticList = pduAutomaticService.selectByExample(example);
        if(null != pduAutomaticList && pduAutomaticList.size() > 0){
            pduAutomaticService.deleteByExample(example);
        }
        //删除场景
        sceneService.deleteByPrimaryKey(id);
        //添加日志
        LogUtil.addLog(request,"删除场景："+scene.getScenename());
        return "redirect:/sceneList";
    }

    /**
     * @Author: kanyuanfeng
     * @Date: 2018/4/2
     * @Description: 查看场景详情
     */
    @RequestMapping("/sceneInfo")
    public String pduInfo(Model model, HttpServletRequest request, HttpServletResponse response, Integer id) throws Exception {
        Scene scene = sceneService.selectByPrimaryKey(id);
        //查找场景关联的自动任务
        PduAutomaticExample example = new PduAutomaticExample();
        example.createCriteria().andSceneidEqualTo(scene.getId());
        List<PduAutomatic> pduAutomaticList = pduAutomaticService.selectByExample(example);
        //通过关联的自动任务查询设备
        List<DeviceWarning> deviceWarningList = new ArrayList<DeviceWarning>();
        if(null != pduAutomaticList && pduAutomaticList.size() > 0){
            for (PduAutomatic pduAutomatic : pduAutomaticList) {
                Pdu pdu = pduService.selectByPrimaryKey(pduAutomatic.getPudid());
                if ("1".equals(pdu.getState())){
                    int i = pdu.getId();
                    PduWarningSet pduWarningSet = pduWarningSetService.selectByPduWarningSet(pdu.getId());
                    DeviceWarning deviceWarning = null;
                    if(null != pduWarningSet){
                        //组装到DeviceWarning中
                        deviceWarning = new DeviceWarning(pdu.getId(),pdu.getName(),pduWarningSet.getVoltage(),
                                pduWarningSet.getCurrent());
                    }else{
                        deviceWarning = new DeviceWarning(pdu.getId(),pdu.getName(),"","");
                    }
                    //添加到deviceWarningList中
                    deviceWarningList.add(deviceWarning);
                }else{
                    pduAutomaticService.deleteByPrimaryKey(pdu.getId());
                }
            }
        }
        model.addAttribute("scene", scene);
        model.addAttribute("deviceWarningList", deviceWarningList);
        return "scene_info";
    }

    /**
     * 查询所有场景。APP接口
     * @return
     */
    @RequestMapping("/scene/get_scene_list")
    @ResponseBody
    public Object selectList(HttpServletRequest request, HttpServletResponse response) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String token = request.getHeader("access_token");
        MsgBean msgBean = null;
        try {
            //验证并解析token
            Token apptokenyanzheng = TokenUtil.apptokenyanzheng(token);
            if(apptokenyanzheng.getJieguo()){
                //查询所有的场景
                SceneExample example = new SceneExample();
                List<Scene> sceneList = sceneService.selectByExample(example);
                //创建SceneBean集合
                List<SceneBean> scene_list = new ArrayList<SceneBean>();
                if(null != sceneList && sceneList.size() > 0) {
                    for (Scene scene : sceneList) {
                        //创建SceneBean
                        SceneBean sceneBean = new SceneBean();
                        //添加scenBean属性
                        sceneBean.setScene_id(scene.getId().toString());
                        sceneBean.setName(scene.getScenename());
                        sceneBean.setStart_time(scene.getStarttime());
                        sceneBean.setEnd_time(scene.getEndtime());
                        //拼接重复日期
                        String repeatday = scene.getRepeatday();
                        String[] strings = repeatday.substring(1, repeatday.length()-1).split(", ");
                        int[] repeat_day = new int[strings.length];
                        for (int i = 0; i < strings.length; i++) {
                            repeat_day[i] = Integer.parseInt(strings[i]);
                        }
                        sceneBean.setRepeat_day(repeat_day);
                        sceneBean.setPower(null != scene.getWatt() && !"".equals(scene.getWatt()) ? Float.parseFloat(scene.getWatt()) : 0.0f);
                        sceneBean.setOvervoltage((null != scene.getOvervoltage() && !"".equals(scene.getOvervoltage())) ? Float.parseFloat(scene.getOvervoltage()) : 0.0f);
                        sceneBean.setUndervoltage((null != scene.getUndervoltage() && !"".equals(scene.getUndervoltage())) ? Float.parseFloat(scene.getUndervoltage()) : 0.0f);
                        sceneBean.setOvercurrent((null != scene.getOvercurrent() && !"".equals(scene.getOvercurrent())) ? Float.parseFloat(scene.getOvercurrent()) : 0.0f);
                        sceneBean.setOpen_circuit((null != scene.getCircuitbreaker() && !"".equals(scene.getCircuitbreaker())) ? Integer.valueOf(scene.getCircuitbreaker()) : 0);
                        sceneBean.setElectric_leakage((null != scene.getElectricleakage() && !"".equals(scene.getElectricleakage())) ? Integer.valueOf(scene.getElectricleakage()) : 0);
                        sceneBean.setState((null != scene.getState() && !"".equals(scene.getState())) ? Integer.valueOf(scene.getState()) : 0);
                        //创建DeviceBean集合
                        List<DeviceBean> device_list = new ArrayList<DeviceBean>();
                        //根据场景id查询查找设备id
                        PduAutomaticExample pduateExample = new PduAutomaticExample();
                        Criteria criteria = pduateExample.createCriteria();
                        criteria.andSceneidEqualTo(scene.getId());
                        List<PduAutomatic> pduAutomaticList = pduAutomaticService.selectByExample(pduateExample);
                        if(null != pduAutomaticList && pduAutomaticList.size() > 0){
                            for (PduAutomatic pduAutomatic : pduAutomaticList) {
                                //创建DeviceBean
                                DeviceBean deviceBean = new DeviceBean();
                                //设备id
                                Integer pudId = pduAutomatic.getPudid();
                                deviceBean.setDevice_id(pduAutomatic.getPudid().toString());
                                //根据设备id查询设备名称
                                String name = pduService.selectByPrimaryKey(pudId).getName();
                                deviceBean.setDevice_name(name);
                                device_list.add(deviceBean);
                            }
                        }
                        sceneBean.setDevice_list(device_list);
                        scene_list.add(sceneBean);
                    }
                }
                //创建map，将scene_list传到msgBean中
                Map<String, List> map = new HashMap<String, List>();
                map.put( "scene_list", scene_list);
                //返回成功的msgBean
                msgBean = new MsgBean(0,"操作成功",map);
            }else {
                msgBean = MsgBean.getTokenFalseInstance();
            }
        } catch (Exception e) {
            msgBean = MsgBean.getFalseInstance("场景列表获取失败");
            e.printStackTrace();
        }
       return msgBean;
    }

    /**
     * 新建场景 APP接口
     */
    @RequestMapping("/scene/create_scene")
    @ResponseBody
    public MsgBean createScene(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String data = request.getParameter("data");
        Gson gson = new Gson();
        SceneBean sceneBean = gson.fromJson(data, SceneBean.class);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String access_token = request.getHeader("access_token");
        MsgBean msgBean = null;
        //创建场景对象Scene,将传进来的SceneBean装换成Scene
        Scene scene = null;
        try {
            //验证并解析token
            Token token = TokenUtil.apptokenyanzheng(access_token);
            if(token.getJieguo()){
                if(null != sceneBean){
                    //创建场景对象Scene,将传进来的SceneBean装换成Scene
                    scene = new Scene();
                    scene.setScenename(sceneBean.getName());
                    scene.setStarttime(sceneBean.getStart_time());
                    scene.setEndtime(sceneBean.getEnd_time());
                    //保存repeatDay
                    if(null != sceneBean.getRepeat_day() && sceneBean.getRepeat_day().length> 0){
                        scene.setRepeatday(Arrays.toString(sceneBean.getRepeat_day()));
                    }else{
                        scene.setRepeatday("[0, 1, 2, 3, 4, 5, 6]");
                    }
                    scene.setWatt(String.valueOf(sceneBean.getPower()));
                    scene.setOvervoltage(String.valueOf(sceneBean.getOvervoltage()));
                    scene.setUndervoltage(String.valueOf(sceneBean.getUndervoltage()));
                    scene.setOvercurrent(String.valueOf(sceneBean.getOvercurrent()));
                    scene.setCircuitbreaker(String.valueOf(sceneBean.getOpen_circuit()));
                    scene.setElectricleakage(String.valueOf(sceneBean.getElectric_leakage()));
                    scene.setState(String.valueOf(sceneBean.getState()));
                    sceneService.insertSelective(scene);
                    //将场景id和设备id保存到自动任务表
                    List<DeviceBean> device_list = sceneBean.getDevice_list();
                    if(null != device_list && device_list.size() > 0){
                        for (DeviceBean deviceBean: device_list) {
                            //创建PduAutomatic对象
                            PduAutomatic pduAutomatic = new PduAutomatic();
                            pduAutomatic.setSceneid(scene.getId());
                            pduAutomatic.setPudid(Integer.valueOf(deviceBean.getDevice_id()));
                            pduAutomaticService.insertSelective(pduAutomatic);
                        }
                    }
                    //添加日志
                    LogUtil.addLog(token.getUsername(),"添加场景："+sceneBean.getName());
                    //返回成功的msgBean
                    msgBean = MsgBean.getInstance();
                }else{
                    msgBean = MsgBean.getFalseInstance("新建场景失败");
                }
            }else {
                //token验证错误
                msgBean = MsgBean.getTokenFalseInstance();
            }
        } catch (Exception e) {
            msgBean = MsgBean.getFalseInstance("新建场景失败");
            e.printStackTrace();
        }
        return msgBean;
    }

    /**
     * 修改场景 APP接口
     */
    @RequestMapping("/scene/modify_scene")
    @ResponseBody
    public MsgBean modifyScene(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String data = request.getParameter("data");
        Gson gson = new Gson();
        SceneBean sceneBean = gson.fromJson(data, SceneBean.class);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String access_token = request.getHeader("access_token");
        MsgBean msgBean = null;
        try {
            Token token = TokenUtil.apptokenyanzheng(access_token);
            if (token.getJieguo()){
                if(null != sceneBean){
                    //根据场景id查询需要修改的场景
                    Scene scene = sceneService.selectByPrimaryKey(Integer.valueOf(sceneBean.getScene_id()));
                    if(null != scene){
                        scene.setScenename(sceneBean.getName());
                        scene.setStarttime(sceneBean.getStart_time());
                        scene.setEndtime(sceneBean.getEnd_time());
                        scene.setRepeatday(Arrays.toString(sceneBean.getRepeat_day()));
                        scene.setWatt(String.valueOf(sceneBean.getPower()));
                        scene.setOvervoltage(String.valueOf(sceneBean.getOvervoltage()));
                        scene.setUndervoltage(String.valueOf(sceneBean.getUndervoltage()));
                        scene.setOvercurrent(String.valueOf(sceneBean.getOvercurrent()));
                        scene.setCircuitbreaker(String.valueOf(sceneBean.getOpen_circuit()));
                        scene.setElectricleakage(String.valueOf(sceneBean.getElectric_leakage()));
                        scene.setState(String.valueOf(sceneBean.getState()));
                        //查找出该场景下的所有自动任务
                        PduAutomaticExample pduAutomaticExample = new PduAutomaticExample();
                        pduAutomaticExample.createCriteria().andSceneidEqualTo(Integer.valueOf(sceneBean.getScene_id()));
                        List<PduAutomatic> pduAutomaticList = pduAutomaticService.selectByExample(pduAutomaticExample);
                        //判断该场景下是否有自动任务，有就全部删除
                        if(null != pduAutomaticList && pduAutomaticList.size() > 0){
                            PduAutomaticExample example = new PduAutomaticExample();
                            example.createCriteria().andSceneidEqualTo(Integer.valueOf(sceneBean.getScene_id()));
                            pduAutomaticService.deleteByExample(example);
                        }
                        //添加接受的设备到自动任务中
                        List<DeviceBean> device_list = sceneBean.getDevice_list();
                        if(null != device_list && device_list.size() > 0){
                            for (DeviceBean deviceBean: device_list){
                                Integer device_id = Integer.valueOf(deviceBean.getDevice_id());
                                PduAutomatic pduAutomatic = new PduAutomatic();
                                pduAutomatic.setSceneid(Integer.valueOf(sceneBean.getScene_id()));
                                pduAutomatic.setPudid(device_id);
                                pduAutomaticService.insertSelective(pduAutomatic);
                            }
                        }
                    }
                    sceneService.updateByPrimaryKeySelective(scene);
                    //添加日志
                    LogUtil.addLog(token.getUsername(),"修改场景："+scene.getScenename());
                    msgBean = MsgBean.getInstance();
                }else {
                    msgBean = MsgBean.getFalseInstance("修改失败");
                }
            }else {
                //token验证错误
                msgBean = MsgBean.getTokenFalseInstance();
                }
            } catch(Exception e){
                msgBean = MsgBean.getFalseInstance("修改失败");
                e.printStackTrace();
            }
            return msgBean;

    }

    /**
     * 删除场景 APP接口
     */
    @RequestMapping("/scene/delete_scene")
    @ResponseBody
    public MsgBean deleteScene(HttpServletRequest request, HttpServletResponse response){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String access_token = request.getHeader("access_token");
        String scene_id = request.getParameter("scene_id");
        MsgBean msgBean = null;
        try {
            //验证并解析token
            Token token = TokenUtil.apptokenyanzheng(access_token);
            if(token.getJieguo()){
                //先删除关联的自动任务
                PduAutomaticExample example = new PduAutomaticExample();
                example.createCriteria().andSceneidEqualTo(Integer.valueOf(scene_id));
                List<PduAutomatic> pduAutomaticList = pduAutomaticService.selectByExample(example);
                if(null != pduAutomaticList && pduAutomaticList.size() > 0){
                    pduAutomaticService.deleteByExample(example);
                }
                //删除场景
                Scene scene = sceneService.selectByPrimaryKey(Integer.valueOf(scene_id));
                sceneService.deleteByPrimaryKey(Integer.valueOf(scene_id));
                msgBean = MsgBean.getInstance();
                //添加日志
                LogUtil.addLog(token.getUsername(),"删除场景："+scene.getScenename());
            }else {
                msgBean = MsgBean.getTokenFalseInstance();
            }
        } catch (Exception e) {
            msgBean = MsgBean.getFalseInstance("场景删除失败");
            e.printStackTrace();
        }
        return msgBean;
    }

    /**
     * 修改场景开关状态 APP接口
     */
    @RequestMapping("/scene/modify_scene_state")
    @ResponseBody
    public MsgBean modifySceneState(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String access_token = request.getHeader("access_token");
        String scene_id = request.getParameter("scene_id");
        String state = request.getParameter("state");
        MsgBean msgBean = null;
        try {
            //验证并解析token
            Token token = TokenUtil.apptokenyanzheng(access_token);
            if(token.getJieguo()){
                //根据scene_id找到该场景
                if (null != scene_id && !"".equals(scene_id)){
                    Scene scene = sceneService.selectByPrimaryKey(Integer.valueOf(scene_id));
                    if (null != scene){
                        if(null != state && !"".equals(state)){
                            scene.setState(state);
                            //改变该场景状态
                            sceneService.updateByPrimaryKeySelective(scene);
                            msgBean = MsgBean.getInstance();
                            //添加日志
                            //根据state判断是关闭还是开始场景。0关闭，1开启
                            if("0".equals(state)){
                                LogUtil.addLog(token.getUsername(),"关闭场景："+scene.getScenename());
                            }else if("1".equals(state)){
                                LogUtil.addLog(token.getUsername(),"开启场景："+scene.getScenename());
                            }
                        }
                    }
                }else{
                    msgBean = MsgBean.getFalseInstance("修改场景失败");
                }
            }else {
                msgBean = MsgBean.getTokenFalseInstance();
            }
        } catch (Exception e) {
            //根据state判断是关闭还是开始场景。0关闭，1开启
            if("0".equals(state)){
                msgBean = MsgBean.getFalseInstance("场景关闭失败");
            }else if("1".equals(state)){
                msgBean = MsgBean.getFalseInstance("场景打开失败");
            }else {
                msgBean = MsgBean.getFalseInstance("修改场景失败");
            }
            e.printStackTrace();
        }
        return msgBean;
    }
}

