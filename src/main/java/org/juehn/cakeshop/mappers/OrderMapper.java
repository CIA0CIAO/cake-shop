package org.juehn.cakeshop.mappers;

import org.apache.ibatis.annotations.*;
import org.juehn.cakeshop.entity.Good;
import org.juehn.cakeshop.entity.Order;

import java.util.List;

public interface OrderMapper {

    @Insert("insert into `order`(receiver_id, goods, `status`, `total`) value(#{receiverId}, #{goods}, #{status}, #{total})")
    public int addOrder(Order order);

    @Select("select * from `order`")
    public List<Order> getOrdersByAdmin();

    @Select("select * from `order` where receiver_id = #{receiverId}")
    public List<Order> getOrdersByReceiver(@Param("receiverId") int receiverId);

    @Delete("delete from `order` where id = #{id}")
    public int removeOrderById(@Param("id") int id);

}
