package com.uaa.auth.dao.mapper;

import com.uaa.auth.entity.UserAccess;
import com.uaa.auth.entity.XtUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserAccessMapper {

    List<UserAccess> securitySelectByUserId(@Param("userId") String userId);

    List<UserAccess> securitySelectByUserIdWithFakeDoc(@Param("userId") String userId);

    int securityInsertRecord(UserAccess record);

    int securityUpdateRecord(UserAccess record);

    int deleteByUserId(@Param("userId") String userId);

    XtUser findUserByUserName(String userName, String password);
}
