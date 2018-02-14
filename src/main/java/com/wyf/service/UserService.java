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

    /**
     * @return allUsers
     */
    public List<User> listAll() {
        return userMapper.showAll();
    }

    /**
     *
     * @param id
     * @return User
     */
    public User getUserById(String id){
        return userMapper.getById(id);
    }

    /**
     *
     * @param id
     * @param pass
     * @return int 是否存在
     */
    public int hasUser(String id ,String pass){
        User user = userMapper.getById(id);
        if (user!=null){
            if (userMapper.getById(id).getPassword().equals(pass))
                return  1;
            else return 0;
        }
        return 2;
    }

    /**
     *
     * @param user
     * @return 是否添加成功
     */
    public int addUser(User user){
        return userMapper.insert(user);
    }

    /**
     *
     * @param id
     * @return 是否删除成功
     */
    public int deleteUser(String id){
        return userMapper.delete(id);
    }
}
