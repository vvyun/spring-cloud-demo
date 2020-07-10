package com.uaa.auth.service;

import com.uaa.auth.dao.*;
import com.uaa.auth.dto.DefaultRole;
import com.uaa.auth.dto.DefaultRoles;
import com.uaa.auth.dto.RolePermissionDTO;
import com.uaa.auth.dto.UserRoleDTO;
import com.uaa.auth.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author wyf
 * @date 2017/11/22
 */
@Service
@Transactional
public class SecurityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityService.class);

    @Autowired
    private PermissionDAO permissionDAO;

    @Autowired
    private UserAccessDAO userAccessDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private RolePermissionDAO rolePermissionDAO;

    @Autowired
    private UserRoleDAO userRoleDAO;

    public List<Permission> getPermissionListByUserId(String userId) {
        Map<String, UUID> map = new HashMap<>();
        map.put("userId", UUID.fromString(userId));
        return permissionDAO.getPermissionList(map);
    }

    public List<UserAccess> getUserAccessList(String userId) {
        return userAccessDAO.securitySelectByUserId(userId);
    }

    public void revokeRole(UUID userId) {
        deleteAccessRole(userId);
    }

    public List<RolePermissionDTO> getRolePermissionsByUserId(UUID userId) {
        List<RolePermissionDTO> rolePermissionDTOList = getRolesByUserId(userId);
        return rolePermissionDTOList;
    }

    public void addUserAccess(UUID userId, Integer accesslevel) {
        UserAccess record = new UserAccess();
        record.setUserId(userId);
        record.setAccessLevel(accesslevel);
        userAccessDAO.securityInsertRecord(record);
    }

    public void createUserRoleAccesss(UUID userId, DefaultRole role) {
        addUserAccess(userId, 0);
        saveUserRole(userId, role);

        if (DefaultRoles.MASTER_ADMIN.getId().equals(role.getId())) {
            addUserAccess(userId, 1);
        }
    }

    public void saveUserRole(UUID userId, DefaultRole role) {
        UserRole UserRole = new UserRole.UserRoleBuilder()
                .withUserId(userId)
                .withRoleId(role.getId()).build();
        userRoleDAO.insertUtRole(UserRole);
    }


    public List<RolePermissionDTO> getRolesByUserId(UUID userId) {

        List<UserRole> userTenantRoleList = userRoleDAO.selectByUserId(userId);
        List<Role> roleList = new ArrayList<>();
        List<RolePermissionDTO> result = new ArrayList<>();
        for (int i = 0; i < userTenantRoleList.size(); i++) {
            UUID roleId = userTenantRoleList.get(i).getRoleId();
            roleList.add(roleDAO.selectById(roleId));
        }
        //process can be optimized,may be in sql-view not multi queries in sql
        for (int i = 0; i < roleList.size(); i++) {
            RolePermissionDTO rolePermissionDTO = new RolePermissionDTO();
            rolePermissionDTO.setRelationId(userTenantRoleList.get(i).getId());
            rolePermissionDTO.setRoleId(roleList.get(i).getId());
            rolePermissionDTO.setName(roleList.get(i).getName());
            rolePermissionDTO.setDescription(roleList.get(i).getDescription());
            rolePermissionDTO.setUpdateTime(roleList.get(i).getUpdateTime());
            List<RolePermission> rolePermissionList = rolePermissionDAO.selectByRoleId(roleList.get(i).getId());
            List<Permission> permissionList = new ArrayList<>();
            for (int j = 0; j < rolePermissionList.size(); j++) {
                UUID pId = rolePermissionList.get(j).getPermissionId();
                permissionList.add(permissionDAO.selectById(pId));
            }
            rolePermissionDTO.setPermissions(permissionList);
            result.add(rolePermissionDTO);
        }
        return result;
    }

    public List<UserRoleDTO> getUserRoleList(UUID userId) {
        return userRoleDAO.selectUserRoleList(userId);
    }

    public void deleteAccessRole(UUID userId) {
        userRoleDAO.deleteByUserId(userId);
        userAccessDAO.deleteByUserId(userId.toString());
    }

}
