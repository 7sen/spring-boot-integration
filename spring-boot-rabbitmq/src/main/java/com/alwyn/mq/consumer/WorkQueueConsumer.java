package com.alwyn.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WorkQueueConsumer {

    @RabbitListener(queuesToDeclare = @Queue("work.queue"))
    public void receive1(String message) {
        log.info("WorkQueueConsumer[1] -> " + message);
    }

    @RabbitListener(queuesToDeclare = @Queue("work.queue"))
    public void receive2(String message) {
        log.info("WorkQueueConsumer[2] -> " + message);
    }
}
