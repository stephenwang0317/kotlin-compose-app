package com.wjm.springmvc;

import com.wjm.springmvc.bean.User;
import com.wjm.springmvc.mapper.UserMapper;
import com.wjm.springmvc.utils.SqlSessionUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test {

    @org.junit.Test
    public void test() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User insert = new User();
        insert.setUser_id(100031);
        insert.setUser_name("Changed name");
        insert.setUser_pwd("123455");
        Integer line = userMapper.changeInfo(insert);
        System.out.println(line);
    }

}
