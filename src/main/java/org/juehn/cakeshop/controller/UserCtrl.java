package org.juehn.cakeshop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.jsonwebtoken.Claims;
import org.apache.ibatis.session.SqlSession;
import org.juehn.cakeshop.entity.User;
import org.juehn.cakeshop.mappers.UserMapper;
import org.juehn.cakeshop.utils.DBUtils;
import org.juehn.cakeshop.utils.JwtUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.tags.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserCtrl {

    @GetMapping("/user")
    public Map<String, Object> getUser(@RequestHeader("Authorization") String authorization) {
        String token = authorization.split(" ")[1];
        Claims claims = JwtUtils.parseToken(token);
        int userId = (int) claims.get("userId");
        SqlSession sqlSession = DBUtils.getSqlSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getUserById(userId);
        Map<String, Object> data = new HashMap();
        user.setPassword(null);
        data.put("user", user);
        data.put("code", 200);
        data.put("msg", "get:success");
        return data;
    }

    @PostMapping("/user")
    public Map<String, Object> addUser(@RequestBody User user) {
        SqlSession sqlSession = DBUtils.getSqlSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        user.setUsername(user.getTelephone());
        if (user.getNick().isEmpty()) {
            user.setNick("用户" + user.getUsername());
        }
        //添加用户到数据库
        int res = userMapper.addUser(user);
        Map<String, Object> data = new HashMap();
        data.put("code", 200);
        data.put("msg", "add:success");
        return data;
    }

    @GetMapping("/admin/users")
    public Map<String, Object> getUsersByAdmin(@RequestHeader("Authorization") String authorization, @RequestParam("pageNum") int pageNum) {
        String token = authorization.split(" ")[1];
        Claims claims = JwtUtils.parseToken(token);
        Map<String, Object> data = new HashMap();
        if (!claims.get("role").equals("admin")) {
            data.put("msg", "无权限");
            return data;
        }
        SqlSession sqlSession = DBUtils.getSqlSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        PageHelper.startPage(pageNum, 8);
        List<User> users = userMapper.getUsersByRole("user");
        PageInfo<User> userPageInfo = new PageInfo<>(users, 5);
        userPageInfo.getList().forEach(user -> {
            user.setPassword(null);
        });
        data.put("userPageInfo", userPageInfo);
        data.put("code", 200);
        data.put("msg", "get:success");
        return data;
    }

    @PutMapping("/admin/lock")
    public Map<String, Object> lockUser(@RequestHeader("Authorization") String authorization,
                                        @RequestBody Map body) {
        String token = authorization.split(" ")[1];
        Claims claims = JwtUtils.parseToken(token);
        Map<String, Object> data = new HashMap();
        if (!claims.get("role").equals("admin")) {
            data.put("msg", "无权限");
            return data;
        }
        SqlSession sqlSession = DBUtils.getSqlSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int res = userMapper.setLock((Integer) body.get("userId"), (Boolean) body.get("locked"));
        data.put("code", 200);
        data.put("msg", "lock:success");
        return data;
    }
}
