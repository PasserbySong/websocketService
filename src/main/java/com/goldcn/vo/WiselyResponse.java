package com.goldcn.vo;

/**
 * 服务器发送响应消息类
 * Created by Administrator on 2017/5/4.
 */
public class WiselyResponse {
    private String responseMessage;
    public WiselyResponse(String responseMessage){
        this.responseMessage = responseMessage;
    }
    public String getResponseMessage(){
        return responseMessage;
    }
}
