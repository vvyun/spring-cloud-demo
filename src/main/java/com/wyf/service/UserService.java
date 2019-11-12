package com.wyf.service;

import com.wyf.entity.User;
import com.wyf.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yunfei
 * User Service
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> listAll() {
        return userMapper.showAll();
    }

    public User getUserById(String id) {
        return userMapper.getById(id);
    }

    public int userLogin(User user) {

        String password = user.getPassword();
        String id = user.getId();
        user = userMapper.getById(id);

        if (user != null) {
            if (user.getPassword().equals(password))
                return 1;
            else return 0;
        }
        return 2;
    }

    public int addUser(User user) {
        return userMapper.insert(user);
    }

    public int deleteUser(String id) {
        return userMapper.delete(id);
    }

    public int updateUser(User user) {
        return userMapper.update(user);
    }
}
