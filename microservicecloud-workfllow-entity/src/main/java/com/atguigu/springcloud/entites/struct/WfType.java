package com.atguigu.springcloud.entites.struct;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @最后修改人 杨南
 * @最后修改时间 2020/9/27-17:37
 */
@SuppressWarnings("serial")
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class WfType {

    /**
     * 流程类型id, 自增主键
     */
    private Integer wfId;

    /**
     * 流程类型的key(冗余,也可以作为唯一校验,但是此处不强制,主要使用wfId)
     */
    private String wfKey;

    /**
     * 流程类型的名称
     */
    private String wfName;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 有效否
     */
    private Boolean valid;

}
