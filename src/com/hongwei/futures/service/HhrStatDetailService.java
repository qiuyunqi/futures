package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.HhrStatDetail;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface HhrStatDetailService {
	
	//====================== 基本 C R U D 方法  ===========================
	public HhrStatDetail get(Long id) ;
	
	public void save(HhrStatDetail entity) ;
	
	public void delete(Long id) ;
	
	public HhrStatDetail findHhrStatDetailByUser(Long userId);
	
	public HhrStatDetail findHhrStatDetailByUserAndDate(Long userId, String date);
	
	public List<Object[]> findStatDataByMap(Map<String, Object> map);
	
	public HhrStatDetail findHhrStatDetailByUserAndIncomeType(Long userId, Integer incomeType);
}

