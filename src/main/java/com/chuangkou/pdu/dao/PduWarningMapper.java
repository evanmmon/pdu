package com.chuangkou.pdu.dao;

import com.chuangkou.pdu.entity.PduWarning;

import java.util.List;

public interface PduWarningMapper {
    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<PduWarning> selectALL();
    List<PduWarning> selectALL2();
    /**
     * 查询数量
     * @return
     * @throws Exception
     */
    int  selectchulizhong();
    int  selectyihulve();
    int  selectzongshu();
    int  selectweichulishu();
    int  selectyichulishu();
    int  selectjidianqi();
    int  selectguoya();
    int  selectqianya();
    int  selectguoliu();
    int  selectduanlu();
    int  selectloudian();
    /**
     * 根据id编辑
     * @return
     * @throws Exception
     */
    PduWarning update(PduWarning pduWarning);


    int deleteByPrimaryKey(Integer id);

    int insert(PduWarning record);

    int insertSelective(PduWarning record);

    PduWarning selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PduWarning record);

    int updateByPrimaryKey(PduWarning record);
}