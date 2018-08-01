package com.hongwei.futures.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.FuParameterDao;
import com.hongwei.futures.model.FuParameter;
import com.hongwei.futures.service.FuParameterService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class FuParameterServiceImpl implements FuParameterService {
	
	@Autowired
	private FuParameterDao fuParameterDao;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public FuParameter get(Long id) {
		return fuParameterDao.get(id);
	}
	
	@Override
	public void save(FuParameter entity) {
		fuParameterDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		fuParameterDao.delete(id);
	}

	@Override
	public FuParameter findParameter() {
		return fuParameterDao.findParameter();
	}
	
}

