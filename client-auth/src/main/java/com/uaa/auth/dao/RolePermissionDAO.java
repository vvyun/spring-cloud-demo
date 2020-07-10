package com.uaa.auth.dao;

import com.uaa.auth.entity.RolePermission;

import java.util.List;
import java.util.UUID;

/**
 * @author wyf
 * @date 2017/11/22
 */
public interface RolePermissionDAO {

    int deleteById(Long id);

    Long insert(RolePermission record);

    List<RolePermission> selectByRoleId(UUID roleId);

    int updateById(RolePermission record);

    void deleteByRoleIdAndPermissionId(UUID rId, UUID pId);

}
