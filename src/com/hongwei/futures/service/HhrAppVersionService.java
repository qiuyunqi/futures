package com.hongwei.futures.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.HhrAppVersion;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface HhrAppVersionService {
	
	//====================== 基本 C R U D 方法  ===========================
	public HhrAppVersion get(Long id) ;
	
	public void save(HhrAppVersion entity) ;
	
	public void delete(Long id) ;
	
	public List<HhrAppVersion> findVersionList(Long appType);
	
}

