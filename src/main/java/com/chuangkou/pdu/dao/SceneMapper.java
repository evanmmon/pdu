package com.chuangkou.pdu.dao;

import com.chuangkou.pdu.entity.Scene;
import java.util.List;

import com.chuangkou.pdu.entity.SceneExample;
import org.apache.ibatis.annotations.Param;

public interface SceneMapper {
    int countByExample(SceneExample example);

    int deleteByExample(SceneExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Scene record);

    int insertSelective(Scene record);

    List<Scene> selectByExample(SceneExample example);

    Scene selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Scene record);

    int updateByPrimaryKey(Scene record);
   // int updateByExampleSelective(@Param("record") Scene record, @Param("example") SceneExample example);

  //  int updateByExample(@Param("record") Scene record, @Param("example") SceneExample example);
}