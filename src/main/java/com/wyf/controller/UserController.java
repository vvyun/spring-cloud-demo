package com.wyf.controller;

import com.wyf.entity.User;
import com.wyf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Yunfei
 * User controller
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 所有用户
     *
     * @return
     */
    @RequestMapping("/alluser")
    public List<User> listAll() {
        return userService.listAll();
    }

    /**
     * 根据id获取用户
     *
     * @param id
     * @return
     */
    @RequestMapping("/getuser")
    public User getUserByid(@RequestParam("id") String id) {
        return userService.getUserById(id);
    }

    @RequestMapping("/addUser")
    public int addUser(@RequestParam String id, String pass, String name) {
        return userService.addUser(new User(name, id, pass, 1));
    }

    @RequestMapping("/delUser")
    public int delUser(@RequestParam String id) {
        return userService.deleteUser(id);
    }

    @RequestMapping("/updateUser")
    public int updateUser(@RequestParam String id, String name, String pass) {
        return userService.updateUser(new User(name, id, pass, 1));
    }

    @RequestMapping("/repass")
    public int rePass(@RequestParam String passold, String passnew, HttpSession session) {
        User user = (User) session.getAttribute("user");
        user = userService.getUserById(user.getId());
        if (user.getPassword().equals(passold)) {
            user.setPassword(passnew);
            userService.updateUser(user);
        }else {
            return 0;
        }
        return 1;
    }

    @RequestMapping("/reimg")
    public int reImg(@RequestParam("headimg") MultipartFile file, HttpSession session) throws IOException {

        if (file==null){
            return 0;
        }

        User user = (User) session.getAttribute("user");
        user = userService.getUserById(user.getId());

        String headImgFileName = "C:/Users/workpc/" + user.getId()+".png";

        file.transferTo(new File(headImgFileName));//文件转存

        return 1;
    }
}
