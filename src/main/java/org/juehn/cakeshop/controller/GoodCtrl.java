package org.juehn.cakeshop.controller;

import com.github.pagehelper.Constant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.jsonwebtoken.Claims;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSession;
import org.juehn.cakeshop.entity.Good;
import org.juehn.cakeshop.entity.User;
import org.juehn.cakeshop.mappers.GoodMapper;
import org.juehn.cakeshop.utils.DBUtils;
import org.juehn.cakeshop.utils.JwtUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GoodCtrl {

    @GetMapping("/goods")
    public Map<String, Object> getGoods(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        SqlSession sqlSession = DBUtils.getSqlSession();
        PageHelper.startPage(pageNum, pageSize);
        GoodMapper goodMapper = sqlSession.getMapper(GoodMapper.class);
        List<Good> goods = goodMapper.getGoods();
        PageInfo<Good> goodPageInfo = new PageInfo<>(goods, 5);
        Map<String, Object> data = new HashMap();
        data.put("goodPageInfo", goodPageInfo);
        data.put("code", 200);
        data.put("msg", "get:success");
        return data;
    }

    @PostMapping("/admin/good")
    public Map<String, Object> addGood(@RequestHeader("Authorization") String authorization, @RequestBody Good good) {
        String token = authorization.split(" ")[1];
        Claims claims = JwtUtils.parseToken(token);
        Map<String, Object> data = new HashMap();
        if (!claims.get("role").equals("admin")) {
            data.put("msg", "无权限");
            return data;
        }
        SqlSession sqlSession = DBUtils.getSqlSession(true);
        GoodMapper goodMapper = sqlSession.getMapper(GoodMapper.class);
        int res = goodMapper.addGood(good);
        if(res == 0) {
            data.put("msg", "add:fail");
        }
        data.put("code", 200);
        data.put("msg", "add:success");
        return data;
    }

    @GetMapping("/search/good")
    public Map<String, Object> searchGood(@RequestParam("keyword") String keyword, @RequestParam("category") String category,
                                          @RequestParam("minPrice") double minPrice, @RequestParam("maxPrice") double maxPrice) {
        Map<String, Object> constraints = new HashMap<>();
        constraints.put("keyword", keyword);
        constraints.put("category", category);

        SqlSession sqlSession = DBUtils.getSqlSession();
        GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
        if(minPrice >= 0 && maxPrice >= 0) {
            constraints.put("minPrice", minPrice);
            constraints.put("maxPrice", maxPrice);
        }
        List<Good> goods = mapper.searchGood(constraints);
        Map<String, Object> data = new HashMap();
        data.put("code", 200);
        data.put("msg", "add:success");;
        data.put("goods", goods);
        return data;
    }

    @DeleteMapping("/admin/good/{goodId}")
    public Map<String, Object> removeGood(@RequestHeader("Authorization") String authorization, @PathVariable("goodId") int goodId) {
        String token = authorization.split(" ")[1];
        Claims claims = JwtUtils.parseToken(token);
        Map<String, Object> data = new HashMap();
        if (!claims.get("role").equals("admin")) {
            data.put("msg", "无权限");
            return data;
        }
        SqlSession sqlSession = DBUtils.getSqlSession(true);
        GoodMapper goodMapper = sqlSession.getMapper(GoodMapper.class);
        int res = goodMapper.removeGood(goodId);
        if(res == 0) {
            data.put("msg", "add:fail");
        }
        data.put("code", 200);
        data.put("msg", "add:success");
        return data;
    }

    @PutMapping("/admin/good")
    public Map<String, Object> updateGood(@RequestHeader("Authorization") String authorization, @RequestBody Good good) {
        String token = authorization.split(" ")[1];
        Claims claims = JwtUtils.parseToken(token);
        Map<String, Object> data = new HashMap();
        if (!claims.get("role").equals("admin")) {
            data.put("msg", "无权限");
            return data;
        }
        SqlSession sqlSession = DBUtils.getSqlSession(true);
        GoodMapper goodMapper = sqlSession.getMapper(GoodMapper.class);
        int res = goodMapper.updateGood(good);
        if(res == 0) {
            data.put("msg", "update:fail");
        }
        data.put("code", 200);
        data.put("msg", "update:success");
        return data;
    }

    @GetMapping("/good/{goodId}")
    public Map<String, Object> getGoodById(@PathVariable("goodId") int goodId) {
        SqlSession sqlSession = DBUtils.getSqlSession(true);
        GoodMapper goodMapper = sqlSession.getMapper(GoodMapper.class);
        Map<String, Object> data = new HashMap();
        Good good = goodMapper.getGoodById(goodId);
        data.put("code", 200);
        data.put("msg", "get:success");
        data.put("good", good);
        return data;
    }
}
