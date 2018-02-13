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

    @RequestMapping("/alluser")
    public List<User> listAll() {
        return userService.listAll();
    }
    @RequestMapping("/getuser")
    public User getUserByid(@RequestParam("id") String id){
        return userService.getUserById(id);
    }
}
