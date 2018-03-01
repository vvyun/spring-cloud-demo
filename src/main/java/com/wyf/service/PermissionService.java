package com.wyf.service;

import com.wyf.bean.Permission;
import com.wyf.bean.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    public List<Permission> findPer(String id) {
        return permissionMapper.getPerListByUid(id);
    }
}
