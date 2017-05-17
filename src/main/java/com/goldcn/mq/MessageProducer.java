package com.goldcn.mq;

import com.goldcn.config.RabbitMqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 功能概要：消息产生,提交到队列中去
 * Created by Administrator on 2017/5/17.
 */
@Component
public class MessageProducer implements RabbitTemplate.ConfirmCallback{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String content) {

        rabbitTemplate.setConfirmCallback(this); //rabbitTemplate如果为单例的话，那回调就是最后设置的内容

        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.ROUTINGKEY, content,correlationId);
        System.out.println("Sender : " + content);
    }

    /**
     * 回调
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println(" 回调id:" + correlationData);
        if (ack) {
            System.out.println("消息成功消费");
        } else {
            System.out.println("消息消费失败:" + cause);
        }
    }

}
