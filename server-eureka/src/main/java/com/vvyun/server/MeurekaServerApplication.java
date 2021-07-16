package com.vvyun.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MeurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeurekaServerApplication.class, args);
    }

}
