package com.hongwei.futures.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.HhrLoginDao;
import com.hongwei.futures.model.HhrLogin;
import com.hongwei.futures.service.HhrLoginService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class HhrLoginServiceImpl implements HhrLoginService {
	
	@Autowired
	private HhrLoginDao hhrLoginDao;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public HhrLogin get(Long id) {
		return hhrLoginDao.get(id);
	}
	
	@Override
	public void save(HhrLogin entity) {
		hhrLoginDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		hhrLoginDao.delete(id);
	}
	
}

