package com.atguigu.springcloud.entites.instance;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 流程实例
 *
 * @author Administrator
 * @最后修改人 杨南
 * @最后修改时间 2020/9/27-17:24
 */
@SuppressWarnings("serial")
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Process implements Serializable {

    /**
     * 流程实例的id(自增id)
     */
    private Long proId;

    /**
     * 流程的id
     */
    private Integer fwId;

    /**
     * 流程使用的模板的id
     */
    private Integer temId;

    /**
     * 实例创建时间
     */
    private Long ts;

    /**
     * 流程创建的用户(默认为null)
     */
    private String u;

    /**
     * 流程创建的机构(默认为null)
     */
    private String o;

    /**
     * 流程创建的人员(默认为null)
     */
    private String p;

    /**
     * 流程的当前的状态
     */
    private String cs;

}

