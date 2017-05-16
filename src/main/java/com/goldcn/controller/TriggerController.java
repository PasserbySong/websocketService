package com.goldcn.controller;

import com.goldcn.service.HandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/trigger"})
public class TriggerController {
    @Autowired
    private HandleService handleService;

    public TriggerController() {
    }

    @RequestMapping({"/pushMsg/{msgType}"})
    public Object triggerPush(@PathVariable String msgType) {
        return this.handleService.triggerPush(Integer.valueOf(Integer.parseInt(msgType)));
    }
}