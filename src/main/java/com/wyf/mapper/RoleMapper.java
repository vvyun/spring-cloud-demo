package com.wyf.mapper;

import com.wyf.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Yunfei
 */
@Mapper
public interface RoleMapper {

    @Select("select name from table_role where id in (select rid from t_user_role where uid = #{uid} )")
    String getRoleByUI(String uid);

    @Select("select * from table_role where id in (select rid from t_user_role where uid = #{uid} )")
    List<Role> getRoles(String uid);

}
