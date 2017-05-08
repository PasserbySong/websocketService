package com.goldcn.controller;


import com.goldcn.test.dao.QuotationMin5Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/4/17.
 */
@RestController
public class DemoController {

    @Autowired
    private QuotationMin5Mapper quotationMin5Mapper;

    @RequestMapping(value = "/demo",method = RequestMethod.GET)
    public Object demo(){
        return "hello world";
    }

    @RequestMapping(value = "/demo1",method = RequestMethod.GET)
    public Object test(){
        return quotationMin5Mapper.selectByPrimaryKey(2);
    }

}
