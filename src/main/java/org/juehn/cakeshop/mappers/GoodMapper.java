package org.juehn.cakeshop.mappers;

import org.apache.ibatis.annotations.*;
import org.juehn.cakeshop.entity.Good;

import java.util.List;
import java.util.Map;

public interface GoodMapper {

    @Select("select * from good")
    @ResultType(Good.class)
    public List<Good> getGoods();

    @Insert("insert into good(`name`, price, `desc`, stock, weight) value(#{name}, #{price}, #{desc}, #{stock}, #{weight})")
    public int addGood(Good good);

    @Select("select * from good where (`name` like '%${keyword}%' or `desc` like '%${keyword}%' or category like '%${keyword}%') " +
            "and category like '%${category}%' and price >= #{minPrice} and price <= #{maxPrice}")
    public List<Good> searchGood(Map<String, Object> constraints);

    @Delete("delete from good where id = #{id}")
    public int removeGood(@Param("id") int id);

    @Update("update good set `name` = #{name}, category = #{category}, `desc` = #{desc}, price = #{price}, weight = #{weight}, stock = #{stock} where id = #{id}")
    public int updateGood(Good good);

    @Select("select * from good where id = #{id}")
    public Good getGoodById(@Param("id") int id);
}
