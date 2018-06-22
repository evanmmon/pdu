package com.chuangkou.pdu.dao;

import com.chuangkou.pdu.entity.Mohuchaxun;
import com.chuangkou.pdu.entity.Pdu;
import com.chuangkou.pdu.entity.PduGroupRelation;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
@MapperScan
public interface PduGroupRelationMapper {
    /**
     * 根据分组 id 批量查询pdu
     * @return
     * @throws Exception
     */
    List<PduGroupRelation> selectByIds( int id);

    /**
     * 增加
     * @return
     * @throws Exception
     */
    PduGroupRelation savePduGroupRelation(PduGroupRelation pduGroupRelation);
    /**
     * 根据 id 删除
     * @return
     * @throws Exception
     *
     */
    int deleteOne(int id) ;

    /**
     * 根据id查找
     * @return
     * @throws Exception
     */
    PduGroupRelation findBypduId (int id);
    /**
     * 模糊查询 预警
     * @return
     * @throws Exception
     */
    List<Mohuchaxun> selectsmane(String SNAME);

    List<PduGroupRelation> selectPduIdByGroupId( int id);

    List<PduGroupRelation> selectPduIdByGroupId1();

    List<PduGroupRelation> selectPduIdByGroupId2( int id);


    /**
     * 根据 id 删除
     * @return
     * @throws Exception
     *
     */
    int deleteOne1(int id) ;
    int update2(int id);

    int deleteByPrimaryKey(Integer id);

    int insert(PduGroupRelation record);

    int insertSelective(PduGroupRelation record);

    PduGroupRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PduGroupRelation record);

    int updateByPrimaryKey(PduGroupRelation record);
}