package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.AdminLoginLog;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface AdminLoginLogService {
	
	//====================== 基本 C R U D 方法  ===========================
	public AdminLoginLog get(Long id) ;
	
	public void save(AdminLoginLog entity) ;
	
	public void delete(Long id) ;

	public List<AdminLoginLog> queryAdminLoginLog(int i, int pageSize, Map<String, Object> map);

	public Integer countAdminLoginLog(Map<String, Object> map);
	
}

