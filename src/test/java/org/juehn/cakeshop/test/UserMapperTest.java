package org.juehn.cakeshop.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.juehn.cakeshop.entity.User;
import org.juehn.cakeshop.mappers.UserMapper;
import org.juehn.cakeshop.utils.DBUtils;
import org.junit.Test;

import java.util.List;

public class UserMapperTest {

    @Test
    public void addUser() {
        SqlSession sqlSession = DBUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("npc02");
        user.setPassword("123456");
        user.setNick("用戶" + user.getUsername());
        int res = userMapper.addUser(user);
        System.out.println(res);
    }

    @Test
    public void getUserById() {
        SqlSession sqlSession = DBUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getUserById(1);
        System.out.println(user.toString());
    }

    @Test
    public void getUsersByRole() {
        SqlSession sqlSession = DBUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        PageHelper.startPage(2, 4);
        List<User> users = userMapper.getUsersByRole("user");
        PageInfo<User> userPageInfo = new PageInfo<>(users, 5);
//        users.forEach(user -> System.out.println(user));
        System.out.println(userPageInfo);
    }

}
