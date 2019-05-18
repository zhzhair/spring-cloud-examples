package com.example.feign.client.controller;

import com.example.feign.client.service.FeignConsumerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Random;

/**
 * feign消费和eureka服务接口
 */
@RestController
@RequestMapping("feign")
public class FeignConsumerController {

    @Resource
    private FeignConsumerService feignConsumerService;

    @Resource(name = "outer",description = "第三方restful调用")
    private RestTemplate restTemplate;

    @RequestMapping(value = "/consumer", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String consumer() {
        String description = feignConsumerService.consumer();
        System.out.println("feign消费" + "*********" +description);
        return description;
    }

    @RequestMapping(value = "/server", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String server() {
        int mills = new Random().nextInt(2700);
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "feign/server/" + System.currentTimeMillis();
    }

    @RequestMapping(value = "/thirdPart", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String demo() {
        return restTemplate.getForObject("http://localhost/outer/demo",String.class);
    }
}
