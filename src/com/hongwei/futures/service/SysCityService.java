package com.hongwei.futures.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.SysCity;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface SysCityService {
	
	//====================== 基本 C R U D 方法  ===========================
	public SysCity get(Long id) ;
	
	public void save(SysCity entity) ;
	
	public void delete(Long id) ;

	public List<SysCity> findCityByProvince(Long provinceId);
	
	public SysCity findByCityName(String cityName);

	
}

