package org.juehn.cakeshop.utils;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class DBUtils {

    public static SqlSession getSqlSession() {
        SqlSession sqlSession = getSqlSession(false);
        return sqlSession;
    }

    public static SqlSession getSqlSession(boolean autoCommit) {
        SqlSession sqlSession = null;
        try {
            InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = builder.build(stream);
            sqlSession = sqlSessionFactory.openSession(autoCommit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlSession;
    }

}
