package com.uaa.auth.dao;


import com.uaa.auth.entity.UserAccess;
import com.uaa.auth.entity.XtUser;

import java.util.List;
import java.util.Map;


public interface UserAccessDAO {


    List<UserAccess> securitySelectByUserId(String userId);

    List<UserAccess> securitySelectByUserIdWithFakeDoc(String userId);

    int securityInsertRecord(UserAccess record);

    int securityUpdateRecord(UserAccess record);

    int deleteByUserId(String userId);

    XtUser findUserByUserName(Map map);
}
