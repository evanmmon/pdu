package com.chuangkou.pdu.dao;

import com.chuangkou.pdu.entity.Dictionary;

import java.util.List;

public interface DictionaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dictionary record);

    int insertSelective(Dictionary record);

    Dictionary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dictionary record);

    int updateByPrimaryKey(Dictionary record);

    //根据parentid查询
    List<Dictionary> selectByParentid(Integer parentid);

    //查询所有
    List<Dictionary> selectAll();
}