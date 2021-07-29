package com.richard.page;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableCircuitBreaker
@EnableFeignClients
public class PageApplication {
    public static void main(String[] args) {
        SpringApplication.run(PageApplication.class,args);
    }

    /**
     * 向容器中注入resttemplate
     */
    @Bean
    @LoadBalanced // 启用请求的负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
