package com.atguigu.springcloud.cfgbeans;

import com.atguigu.springcloud.entities.Dept;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// 配置类: boot --> spring applicationContext.xml --- @Configuration 配置
@Configuration
public class ConfigBean {

    // 为springcloud写的一个用于服务调用的一个封装,我们应该发现,其命名和JdbcTemplate,哈哈.
    @Bean
    // 加上这个注解,就是ribbon做了负载均衡,因为如此,所以必须访问注册中心,也因此通过RestTemplate获取访问的url不能是指定的ip端口的方式
    // 访问,而是必须通过注册中心服务来调用.
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
