package com.vvyun.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableFeignClients(basePackages = {"com.vvyun.web.client"})
public class ClientUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientUserApplication.class, args);
    }

}
