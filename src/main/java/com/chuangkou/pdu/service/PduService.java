package com.chuangkou.pdu.service;

import com.chuangkou.pdu.dao.PduMapper;
import com.chuangkou.pdu.entity.Pdu;
import com.chuangkou.pdu.entity.PduInfoTemp;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class PduService extends SqlSessionDaoSupport implements PduMapper {
    //    @Resource(name = "dao")
//    private DaoSupport dao;
//    @Resource(name = "sqlSessionTemplate")
//    private SqlSessionTemplate sqlSessionTemplate;
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    /*
    * 查询所有PDU
    * */
    public List<Pdu> selectAll() {

//        return (List<Pdu>) dao.selectAll("PduMapper.selectAll");
        List<Pdu> list = new ArrayList<Pdu>();
        try {
//            list = sqlSessionTemplate.selectList("PduMapper.selectAll");
            list = getSqlSession().selectList("PduMapper.selectAll");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Pdu updateById(Pdu pdu) {
        getSqlSession().update("PduMapper.updateOnlinestate", pdu);
        return pdu;
    }

    @Override
    public List<Pdu> selectByIds(List<Integer> ids) {
        List<Pdu> pdus = getSqlSession().selectList("PduMapper.findPduListByIds", ids);
        return pdus;
    }

    @Override
    public int selectzongshu() {
        int pdushuliang = getSqlSession().selectOne("PduMapper.selectzongshu");
        return pdushuliang;
    }

    @Override
    public int yunxingzhong() {
        int yunxingzhong = getSqlSession().selectOne("PduMapper.yunxingzhong");
        return yunxingzhong;
    }

    @Override
    public int yiguanbi() {
        int yiguanbi = getSqlSession().selectOne("PduMapper.yiguanbi");
        return yiguanbi;
    }

    @Override
    public int guzhang() {
        int guzhang = getSqlSession().selectOne("PduMapper.guzhang");
        return guzhang;
    }

    @Override
    public int lixian() {
        int lixian = getSqlSession().selectOne("PduMapper.lixian");
        return lixian;
    }

    @Override
    public List<Pdu> selectPduList() {
        List<Pdu> pdus = getSqlSession().selectList("PduMapper.selectpdulist");
        return pdus;
    }

    @Override
    public Pdu selectByIp(Integer id) {
        Pdu pdus = getSqlSession().selectOne("PduMapper.selectAllByIp", id);
        return pdus;
    }

    @Override
    public List<Pdu> selectquanxian() {
        List<Pdu> pduquanxian = getSqlSession().selectList("PduMapper.selectquanxian");
        return pduquanxian;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    /**
     * @Author:xulei
     * @Description: 插入pdu记录
     * @Date: 2018-02-08
     */
    public int insert(Pdu pdu) {
        int i = 0;
        try {
            i = getSqlSession().insert("PduMapper.insertSelective", pdu);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        dao.save("PduMapper.insertSelective", pdu);
        return i;
    }

    @Override
    public int insertSelective(Pdu record) {
        return 0;
    }

    /**
     * @Author:xulei
     * @Description:根据ID查询单条记录
     * @Date:2018-02-24
     */
    @Override
    public Pdu selectByPrimaryKey(Integer id) {
        Pdu pdu = new Pdu();
        try {
            pdu = getSqlSession().selectOne("PduMapper.selectByPrimaryKey", id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pdu;
    }

    @Override
    public int updateByPrimaryKeySelective(Pdu record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Pdu record) {
        return 0;
    }

    /**
     * @Author:xulei
     * @Description:修改pdu记录
     * @Date:2018-02-08
     */
    public void update(Pdu pdu) throws Exception {

        try {
            getSqlSession().update("PduMapper.updateByPrimaryKeySelective", pdu);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @Author:xulei
     * @Description:删除设备,实际修改设备的状态为O
     * @Date: 2018-02-08
     */
    public void delete(Pdu pdu) throws Exception {

        try {
            getSqlSession().update("PduMapper.updateByPduState", pdu);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @Author:xulei
     * @Description:根据ID查询单条记录
     * @Date:2018-02-24
     */
    public Pdu selectByMachineID(String mchineID) {
        Pdu pdu = new Pdu();
        try {
            pdu = getSqlSession().selectOne("PduMapper.selectByMachineID", mchineID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pdu;
    }

    /**
     * @Author:xulei
     * @Description:根据ID查询单条记录
     * @Date:2018-02-24
     */

    public void updateStateView(Pdu pdu) throws Exception {

        try {
            getSqlSession().update("PduMapper.updateStateView", pdu);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @Author:xulei
     * @Description:根据二维码查询单条记录
     * @Date:2018-02-24
     */
    public Pdu selectByQrcode(String mchineID) {
        Pdu pdu = new Pdu();
        try {
            pdu = getSqlSession().selectOne("PduMapper.selectByQrcode", mchineID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pdu;
    }

    /**
     * @Author: xulei
     * @Description: 手机接口获取所有设备详细信息，包括当前状态、分组、基本信息等
     * @Date: 2018-03-08
     */


    public List<PduInfoTemp> pduInfoAllList(String groupid,String access_token) {

        List<PduInfoTemp> InfoTempList = new ArrayList<PduInfoTemp>();
//        groupid = "1";
        PduInfoTemp pduInfoTemp = new PduInfoTemp();
        pduInfoTemp.setPdugroupid(Integer.valueOf(groupid));
        pduInfoTemp.setAccess_token(access_token);
        try {
            if (groupid.equals("0")) {
                InfoTempList = getSqlSession().selectList("PduMapper.pduInfoAllList",pduInfoTemp);
            } else {
                InfoTempList = getSqlSession().selectList("PduMapper.pduInfoAllListTwo", pduInfoTemp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return InfoTempList;
    }

    /**
     * @Author:xulei
     * @Description:根据设备ID获取设备的详细信息
     * @Date: 208-03-09
     */
    public PduInfoTemp pduInfoByPrimaryKey(Integer pduid) {
        PduInfoTemp pduInfoTemp = new PduInfoTemp();

        try {
            pduInfoTemp = getSqlSession().selectOne("PduMapper.pduInfoByPrimaryKey", pduid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pduInfoTemp;
    }

    /**
     *@Author:xulei
     *@Description:数据库计算查询 获取设备的当月用电量
     *@Date:  2018-06-12
     */
    public PduInfoTemp pduInfoGetMonthQuantity(Integer pduid) {
        PduInfoTemp monthQuantity = new PduInfoTemp();

        try {
            monthQuantity = getSqlSession().selectOne("PduMapper.pduInfoGetMonthQuantity", pduid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return monthQuantity;
    }


    /**
     * @Author:xulei
     * @Description:根据APP关键字获取设备的详细信息
     * @Date: 208-03-09
     */
    public List<PduInfoTemp> selectByPduKeyword(Map map) {
//        PduInfoTemp pduInfoTemp = new PduInfoTemp();
        List<PduInfoTemp> list = new ArrayList<PduInfoTemp>();
        try {
            list = getSqlSession().selectList("PduMapper.selectByPduKeyword", map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    /*
       * 查询所有状态state为1 并且online状态为1
       *
       * */
    public List<Pdu> selectAllByPduOnline() {

//        return (List<Pdu>) dao.selectAll("PduMapper.selectAll");
        List<Pdu> list = new ArrayList<Pdu>();
        try {
//            list = sqlSessionTemplate.selectList("PduMapper.selectAll");
            list = getSqlSession().selectList("PduMapper.selectAllByPduOnline");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    /**
     *@Author:xulei
     *@Description: 根据查询条件搜索设备
     *@Date:  2018-05-09
     */
    public List<Pdu> selectAllBySearch(Pdu pdu) {

//        return (List<Pdu>) dao.selectAll("PduMapper.selectAll");
        List<Pdu> list = new ArrayList<Pdu>();
        try {

//            list = sqlSessionTemplate.selectList("PduMapper.selectAll");
            list = getSqlSession().selectList("PduMapper.selectAllBySearch",pdu);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     *@Author:xulei
     *@Description: 查询不是空开的所有插座设备
     *@Date:  2018-05-09
     */
    public List<Pdu> selectAllByNotSwitch(Pdu pdu) {

//        return (List<Pdu>) dao.selectAll("PduMapper.selectAll");
        List<Pdu> list = new ArrayList<Pdu>();
        try {

//            list = sqlSessionTemplate.selectList("PduMapper.selectAll");
            list = getSqlSession().selectList("PduMapper.selectAllByNotSwitch",pdu);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }



}
