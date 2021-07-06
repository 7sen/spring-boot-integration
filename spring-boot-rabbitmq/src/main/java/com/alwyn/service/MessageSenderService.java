package com.alwyn.service;

import com.alwyn.mq.callback.ConfirmCallbackService;
import com.alwyn.mq.callback.ReturnsCallbackService;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageSenderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ConfirmCallbackService confirmCallbackService;
    @Autowired
    private ReturnsCallbackService returnsCallbackService;

    public void sendMessage(String message) {
        rabbitTemplate.setConfirmCallback(confirmCallbackService);
        /**
         * true：
         * 交换机无法将消息进行路由时，会将该消息返回给生产者
         * false：
         * 如果发现消息无法进行路由，则直接丢弃
         */
        rabbitTemplate.setMandatory(true);
        // 设置回退消息给谁处理
        rabbitTemplate.setReturnsCallback(returnsCallbackService);
        CorrelationData correlationData1 = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("confirm.exchange", "key1", message + " key1", correlationData1);
        log.info("发送消息 id 为:{}内容为{}", correlationData1.getId(), message + " key1");
        CorrelationData correlationData2 = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("confirm.exchange", "key2", message + "key2", correlationData2);
        log.info("发送消息 id 为:{}内容为{}", correlationData2.getId(), message + " key2");
    }

}