package com.chuangkou.pdu.dao;

import com.chuangkou.pdu.entity.PduSelfcheck;

import java.util.List;

public interface PduSelfcheckMapper {

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<PduSelfcheck> selectALL();

    /**
     * 根据id删除
     * @return
     * @throws Exception
     */
    int deleteOne(int id) ;

    /**
     * 增加
     * @return
     * @throws Exception
     */
    PduSelfcheck savePduSelfcheck(PduSelfcheck pduSelfcheck);
    /**
     * 查询ID最大值
     * @return
     * @throws Exception
     */
    int  selectMaxId();


    PduSelfcheck update(PduSelfcheck pduSelfcheck);












    int deleteByPrimaryKey(Integer id);

    int insert(PduSelfcheck record);

    int insertSelective(PduSelfcheck record);

    PduSelfcheck selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PduSelfcheck record);

    int updateByPrimaryKey(PduSelfcheck record);
}