package com.wjm.springmvc;

import com.wjm.springmvc.bean.User;
import com.wjm.springmvc.mapper.UserMapper;
import com.wjm.springmvc.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

public class Test {

    @org.junit.Test
    public void test() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.searchUserById(100000);

        SqlSession sqlSession2 = SqlSessionUtils.getSqlSession();
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = mapper2.searchUserById(100000);

    }

}
