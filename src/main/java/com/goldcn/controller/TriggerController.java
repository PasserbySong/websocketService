package com.goldcn.controller;

import com.goldcn.service.HandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/trigger")
public class TriggerController {
    @Autowired
    private HandleService handleService;

    /**
     * 触发消息推送
     * @param msgType
     * @return
     */
    @RequestMapping(value = "/pushMsg/{msgType}",method = RequestMethod.GET)
    public Object triggerPush(@PathVariable String msgType) {
        return this.handleService.triggerPush(Integer.valueOf(Integer.parseInt(msgType)));
    }
}