package org.juehn.cakeshop.test;

import org.apache.ibatis.session.SqlSession;
import org.juehn.cakeshop.entity.Good;
import org.juehn.cakeshop.mappers.GoodMapper;
import org.juehn.cakeshop.mappers.UserMapper;
import org.juehn.cakeshop.utils.DBUtils;
import org.junit.Test;

public class GoodMapperTest {

    @Test
    public void addGood() {
        SqlSession sqlSession = DBUtils.getSqlSession(true);
        GoodMapper goodMapper = sqlSession.getMapper(GoodMapper.class);
        Good good = new Good();
        good.setName("云朵芒芒");
        good.setDesc("当季大块芒果蛋糕，香甜而多汁");
        good.setPrice(336.0);
        good.setWeight(828.0);
        good.setStock(6);
        int res = goodMapper.addGood(good);
        System.out.println(res);
    }
}
