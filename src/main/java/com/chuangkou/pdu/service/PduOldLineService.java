package com.chuangkou.pdu.service;

import com.chuangkou.pdu.dao.PduOldLineMapper;
import com.chuangkou.pdu.entity.PduOldLine;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:
 * @Description:
 * @Date:Created in 18:20 2018/5/10
 */
@Component
public class PduOldLineService extends SqlSessionDaoSupport implements PduOldLineMapper {

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }


    @Override
    public int insert(PduOldLine pduOldLine) {
        int i = 0 ;
        try {
            i = getSqlSession().insert("com.chuangkou.pdu.dao.PduOldLineMapper",pduOldLine);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public PduOldLine selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKey(PduOldLine pduOldLine) {
        return 0;
    }


    /**
     *@Author:xulei
     *@Description:按日期查询设备老化记录
     *@Date:  2018-05-11
     */
    public List<PduOldLine> selectAllByHistoryData(PduOldLine pduOldLine) {
        List<PduOldLine> pduOldLinesList = new ArrayList<PduOldLine>();

        try {
            pduOldLinesList = getSqlSession().selectList("com.chuangkou.pdu.dao.PduOldLineMapper.selectAllByHistoryData",pduOldLine);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pduOldLinesList;
    }



    /**
     *@Author:xulei
     *@Description:按日期查询设备老化记录
     *@Date:  2018-05-11
     */
    public List<PduOldLine> selectAllByHistoryHours(PduOldLine pduOldLine) {
        List<PduOldLine> pduOldLinesList = new ArrayList<PduOldLine>();

        try {
            pduOldLinesList = getSqlSession().selectList("com.chuangkou.pdu.dao.PduOldLineMapper.selectAllByHistoryHours",pduOldLine);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pduOldLinesList;
    }
}
