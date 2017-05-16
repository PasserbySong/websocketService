package com.goldcn.service;

import com.goldcn.vo.WiselyResponse;
import java.util.Iterator;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class HandleService {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    public HandleService() {
    }

    public Object triggerPush(Integer msgType) {
        String key = "msgType_" + msgType;
        WiselyResponse msg;
        switch(msgType.intValue()) {
            case 0:
                msg = new WiselyResponse("消息0发送");
                break;
            case 1:
                msg = new WiselyResponse("消息1发送");
                break;
            case 2:
                msg = new WiselyResponse("消息2发送");
                break;
            default:
                return "error";
        }

        Set names = this.redisTemplate.opsForSet().members(key);
        Iterator var5 = names.iterator();

        while(var5.hasNext()) {
            String name = (String)var5.next();
            this.messagingTemplate.convertAndSendToUser(name, "/queue/notifications", msg);
        }

        return "success";
    }

    public void setupPushMsgType(String name, String msgType) {
        String key = "msgType_" + msgType;
        this.redisTemplate.opsForSet().add(key, new Object[]{name});
    }
}