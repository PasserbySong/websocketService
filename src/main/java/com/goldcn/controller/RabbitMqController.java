package com.goldcn.controller;

import com.goldcn.mq.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/5/17.
 */
@RestController
@RequestMapping(value="/rabbitmq")
public class RabbitMqController {

    @Autowired
    private MessageProducer messageProducer;

    /**
     * 发送测试消息队列
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Object addEntity() {
        messageProducer.send("jkljklkjljjkljl");
        return "success";
    }
}
