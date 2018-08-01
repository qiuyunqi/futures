package com.hongwei.futures.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.SysDepartmentDao;
import com.hongwei.futures.model.SysDepartment;
import com.hongwei.futures.service.SysDepartmentService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class SysDepartmentServiceImpl implements SysDepartmentService {
	
	@Autowired
	private SysDepartmentDao sysDepartmentDao;
	
	//====================== 基本 C R U D 方法  ===========================
	public SysDepartment get(Long id) {
		return sysDepartmentDao.get(id);
	}
	
	public void save(SysDepartment entity) {
		sysDepartmentDao.save(entity);
	}
	
	public void delete(Long id) {
		sysDepartmentDao.delete(id);
	}
	
}

