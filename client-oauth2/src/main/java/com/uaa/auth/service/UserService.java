package com.uaa.auth.service;

import com.uaa.auth.entity.UserAccess;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserAccess checkUsernameAndPassword(String username,String password,String type) {
        UserAccess userAccess = new UserAccess();
        userAccess.setUserId("001");
        userAccess.setUserName(username);
        userAccess.setUserPassword(password);
        return userAccess;
    }
}
