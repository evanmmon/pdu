package com.chuangkou.pdu.service;

import com.chuangkou.pdu.dao.RoleMapper;
import com.chuangkou.pdu.entity.Role;
import com.chuangkou.pdu.util.LogUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Component
public class RoleService extends SqlSessionDaoSupport implements RoleMapper{
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    /**
     * 查询全部角色
     *
     * @return
     * @throws Exception
     */
    public List<Role> selectALL() {
        List<Role> roles = getSqlSession().selectList("com.chuangkou.pdu.dao.RoleMapper.selectall");
        return roles;

    }
//    /**
//     * 根据id 查询角色 信息
//     *
//     * @return
//     * @throws Exception
//     */


//    public Role findRoleById (int id) {
//        Role role1 = getSqlSession().selectOne("com.chuangkou.pdu.dao.RoleMapper.selectOne",id);
//      return role1;
//
//    }
//    /**
//     * 根据 id 删除 角色
//     *
//     * @return
//     * @throws Exception
//     */
//    public int deleteOne(HttpServletRequest request, int id) {
//
//        int deleteRole = getSqlSession().delete("com.chuangkou.pdu.dao.RoleMapper.deleteById",id);
//        LogUtil.addLog(request, "删除角色");
//        return deleteRole;
//    }
    /**
     * 根据 id  编辑 角色
     *

     * @return
     * @throws Exception
     */

    public Role update(Role role) {
        getSqlSession().update("com.chuangkou.pdu.dao.RoleMapper.update",role);
        LogUtil.addLog("修改角色");
        return role;
    }

    @Override
    public Role update1(Role role) {
        getSqlSession().update("com.chuangkou.pdu.dao.RoleMapper.update",role);
        return role;
    }

    /**
     * 新增用户用户
     *

     * @return
     * @throws Exception
     */
    public Role saveRole(Role role){
        getSqlSession().insert("com.chuangkou.pdu.dao.RoleMapper.saveRole", role);
        return role;
    }
    /**
     * @Author: kanyuanfeng
     * @Date: 2018/4/17
     * @Description:根据角色id查找角色
     *
     */
    public Role selectByPrimaryKey(Integer id) {
        Role role = getSqlSession().selectOne("com.chuangkou.pdu.dao.RoleMapper.selectOne", id);
        return role;
    }

    @Override
    public Role selectByUsername(String username) {
        Role role = getSqlSession().selectOne("com.chuangkou.pdu.dao.RoleMapper.selectByUsername", username);
        return role;
    }


    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }


    public int insert(Role record) {
        return 0;
    }


    public int insertSelective(Role record) {
        return 0;
    }





    public int updateByPrimaryKeySelective(Role record) {
        return 0;
    }


    public int updateByPrimaryKey(Role record) {
        return 0;
    }

}
