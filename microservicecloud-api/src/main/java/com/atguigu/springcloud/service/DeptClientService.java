package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Dept;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 指定一个微服务,然后定一个接口.
 */
@FeignClient("MICROSERVICECLOUD-DEPT")
public interface DeptClientService {

    // 此处是直接调用提供者的,所以要注意,mapping的地址要是提供者的url
    @PostMapping("/dept/add")
    public boolean add(Dept dept);

    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id);

    @GetMapping("/dept/list")
    public List<Dept> list();

}
