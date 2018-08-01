package com.hongwei.futures.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.FuAdminDao;
import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.service.FuAdminService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class FuAdminServiceImpl implements FuAdminService {
	
	@Autowired
	private FuAdminDao fuAdminDao;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public FuAdmin get(Long id) {
		return fuAdminDao.get(id);
	}
	
	@Override
	public void save(FuAdmin entity) {
		fuAdminDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		fuAdminDao.delete(id);
	}

	@Override
	public FuAdmin findAdminByAccount(String account) {
		return fuAdminDao.findAdminByAccount(account);
	}

	@Override
	public FuAdmin findLoginByToken(String token) {
		return fuAdminDao.findLoginByToken(token);
	}

	@Override
	public List<Object[]> findList(int i, int pageSize, Map<String, Object> map) {
		return fuAdminDao.findList(i, pageSize, map);
	}

	@Override
	public Integer getCount(Map<String, Object> map) {
		return fuAdminDao.getCount(map);
	}
	
}

