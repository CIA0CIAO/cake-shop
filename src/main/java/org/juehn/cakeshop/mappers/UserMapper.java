package org.juehn.cakeshop.mappers;

import org.apache.ibatis.annotations.*;
import org.juehn.cakeshop.entity.User;

import java.util.List;

public interface UserMapper {

    @Select("select * from user where id = #{id}")
    @ResultType(User.class)
    public User getUserById(@Param("id") int id);

    @Insert("insert into user(username,password,nick, telephone) value(#{username}, #{password}, #{nick}, #{telephone})")
    public int addUser(User user);

    @Select("select * from user where username = #{username} and password = #{password}")
    public User getUserByLogin(@Param("username") String username, @Param("password") String password);

    @Select("select * from user where role = #{role}")
    @ResultType(User.class)
    public List<User> getUsersByRole(@Param("role") String role);

    @Update("update user set password = #{password} where id = #{id}")
    public int setPassword(@Param("id") int id, @Param("password") String password);

    @Update("update user set nick = #{nick} where id = #{id}")
    public int setNick(@Param("id") int id, @Param("nick") String nick);

    @Update("update user set locked = #{locked} where id = #{id}")
    public int setLock(@Param("id") int id, @Param("locked") boolean locked);
}
