package com.hongwei.futures.service;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.HhrOrgInfo;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface HhrOrgInfoService {
	
	//====================== 基本 C R U D 方法  ===========================
	public HhrOrgInfo get(Long id) ;
	
	public void save(HhrOrgInfo entity) ;
	
	public void delete(Long id) ;
	
}

