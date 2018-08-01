package com.hongwei.futures.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.HhrOrgInfoDao;
import com.hongwei.futures.model.HhrOrgInfo;
import com.hongwei.futures.service.HhrOrgInfoService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class HhrOrgInfoServiceImpl implements HhrOrgInfoService {
	
	@Autowired
	private HhrOrgInfoDao hhrOrgInfoDao;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public HhrOrgInfo get(Long id) {
		return hhrOrgInfoDao.get(id);
	}
	
	@Override
	public void save(HhrOrgInfo entity) {
		hhrOrgInfoDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		hhrOrgInfoDao.delete(id);
	}
	
}

