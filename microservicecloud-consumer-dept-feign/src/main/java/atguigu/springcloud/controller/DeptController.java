package atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/feign/dept")
public class DeptController {
    /**
     * 本次修改的核心就是Feign接口声明了接口有那些,并且负责http rest调用,这里我们直接把这个定义的接口注入进来用就可以啦
     */
    @Autowired
    private DeptClientService mDeptClientService;

    @PostMapping("/add")
    public boolean add(Dept dept) {
        // 调用add方法,实际上就是通过Feign接口中的声明,就去找对应的服务,并获取到对应提供服务的客户端请求对应的方法来获取结果这么一件事啦,其他的类似不多说
        return mDeptClientService.add(dept);
    }

    @GetMapping("/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return mDeptClientService.get(id);
    }

    @GetMapping("/list")
    public List<Dept> list(HttpServletRequest request) {
        return mDeptClientService.list();
    }
}
