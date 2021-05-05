package com.nanonini.stdmq.rout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicsRecv {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "topics", type = "topic"),
                    key = {"user.*"}
            )
    })
    public void topicsMe1(String message) {
        System.out.println("direct recv 1:" + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "topics", type = "topic"),
                    key = {"user.save"}
            )
    })
    public void topicsMe2(String message) {
        System.out.println("direct recv 2:" + message);
    }
}
