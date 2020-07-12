package com.vvyun.web.controller;

import com.vvyun.web.client.SayHelloCllient;
import com.vvyun.web.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Yunfei
 */
@RestController
public class HelloController {

    private SayHelloCllient sayHello;

    @Autowired
    public HelloController(SayHelloCllient sayHello) {
        this.sayHello = sayHello;
    }


    @RequestMapping("/hello")
    public String hello(@RequestParam String name) {
        System.out.println(DateUtil.formatDate(new Date()) + "hello");
        return sayHello.sayHello(name);
    }

}
