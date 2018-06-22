package com.chuangkou.pdu.dao;

import com.chuangkou.pdu.entity.Role;
import org.mybatis.spring.annotation.MapperScan;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@MapperScan
public interface RoleMapper {

    /**
     * 查询所有角色
     * @return
     * @throws Exception
     */
    List<Role> selectALL();
//    /**
//     * 根据id删除角色
//     * @return
//     * @throws Exception
//     */
//    int deleteOne(HttpServletRequest request, int id) ;
    /**
     * 根据用户名编辑角色
     * @return
     * @throws Exception
     */
    Role update(Role role);
    Role update1( Role role);
    /**
     * 增加角色
     * @return
     * @throws Exception
     */
    Role saveRole(Role role);
//    /**
//     * 根据id查找角色
//     * @return
//     * @throws Exception
//     */
//    Role findRoleById(int id);




    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);


    Role selectByUsername(String username);
}