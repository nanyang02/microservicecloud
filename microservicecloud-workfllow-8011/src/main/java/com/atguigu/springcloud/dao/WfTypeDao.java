package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entites.struct.WfType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @最后修改人 杨南
 * @最后修改时间 2020/9/27-18:00
 */
@Mapper
public interface WfTypeDao {

    boolean add(WfType fwType);

    boolean update(WfType fwType);

    boolean deleteById(Integer id);

    List<WfType> findAll();

    List<WfType> findByIds(String ids);
}
