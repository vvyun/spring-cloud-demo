package com.uaa.auth.dao.impl;

import com.uaa.auth.dao.UserAccessDAO;
import com.uaa.auth.dao.mapper.UserAccessMapper;
import com.uaa.auth.entity.UserAccess;
import com.uaa.auth.entity.XtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("UserAccessDAO")
public class MybatisUserAccessDAO implements UserAccessDAO {

    @Autowired
    UserAccessMapper userAccessMapper;

    @Override
    public List<UserAccess> securitySelectByUserId(String userId) {
        return userAccessMapper.securitySelectByUserId(userId);
    }

    @Override
    public List<UserAccess> securitySelectByUserIdWithFakeDoc(String userId) {
        return userAccessMapper.securitySelectByUserIdWithFakeDoc(userId);
    }

    @Override
    public int securityInsertRecord(UserAccess record) {
        return userAccessMapper.securityInsertRecord(record);
    }

    @Override
    public int securityUpdateRecord(UserAccess record) {
        return userAccessMapper.securityUpdateRecord(record);
    }

    @Override
    public int deleteByUserId(String userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        return userAccessMapper.deleteByUserId(userId);
    }

    @Override
    public XtUser findUserByUserName(Map param) {
        String userName = param.get("userName").toString();
        String password = param.get("password").toString();
        XtUser map = userAccessMapper.findUserByUserName(userName, password);
        return map;
    }

}
