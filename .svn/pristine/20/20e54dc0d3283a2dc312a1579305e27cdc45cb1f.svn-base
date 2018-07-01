package com.chuangkou.pdu.dao;

import com.chuangkou.pdu.entity.Organization;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
@MapperScan
public interface OrganizationMapper {
    /**
     * 查询所有
     * @throws Exception
     */
    List<Organization> selectALL();
    /**
     * 根据id删除
     * @return
     * @throws Exception
     */
    int deleteOne(int id) ;
    /**
     * 根据id编辑
     * @return
     * @throws Exception
     */
    Organization update(Organization organization);
    /**
     * 增加角色
     * @return
     * @throws Exception
     */
    Organization saveOrganization(Organization organization);
    /**
     * 根据id查找
     * @return
     * @throws Exception
     */
    Organization findOrganizationById (int id);


    int deleteByPrimaryKey(Integer id);

    int insert(Organization record);

    int insertSelective(Organization record);

    Organization selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);
}