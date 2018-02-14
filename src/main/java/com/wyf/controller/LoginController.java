package com.wyf.controller;

import com.wyf.bean.User;
import com.wyf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Yunfei
 * login controller
 *
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/userlogin")
    public String login(@RequestParam String id,String pass, Model m){

        int issu =  userService.hasUser(id,pass);
//        if (id.equals("123")&&pass.equals("123456")){
//        System.out.println("id = :"+id+"pass = "+pass);
//        System.out.printf(" .. "+issu);
        if (issu>0){
            m.addAttribute("name",userService.getUserById(id).getName());
            return "success";
        }
        m.addAttribute("name","用户名或密码不正确");

        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String indexpage(String name,Model model){
        model.addAttribute("name",name);
        return  "login";
    }
}