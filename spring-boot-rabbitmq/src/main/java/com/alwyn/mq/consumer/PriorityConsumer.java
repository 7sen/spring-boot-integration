package com.alwyn.mq.consumer;

import com.alwyn.mq.config.PriorityQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PriorityConsumer {

    @RabbitListener(queues = PriorityQueueConfig.PRIORITY_QUEUE)
    public void receiveMsg(Message message) {
        String msg = new String(message.getBody());
        log.info("优先级队列接收消息:{}", msg);
    }
}