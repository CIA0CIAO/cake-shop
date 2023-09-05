package org.juehn.cakeshop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.session.SqlSession;
import org.juehn.cakeshop.entity.User;
import org.juehn.cakeshop.mappers.UserMapper;
import org.juehn.cakeshop.utils.DBUtils;
import org.juehn.cakeshop.utils.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthCtrl {

    @GetMapping("/auth/login")
    public Map<String, Object> login(@RequestParam String username, @RequestParam String password) throws JsonProcessingException {
        SqlSession sqlSession = DBUtils.getSqlSession(true);// 连接数据库
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);// 获取UserMapper,用于操作数据库
        User user = userMapper.getUserByLogin(username, password);// 根据用户名和密码查询用户信息
        Map<String, Object> data = new HashMap();
        if(user.isLocked()) {
            data.put("msg", "账号已冻结");
            return data;
        }
        Map<String, Object> claims = new HashMap();
        claims.put("userId", user.getId());
        claims.put("role", user.getRole());
        String token = JwtUtils.createToken(claims);
        data.put("code", 200);
        data.put("token", token);
        return data;
    }
}
