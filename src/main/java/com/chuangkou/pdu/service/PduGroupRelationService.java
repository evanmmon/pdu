package com.chuangkou.pdu.service;

import com.chuangkou.pdu.dao.PduGroupRelationMapper;
import com.chuangkou.pdu.entity.Mohuchaxun;
import com.chuangkou.pdu.entity.Pdu;
import com.chuangkou.pdu.entity.PduGroupRelation;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class PduGroupRelationService extends SqlSessionDaoSupport implements PduGroupRelationMapper {
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    @Override
    public PduGroupRelation findBypduId(int id) {
        PduGroupRelation pduGroupRelation1 = getSqlSession().selectOne("com.chuangkou.pdu.dao.PduGroupRelationMapper.selectapp",id);
        return pduGroupRelation1 ;
    }

    //根据设备id查询出设备与分组关系
   public PduGroupRelation findPduGroupRelationBypduId(int id) {
       PduGroupRelation pduGroupRelation = getSqlSession().selectOne("com.chuangkou.pdu.dao.PduGroupRelationMapper.selectByPduId",id);
       return pduGroupRelation ;
   }

    @Override
    public List<Mohuchaxun> selectsmane(String SNAME) {
        List<Mohuchaxun> pduwarnings= getSqlSession().selectList("com.chuangkou.pdu.dao.PduGroupRelationMapper.selectall3",SNAME);
        return pduwarnings;
    }

    @Override
    public List<PduGroupRelation> selectByIds(int id) {
        List<PduGroupRelation> pdus = getSqlSession().selectList("com.chuangkou.pdu.dao.PduGroupRelationMapper.selectPdubyPduGroupId",id);
          return pdus;
    }



    @Override
    public PduGroupRelation savePduGroupRelation(PduGroupRelation pduGroupRelation) {
        getSqlSession().insert("com.chuangkou.pdu.dao.PduGroupRelationMapper.savePduGroupRelation",pduGroupRelation);
        return pduGroupRelation;
    }

    @Override
    public int deleteOne(int id) {
        int delete = getSqlSession().delete("com.chuangkou.pdu.dao.PduGroupRelationMapper.deleteByPrimaryKey",id);
        return  delete ;
    }


    @Override
    public List<PduGroupRelation> selectPduIdByGroupId(int id) {
        List<PduGroupRelation> ids  = getSqlSession().selectList("com.chuangkou.pdu.dao.PduGroupRelationMapper.selectPduIdByPduGroupId",id);
        return ids;
    }

    @Override
    public List<PduGroupRelation> selectPduIdByGroupId1() {
        List<PduGroupRelation> ids  = getSqlSession().selectList("com.chuangkou.pdu.dao.PduGroupRelationMapper.selectPdubyPduGroupId1");
        return ids;
    }

    @Override
    public List<PduGroupRelation> selectPduIdByGroupId2(int id) {
        List<PduGroupRelation> ids  = getSqlSession().selectList("com.chuangkou.pdu.dao.PduGroupRelationMapper.selectPdubyPduGroupId2",id);
        return ids;
    }

    @Override
    public int deleteOne1(int id) {
        int delete = getSqlSession().delete("com.chuangkou.pdu.dao.PduGroupRelationMapper.deleteByPduId",id);
        return  delete ;
    }

    @Override
    public int update2(int id) {
        int update = getSqlSession().update("com.chuangkou.pdu.dao.PduGroupRelationMapper.updateGroup2", id);
        return update;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(PduGroupRelation record) {
        return 0;
    }

    @Override
    public int insertSelective(PduGroupRelation record) {
        return 0;
    }

    @Override
    public PduGroupRelation selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(PduGroupRelation pduGroupRelation) {
        getSqlSession().update("com.chuangkou.pdu.dao.PduGroupRelationMapper.updateByPrimaryKeySelective", pduGroupRelation);
        return 0;
    }

    @Override
    public int updateByPrimaryKey(PduGroupRelation record) {

        return 0;
    }

    /**
     *@Author:xulei
     *@Description:修改分组ID
     *@Date:  2018-03-21
     */
    public void updateGroup(PduGroupRelation record) {

        getSqlSession().update("com.chuangkou.pdu.dao.PduGroupRelationMapper.updateGroup",record);

    }

    /**
     *@Author:xulei
     *@Description:新增设备分组关系
     *@Date: 2018-03-21
     */
    public void insertNew(PduGroupRelation pduGroupRelation) {

        try {
            getSqlSession().insert("com.chuangkou.pdu.dao.PduGroupRelationMapper.insertSelective",pduGroupRelation);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public PduGroupRelation selectPdubyPdu(int pduid) {
        PduGroupRelation pduGroupRelation = new PduGroupRelation();

        try {
            pduGroupRelation = getSqlSession().selectOne("com.chuangkou.pdu.dao.PduGroupRelationMapper.selectPdubyPdu",pduid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pduGroupRelation;
    }
}
