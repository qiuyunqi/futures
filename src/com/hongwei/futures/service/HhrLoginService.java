package com.hongwei.futures.service;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.HhrLogin;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface HhrLoginService {
	
	//====================== 基本 C R U D 方法  ===========================
	public HhrLogin get(Long id) ;
	
	public void save(HhrLogin entity) ;
	
	public void delete(Long id) ;
	
}

