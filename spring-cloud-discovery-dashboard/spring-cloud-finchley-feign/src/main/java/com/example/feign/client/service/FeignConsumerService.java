package com.example.feign.client.service;

import com.example.feign.client.service.impl.FeignConsumerFallbackServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "RIBBONCLIENT", fallback = FeignConsumerFallbackServiceImpl.class)
public interface FeignConsumerService {
    //这里@RequestMapping不能放到类上
    @RequestMapping(value = "/ribbon/server", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    String consumer();
}
