package com.nanonini.stdmq.hello;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queuesToDeclare = @Queue(value = "hello"))
public class HelloRecv {

    @RabbitHandler
    public void hello(String message) {
        System.out.println("hello rec 1:" + message);
    }
}
