package com.wyf.controller;

import com.wyf.entity.User;
import com.wyf.entity.Permission;
import com.wyf.service.PermissionService;
import com.wyf.service.UserService;
import com.wyf.urp.Menu;
import com.wyf.util.MenuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Yunfei
 * login controller
 */
@Controller
public class LoginController {

    private User user = new User();

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/userlogin")
    public String login(@RequestParam String id, String pass, Model m, HttpSession session) {

        Object usero = session.getAttribute("user");
        //如果用户登陆过直接进入用户主页
        if (usero != null) {
            user = (User) usero;
            m.addAttribute("name", user.getName());
            List<Permission> list = permissionService.listPerssion(user.getId());
            List<Menu> menus = MenuUtil.permission2Menu(list);
            m.addAttribute("menus", menus);
            return "userhome";
        }

        //判断是否存在该用户
        user.setId(id);
        user.setPassword(pass);
        int issu = userService.userLogin(user);

        if (issu == 1) {          //登录成功
            user = userService.getUserById(id);
            session.setAttribute("user", user);
            m.addAttribute("name", user.getName());
            List<Permission> list = permissionService.listPerssion(user.getId());
            List<Menu> menus = MenuUtil.permission2Menu(list);
            m.addAttribute("menus", menus);
            return "userhome";
        } else if (issu == 2) {      //密码错误
            m.addAttribute("name", "1");
            return "login";
        } else {                 //不存在该用户
            m.addAttribute("name", "2");
            return "login";
        }
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(String name, Model model) {
        model.addAttribute("name", name);
        return "login";
    }

}