package com.alwyn.mq.consumer;

import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
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

    @RabbitListener(queuesToDeclare = @Queue("work.queue"))
    public void receive3(String message, @Headers Map<String, Object> headers, Channel channel) {
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        log.info("WorkQueueConsumer[3]，message：{}，deliveryTag：{}.-> ", message, deliveryTag);
        try {
            /**
             * Delivery Tag 用来标识信道中投递的消息。RabbitMQ 推送消息给 Consumer 时，会附带一个 Delivery Tag，
             * 以便 Consumer 可以在消息确认时告诉 RabbitMQ 到底是哪条消息被确认了。
             * RabbitMQ 保证在每个信道中，每条消息的 Delivery Tag 从 1 开始递增。
             */
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            try {
                channel.basicNack(deliveryTag, false, true);
            } catch (IOException e1) {
            }
        }
    }
}
