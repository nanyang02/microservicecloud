package com.atguigu.springcloud.controller;

import java.util.List;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.service.DeptService;

import javax.servlet.http.HttpServletRequest;

// content-type:application/json;charset=UTF-8
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService mDeptService;

    @Autowired
    private DiscoveryClient mDiscoveryClient;


    @GetMapping("/discovery")
    public Object discovery() {
        List<String> list = mDiscoveryClient.getServices();
        System.out.println("****************" + list);

        List<ServiceInstance> serviceInstances = mDiscoveryClient.getInstances("MICROSERVICECLOUD-DEPT");

        for (ServiceInstance si : serviceInstances) {
            System.out.println(
                    new StringBuilder(si.getServiceId())
                            .append("\t")
                            .append(si.getHost())
                            .append("\t")
                            .append(si.getPort())
                            .append("\t")
                            .append(si.getUri())
                            .toString()
            );
        }
        return this.mDiscoveryClient;
    }


    @PostMapping("/add")
    public boolean add(@RequestBody Dept dept) {
        return mDeptService.add(dept);
    }

    @GetMapping("/get/{id}")
    public Dept get(@PathVariable Long id) {
        return mDeptService.get(id);
    }

    @GetMapping("/list")
    public List<Dept> list(HttpServletRequest request) {
        String contentType = request.getContentType();
        System.out.println(contentType);
        return mDeptService.list();
    }

    @PutMapping("/name")
    public boolean name(Dept dept) {

        System.out.println(new Gson().toJson(dept));

        return mDeptService.name(dept);
    }

}
