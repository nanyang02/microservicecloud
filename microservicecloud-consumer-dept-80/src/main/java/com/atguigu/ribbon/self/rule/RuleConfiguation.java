package com.atguigu.ribbon.self.rule;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 注意事项: 坑爹的地方
 * <p>
 * - 不能放在主启动类平等或者子级目录下面,并且不能是@ComponentScan所扫描的当前包及子包下面,否则这个配置类就会被所有的
 * Ribbon客户端所共享,也就说我们是达不到特殊化定制的目的了,所以单独的起了一个包,专门供自定义规则使用.
 */
@Configuration
public class RuleConfiguation {

//    1) 自定义配置来直接测试一下
//    @Bean
//    public IRule getIRule() {
//        return new RandomRule();
//    }

    //    2) 完全自定义一个规则来实现负载均衡.
    // 其实我们就是自己重新实现了一下随机方法,但是不同的是,这个是添加了点打印消息的随机,哈哈
    @Bean
    public IRule getIRule() {
        return new MySelfRule();
    }

}
