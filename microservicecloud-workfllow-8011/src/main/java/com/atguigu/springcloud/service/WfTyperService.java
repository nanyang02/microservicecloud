package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entites.struct.WfType;

import java.util.List;

/**
 * @最后修改人 杨南
 * @最后修改时间 2020/9/30-11:39
 */
public interface WfTyperService {

    /**
     * 添加一个流程类型
     * @param wfType
     * @return
     */
    void add(WfType wfType);

    void edit(WfType wfType);

    void delete(Integer id);

    /**
     * 获取流程的列表
     * @return
     */
    List<WfType> list();

}
