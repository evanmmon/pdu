package com.chuangkou.pdu.service;

import com.chuangkou.pdu.dao.MpermissionsMapper;
import com.chuangkou.pdu.entity.Mpermissions;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class MpermissionsService extends SqlSessionDaoSupport implements MpermissionsMapper{
    @Autowired
    private MpermissionsMapper mpermissionsMapper;
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public List<Mpermissions> selectALL() {
        List<Mpermissions>mpermissionsList =getSqlSession().selectList("com.chuangkou.pdu.dao.MpermissionsMapper.selectall");
        return mpermissionsList;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Mpermissions record) {
        return 0;
    }

    @Override
    public int insertSelective(Mpermissions record) {
        return 0;
    }

    @Override
    public Mpermissions selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Mpermissions record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(Mpermissions record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Mpermissions record) {
        return 0;
    }
}
