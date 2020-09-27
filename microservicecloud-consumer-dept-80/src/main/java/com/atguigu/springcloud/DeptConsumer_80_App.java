package com.atguigu.springcloud;

import com.atguigu.ribbon.self.rule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
// 添加下面的注解,适用自定义的规则.
// 启动的时候加载我们的自定义的规则类,从而实现自己定义的均衡算法
// Ex: @@RibbonClient(name="xxxx", configuration=MySelfRule.class) ; 1) 指定应用的服务的名称; 2)指定我们的自定义的规则的class
@RibbonClient(name="MICROSERVICECLOUD-DEPT", configuration = MySelfRule.class)
public class DeptConsumer_80_App {

    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer_80_App.class, args);
    }
}
