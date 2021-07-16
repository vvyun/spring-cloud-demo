package com.uaa.auth.service;

import com.uaa.auth.dao.UserAccessDao;
import com.uaa.auth.entity.UserAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserAccessDao userAccessDao;

    public UserAccess checkUsernameAndPassword(String username,String password,String type) {
        UserAccess userAccess = new UserAccess();
        userAccess.setUserId("001");
        userAccess.setUserName(username);
        userAccess.setUserPassword(password);
        UserAccess userAccess1 = userAccessDao.getUser();
        return userAccess;
    }
}
