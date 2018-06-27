package com.chuangkou.pdu.dao;

import com.chuangkou.pdu.entity.PduTemporary;

public interface PduTemporaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PduTemporary record);

    int insertSelective(PduTemporary record);

    PduTemporary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PduTemporary record);

    int updateByPrimaryKey(PduTemporary record);
}