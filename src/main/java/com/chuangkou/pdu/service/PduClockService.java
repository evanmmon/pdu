package com.chuangkou.pdu.service;

import com.chuangkou.pdu.dao.PduClockMapper;
import com.chuangkou.pdu.entity.PduClock;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:
 * @Description:
 * @Date:Created in 18:17 2018/4/3
 */
@Component
public class PduClockService extends SqlSessionDaoSupport implements PduClockMapper{
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        try {
            getSqlSession().delete("com.chuangkou.pdu.dao.PduClockMapper.deleteByPrimaryKey",id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int insert(PduClock record) {
        return 0;
    }

    @Override
    public int insertSelective(PduClock record) {

        try {
            getSqlSession().insert("com.chuangkou.pdu.dao.PduClockMapper.insertSelective",record);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public PduClock selectByPrimaryKey(Integer id) {
        return getSqlSession().selectOne("com.chuangkou.pdu.dao.PduClockMapper.selectByPrimaryKey",id);
    }

    @Override
    public int updateByPrimaryKeySelective(PduClock record) {
        try {
            getSqlSession().update("com.chuangkou.pdu.dao.PduClockMapper.updateByPrimaryKeySelective",record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateByPrimaryKey(PduClock record) {
        return 0;
    }

    public List<PduClock> selectAll(){
        List<PduClock> list = new ArrayList<PduClock>();

        try {
            list = getSqlSession().selectList("com.chuangkou.pdu.dao.PduClockMapper.selectAll");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<PduClock> selectPduClockByPduId(Integer pduid) {
        List<PduClock> pduClockList = getSqlSession().selectList("com.chuangkou.pdu.dao.PduClockMapper.selectPduClockByPduId", pduid);
        return  pduClockList;
    }

    /**
     *@Author:xulei
     *@Description:根据日期和时间 查找对应的设备定时任务
     *@Date:  2018-05-03
     * @param:设备ID ，当前日期  当前时间
     */
    public PduClock selectPduClockTask(PduClock pduClock) {

        PduClock rpduClock = new PduClock();

        try {
            rpduClock = getSqlSession().selectOne("com.chuangkou.pdu.dao.PduClockMapper.selectPduClockTask", pduClock);
        } catch (Exception e) {
                e.printStackTrace();
        }

        return rpduClock;
    }

}
