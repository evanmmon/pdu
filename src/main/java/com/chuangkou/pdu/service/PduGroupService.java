package com.chuangkou.pdu.service;

import com.chuangkou.pdu.dao.PduGroupMapper;
import com.chuangkou.pdu.entity.PduGroup;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class PduGroupService extends SqlSessionDaoSupport implements PduGroupMapper {
    @Autowired
    private PduGroupMapper pduGroupMapper;
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }



    @Override
    public List<PduGroup> selectALL() {
        List<PduGroup> PduGroup = getSqlSession().selectList("com.chuangkou.pdu.dao.PduGroupMapper.selectall");
        return PduGroup;
    }



    @Override
    public int deleteOne(int id) {
        int deletePduGroup = getSqlSession().delete("com.chuangkou.pdu.dao.PduGroupMapper.deleteById",id);
        return deletePduGroup;
    }

    @Override
    public PduGroup savePduGroup(PduGroup pduGroup) {
        getSqlSession().insert("com.chuangkou.pdu.dao.PduGroupMapper.savePduGroup", pduGroup);
        return pduGroup;
    }

    @Override
    public int selectMaxId() {
        int MaxId =getSqlSession().selectOne("com.chuangkou.pdu.dao.PduGroupMapper.selectidmax");
        return MaxId;
    }

    @Override
    public PduGroup update(PduGroup pduGroup) {
       getSqlSession().update("com.chuangkou.pdu.dao.PduGroupMapper.update",pduGroup);
       return pduGroup ;
    }

    @Override
    public PduGroup findPduGroupById(int id) {
        PduGroup pduGroup = getSqlSession().selectOne("com.chuangkou.pdu.dao.PduGroupMapper.selectOne",id);
        return pduGroup;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(PduGroup record) {
        return 0;
    }

    @Override
    public int insertSelective(PduGroup record) {
        return 0;
    }

    @Override
    public PduGroup selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(PduGroup record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(PduGroup record) {
        return 0;
    }
}
