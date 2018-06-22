package com.chuangkou.pdu.service;

import com.chuangkou.pdu.dao.PduAutomaticInfoMapper;
import com.chuangkou.pdu.entity.PduAutomaticInfo;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Kanyuanfeng
 * @Description: 自动任务记录表业务
 * @Date: 2018/3/29
 * @Modified By:
 */
@Component
public class PduAutomaticInfoService extends SqlSessionDaoSupport implements PduAutomaticInfoMapper {
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    @Override
    public int insert(PduAutomaticInfo pduAutomaticInfo) {
        return 0;
    }

    @Override
    public int insertSelective(PduAutomaticInfo pduAutomaticInfo) {
        return 0;
    }
}
