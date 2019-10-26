package com.wyf.mapper;

import com.wyf.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author Yunfei
 */
@Mapper
public interface UserRoleMapper {

    @Update("UPDATE t_user_role SET rid = #{rid} WHERE uid = #{uid}")
    int changeRole(UserRole userRole);
}
