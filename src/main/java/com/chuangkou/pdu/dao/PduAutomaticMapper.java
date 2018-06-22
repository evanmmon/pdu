package com.chuangkou.pdu.dao;

import com.chuangkou.pdu.entity.PduAutomatic;
import com.chuangkou.pdu.entity.PduAutomaticExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PduAutomaticMapper {
    int countByExample(PduAutomaticExample example);

    int deleteByExample(PduAutomaticExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PduAutomatic record);

    int insertSelective(PduAutomatic record);

    List<PduAutomatic> selectByExample(PduAutomaticExample example);

    PduAutomatic selectByPrimaryKey(Integer id);

/*    int updateByExampleSelective(@Param("record") PduAutomatic record, @Param("example")PduAutomaticExample example);

    int updateByExample(@Param("record") PduAutomatic record, @Param("example")PduAutomaticExample example);*/

    int updateByPrimaryKeySelective(PduAutomatic record);

    int updateByPrimaryKey(PduAutomatic record);
}