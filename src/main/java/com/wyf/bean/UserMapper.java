package com.wyf.bean;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 *
 * @author Yunfei
 *
 * 该接口不需要类去实现它
 * 对 User 表----查询所有的记录，插入，删除，修改，查询，
 *
 */
@Mapper
public interface UserMapper {
    @Select("select * from table_user")
    List<User> showAll();
    @Insert("insert into table_user(name,id,password,status) values(#{name},#{id},#{password},#{status})")
    int insert(User user);
    @Delete("delete from table_user where id = #{id}")
    int delete(String id);
    @Update("update table_user set name = #{name} , password = #{password} where id = #{id}")
    int update(User user);
    @Select("select * from table_user where id = #{id}")
    User getById(String id);
}
