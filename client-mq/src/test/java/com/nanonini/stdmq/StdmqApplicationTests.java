package com.nanonini.stdmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StdmqApplicationTests {

    @Autowired
    private RabbitTemplate template;

    // hello word 模式
    @Test
    void sendHello() {
        template.convertAndSend("hello", "hello word message!");
    }

    // work 模式
    @Test
    void sendWork() {
        for (int i = 0; i < 10; i++) {
            template.convertAndSend("work", "work message index=" + i);
        }
    }

    // fanout 模式
    @Test
    void sendFanout() {
        for (int i = 0; i < 10; i++) {
            template.convertAndSend("logs", "", "fanout messages index=" + i);
        }
    }

    // direct 模式
    @Test
    void sendDirect() {
        template.convertAndSend("dir", "rout_q1", "direct message=1");
    }

    // topics 模式 动态路由
    @Test
    void sendTopics() {
        template.convertAndSend("topics", "user.save", "topics message=1");
        template.convertAndSend("topics", "user.delete", "topics message=2");
    }

}
