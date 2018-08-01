package com.hongwei.futures.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.HhrAppVersionDao;
import com.hongwei.futures.model.HhrAppVersion;
import com.hongwei.futures.service.HhrAppVersionService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class HhrAppVersionServiceImpl implements HhrAppVersionService {
	
	@Autowired
	private HhrAppVersionDao hhrAppVersionDao;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public HhrAppVersion get(Long id) {
		return hhrAppVersionDao.get(id);
	}
	
	@Override
	public void save(HhrAppVersion entity) {
		hhrAppVersionDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		hhrAppVersionDao.delete(id);
	}

	@Override
	public List<HhrAppVersion> findVersionList(Long appType) {
		return hhrAppVersionDao.findVersionList(appType);
	}
	
}

