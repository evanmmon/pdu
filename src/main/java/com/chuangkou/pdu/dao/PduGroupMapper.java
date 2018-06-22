package com.chuangkou.pdu.dao;

import com.chuangkou.pdu.entity.PduGroup;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
@MapperScan
public interface PduGroupMapper {
    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<PduGroup> selectALL();
    /**
     * 根据id删除
     * @return
     * @throws Exception
     */
    int deleteOne(int id) ;

    /**
     * 根据id查找
     * @return
     * @throws Exception
     */
    PduGroup findPduGroupById(int id);
    /**
     * 增加
     * @return
     * @throws Exception
     */
    PduGroup savePduGroup(PduGroup pduGroup);
    /**
     * 查询ID最大值
     * @return
     * @throws Exception
     */
    int  selectMaxId();
    /**
     * 根据id编辑
     * @return
     * @throws Exception
     */
    PduGroup update(PduGroup pduGroup);


    int deleteByPrimaryKey(Integer id);

    int insert(PduGroup record);

    int insertSelective(PduGroup record);

    PduGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PduGroup record);

    int updateByPrimaryKey(PduGroup record);
}