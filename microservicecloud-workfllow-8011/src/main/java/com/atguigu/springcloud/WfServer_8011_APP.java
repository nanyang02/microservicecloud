package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @最后修改人 杨南
 * @最后修改时间 2020/9/30-14:34
 */
@SpringBootApplication
@EnableEurekaClient // 开启自动注册到eureka注册中心
@EnableDiscoveryClient // 服务发现
public class WfServer_8011_APP {

    public static void main(String[] args) {
        SpringApplication.run(WfServer_8011_APP.class, args);
    }
}
