package com.goldcn.vo;

/**
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
