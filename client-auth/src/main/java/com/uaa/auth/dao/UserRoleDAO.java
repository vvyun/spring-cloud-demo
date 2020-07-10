package com.uaa.auth.dao;


import com.uaa.auth.dto.UserRoleDTO;
import com.uaa.auth.entity.UserRole;

import java.util.List;
import java.util.UUID;


public interface UserRoleDAO {

    Long insertUtRole(UserRole userRole);

    List<UserRole> selectByUserId(UUID userId);

    int updateById(UserRole record);


    int deleteByUserId(UUID userId);

    List<UserRoleDTO> selectUserRoleList(UUID userId);
}
