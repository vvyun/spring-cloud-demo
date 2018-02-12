package com.wyf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Yunfei
 *
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String indexPage() {
        return "index";
    }
}
