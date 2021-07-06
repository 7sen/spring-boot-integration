package com.alwyn.mq.consumer;

import com.rabbitmq.client.Channel;
import java.time.LocalTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeadLetterQueueConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(name = "QD"),
                    exchange = @Exchange(value = "YD")
            )
    })
    public void receive(Message message, Channel channel) {
        String msg = new String(message.getBody());
        log.info("当前时间：{},收到死信队列信息{}", LocalTime.now().withNano(0).toString(), msg);
    }

}
