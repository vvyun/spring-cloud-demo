package com.wyf.demo;

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
 * 这是一个测试用例
 *
 */
@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class BootdemoApplication {

	@RequestMapping(value = "/",method = RequestMethod.GET)
	String indexpage( ){
		return  "hello word";
	}
	public static void main(String[] args) {
		SpringApplication.run(BootdemoApplication.class, args);
	}
}
