package com.chuangkou.pdu.controller;

import com.chuangkou.pdu.entity.Dictionary;
import com.chuangkou.pdu.service.DictionaryService;
import com.chuangkou.pdu.util.LogUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 字典功能
 */
@Controller
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;
    /**
     *  进入字典界面
     */
    @RequestMapping("/dictionaryList")
    public String selectAll(Model model,  @RequestParam(value="parentid", defaultValue = "0") Integer parentid){
        try {
            List<Dictionary> dictionaryList = dictionaryService.selectByParentid(parentid);
            model.addAttribute("dictionaryList", dictionaryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "dictionary";
    }

    /**
     * 根据id查询字典，进行修改
     */
    @RequestMapping("/selectDictionaryById")
    @ResponseBody
    public Dictionary selectId(Integer id){
        Dictionary dictionary = dictionaryService.selectByPrimaryKey(id);
        return dictionary;
    }
    /*
    public String selectId(Model model, Integer id){
        List<Dictionary> parentDictionaryList = null;
        try {
            Dictionary dictionary = dictionaryService.selectByPrimaryKey(id);
            //查询出所有符合条件的上级字典
            if(dictionary.getParentid() != 0){//如果parentid为0 则为顶层根节点
                Dictionary parentDictionary = dictionaryService.selectByPrimaryKey(dictionary.getParentid());
               parentDictionaryList = dictionaryService.selectByParentid(parentDictionary.getParentid());
            }
            model.addAttribute("dictionary", dictionary);
            model.addAttribute("parentDictionaryList", parentDictionaryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "dictionary_edit";
    }
    */

    /**
     * 根据id查询分类
     */
    @RequestMapping("/selectParentDictionaryById")
    @ResponseBody
    public Dictionary findParentDictionaryById(Integer id){
        Dictionary dictionary = dictionaryService.selectByPrimaryKey(id);
        return dictionary;
    }
    /**
     * 根据parentid查询字典
     */
    @RequestMapping("/selectDictionaryByParentid")
    @ResponseBody
    public Object selectByParentid(Integer parentid){
        List<Dictionary> dictionaryList = null;
        try {
            dictionaryList = dictionaryService.selectByParentid(parentid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dictionaryList;
    }

    /**
     * 编辑字典
     */
    @RequestMapping("/updateDictionary")
    public String updateDictionary(HttpServletRequest request, Dictionary dictionary){
        try {
            dictionaryService.updateByPrimaryKeySelective(dictionary);
            //添加日志
            LogUtil.addLog(request, "编辑字典:"+dictionary.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/dictionaryList";
    }

    /**
     * 根据id删除字典
     */
    @RequestMapping("/delDictionaryById")
    public String delDictionaryById(HttpServletRequest request, Integer id){
        try {
            Dictionary dictionary = dictionaryService.selectByPrimaryKey(id);
            dictionaryService.deleteByPrimaryKey(id);
            //添加日志
            LogUtil.addLog(request, "删除字典："+dictionary.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/dictionaryList";
    }

    /**
     * 添加字典
     */
    @RequestMapping("/addDictionary")
    public String addDictionary(HttpServletRequest request, Dictionary dictionary){
        try {
            if (null == dictionary.getParentid()){
                dictionary.setParentid(0);
            }
            dictionaryService.insert(dictionary);
            //添加日志
            LogUtil.addLog(request, "添加字典："+dictionary.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/dictionaryList";
    }
}
