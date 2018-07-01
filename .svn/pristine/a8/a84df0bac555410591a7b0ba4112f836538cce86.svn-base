package com.chuangkou.pdu.service;

import com.chuangkou.pdu.dao.PduSelfcheckMapper;
import com.chuangkou.pdu.entity.PduSelfcheck;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class PduSelfcheckService extends SqlSessionDaoSupport implements PduSelfcheckMapper{
    @Autowired
    private PduSelfcheckMapper pduSelfcheckMapper;
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
    public List<PduSelfcheck> selectALL() {
        List<PduSelfcheck> pduSelfchecks = getSqlSession().selectList("com.chuangkou.pdu.dao.PduSelfcheckMapper.selectall");
        return pduSelfchecks;
    }
    /**
     * 根据 id 查询
     *
     * @return
     * @throws Exception
     */
    public PduSelfcheck selectById(int id) {
        PduSelfcheck pduSelfcheck  = getSqlSession().selectOne("com.chuangkou.pdu.dao.PduSelfcheckMapper.selectOne", id);
        return pduSelfcheck;
    }
    /**
     * 根据 id 删除
     *
     * @return
     * @throws Exception
     */
    public int deleteOne(int id) {
        int deletePduSelfcheck = getSqlSession().delete("com.chuangkou.pdu.dao.PduSelfcheckMapper.deleteById",id);
        return deletePduSelfcheck;
    }

    /**
     * 新增用户用户
     *

     * @return
     * @throws Exception
     */
    public PduSelfcheck savePduSelfcheck(PduSelfcheck pduSelfcheck){
        getSqlSession().insert("com.chuangkou.pdu.dao.PduSelfcheckMapper.savePduSelfcheck", pduSelfcheck);
        return pduSelfcheck;
    }

    /**
     * 最大id 查询
     *
     * @return
     * @throws Exception
     */
    @Override
    public int selectMaxId() {
        int MaxId =getSqlSession().selectOne("com.chuangkou.pdu.dao.PduSelfcheckMapper.selectidmax");
        return MaxId;
    }

    @Override
    public PduSelfcheck update(PduSelfcheck pduSelfcheck) {
        int update = getSqlSession().update("com.chuangkou.pdu.dao.PduSelfcheckMapper.updateByPrimaryKeySelective");
        return pduSelfcheck;
    }


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(PduSelfcheck record) {
        return 0;
    }

    @Override
    public int insertSelective(PduSelfcheck record) {
        return 0;
    }

    @Override
    public PduSelfcheck selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(PduSelfcheck record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(PduSelfcheck record) {
        return 0;
    }
}
