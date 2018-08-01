package com.hongwei.futures.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.HhrPromoteParameterDao;
import com.hongwei.futures.model.HhrPromoteParameter;
import com.hongwei.futures.service.HhrPromoteParameterService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class HhrPromoteParameterServiceImpl implements HhrPromoteParameterService {
	
	@Autowired
	private HhrPromoteParameterDao hhrPromoteParameterDao;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public HhrPromoteParameter get(Long id) {
		return hhrPromoteParameterDao.get(id);
	}
	
	@Override
	public void save(HhrPromoteParameter entity) {
		hhrPromoteParameterDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		hhrPromoteParameterDao.delete(id);
	}

	@Override
	public HhrPromoteParameter findParameter() {
		return hhrPromoteParameterDao.findParameter();
	}
	
}

