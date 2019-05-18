package com.example.feign.client.service.impl;

import com.example.feign.client.service.FeignConsumerService;
import org.springframework.stereotype.Service;

@Service
public class FeignConsumerFallbackServiceImpl implements FeignConsumerService {

    /**
     * 定义服务降级处理--hystrix容错处理，包括服务中断，处理超时，异常等等情况
     *
     * @return description 服务降级描述
     */
    @Override
    public String consumer() {
        //todo 记录调用失败日志，可以用mongodb保存
        return "interrupt or timeout :" + this.getClass().getName() + " ===== path: http://ribbon/server";
    }
}
