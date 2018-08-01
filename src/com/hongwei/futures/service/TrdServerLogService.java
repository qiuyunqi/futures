package com.hongwei.futures.service;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.TrdServerLog;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface TrdServerLogService {
	
	//====================== 基本 C R U D 方法  ===========================
	public TrdServerLog get(Long id) ;
	
	public void save(TrdServerLog entity) ;
	
	public void delete(Long id) ;
	
}

