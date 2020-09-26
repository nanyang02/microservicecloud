package com.atguigu.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.dao.DeptDao;

@Service
public class DeptService implements com.atguigu.springcloud.service.DeptService {

	@Autowired
	private DeptDao mDeptDao;

	@Override
	public boolean add(Dept dept) {
		return mDeptDao.addDept(dept);
	}

    @Override
    public boolean name(Dept dept) {
        return mDeptDao.updateName(dept);
    }

    @Override
	public Dept get(Long id) {
		return mDeptDao.findById(id);
	}

	@Override
	public List<Dept> list() {
		return mDeptDao.findAll();
	}

}
