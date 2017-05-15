package com.goldcn.controller;

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
    private SimpMessagingTemplate messagingTemplate;//1

    @MessageMapping("/welcome")//1
    @SendTo("/topic/getResponse")//2
    public WiselyResponse say(WiselyMessage message) throws Exception {
        return new WiselyResponse("Welcome, " + message.getName() + "!");
    }

    @MessageMapping("/chat")
    public void handleChat(Principal principal, String msg) { //2
        if (principal.getName().equals("aaa")) {//3
            messagingTemplate.convertAndSendToUser("bbb",
                    "/queue/notifications", principal.getName() + "-send:"
                            + msg);
        } else {
            messagingTemplate.convertAndSendToUser("aaa",
                    "/queue/notifications", principal.getName() + "-send:"
                            + msg);
        }
    }

}
