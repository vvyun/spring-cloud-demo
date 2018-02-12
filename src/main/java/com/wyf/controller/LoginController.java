package com.wyf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Yunfei
 * login action
 *
 */
@Controller
public class LoginController {

    @RequestMapping("/userlogin")
    public String login(String name, Model m){
        m.addAttribute("name",name);
        return "success";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String indexpage(String name,Model model){
        model.addAttribute("name",name);
        return  "login";
    }
}