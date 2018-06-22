package com.chuangkou.pdu.service;

import com.chuangkou.pdu.dao.PduSelfcheckInfoMapper;

import com.chuangkou.pdu.entity.PduSelfcheckInfo;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class PduSelfcheckInfoService extends SqlSessionDaoSupport implements PduSelfcheckInfoMapper {
    @Autowired
    private PduSelfcheckInfoMapper pduSelfcheckInfoMapper;
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    /**
     * 查询全部
     *
     * @return
     * @throws Exception
     */

    @Override
    public List<PduSelfcheckInfo> selectALL(int id) {
        List<PduSelfcheckInfo> pduSelfcheckInfos = getSqlSession().selectList("com.chuangkou.pdu.dao.PduSelfcheckInfoMapper.selectall",id);
        return pduSelfcheckInfos;
    }

    @Override
    public PduSelfcheckInfo savePduSelfcheckInfo(PduSelfcheckInfo pduSelfcheckInfo) {
        getSqlSession().insert("com.chuangkou.pdu.dao.PduSelfcheckInfoMapper.savePduSelfcheckInfo", pduSelfcheckInfo);
        return pduSelfcheckInfo;
    }

    @Override
    public List<PduSelfcheckInfo> selectPduIDbyPduSelfcheckID(int id) {
        List<PduSelfcheckInfo> pduids = getSqlSession().selectList("com.chuangkou.pdu.dao.PduSelfcheckInfoMapper.selectPduIdBySelfcheckId",id);
        return pduids;
    }

    @Override
    public int insert(com.chuangkou.pdu.entity.PduSelfcheckInfo record) {
        return 0;
    }

    @Override
    public int insertSelective(com.chuangkou.pdu.entity.PduSelfcheckInfo record) {
        return 0;
    }
}