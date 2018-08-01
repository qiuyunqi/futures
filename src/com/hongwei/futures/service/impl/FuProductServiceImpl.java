package com.hongwei.futures.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.FuProductDao;
import com.hongwei.futures.model.FuProduct;
import com.hongwei.futures.service.FuProductService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class FuProductServiceImpl implements FuProductService {
	
	@Autowired
	private FuProductDao fuProductDao;
	
	//====================== 基本 C R U D 方法  ===========================
	public FuProduct get(Long id) {
		return fuProductDao.get(id);
	}
	
	public void save(FuProduct entity) {
		fuProductDao.save(entity);
	}
	
	public void delete(Long id) {
		fuProductDao.delete(id);
	}

	// 获取全部的产品 isDelete=0
	public List<FuProduct> getList() {
		int isDelete = 0;
		return fuProductDao.getList(isDelete);
	}

	// 获取全部的产品
	public List<FuProduct> findList() {
		return fuProductDao.findAll();
	}

	// 获取最大顺序的值
	public FuProduct getMaxOrderNum() {
		return fuProductDao.getMaxOrderNum();
	}

	// 获取最大的id值
	public Long getMaxId() {
		return fuProductDao.getMaxId();
	}
	
	public FuProduct findProductByName(String name){
		return fuProductDao.findProductByName(name);
	}
}

