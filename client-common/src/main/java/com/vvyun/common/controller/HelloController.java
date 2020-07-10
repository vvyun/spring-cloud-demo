package com.vvyun.common.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yunfei
 */
@RestController
public class HelloController {
    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name) {

        return "hello " + name;
    }
}
