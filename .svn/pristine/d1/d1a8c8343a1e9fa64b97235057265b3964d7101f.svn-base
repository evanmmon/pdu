package com.chuangkou.pdu.service;

import com.chuangkou.pdu.dao.PduTemporaryMapper;
import com.chuangkou.pdu.entity.Pdu;
import com.chuangkou.pdu.entity.PduTemporary;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:xulei
 * @Description:pud设备临时表
 * @Date:Created in 9:25 2018/2/8
 */
@Component
public class PduTemporaryService extends SqlSessionDaoSupport implements PduTemporaryMapper {
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }


    /**
     * @Author:xulei
     * @Description:查找PduTemp所有待添加的新设备
     * @Date:2018-02-08
     */
    public List<PduTemporary> selectAll() throws Exception {
        List<PduTemporary> list = new ArrayList<PduTemporary>();
        try {
            list = getSqlSession().selectList("com.chuangkou.pdu.dao.PduTemporaryMapper.selectByAll");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    /**
     * @Author:xulei
     * @Description:根据设备ID，返回新设备的信息
     * @Date:2018-02-08
     */
    public PduTemporary selectByPduTemporary(String machineid) throws Exception {
        PduTemporary pduTemporary = null;
        List<PduTemporary> list = new ArrayList<PduTemporary>();
        try {

            pduTemporary = getSqlSession().selectOne("com.chuangkou.pdu.dao.PduTemporaryMapper.selectByPduTemporary", machineid);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }

        return pduTemporary;
    }

    /**
     * @Author:xulei
     * @Description:根据设备ID，返回新设备的信息
     * @Date:2018-02-08
     */
    public PduTemporary selectByPduTemporary2(String machineid) throws Exception {
        PduTemporary pduTemporary = null;
        List<PduTemporary> list = new ArrayList<PduTemporary>();
        try {
//            System.out.println(getSqlSession());
            pduTemporary = getSqlSession().selectOne("com.chuangkou.pdu.dao.PduTemporaryMapper.selectByPduTemporary", machineid);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }

        return pduTemporary;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    /**
     * @Author:xulei
     * @Description:添加设备
     * @Date: 2018-02-08
     */
    public int insert(PduTemporary pduTemporary) {
        int i = 0;
        try {
            System.out.println("添加临时设备。。。" + pduTemporary.getMachineid());
            i = getSqlSession().insert("com.chuangkou.pdu.dao.PduTemporaryMapper.insert", pduTemporary);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int insertSelective(PduTemporary record) {
        return 0;
    }

    @Override
    public PduTemporary selectByPrimaryKey(Integer id) {
        PduTemporary pduTemporary = new PduTemporary();

        try {
            pduTemporary = getSqlSession().selectOne("com.chuangkou.pdu.dao.PduTemporaryMapper.selectByPrimaryKey", id);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return pduTemporary;
    }

    @Override
    public int updateByPrimaryKeySelective(PduTemporary record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(PduTemporary record) {
        return 0;
    }

    /**
     * @Author:xulei
     * @Description:删除设备后，关联的修改临时设备表的记录状态为O
     * @Date: 2018-02-24
     */
    public void delete(String machineid) throws Exception {

        try {
            getSqlSession().update("com.chuangkou.pdu.dao.PduTemporaryMapper.updateByPduTemporaryState", machineid);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * @Author:xulei
     * @Description:修改临时表设备状态为1
     * @Date: 2018-02-24
     */
    public void updateStateAdd(String machineid) throws Exception {

        try {
            getSqlSession().update("com.chuangkou.pdu.dao.PduTemporaryMapper.updateStateAdd", machineid);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
