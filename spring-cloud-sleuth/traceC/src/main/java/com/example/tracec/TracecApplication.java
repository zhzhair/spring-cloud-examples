package com.example.tracec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestController
@SpringCloudApplication
public class TracecApplication {

    public static void main(String[] args) {
        SpringApplication.run(TracecApplication.class, args);
    }

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @GetMapping(value = "/trace-3")
    public String trace(){
        LOGGER.info("trace-3 is running successfully! ");
        return "TraceC";
    }

    @GetMapping(value = "/traceC-1")
    public String traceRPC1(HttpServletRequest request){
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            System.out.println(headerNames.nextElement());
        }
        LOGGER.info("trace-1 is calling successfully! ");
        return getRestTemplate().getForEntity("http://trace-a/trace-1",String.class).getBody();
    }

    @GetMapping(value = "/traceC-2")
    public String traceRPC2(){
        LOGGER.info("trace-2 is calling successfully! ");
        return getRestTemplate().getForEntity("http://trace-b/trace-2",String.class).getBody();
    }
}

