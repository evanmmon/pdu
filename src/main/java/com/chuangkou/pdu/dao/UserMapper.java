package com.chuangkou.pdu.dao;

import com.chuangkou.pdu.entity.User;
import org.mybatis.spring.annotation.MapperScan;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@MapperScan
public interface UserMapper {
    /**
     * 查询所有用户
     * @return
     * @throws Exception
     */
    List<User> selectALL();
    /**
     * 根据id删除用户
     * @return
     * @throws Exception
     */
    int deleteOne(HttpServletRequest request, int id) ;
    /**
     * 根据id编辑用户
     * @return
     * @throws Exception
     */
    User update2(HttpServletRequest request, User user);
    /**
     * 根据username编辑用户
     * @return
     * @throws Exception
     */
    User update3(User user);

    /**
     * 根据username编辑昵称
     * @return
     * @throws Exception
     */
    User update4(User user);

    /**
     * 根据username编辑token
     * @return
     * @throws Exception
     */
    User update5(User user);

    /**
     * 根据phone编辑用户
     * @return
     * @throws Exception
     */
    User update(HttpServletRequest request, User user);
    /**
     * 增加用户
     * @return
     * @throws Exception
     */
     User saveUser(HttpServletRequest request, User user);
    /**
     * 根据id查找用户
     * @return
     * @throws Exception
     */
     User findUserById (int id);

     User selectOneByUserName (String username);

     User updatePassword(User user);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}