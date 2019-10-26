package com.wyf.service;

import com.wyf.entity.Permission;
import com.wyf.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yunfei
 *
 */
@Service
public class PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    public List<Permission> listPerssion(String id) {
        return permissionMapper.listPermissionByUid(id);
    }
}
