package com.alwyn.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FanoutConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 临时队列
                    exchange = @Exchange(value = "logs", type = ExchangeTypes.FANOUT)
            )})
    public void receive1(String message) {
        log.info("FanoutConsumer[1] -> " + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 临时队列
                    exchange = @Exchange(value = "logs", type = ExchangeTypes.FANOUT)
            )})
    public void receive2(String message) {
        log.info("FanoutConsumer[2] -> " + message);
    }
}
