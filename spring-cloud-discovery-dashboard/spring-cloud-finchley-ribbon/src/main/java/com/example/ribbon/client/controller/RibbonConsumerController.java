package com.example.ribbon.client.controller;

import com.example.ribbon.client.service.RibbonConsumerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Random;

/**
 * ribbon消费和eureka服务接口
 */
@RestController
@RequestMapping("ribbon")
public class RibbonConsumerController {

    @Resource
    private RibbonConsumerService ribbonConsumerService;

    @RequestMapping(value = "/consumer", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String consumer() {
        String description = ribbonConsumerService.consumer();
        System.out.println("ribbon消费 ********* " +description);
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
        return "ribbon/server/" + System.currentTimeMillis();
    }

    @RequestMapping(value = "/consumer/{n}", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String consumer(@PathVariable("n") int n) {
        for (int i = 0; i < n; i++) {
            String description = ribbonConsumerService.consumer();
            System.out.println("ribbon消费 ********* " + description);
        }
        return "ribbon消费 ********* dashboard test";
    }
}
