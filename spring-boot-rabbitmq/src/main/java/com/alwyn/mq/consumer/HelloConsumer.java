package com.alwyn.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloConsumer {

    @RabbitListener(queuesToDeclare = @Queue("hello.word"))
    public void receive(String message) {
        log.info("HelloConsumer -> " + message);
    }
}
