package com.atguigu.ribbon.self.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Random;


public class MySelfRule extends AbstractLoadBalancerRule {

    Random random = new Random();

    private int total=0;
    private int ci=0;

    /**
     * 根据lb和key来选择一个
     *
     * @param lb
     * @param key
     * @return
     */
    public Server choose(ILoadBalancer lb, Object key) {

        if (null == lb) {
            return null;
        }
        Server server = null;

        while (null == server) {
            // 线程被中断则返回null
            if (Thread.interrupted()) {
                return null;
            }

            // 先找到可达的服务
            List<Server> upList = lb.getReachableServers();

            // 所有的服务
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();
            if (serverCount == 0) {
                // No server
                return null;
            }

            // 获取随机值
//            int index = random.nextInt(serverCount);
//            server = upList.get(index);

            // 自定义一个轮训规则
            if (total < 5) {
                server = upList.get(ci);
                total ++;
            } else {
                total = 0;
                ci ++;
                if (ci >= upList.size()) {
                    ci = 0;
                }
            }


            if (null == server) {

                // 如果没有获取到,则中断一会,下一轮重新开始排
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            // 不会出现,但是必须处理一下,或者出现bug
            server = null;
            // 又重新中断一下,等待下一轮
            Thread.yield();
        }


        return server;
    }


    @Override
    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

}
