package com.example.demo.outer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("outer")
public class OuterController {

    //spring boot项目，模拟第三方接口供spring cloud服务调用
    @RequestMapping(value = "/demo",method = RequestMethod.GET)
    public String thirdPartInterface(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "third part result:" + sdf.format(new Date());
    }
}
