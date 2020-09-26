package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/consumer/dept")
public class DeptController {

    private static final String SUF_DEPT_SERVERICE = "http://localhost:8001/dept";
    private static final String SERVER_DEPT = "http://MICROSERVICECLOUD-DEPT/dept";

    @Autowired
    private RestTemplate mRestTemplate;

    // post(url, requestMap, responseBean.class)


//    @PostMapping("/add_d")
//    public boolean add_d(Dept dept) {
//        // 发一个post请求,用于通过rest服务调用的方式来访问内部的服务的
//        return mRestTemplate.postForObject(SUF_DEPT_SERVERICE+"/add", dept, Boolean.class);
//    }

    @PostMapping("/add")
    public boolean add(Dept dept) {
        // 发一个post请求,用于通过rest服务调用的方式来访问内部的服务的
        return mRestTemplate.postForObject(SERVER_DEPT+"/add", dept, Boolean.class);
    }

//    @GetMapping("/get_d/{id}")
//    public Dept get_d(@PathVariable("id") Long id) {
//        return mRestTemplate.getForObject(SUF_DEPT_SERVERICE+"/get/" + id, Dept.class);
//    }
    @GetMapping("/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return mRestTemplate.getForObject(SERVER_DEPT+"/get/" + id, Dept.class);
    }

//    @GetMapping("/list_d")
//    public List<Dept> list_d() {
//        return mRestTemplate.getForObject(SUF_DEPT_SERVERICE+"/list", List.class);
//    }

    @GetMapping("/list")
    public List<Dept> list(HttpServletRequest request) {
        return mRestTemplate.getForObject(SERVER_DEPT+"/list", List.class, request);
    }



    // 调用一下服务发现,就是获取一下需要的服务发现的接口
    @GetMapping("/discovery")
    public Object discovery() {
        return mRestTemplate.getForObject(SERVER_DEPT+"/discovery", Object.class);
    }
}
