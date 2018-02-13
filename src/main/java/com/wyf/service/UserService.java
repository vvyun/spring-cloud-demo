package com.wyf.service;

import com.wyf.bean.User;
import com.wyf.bean.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Yunfei
 * User Service
 *
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> listAll() {
        return userMapper.showAll();
    }
    public User getUserById(String id){
        return userMapper.getById(id);
    }
}
