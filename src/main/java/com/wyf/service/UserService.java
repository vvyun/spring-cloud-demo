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
    public int hasUser(String id ,String pass){
        User user = userMapper.getById(id);
        if (user!=null){
            if (userMapper.getById(id).getPassword().equals(pass))
                return  1;
            else return 0;
        }
        return 2;
    }
}
