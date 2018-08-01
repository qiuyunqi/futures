package com.hongwei.futures.service;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.FuExamine;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuExamineService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuExamine get(Long id) ;
	
	public void save(FuExamine entity) ;
	
	public void delete(Long id) ;
	
}

