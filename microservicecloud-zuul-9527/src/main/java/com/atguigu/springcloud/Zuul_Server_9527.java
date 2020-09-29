package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @最后修改人 杨南
 * @最后修改时间 2020/9/29-10:05
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class Zuul_Server_9527 {
    public static void main(String[] args) {
        SpringApplication.run(Zuul_Server_9527.class, args);
    }
}
