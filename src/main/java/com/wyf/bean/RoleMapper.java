package com.wyf.bean;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 *
 * @author Yunfei
 *
 */
@Mapper
public interface RoleMapper {

    @Select("select name from table_role where id in (select rid from t_user_role where uid = #{uid} )")
    public String getRoleByUI(String uid);

}
