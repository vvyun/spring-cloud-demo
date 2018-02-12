package com.wyf.controller;

import com.wyf.demo.BootdemoApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableAutoConfiguration
//@RequestMapping(value = "/demo")
public class HomeController {
    @RequestMapping(value = "/getInfoById",method = RequestMethod.GET)
    String getuserInfoById(@RequestParam(value = "id") String id){
        return  "您的信息为：" + "id: " + id;
    }

    @RequestMapping(value = "/getInfoByname",method = RequestMethod.GET)
    String getuserInfoByName(@RequestParam(value = "name") String name){
        return  "您的信息为：" + "name: " + name;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    String indexpage( ){
        return  "hello";
    }
    public static void main(String[] args) {
        SpringApplication.run(HomeController.class, args);
    }
}
