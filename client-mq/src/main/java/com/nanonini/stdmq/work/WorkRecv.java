package com.nanonini.stdmq.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WorkRecv {

    @RabbitListener(queuesToDeclare = @Queue(value = "work"))
    public void work1(String message) {
        System.out.println("word rec 1:" + message);
    }

    @RabbitListener(queuesToDeclare = @Queue(value = "work"))
    public void work2(String message) {
        System.out.println("word rec 2:" + message);
    }
}
