package com.chuangkou.pdu.service;

import com.chuangkou.pdu.dao.PduAutomaticMapper;
import com.chuangkou.pdu.entity.PduAutomatic;
import com.chuangkou.pdu.entity.PduAutomaticExample;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class PduAutomaticService extends SqlSessionDaoSupport implements PduAutomaticMapper {
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    @Override
    public int countByExample(PduAutomaticExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(PduAutomaticExample example) {
        getSqlSession().delete("com.chuangkou.pdu.dao.PduAutomaticMapper.deleteByExample", example);
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        getSqlSession().delete("com.chuangkou.pdu.dao.PduAutomaticMapper.deleteByPrimaryKey", id);
        return 0;
    }

    @Override
    public int insert(PduAutomatic pduAutomatic) {
        getSqlSession().insert("com.chuangkou.pdu.dao.PduAutomaticMapper.insert", pduAutomatic);
        return 0;
    }

    @Override
    public int insertSelective(PduAutomatic pduAutomatic) {
        int id = getSqlSession().insert("com.chuangkou.pdu.dao.PduAutomaticMapper.insertSelective", pduAutomatic);
        return id;
    }

    @Override
    public List<PduAutomatic> selectByExample(PduAutomaticExample example) {
        List<PduAutomatic> pduAutomaticList = getSqlSession().selectList("com.chuangkou.pdu.dao.PduAutomaticMapper.selectByExample", example);
        return pduAutomaticList;
    }

    @Override
    public PduAutomatic selectByPrimaryKey(Integer id) {
        PduAutomatic pduAutomatic = getSqlSession().selectOne("com.chuangkou.pdu.dao.PduAutomaticMapper.selectByPrimaryKey", id);
        return pduAutomatic;
    }

  /*  @Override
    public int updateByExampleSelective(PduAutomatic record, PduAutomaticExample example) {
        return 0;
    }

    @Override
    public int updateByExample(PduAutomatic record, PduAutomaticExample example) {
        return 0;
    }*/

    @Override
    public int updateByPrimaryKeySelective(PduAutomatic pduAutomatic) {
        getSqlSession().update("com.chuangkou.pdu.dao.PduAutomaticMapper.updateByPrimaryKeySelective", pduAutomatic);
        return 0;
    }

    @Override
    public int updateByPrimaryKey(PduAutomatic pduAutomatic) {
        getSqlSession().update("com.chuangkou.pdu.dao.PduAutomaticMapper.updateByPrimaryKey", pduAutomatic);
        return 0;
    }

    /**
     *@Author:
     *@Description:
     *@Date:  2018-04-11
     */

    public List<PduAutomatic> selectBySceneId(int pduId){
        List<PduAutomatic> pduAutomaticList = new ArrayList<PduAutomatic>();

        try {
            pduAutomaticList = getSqlSession().selectList("com.chuangkou.pdu.dao.PduAutomaticMapper.selectBySceneId",pduId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pduAutomaticList;
    }
}
