package com.chuangkou.pdu.dao;

import com.chuangkou.pdu.entity.Mpermissions;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
@MapperScan
public interface MpermissionsMapper {
    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<Mpermissions> selectALL();

    int deleteByPrimaryKey(Integer id);

    int insert(Mpermissions record);

    int insertSelective(Mpermissions record);

    Mpermissions selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Mpermissions record);

    int updateByPrimaryKeyWithBLOBs(Mpermissions record);

    int updateByPrimaryKey(Mpermissions record);
}