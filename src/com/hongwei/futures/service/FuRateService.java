package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.FuRate;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuRateService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuRate get(Long id) ;
	
	public void save(FuRate entity) ;
	
	public void delete(Long id) ;
	
	public FuRate getByUserID(Long userid);
	
	/**
	 * 统计用户费率数目
	 * @param map 
	 * @return
	 */
	public Integer countRate(Map<String, Object> map);
	
	public List<FuRate> findRateList(int i, int pageSize, Map<String, Object> map);
	
}

