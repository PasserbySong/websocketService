package com.goldcn.mq;

import com.goldcn.service.HandleService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.asm.Handle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 功能概要：消费接收
 * Created by Administrator on 2017/5/17.
 */
@Component
@RabbitListener(queues = "hello")
public class MessageConsumer{

    @Autowired
    private HandleService handleService;

    @RabbitHandler
    public void process(String content) {
        handleService.triggerPush(0,content);
        System.out.println("Receiver11 : " + content);
    }

}
