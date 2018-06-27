package com.chuangkou.pdu.dao;

import com.chuangkou.pdu.entity.PduSelfcheckInfo;

import java.util.List;

public interface PduSelfcheckInfoMapper {
    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<PduSelfcheckInfo> selectALL(int id);

    /**
     * 增加
     * @return
     * @throws Exception
     */
    PduSelfcheckInfo savePduSelfcheckInfo(PduSelfcheckInfo pduSelfcheckInfo);
    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<PduSelfcheckInfo> selectPduIDbyPduSelfcheckID(int id);

    int insert(PduSelfcheckInfo record);

    int insertSelective(PduSelfcheckInfo record);
}