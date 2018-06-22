package com.chuangkou.pdu.dao;

import com.chuangkou.pdu.entity.Pdu;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan
public interface PduMapper {
    /**
     * 根据id 批量查询pdu
     * @return
     * @throws Exception
     */
    List<Pdu> selectByIds(List<Integer> ids);
    //查询设备数量
    int  selectzongshu();

    int yunxingzhong();
    int yiguanbi();
    int guzhang();
    int lixian();
    List<Pdu>selectPduList();
    Pdu selectByIp(Integer id);
    List<Pdu> selectquanxian();
    int deleteByPrimaryKey(Integer id);
    Pdu updateById(Pdu pdu);
    int insert(Pdu record);

    int insertSelective(Pdu record);

    Pdu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Pdu record);

    int updateByPrimaryKey(Pdu record);

    List<Pdu> selectAll();

}