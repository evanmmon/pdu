package com.chuangkou.pdu.dao;

import com.chuangkou.pdu.entity.Log;

import java.util.List;

public interface LogMapper {
    boolean deleteByPrimaryKey(Integer id);

    boolean insert(Log record);

    boolean insertSelective(Log record);

    Log selectByPrimaryKey(Integer id);

    boolean updateByPrimaryKeySelective(Log record);

    boolean updateByPrimaryKey(Log record);

    List<Log> findAll();
}