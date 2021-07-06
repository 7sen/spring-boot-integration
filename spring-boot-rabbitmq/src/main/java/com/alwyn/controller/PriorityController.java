package com.alwyn.controller;

import com.alwyn.mq.config.PriorityQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/priority")
@Slf4j
public class PriorityController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMessage")
    public void sendMessage() {
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