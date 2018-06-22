package com.chuangkou.pdu.service;

import com.chuangkou.pdu.dao.SceneMapper;
import com.chuangkou.pdu.entity.Scene;
import com.chuangkou.pdu.entity.SceneExample;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SceneService extends SqlSessionDaoSupport implements SceneMapper {
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    @Override
    public int countByExample(SceneExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(SceneExample example) {
        getSqlSession().delete("com.chuangkou.pdu.dao.SceneMapper.deleteByExample", example);
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        getSqlSession().delete("com.chuangkou.pdu.dao.SceneMapper.deleteByPrimaryKey", id);
        return 0;
    }

    @Override
    public int insert(Scene insert) {
        getSqlSession().insert("com.chuangkou.pdu.dao.SceneMapper.insert", insert);
        return 0;
    }

    @Override
    public int insertSelective(Scene scene) {
        int id = getSqlSession().insert("com.chuangkou.pdu.dao.SceneMapper.insertSelective", scene);
        return id;
    }

    @Override
    public List<Scene> selectByExample(SceneExample example) {
        List<Scene> sceneList = getSqlSession().selectList("com.chuangkou.pdu.dao.SceneMapper.selectByExample", example);
        return sceneList;
    }

    @Override
    public Scene selectByPrimaryKey(Integer id) {
        Scene scene = null;
        try {
            scene = getSqlSession().selectOne("com.chuangkou.pdu.dao.SceneMapper.selectByPrimaryKey", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scene;
    }


    @Override
    public int updateByPrimaryKeySelective(Scene scene) {
        getSqlSession().update("com.chuangkou.pdu.dao.SceneMapper.updateByPrimaryKeySelective", scene);
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Scene scene) {
        getSqlSession().update("com.chuangkou.pdu.dao.SceneMapper.updateByPrimaryKey", scene);
        return 0;
    }

   /* @Override
    public int updateByExampleSelective(Scene scene, SceneExample example) {
        return 0;
    }

    @Override
    public int updateByExample(Scene record, SceneExample example) {
        return 0;
    }*/


}
