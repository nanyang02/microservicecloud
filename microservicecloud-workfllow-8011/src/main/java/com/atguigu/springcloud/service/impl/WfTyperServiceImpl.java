package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.common.service.AbsService;
import com.atguigu.springcloud.dao.WfTypeDao;
import com.atguigu.springcloud.entites.struct.WfType;
import com.atguigu.springcloud.service.WfTyperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @最后修改人 杨南
 * @最后修改时间 2020/9/30-11:40
 */
@Service("WorkFllowTypeService")
public class WfTyperServiceImpl extends AbsService implements WfTyperService {

    @Autowired
    private WfTypeDao mWfTypeDao;

    @Override
    @Transactional(readOnly = false)
    public void add(WfType wfType) {

        checkStringMaxLength(wfType.getWfName(), 30, "fwName", "don't recived empty value");

        execute(mWfTypeDao.add(wfType));
    }

    @Override
    @Transactional(readOnly = false)
    public void edit(WfType wfType) {
        checkStringMaxLength(wfType.getWfName(), 30, "fwName", "don't recived empty value");

        execute(mWfTypeDao.update(wfType));
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Integer id) {
        execute(mWfTypeDao.deleteById(id));
    }

    @Override
    public List<WfType> list() {
        return mWfTypeDao.findAll();
    }


}
