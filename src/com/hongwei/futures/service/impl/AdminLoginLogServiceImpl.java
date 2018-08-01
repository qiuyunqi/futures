package com.hongwei.futures.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.AdminLoginLogDao;
import com.hongwei.futures.model.AdminLoginLog;
import com.hongwei.futures.service.AdminLoginLogService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class AdminLoginLogServiceImpl implements AdminLoginLogService {
	
	@Autowired
	private AdminLoginLogDao adminLoginLogDao;
	
	//====================== 基本 C R U D 方法  ===========================
	public AdminLoginLog get(Long id) {
		return adminLoginLogDao.get(id);
	}
	
	public void save(AdminLoginLog entity) {
		adminLoginLogDao.save(entity);
	}
	
	public void delete(Long id) {
		adminLoginLogDao.delete(id);
	}

	@Override
	public List<AdminLoginLog> queryAdminLoginLog(int i, int pageSize, Map<String, Object> map) {
		return adminLoginLogDao.queryAdminLoginLog(i, pageSize, map);
	}

	@Override
	public Integer countAdminLoginLog(Map<String, Object> map) {
		return adminLoginLogDao.countAdminLoginLog(map);
	}
	
}

