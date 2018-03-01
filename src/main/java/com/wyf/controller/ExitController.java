package com.wyf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 *
 * @author Yunfei
 *
 */
@Controller
public class ExitController {

    @RequestMapping("/logout")
    public String logOut(HttpSession session) {
        session.invalidate();
        // remove("user");
        return "index";
    }
}
