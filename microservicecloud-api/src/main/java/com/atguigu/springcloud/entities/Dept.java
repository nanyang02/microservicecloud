package com.atguigu.springcloud.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

//import lombok.AllArgsConstructor;

@SuppressWarnings("serial")
//@AllArgsConstructor
@NoArgsConstructor
@Data
// 开启支持链式
@Accessors(chain = true)
// 注意,一定需要进行序列化
public class Dept implements Serializable {
    private Long deptno; // 主键
    private String dname; // 部门的名称

    /*
     * 可能有多个实例,那么存储的时候,1号服务可以对应db1, 2号服务可以对应db2.
     * */
    private String db_source;// 来自那个db, 微服务一个服务可以对应一个数据库,同一个信息被存储到不同的数据库

    // 自行定义有参定义构造
    public Dept(String dname) {
        super();
        this.dname = dname;
    }
}
