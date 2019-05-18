package com.example.notes.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RefreshScope
@RestController
public class ConfigController {
    @Value("${from}")
    private String from;
    @Resource
    private Environment environment;

    @RequestMapping(value = "/config",method = {RequestMethod.GET,RequestMethod.POST})
    public String config(){
        String sout = "配置中心的值，from: " + environment.getProperty("from");
        System.err.println("配置中心的值，from: " + from);
        System.err.println(sout);
        return sout;
    }
}
