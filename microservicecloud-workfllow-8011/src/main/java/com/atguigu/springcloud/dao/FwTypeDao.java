package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entites.struct.WfType;

import java.util.List;

/**
 * @最后修改人 杨南
 * @最后修改时间 2020/9/27-18:00
 */
public interface FwTypeDao {

    boolean addFwType(WfType fwType);

    boolean updateName(WfType fwType);

    List<WfType> findAll();

}
