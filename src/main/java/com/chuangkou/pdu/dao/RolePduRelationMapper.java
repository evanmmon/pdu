package com.chuangkou.pdu.dao;

import com.chuangkou.pdu.entity.PduPermissions;
import com.chuangkou.pdu.entity.RolePduRelation;

import java.util.List;

public interface RolePduRelationMapper {
    int insert(RolePduRelation record);

    int insertSelective(RolePduRelation record);

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<PduPermissions> selectALL(int id);

    RolePduRelation saveRolePduRelation(RolePduRelation rolePduRelation);

    RolePduRelation update( RolePduRelation rolePduRelation);
}