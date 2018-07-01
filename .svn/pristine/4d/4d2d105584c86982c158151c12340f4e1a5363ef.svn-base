package com.chuangkou.pdu.service;

import com.chuangkou.pdu.dao.DictionaryMapper;
import com.chuangkou.pdu.entity.Dictionary;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DictionaryService extends SqlSessionDaoSupport implements DictionaryMapper {
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    @Override
    public int deleteByPrimaryKey(Integer id) {
        //判断是否有子节点，有子节点递归删除子节点
        List<Dictionary> dictionaryList = this.selectByParentid(id);
        if(null != dictionaryList && dictionaryList.size() > 0){
            for (Dictionary dictionary : dictionaryList) {
                this.deleteByPrimaryKey(dictionary.getId());
            }
        }
        int delete = getSqlSession().delete("com.chuangkou.pdu.dao.DictionaryMapper.deleteByPrimaryKey", id);
        return delete;
    }


    @Override
    public int insert(Dictionary record) {
        int insert = getSqlSession().insert("com.chuangkou.pdu.dao.DictionaryMapper.insert", record);
        return insert;
    }

    @Override
    public int insertSelective(Dictionary record) {
        int insert = getSqlSession().insert("com.chuangkou.pdu.dao.DictionaryMapper.insertSelective", record);
        return insert;
    }

    @Override
    public Dictionary selectByPrimaryKey(Integer id) {
        Dictionary dictionary = getSqlSession().selectOne("com.chuangkou.pdu.dao.DictionaryMapper.selectByPrimaryKey", id);
        return dictionary;
    }


    @Override
    public int updateByPrimaryKeySelective(Dictionary record) {
        int update = getSqlSession().update("com.chuangkou.pdu.dao.DictionaryMapper.updateByPrimaryKeySelective", record);
        return update;
    }

    @Override
    public int updateByPrimaryKey(Dictionary record) {
        int update = getSqlSession().update("com.chuangkou.pdu.dao.DictionaryMapper.updateByPrimaryKey", record);
        return update;
    }

    //根据parentid查询
    @Override
    public List<Dictionary> selectByParentid(Integer parentid) {
        List<Dictionary> dictionaryList = getSqlSession().selectList("com.chuangkou.pdu.dao.DictionaryMapper.selectByParentid", parentid);
        return dictionaryList;
    }

    @Override
    public List<Dictionary> selectAll() {
        List<Dictionary> dictionaryList = getSqlSession().selectList("com.chuangkou.pdu.dao.DictionaryMapper.selectAll");
        return dictionaryList;
    }
}
