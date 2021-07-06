package com.alwyn.mq.config;

import java.util.HashMap;
import java.util.Map;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PriorityQueueConfig {

    public static final String PRIORITY_EXCHANGE = "priority-exchange";

    public static final String PRIORITY_QUEUE = "priority.queue";

    public static final String ROUTING_KEY = "priority.queue";

    @Bean
    DirectExchange priorityExchange() {
        return new DirectExchange(PRIORITY_EXCHANGE);
    }

    @Bean
    Queue priorityQueue() {
        Map<String, Object> map = new HashMap<>(1);
        //设置最大的优先级数量
        map.put("x-max-priority", 10);
        return new Queue(PRIORITY_QUEUE, true, false, false, map);
    }

    @Bean
    public Binding bindingPriorityExchange() {
        return BindingBuilder.bind(priorityQueue()).to(priorityExchange()).with(ROUTING_KEY);
    }
}