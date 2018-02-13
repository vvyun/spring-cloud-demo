package com.wyf.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Yunfei
 * 测试 demo
 *
 */
@RestController
@SpringBootApplication
@EnableAutoConfiguration
//@RequestMapping(value = "/demo")
public class TestController {
    @RequestMapping(value = "/getInfoById",method = RequestMethod.GET)
    String getuserInfoById(@RequestParam(value = "id") String id){
        return  "您的信息为：" + "id: " + id;
    }

    @RequestMapping(value = "/getInfoByname",method = RequestMethod.GET)
    String getuserInfoByName(@RequestParam(value = "name") String name){
        return  "您的信息为：" + "name: " + name;
    }

//    @RequestMapping(value = "/",method = RequestMethod.GET)
//    String indexpage(Model model){
//        model.addAttribute("name","Wang Yunfei");
//        return  "index";
//    }
    public static void main(String[] args) {
        SpringApplication.run(TestController.class, args);
    }
}
