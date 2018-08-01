package com.hongwei.futures.service;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.HhrPromoteParameter;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface HhrPromoteParameterService {
	
	//====================== 基本 C R U D 方法  ===========================
	public HhrPromoteParameter get(Long id) ;
	
	public void save(HhrPromoteParameter entity) ;
	
	public void delete(Long id) ;
	
	/**
	 * 推广参数
	 * @return
	 */
	public HhrPromoteParameter findParameter();
	
}

