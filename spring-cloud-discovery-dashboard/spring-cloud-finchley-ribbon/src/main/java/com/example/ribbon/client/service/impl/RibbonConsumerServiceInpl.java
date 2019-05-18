package com.example.ribbon.client.service.impl;

import com.example.ribbon.client.service.RibbonConsumerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class RibbonConsumerServiceInpl implements RibbonConsumerService {

    @Resource
    private RestTemplate restTemplate;
    private final String URL_CORE = "http://FEIGNCLIENT/feign/";

    @HystrixCommand(fallbackMethod = "consumerFallback",
//        threadPoolProperties = {
//            @HystrixProperty(name = "coreSize", value = "30"),
//            @HystrixProperty(name = "maxQueueSize", value = "101"),
//            @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
//            @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
//            @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
//            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
//        },
        commandProperties = {
            @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),//断路器超时时间
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "1"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "6000")})
    @Override
    public String consumer() {
        return restTemplate.getForEntity(URL_CORE + "server", String.class).getBody();
    }

    /**
     * 定义服务降级处理--hystrix容错处理，包括服务中断，处理超时，异常等等情况
     *
     * @return description 服务降级描述
     */
    private String consumerFallback(){
        //todo 记录调用失败日志，可以用mongodb保存
        return "interrupt or timeout :" + this.getClass().getName() + " ===== path: " + URL_CORE + "server";
    }
}
