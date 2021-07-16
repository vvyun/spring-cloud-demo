package com.nanonini.stdmq.fanout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutRecv {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 创建临时队列
                    exchange = @Exchange(value = "logs", type = "fanout")   // 绑定交换机
            )
    })
    public void fanout1(String message) {
        System.out.println("fanout recv 1:" + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 创建临时队列
                    exchange = @Exchange(value = "logs", type = "fanout")   // 绑定交换机
            )
    })
    public void fanout2(String message) {
        System.out.println("fanout recv 2:" + message);
    }

}
