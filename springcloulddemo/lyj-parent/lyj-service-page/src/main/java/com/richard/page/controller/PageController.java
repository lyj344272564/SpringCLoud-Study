package com.richard.page.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.richard.page.feign.ProductFeign;
import com.richard.pojo.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
@RequestMapping("/page")
public class PageController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ProductFeign productFeign;

    @GetMapping("/getProduct/{id}")
    public Products getProduct(@PathVariable Integer id) {
        return productFeign.getProduct(id);
    }

    @GetMapping("/loadProductServerPort")
    public String getProductServerPort() {
        return productFeign.getPort();
    }

    @HystrixCommand(
            threadPoolKey = "getProductServerPort2",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize" , value = "1"),
                    @HystrixProperty(name = "maxQueueSize", value = "20")
            },
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            }
    )
    @GetMapping("/loadProductServerPort2")
    public String getProductServerPort2() {
//        List<ServiceInstance> instances = discoveryClient.getInstances("lyj-service-product");
//        ServiceInstance instance = instances.get(0);
//        String host = instance.getHost();
//        int port = instance.getPort();
        return productFeign.getPort();
    }


    @HystrixCommand(
            threadPoolKey = "getProductServerPort3",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize" , value = "1"),
                    @HystrixProperty(name = "maxQueueSize", value = "20")
            },
            commandProperties = {
//                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "8000"),
//                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),
//                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
//                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "3000")
            },
            fallbackMethod = "getProductPortfallBack"
    )
    @GetMapping("/loadProductServerPort3")
    public String getProductServerPort3() {
//        List<ServiceInstance> instances = discoveryClient.getInstances("lyj-service-product");
//        ServiceInstance instance = instances.get(0);
//        String host = instance.getHost();
//        int port = instance.getPort();
        return productFeign.getPort();
    }

    public String getProductPortfallBack() {
        return "-1";
    }
}
