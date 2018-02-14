package com.wyf.controller;

import com.wyf.bean.User;
import com.wyf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author Yunfei
 * User controller
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 所有用户
     * @return
     */
    @RequestMapping("/alluser")
    public List<User> listAll() {
        return userService.listAll();
    }

    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    @RequestMapping("/getuser")
    public User getUserByid(@RequestParam("id") String id){
        return userService.getUserById(id);
    }

    /**
     * 添加用户
     * @param id
     * @param pass
     * @param name
     * @return
     */
    @RequestMapping("/addUser")
    public int addUser(@RequestParam String id,String pass,String name){
        System.out.println("name = " + name);
        return userService.addUser(new User(name,id,pass,1));
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping("/delUser")
    public int delUser(@RequestParam String id){
        return userService.deleteUser(id);
    }

    /**
     * 更新用户
     * @param id
     * @param name
     * @param pass
     * @return
     */
    @RequestMapping("/updateUser")
    public int updateUser(@RequestParam String id,String name,String pass){
        return 1;
    }
}
