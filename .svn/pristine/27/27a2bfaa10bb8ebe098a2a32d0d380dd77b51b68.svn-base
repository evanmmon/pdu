package com.chuangkou.pdu.service;


import com.chuangkou.pdu.dao.PduInfoMapper;
import com.chuangkou.pdu.entity.PduInfo;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:
 * @Description:
 * @Date:Created in 9:38 2018/3/7
 */
@Component
public class PduInfoService extends SqlSessionDaoSupport implements PduInfoMapper {
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }



    /**
     * @Author:xulei
     * @Description: 根据ID返回所有的设备状态
     * @Date: 2018-03-07
     */
    public List selectAllByPrimaryKey(Integer pduID) {
        List<PduInfo> list = new ArrayList<PduInfo>();

        try {

            list = getSqlSession().selectList("PduInfoMapper.selectAllByPrimaryKey", pduID);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * @Author:xulei
     * @Description:app 根据时间查询设备功率、电压、电流指数  按天平均数返回
     * @Date: 2018-03-21
     */

    public List selectAllByHistoryData(PduInfo pduInfo) {
        List<PduInfo> list = new ArrayList<PduInfo>();

        try {
            list = getSqlSession().selectList("PduInfoMapper.selectAllByHistoryData", pduInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     *@Author:
     *@Description:根据时间查询设备电压、功率、电流指数  按小时平均数返回
     *@Date:
     */

    public List selectAllByHistoryHours(PduInfo pduInfo) {
        List<PduInfo> list = new ArrayList<PduInfo>();

        try {
            list = getSqlSession().selectList("PduInfoMapper.selectAllByHistoryHours", pduInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public String wattzongshu(Integer pduid) {
        String watt = getSqlSession().selectOne("PduInfoMapper.selectBwatt", pduid);
        return watt;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(PduInfo record) {
        return 0;
    }

    @Override
    public int insertSelective(PduInfo record) {
        int i = 0;
        try {
            i = getSqlSession().insert("PduInfoMapper.insertSelective",record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public PduInfo selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(PduInfo record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(PduInfo record) {
        return 0;
    }



    public PduInfo selectByHoursAVG(Integer id) {
        PduInfo pduInfo = new PduInfo();

        try {
            pduInfo = getSqlSession().selectOne("PduInfoMapper.selectByHoursAVG", id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pduInfo;
    }
}
