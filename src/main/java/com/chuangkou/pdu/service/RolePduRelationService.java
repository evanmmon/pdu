package com.chuangkou.pdu.service;

import com.chuangkou.pdu.dao.RolePduRelationMapper;
import com.chuangkou.pdu.entity.PduPermissions;
import com.chuangkou.pdu.entity.RolePduRelation;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RolePduRelationService extends SqlSessionDaoSupport implements RolePduRelationMapper{
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    @Override
    public int insert(RolePduRelation record) {
        return 0;
    }

    @Override
    public int insertSelective(RolePduRelation record) {
        return 0;
    }


    // 角色 部门 设备 名称 ip 可读 可控
    @Override
    public List<PduPermissions> selectALL(int id) {
        List<PduPermissions> rolePduRelations = getSqlSession().selectList("com.chuangkou.pdu.dao.RolePduRelationMapper.selectall",id);
        return  rolePduRelations;
    }

    @Override
    public RolePduRelation saveRolePduRelation(RolePduRelation rolePduRelation) {
      getSqlSession().insert("com.chuangkou.pdu.dao.RolePduRelationMapper.insertSelective",rolePduRelation);
      return rolePduRelation;
    }

    @Override
    public RolePduRelation update(RolePduRelation rolePduRelation) {
      getSqlSession().update("com.chuangkou.pdu.dao.RolePduRelationMapper.update",rolePduRelation);
      return rolePduRelation;
    }


    /**
     *@Author: xulei
     *@Description: 根据角色ID 查找用户权限
     *@Date:  2018-06-19
     */
    public List<RolePduRelation> getRolePduRelationList(String username) {
        List<RolePduRelation>  list = new ArrayList<RolePduRelation>();

        try {
            list = getSqlSession().selectList("com.chuangkou.pdu.dao.RolePduRelationMapper.getRolePduRelationList",username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
