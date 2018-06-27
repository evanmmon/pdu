package com.chuangkou.pdu.service;

import com.chuangkou.pdu.dao.UserMapper;
import com.chuangkou.pdu.entity.User;
import com.chuangkou.pdu.util.LogUtil;
import com.chuangkou.pdu.util.MyException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class UserService  extends SqlSessionDaoSupport implements UserMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    /**
     * 查询全部用户  用户列表
     *
     * @return
     *
     * @throws Exception
     */
    public List<User> selectALL() {
        List<User> users = getSqlSession().selectList("com.chuangkou.pdu.dao.UserMapper.selectall");

        return users;
    }

    /**
     * 根据id 查询 用户 信息
     *
     * @return
     * @throws Exception
     */


    public User findUserById(int id) {
       User user1 = getSqlSession().selectOne("com.chuangkou.pdu.dao.UserMapper.selectOne",id);
        return user1;
        
    }

    @Override
    public User selectOneByUserName(String username) {
        User user1 = getSqlSession().selectOne("com.chuangkou.pdu.dao.UserMapper.selectOneByUserName",username);
        return user1;
    }

    @Override
    public User updatePassword(User user) {
       getSqlSession().update("com.chuangkou.pdu.dao.UserMapper.updatePasswordByUname", user);
       return user;
    }

    /**
     * 根据 id 删除 用户
     *
     * @return
     * @throws Exception
     */
    public int deleteOne(HttpServletRequest request, int id) {
        int deleteUser = getSqlSession().delete("com.chuangkou.pdu.dao.UserMapper.deleteById",id);
        LogUtil.addLog(request, "删除用户");
        return deleteUser;
    }


    /**
     * 根据 id  编辑 用户
     *

     * @return
     * @throws Exception
     */

    public User update(HttpServletRequest request, User user ) {
     getSqlSession().update("com.chuangkou.pdu.dao.UserMapper.update",user);
        LogUtil.addLog(request, "修改用户");
        return user;
    }
    /**
     * 根据 phone 编辑 用户
     *

     * @return
     * @throws Exception
     */

    public User update2(HttpServletRequest request, User user ) {
        getSqlSession().update("com.chuangkou.pdu.dao.UserMapper.update2",user);
        LogUtil.addLog(request, "修改用户");
        return user;
    }

    @Override
    public User update3(User user) {
        getSqlSession().update("com.chuangkou.pdu.dao.UserMapper.updateAvatarByUname",user);
        return user;
    }
//修改昵称
    @Override
    public User update4(User user) {
        getSqlSession().update("com.chuangkou.pdu.dao.UserMapper.updateNickNameByUserName",user);
        return user;
    }

    @Override
    public User update5(User user) {
        getSqlSession().update("com.chuangkou.pdu.dao.UserMapper.updatetokenByUserName",user);
        return user;
    }

    /**
     * 新增用户用户
     *

     * @return
     * @throws Exception
     */
    public User saveUser(HttpServletRequest request, User user){
     getSqlSession().insert("com.chuangkou.pdu.dao.UserMapper.saveUser", user);
        LogUtil.addLog(request, "添加用户");
        return user;
    }
    /**
     * 登录
     *
     * @return
     * @throws Exception
     */
    public void login(HttpServletRequest request, String username, String password) throws MyException{
        // 得到用户
        Subject user = SecurityUtils.getSubject();

        // 创建用户名和密码的验证方式的token
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 登录
        try {
            user.login(token);
            // 获取用户session
            User cuurentUser = this.findUserByUsername(username);
            request.getSession().setAttribute("user",cuurentUser);
            LogUtil.addLog(request, "登录系统");
            System.out.println("登录成功");
        }catch (AuthenticationException e){
            e.printStackTrace();

            System.out.println("登录失败");
            throw new MyException("E1001");
        }
    }

   public  User findUserByUsername(String username){
        User user = getSqlSession().selectOne("com.chuangkou.pdu.dao.UserMapper.selectByUsername", username);
        return user;
   }

    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    public int insert(User record) {
        return 0;
    }

    public int insertSelective(User record) {
        return 0;
    }

    public User selectByPrimaryKey(Integer id) {
        return null;
    }

    public int updateByPrimaryKeySelective(User record) {
        return 0;
    }

    public int updateByPrimaryKey(User record) {
        return 0;
    }
}














//    public void login(String username, String password) throws MyException {
//        // 得到用户
//        Subject user = SecurityUtils.getSubject();
//
//        // 创建用户名和密码的验证方式的token
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//
//        // 登录
//        try {
//            user.login(token);
//            System.out.println("登录成功");
//        }catch (AuthenticationException e){
//            e.printStackTrace();
//            System.out.println("登录失败");
//            throw new MyException("E1001");
//        }
//    }

