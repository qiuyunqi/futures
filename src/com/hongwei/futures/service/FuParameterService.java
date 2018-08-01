package com.hongwei.futures.service;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.FuParameter;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuParameterService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuParameter get(Long id) ;
	
	public void save(FuParameter entity) ;
	
	public void delete(Long id) ;
	/**
	 * 系统参数
	 * @return
	 */
	public FuParameter findParameter();
	
}

