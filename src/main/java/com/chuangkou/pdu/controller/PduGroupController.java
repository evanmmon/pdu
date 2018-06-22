package com.chuangkou.pdu.controller;

import com.chuangkou.pdu.entity.Pdu;
import com.chuangkou.pdu.entity.PduGroup;
import com.chuangkou.pdu.entity.PduGroupRelation;
import com.chuangkou.pdu.service.PduGroupRelationService;
import com.chuangkou.pdu.service.PduGroupService;
import com.chuangkou.pdu.service.PduService;
import com.chuangkou.pdu.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PduGroupController {
    @Autowired
    private PduGroupService pduGroupService;
    @Autowired
    private PduService pduService;
    @Autowired
    private PduGroupRelationService pduGroupRelationService;

    /**
     * 查询所有列表
     *刘哲
     * @return
     * @throws Exception
     */
    @RequestMapping("/pduGrouplist")
    public String selectPduSelfchecklist(Model model, HttpServletRequest request, HttpServletResponse resp) {
        try {
            List<PduGroup> PduGroupList = pduGroupService.selectALL();
            model.addAttribute("PduGrouplist", PduGroupList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pdu_group_list";
    }

    /**
     * 根据id删除  删除的分时  设备应添加默认分组
     *
     * @param id
     */
    @RequestMapping("/delPduGroup")
    public String deletePduSelfcheck(HttpServletRequest request, Model model, int id) {
        try {
            List<PduGroupRelation> pduGroupRelations = pduGroupRelationService.selectPduIdByGroupId(id);
            for (int i =0 ;i<pduGroupRelations.size();i++) {
                PduGroupRelation pduGroupRelation = new PduGroupRelation();
                pduGroupRelation.setPduid(pduGroupRelations.get(i).getPduid());
                pduGroupRelation.setPdugroupid(1);
                pduGroupRelationService.savePduGroupRelation(pduGroupRelation);
            }
            PduGroup group = pduGroupService.findPduGroupById(id);
            model.addAttribute("PduGroup", pduGroupService.deleteOne(id));

            //添加日志
            LogUtil.addLog(request,"删除设备分组："+group.getGroupname());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/pduGrouplist";
    }

    /**
     * 查询详情列表
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/PduGroupInfo")
    public String getUser(int id, Model model) {
        try {
            model.addAttribute("PduGroupInfolist", pduGroupRelationService.selectByIds(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pdu_group_info";
    }

    /**
     * 添加 分组界面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/addPduGroup")
    public String addPduSelfcheck(HttpServletRequest request,Model model) {
        try {
//            List<Pdu> pduList = pduService.selectAll();
            List<PduGroupRelation> pduList = pduGroupRelationService.selectPduIdByGroupId1();
            model.addAttribute("pduList", pduList);

        } catch (Exception e) {
            e.printStackTrace();
        }
//        PduSelfcheck pduSelfcheck1 = pduSelfcheckService.savePduSelfcheck(pduSelfcheck);
//        model.addAttribute("PduSelfcheck", pduSelfcheck1);
        return "pdu_group_add";
    }

    @RequestMapping("/addPduGroup2")
    public String add2(Model model, HttpServletRequest request, HttpServletResponse resp, PduGroup pduGroup) {
        try {
            String[] pduIds = request.getParameterValues("pdu");
            String groupname = request.getParameter("groupname");
            String remark = request.getParameter("remark");
            pduGroup.setGroupname(groupname);
            pduGroup.setRemark(remark);
            PduGroup pduGroup1 = pduGroupService.savePduGroup(pduGroup);
            //添加日志
            LogUtil.addLog(request, "添加设备分组："+groupname);
            int maxId = pduGroup1.getId();
            //如果没添加设备
            if (null == pduIds) {

                return "redirect:/pduGrouplist";
            } else {
                for (int i = 0; i < pduIds.length; i++) {

//            Map<String,Object> Map = new HashMap<String,Object>();
//            Map.put("selfcheckid",maxId);
//            int a = Integer.parseInt(pduIds[i]);
//            Map.put("pduid",a);
//            Map.put("createtime","createtime");

                    PduGroupRelation pduGroupRelation = new PduGroupRelation();
                    pduGroupRelation.setPdugroupid(maxId);


                    int a = Integer.parseInt(pduIds[i]);
                    pduGroupRelation.setPduid(a);
                    pduGroupRelationService.deleteOne1(a);
                    pduGroupRelationService.savePduGroupRelation(pduGroupRelation);


//           System.out.println(pduIds[i]);
//            System.out.println(selfcheckname);
//            System.out.println(createtime);
//            System.out.println(maxId);
                }
            }} catch(Exception e){
                e.printStackTrace();
            }
        return "redirect:/pduGrouplist";
        }


    /**
     * 根据id查找
     *
     * @param id
     */
    @RequestMapping("/seleceOnePduGroup")
    public String getPduGroup(int id, Model model) {
        try {
        model.addAttribute("pduGroup", pduGroupService.findPduGroupById(id));
        List<PduGroupRelation> pduGroupRelations = pduGroupRelationService.selectPduIdByGroupId(id);
        List<Integer> PduIdList = new ArrayList<Integer>();
        for (int i=0;i<pduGroupRelations.size();i++){
            PduIdList.add(i,pduGroupRelations.get(i).getPduid());
        }


            List<PduGroupRelation> pduList = pduGroupRelationService.selectPduIdByGroupId2(id);


//            List<Pdu> pduList = pduService.selectAll();
            model.addAttribute("pduList", pduList);
            model.addAttribute("PduIdList",PduIdList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pdu_group_edti";
    }

    @RequestMapping("/updateOnePduGroup")
    public String updatePduGroup(Model model, HttpServletRequest request, HttpServletResponse resp, PduGroup pduGroup) {
//        model.addAttribute("PduGroupRelation",pduGroupRelationService.deleteOne(pdugrid));
        try {
        String[] pduIds = request.getParameterValues("pdu");
        String groupname = request.getParameter("groupname");
        String remark = request.getParameter("remark");
        String a = request.getParameter("id");
        int id = Integer.parseInt(a);

        pduGroup.setId(id);

        pduGroup.setGroupname(groupname);
        pduGroup.setRemark(remark);
        pduGroupService.update(pduGroup);
            //添加日志
            LogUtil.addLog(request, "修改设备分组："+groupname);
        pduGroupRelationService.update2(id);

        for (int i = 0; i < pduIds.length; i++) {

            PduGroupRelation pduGroupRelation = new PduGroupRelation();
            //分组id
            pduGroupRelation.setPdugroupid(id);
            //设备id
            int b = Integer.parseInt(pduIds[i]);
            pduGroupRelation.setPduid(b);
            pduGroupRelationService.updateGroup(pduGroupRelation);
//            pduGroupRelationService.deleteOne1(b);
//            pduGroupRelation.setPduid(b);
//            pduGroupRelationService.savePduGroupRelation(pduGroupRelation);
        } } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/pduGrouplist";
    }
}
