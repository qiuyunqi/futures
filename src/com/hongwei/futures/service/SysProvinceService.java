package com.hongwei.futures.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.SysProvince;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface SysProvinceService {
	
	//====================== 基本 C R U D 方法  ===========================
	public SysProvince get(Long id) ;
	
	public void save(SysProvince entity) ;
	
	public void delete(Long id) ;

	public List<SysProvince> findAllProvince();

	public SysProvince findByName(String provinceName);
}

