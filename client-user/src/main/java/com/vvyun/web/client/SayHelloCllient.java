package com.vvyun.web.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Yunfei
 */
@FeignClient(name = "client-common")
public interface SayHelloCllient {

    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    String sayHello(@PathVariable("name") String name);

}
