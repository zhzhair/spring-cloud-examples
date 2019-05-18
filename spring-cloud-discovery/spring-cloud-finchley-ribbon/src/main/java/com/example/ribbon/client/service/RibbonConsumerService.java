package com.example.ribbon.client.service;

/**
 * ribbon消费接口,ribbon和feign消费客户端互相调用
 */
public interface RibbonConsumerService {

    /**
     * ribbon消费
     *
     * @return path 请求路径
     */
    String consumer();
}
