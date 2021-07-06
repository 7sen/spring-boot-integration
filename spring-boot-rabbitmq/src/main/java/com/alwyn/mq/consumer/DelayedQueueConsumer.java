package com.alwyn.mq.consumer;

import com.alwyn.mq.config.DelayedQueueConfig;
import java.time.LocalTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DelayedQueueConsumer {

    @RabbitListener(queues = DelayedQueueConfig.DELAYED_QUEUE_NAME)
    public void receive(String message) {
        log.info("当前时间：{},收到延迟队列信息{}", LocalTime.now().withNano(0).toString(), message);
    }
}
