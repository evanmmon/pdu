package com.chuangkou.pdu.service;

import com.chuangkou.pdu.dao.PduWarningSetMapper;
import com.chuangkou.pdu.entity.PduWarningSet;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PduWarningSetService extends SqlSessionDaoSupport implements PduWarningSetMapper {

    @Autowired
    private PduWarningSetMapper pduWarningSetMapper;

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insert(PduWarningSet record) {
        return 0;
    }

    @Override
    public int insertSelective(PduWarningSet record) {
        return 0;
    }


    @Override
    public PduWarningSet update(PduWarningSet pduWarningSet) {
        try {
            getSqlSession().update("com.chuangkou.pdu.dao.PduWarningSetMapper.update", pduWarningSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pduWarningSet;
    }

    @Override
    public PduWarningSet findPduWarningSetById(int id) {
        PduWarningSet pduWarningSet = getSqlSession().selectOne("com.chuangkou.pdu.dao.PduWarningSetMapper.selectOne",id);
        return pduWarningSet;
    }

    @Override
    public PduWarningSet savePduWarningSetr(PduWarningSet pduWarningSet) {
        getSqlSession().insert("com.chuangkou.pdu.dao.PduWarningSetMapper.insertSelective",pduWarningSet);
        return pduWarningSet;



    }

    /**
     *@Author:xulei
     *@Description:根据设备ID返回设备的预警设置信息
     *@Date:2018-02-26
     */
    public PduWarningSet selectByPduWarningSet(Integer pduid){
        PduWarningSet pduWarningSet = new PduWarningSet();

        try {
            pduWarningSet = getSqlSession().selectOne("com.chuangkou.pdu.dao.PduWarningSetMapper.selectByPduWarningSet",pduid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pduWarningSet;
    }

    /**
     *@Author:xulei
     *@Description:修改保存设备预警设置值
     *@Date:
     */
    public void updateByPduWarningSet(PduWarningSet pduWarningSet) throws Exception {

        try {
            getSqlSession().update("com.chuangkou.pdu.dao.PduWarningSetMapper.updateByPduWarningSet", pduWarningSet);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}