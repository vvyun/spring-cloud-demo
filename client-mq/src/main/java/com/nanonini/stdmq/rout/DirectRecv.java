package com.nanonini.stdmq.rout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectRecv {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "dir", type = "direct"),
                    key = {"rout_q1"}
            )
    })
    public void directMe1(String message) {
        System.out.println("direct recv 1:" + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "dir", type = "direct"),
                    key = {"rout_q2"}
            )
    })
    public void directMe2(String message) {
        System.out.println("direct recv 2:" + message);
    }
}
