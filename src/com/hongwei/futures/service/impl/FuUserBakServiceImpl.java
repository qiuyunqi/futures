package com.hongwei.futures.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.FuUserBakDao;
import com.hongwei.futures.model.FuUserBak;
import com.hongwei.futures.service.FuUserBakService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class FuUserBakServiceImpl implements FuUserBakService {
	
	@Autowired
	private FuUserBakDao fuUserBakDao;
	
	//====================== 基本 C R U D 方法  ===========================
	public FuUserBak get(Long id) {
		return fuUserBakDao.get(id);
	}
	
	public void save(FuUserBak entity) {
		fuUserBakDao.save(entity);
	}
	
	public void delete(Long id) {
		fuUserBakDao.delete(id);
	}
	
}

