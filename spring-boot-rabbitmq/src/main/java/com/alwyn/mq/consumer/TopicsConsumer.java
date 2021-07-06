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
public class TopicsConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 临时队列
                    exchange = @Exchange(name = "topics", type = ExchangeTypes.TOPIC),
                    key = {"user.save", "user.*"}
            )})
    public void receive1(String message) {
        log.info("TopicsConsumer[1] -> " + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 临时队列
                    exchange = @Exchange(name = "topics", type = ExchangeTypes.TOPIC),
                    key = {"order.#", "product.#"}
            )})
    public void receive2(String message) {
        log.info("TopicsConsumer[2] -> " + message);
    }
}
