package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.common.servlet.ListResponse;
import com.atguigu.springcloud.common.servlet.ObjectResponse;
import com.atguigu.springcloud.common.utils.JacksonUtil;
import com.atguigu.springcloud.entites.struct.WfType;
import com.atguigu.springcloud.service.WfTyperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @最后修改人 杨南
 * @最后修改时间 2020/9/30-14:39
 */
@RestController
@RequestMapping("/wf")
public class WfTypeController {

    @Autowired
    private WfTyperService mWfTyperService;

    @GetMapping("/list")
    public ListResponse list() {
        return new ListResponse(mWfTyperService.list())
                .useDataSize();
    }

    @PostMapping("/add")
    public ObjectResponse add(@RequestBody WfType wfType) {
        System.out.println(">>>>> POST REQUEST : " + JacksonUtil.toJson(wfType));
        mWfTyperService.add(wfType);

        return new ObjectResponse();
    }

    @DeleteMapping("/delete/{id}")
    public ObjectResponse delete(@PathVariable("id") Integer id) {
        // @RequestParam Integer id
        System.out.println(">>>>> DELETE REQUEST : " + id);
//        mWfTyperService.delete(id);
        return new ObjectResponse();
    }

    @DeleteMapping("/delete/ids")
    public ObjectResponse delete(@RequestParam String ids) {
        // @RequestParam Integer id
        System.out.println(">>>>> DELETE REQUEST : " + ids);
//        mWfTyperService.delete(id);
        return new ObjectResponse();
    }

    @PutMapping("/edit")
    public ObjectResponse edit(@RequestBody WfType wfType) {
        System.out.println(">>>>> PUT REQUEST : " + JacksonUtil.toJson(wfType));

        mWfTyperService.edit(wfType);

        return new ObjectResponse();
    }

}
