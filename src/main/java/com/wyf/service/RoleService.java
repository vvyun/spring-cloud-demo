package com.wyf.service;

import com.wyf.bean.Role;
import com.wyf.bean.RoleMapper;
import com.wyf.bean.UserRole;
import com.wyf.bean.UserRoleMapper;
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
