package com.chuangkou.pdu.dao;

import com.chuangkou.pdu.entity.PduOldLine;

/**
 * @Author:
 * @Description:
 * @Date:Created in 18:16 2018/5/10
 */
public interface PduOldLineMapper {

    int insert(PduOldLine pduOldLine);

    PduOldLine selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(PduOldLine pduOldLine);
}
