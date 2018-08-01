package com.hongwei.futures.service;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.FuUserBak;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuUserBakService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuUserBak get(Long id) ;
	
	public void save(FuUserBak entity) ;
	
	public void delete(Long id) ;
	
}

