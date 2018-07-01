package com.chuangkou.pdu.service;

import com.chuangkou.pdu.dao.PduWarningMapper;
import com.chuangkou.pdu.entity.PduWarning;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PduWarningService extends SqlSessionDaoSupport implements PduWarningMapper {

    @Autowired
    private PduWarningMapper pduWarningMapper;

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    /**
     * 查询全部列表
     *
     * @return
     *
     * @throws       Exception
     */
    @Override
    public List<PduWarning> selectALL() {
        List<PduWarning> pduWarnings = getSqlSession().selectList("com.chuangkou.pdu.dao.PduWarningMapper.selectall");

        return pduWarnings;
    }

    @Override
    public List<PduWarning> selectALL2() {
        List<PduWarning> pduWarnings = getSqlSession().selectList("com.chuangkou.pdu.dao.PduWarningMapper.selectall2");
        return pduWarnings;
    }

    @Override
    public int selectchulizhong() {
        int chulizhong =getSqlSession().selectOne("com.chuangkou.pdu.dao.PduWarningMapper.selectchulizhong");
        return chulizhong;
    }

    @Override
    public int selectyihulve() {
        int yihulve =getSqlSession().selectOne("com.chuangkou.pdu.dao.PduWarningMapper.selectyihulve");
        return yihulve;
    }

    @Override
    public int selectzongshu() {
        int zongshu =getSqlSession().selectOne("com.chuangkou.pdu.dao.PduWarningMapper.selectzongshu");
        return zongshu;
    }

    @Override
    public int selectweichulishu() {
        int weichulizongshu =getSqlSession().selectOne("com.chuangkou.pdu.dao.PduWarningMapper.selectweichulizongshu");
        return weichulizongshu;
    }

    @Override
    public int selectyichulishu() {
        int yichulizongshu =getSqlSession().selectOne("com.chuangkou.pdu.dao.PduWarningMapper.selectyichulizongshu");
        return yichulizongshu;
    }

    @Override
    public int selectjidianqi() {
        int jidianqi =getSqlSession().selectOne("com.chuangkou.pdu.dao.PduWarningMapper.selectjidianqi");
        return jidianqi;
    }

    @Override
    public int selectguoya() {
        int guoya =getSqlSession().selectOne("com.chuangkou.pdu.dao.PduWarningMapper.selectguoya");
        return guoya;
    }

    @Override
    public int selectqianya() {
        int qianya =getSqlSession().selectOne("com.chuangkou.pdu.dao.PduWarningMapper.selectqianya");
        return qianya;
    }

    @Override
    public int selectguoliu() {
        int guoliu =getSqlSession().selectOne("com.chuangkou.pdu.dao.PduWarningMapper.selectguoliu");
        return guoliu;
    }

    @Override
    public int selectduanlu() {
        int duanlu =getSqlSession().selectOne("com.chuangkou.pdu.dao.PduWarningMapper.selectduanlu");
        return duanlu;
    }

    @Override
    public int selectloudian() {
        int loudian =getSqlSession().selectOne("com.chuangkou.pdu.dao.PduWarningMapper.selectloudian");
        return loudian;
    }

    @Override
    public PduWarning update(PduWarning pduWarning) {
        getSqlSession().update("com.chuangkou.pdu.dao.PduWarningMapper.update",pduWarning);

        return pduWarning;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(PduWarning record) {

        try {
            getSqlSession().insert("com.chuangkou.pdu.dao.PduWarningMapper.insertSelective",record);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int insertSelective(PduWarning record) {
        return 0;
    }

    @Override
    public PduWarning selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(PduWarning record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(PduWarning record) {
        return 0;
    }
}