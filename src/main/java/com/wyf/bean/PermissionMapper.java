package com.wyf.bean;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *
 * @author Yunfei
 *
 */
@Mapper
public interface PermissionMapper {

    @Select("SELECT * from table_per WHERE id in (SELECT pid FROM t_role_per where rid in (SELECT rid from t_user_role WHERE uid = #{uid}))")
    public List<Permission> getPerListByUid(String uid);
}
