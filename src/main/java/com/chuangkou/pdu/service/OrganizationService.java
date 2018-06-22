package com.chuangkou.pdu.service;

import com.chuangkou.pdu.dao.OrganizationMapper;
import com.chuangkou.pdu.entity.Organization;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class OrganizationService extends SqlSessionDaoSupport implements OrganizationMapper {
    @Autowired
    private  OrganizationMapper organizationMapper;
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    @Override
    public List<Organization> selectALL() {
        List<Organization> organizations = getSqlSession().selectList("com.chuangkou.pdu.dao.OrganizationMapper.selectall");
        return organizations;
    }

    @Override
    public int deleteOne(int id) {
        int deleteOrganization = getSqlSession().delete("com.chuangkou.pdu.dao.OrganizationMapper.deleteById",id);
        return deleteOrganization;
    }

    @Override
    public Organization update(Organization organization) {
        getSqlSession().update("com.chuangkou.pdu.dao.OrganizationMapper.update",organization);

        return organization;
    }

    @Override
    public Organization saveOrganization(Organization organization) {
        getSqlSession().insert("com.chuangkou.pdu.dao.OrganizationMapper.saveOrganization", organization);
        return organization;
    }

    @Override
    public Organization findOrganizationById(int id) {
        Organization organization1 = getSqlSession().selectOne("com.chuangkou.pdu.dao.OrganizationMapper.selectOne",id);
        return organization1;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Organization record) {
        return 0;
    }

    @Override
    public int insertSelective(Organization record) {
        return 0;
    }

    @Override
    public Organization selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Organization record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Organization record) {
        return 0;
    }
}
