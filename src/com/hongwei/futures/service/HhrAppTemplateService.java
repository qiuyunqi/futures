package com.hongwei.futures.service;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.HhrAppTemplate;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface HhrAppTemplateService {
	
	//====================== 基本 C R U D 方法  ===========================
	public HhrAppTemplate get(Long id) ;
	
	public void save(HhrAppTemplate entity) ;
	
	public void delete(Long id) ;
	
}

