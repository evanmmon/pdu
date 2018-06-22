package com.chuangkou.pdu.service;

import com.chuangkou.pdu.entity.Log;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import com.chuangkou.pdu.dao.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class LogService extends SqlSessionDaoSupport implements LogMapper{
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    @Override
    public boolean deleteByPrimaryKey(Integer id) {
        try {
            getSqlSession().delete("com.chuangkou.pdu.dao.LogMapper.deleteByPrimaryKey", id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insert(Log record) {
        try {
            getSqlSession().insert("com.chuangkou.pdu.dao.LogMapper.insert", record);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Log> findAll() {
       List<Log> logList = getSqlSession().selectList("com.chuangkou.pdu.dao.LogMapper.findAll");
        return logList;
    }

    @Override
    public boolean insertSelective(Log record) {
        return true;
    }

    @Override
    public Log selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public boolean updateByPrimaryKeySelective(Log record) {
        return true;
    }

    @Override
    public boolean updateByPrimaryKey(Log record) {
        return true;
    }

}
