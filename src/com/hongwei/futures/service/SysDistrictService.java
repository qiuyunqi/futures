package com.hongwei.futures.service;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.SysDistrict;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface SysDistrictService {
	
	//====================== 基本 C R U D 方法  ===========================
	public SysDistrict get(Long id) ;
	
	public void save(SysDistrict entity) ;
	
	public void delete(Long id) ;
	
}

