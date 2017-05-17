package com.goldcn.controller;

import com.goldcn.service.HandleService;
import com.goldcn.vo.WiselyMessage;
import com.goldcn.vo.WiselyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Created by Administrator on 2017/5/4.
 */
@Controller
public class WsController {

    @Autowired
    private HandleService handleService;

    /**
     * 推送消息给订阅客户端
     * @param message
     * @return
     * @throws Exception
     */
    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public WiselyResponse say(WiselyMessage message) throws Exception {
        return new WiselyResponse("Welcome, " + message.getName() + "!");
    }

    /**
     * 客户端发送消息类型 请求响应相应内容
     * @param principal
     * @param msgType
     */
    @MessageMapping("/chat")
    public void handleChat(Principal principal, String msgType) {
        handleService.setupPushMsgType(principal.getName(), msgType);
    }

}
