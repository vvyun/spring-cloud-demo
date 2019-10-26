package com.wyf.service;

import com.wyf.entity.Role;
import com.wyf.mapper.RoleMapper;
import com.wyf.entity.UserRole;
import com.wyf.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yunfei
 */
@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    public List<Role> getRoles(String uid) {
        return roleMapper.getRoles(uid);
    }

    public int changeRole(UserRole userRole) {
//        System.out.println("uid = "+uid+" --rid = "+rid);
        return userRoleMapper.changeRole(userRole);
    }
}
