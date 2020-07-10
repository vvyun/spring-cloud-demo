package com.uaa.auth.dao;

import com.uaa.auth.entity.Permission;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author wyf
 * @date 2017/11/22
 */
public interface PermissionDAO {

    int deleteById(UUID id);

    int insert(Permission record);

    Permission selectById(UUID id);

    void updateName(UUID id, String newName);

    List<Permission> selectAll();

    List<Permission> getPermissionList(Map paramMap);
}
