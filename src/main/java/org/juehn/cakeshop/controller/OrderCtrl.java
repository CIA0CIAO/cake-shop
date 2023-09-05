package org.juehn.cakeshop.controller;

import io.jsonwebtoken.Claims;
import org.apache.ibatis.session.SqlSession;
import org.juehn.cakeshop.entity.Order;
import org.juehn.cakeshop.entity.User;
import org.juehn.cakeshop.mappers.OrderMapper;
import org.juehn.cakeshop.mappers.UserMapper;
import org.juehn.cakeshop.utils.DBUtils;
import org.juehn.cakeshop.utils.JwtUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OrderCtrl {

    @PostMapping("/order")
    public Map<String, Object> addOrder(@RequestHeader("Authorization") String authorization, @RequestBody Order order) {
        String token = authorization.split(" ")[1];
        Claims claims = JwtUtils.parseToken(token);
        int userId = (int) claims.get("userId");
        SqlSession sqlSession = DBUtils.getSqlSession(true);
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        order.setStatus("已支付");
        int res = orderMapper.addOrder(order);
        Map<String, Object> data = new HashMap();
        if (res <= 0) {
            data.put("msg", "add:fail");
        }
        data.put("code", 200);
        data.put("msg", "get:success");
        return data;
    }

    @GetMapping("/user/orders")
    public Map<String, Object> getOrdersByUser(@RequestHeader("Authorization") String authorization) {
        String token = authorization.split(" ")[1];
        Claims claims = JwtUtils.parseToken(token);
        int userId = (int) claims.get("userId");
        SqlSession sqlSession = DBUtils.getSqlSession(true);
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        List<Order> orders = orderMapper.getOrdersByReceiver(userId);
        Map<String, Object> data = new HashMap();
        data.put("code", 200);
        data.put("msg", "get:success");
        data.put("orders", orders);
        return data;
    }

    @GetMapping("/admin/orders")
    public Map<String, Object> getOrdersByAdmin(@RequestHeader("Authorization") String authorization) {
        String token = authorization.split(" ")[1];
        Claims claims = JwtUtils.parseToken(token);
        String role = (String) claims.get("role");
        SqlSession sqlSession = DBUtils.getSqlSession(true);
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);// 获取UserMapper,用于操作数据库
        List<Order> orders = orderMapper.getOrdersByAdmin();
        Map<String, Object> data = new HashMap();
        data.put("code", 200);
        data.put("msg", "get:success");
        data.put("orders", orders);
        return data;
    }

    @DeleteMapping("/order/{orderId}")
    public Map<String, Object> remvoeOrder(@RequestHeader("Authorization") String authorization, @PathVariable("orderId") int orderId) {
        String token = authorization.split(" ")[1];
        Claims claims = JwtUtils.parseToken(token);
        SqlSession sqlSession = DBUtils.getSqlSession(true);
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        int res = orderMapper.removeOrderById(orderId);
        Map<String, Object> data = new HashMap();
        data.put("code", 200);
        data.put("msg", "get:success");
        return data;
    }

}
