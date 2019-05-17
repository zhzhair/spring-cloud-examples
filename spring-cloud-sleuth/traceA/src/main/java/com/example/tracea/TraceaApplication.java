package com.example.tracea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringCloudApplication
public class TraceaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TraceaApplication.class, args);
    }

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @GetMapping(value = "/trace-1")
    public String trace(){
        LOGGER.info("trace-1 is running successfully! ");
        return "TraceA";
    }

    @GetMapping(value = "/traceA-2")
    public String traceRPC(){
        LOGGER.info("trace-2 is calling successfully! ");
        return getRestTemplate().getForEntity("http://trace-b/trace-2",String.class).getBody();
    }
}

