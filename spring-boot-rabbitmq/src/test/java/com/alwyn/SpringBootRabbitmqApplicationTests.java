package com.alwyn;

import com.alwyn.dto.Order;
import com.alwyn.mq.config.DelayedQueueConfig;
import com.alwyn.mq.config.PriorityQueueConfig;
import java.time.LocalTime;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(classes = SpringBootRabbitmqApplication.class)
class SpringBootRabbitmqApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void helloProducer() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("hello.word", "hello word!");
        }
    }

    @Test
    public void workQueueProducer() {
        for (int i = 0; i < 100000; i++) {
            rabbitTemplate.convertAndSend("work.queue", "hello word! " + i);
        }
    }

    @Test
    public void fanoutProducer() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("logs", "", "hello word !" + i);
        }
    }

    @Test
    public void routeProducer() {
        rabbitTemplate.convertAndSend("directs", "info", "hello word !");
        rabbitTemplate.convertAndSend("directs", "error", "hello word !");
    }

    @Test
    public void topicsProducer() {
        rabbitTemplate.convertAndSend("topics", "user.save", "hello word !");
    }

    @Test
    public void deadLetterProducer() {
        String message = "hello word!";
        log.info("当前时间：{},发送一条信息给两个 TTL 队列:{}", LocalTime.now().withNano(0).toString(), message);
        rabbitTemplate.convertAndSend("X", "XA", "消息来自 ttl 为 10S 的队列: " + message);
        rabbitTemplate.convertAndSend("X", "XB", "消息来自 ttl 为 40S 的队列: " + message);
    }

    @Test
    public void deadLetterProducer1() {
        String message = "hello word!";

        String ttlTime = "10000";
        rabbitTemplate.convertAndSend("X", "XC", message, correlationData -> {
            correlationData.getMessageProperties().setExpiration(ttlTime);
            return correlationData;
        });
        log.info("当前时间：{},发送一条时长{}毫秒 TTL 信息给队列 C:{}", LocalTime.now().withNano(0).toString(), ttlTime, message);
    }

    @Test
    public void conversionProducer() {
        rabbitTemplate.convertAndSend("conversion", "", new Order(100L, 200L));
    }

    @Test
    public void delayedProducer() {
        String message = "hello word!";
        int ttlTime = 20000;
        rabbitTemplate.convertAndSend(DelayedQueueConfig.DELAYED_EXCHANGE_NAME, DelayedQueueConfig.DELAYED_ROUTING_KEY,
                "hello word![20]", correlationData -> {
                    correlationData.getMessageProperties().setDelay(ttlTime);
                    return correlationData;
                });
        log.info(" 当 前 时 间 ： {}, 发送一条延迟 {} 毫秒的信息给队列 delayed.queue:{}", LocalTime.now().withNano(0).toString(),
                ttlTime, message);

        int ttlTime1 = 2000;
        rabbitTemplate.convertAndSend(DelayedQueueConfig.DELAYED_EXCHANGE_NAME, DelayedQueueConfig.DELAYED_ROUTING_KEY,
                "hello word![2]", correlationData -> {
                    correlationData.getMessageProperties().setDelay(ttlTime1);
                    return correlationData;
                });
        log.info(" 当 前 时 间 ： {}, 发送一条延迟 {} 毫秒的信息给队列 delayed.queue:{}", LocalTime.now().withNano(0).toString(),
                ttlTime1, message);
    }

    @Test
    public void priorityProducer() {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            if (i == 5) {
                rabbitTemplate.convertAndSend(PriorityQueueConfig.PRIORITY_EXCHANGE, PriorityQueueConfig.ROUTING_KEY,
                        "hello word![" + i + "]", message -> {
                            message.getMessageProperties().setPriority(finalI);
                            return message;
                        });
            } else {
                rabbitTemplate.convertAndSend(PriorityQueueConfig.PRIORITY_EXCHANGE, PriorityQueueConfig.ROUTING_KEY,
                        "hello word![" + i + "]");
            }
        }
    }
}

