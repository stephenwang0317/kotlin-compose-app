package com.wjm.springmvc.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionUtils {
    private static SqlSession sqlSession;
    private SqlSessionUtils() {}

    public static SqlSession getSqlSession() {
        if(sqlSession == null) {
            try {
                InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSession = new SqlSessionFactoryBuilder().build(is).openSession(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sqlSession;
    }
}
