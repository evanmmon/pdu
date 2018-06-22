package com.chuangkou.pdu.service;

import com.chuangkou.pdu.dao.PduInfoMapper;
import com.chuangkou.pdu.dao.PduRelationMapper;
import com.chuangkou.pdu.entity.PduRelation;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:
 * @Description:
 * @Date:Created in 14:23 2018/5/9
 */
@Component
public class PduRelationService extends SqlSessionDaoSupport implements PduRelationMapper {
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }


    @Override
    public int insert(PduRelation pduRelation) {

        int i = 0;
        try {
            i = getSqlSession().insert("com.chuangkou.pdu.dao.PduRelationMapper.insert",pduRelation);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;
    }

    @Override
    public PduRelation selectByPrimaryKey(Integer id) {
        PduRelation pduRelation = new PduRelation();
        try {

            pduRelation = getSqlSession().selectOne("com.chuangkou.pdu.dao.PduRelationMapper.selectByPrimaryKey",id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pduRelation;
    }

    @Override
    public int updateByPrimaryKey(PduRelation pduRelation) {
//        PduRelation pduRelation = new PduRelation();
        int i = 0;
        try {

            i = getSqlSession().update("com.chuangkou.pdu.dao.PduRelationMapper.updateByPrimaryKey",pduRelation);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;
    }

    public int updateByPrimaryKeySelective(PduRelation pduRelation) {
//        PduRelation pduRelation = new PduRelation();
        int i = 0;
        try {

            i = getSqlSession().update("com.chuangkou.pdu.dao.PduRelationMapper.updateByPrimaryKeySelective",pduRelation);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;
    }

/**
 *@Author:
 *@Description:根据空开设备查找插座
 *@Date:
 */
    public List selectByPlugs(Integer switchPduID) {
        List<PduRelation> list  = new ArrayList<PduRelation>();

        try {
            list = getSqlSession().selectList("com.chuangkou.pdu.dao.PduRelationMapper.selectByPlugs",switchPduID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    /**
     *@Author:xulei
     *@Description:parentID不等于空 并且switchpduid==当前空开的
     *@Date:  2018-06-14
     */
    public List selectByPlugsNotEmpty(Integer switchPduID) {
        List<PduRelation> list  = new ArrayList<PduRelation>();

        try {
            list = getSqlSession().selectList("com.chuangkou.pdu.dao.PduRelationMapper.selectByPlugsNotEmpty",switchPduID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    /**
     *@Author:xulei
     *@Description: 根据插座ID搜索关联的下级插座
     *@Date:  2018-06-13
     */

    public List selectBySubChildrens(Integer pduID) {
        List<PduRelation> list  = new ArrayList<PduRelation>();

        try {
            list = getSqlSession().selectList("com.chuangkou.pdu.dao.PduRelationMapper.selectBySubChildrens",pduID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }



//    public PduRelation selectByPduInfo(Integer pduID) {
//        PduRelation pduRelation = new PduRelation();
//
//        pduRelation = getSqlSession().selectOne("com.chuangkou.pdu.dao.PduRelationMapper.selectByPduInfo",pduID);
//
//        return pduRelation;
//    }

}
