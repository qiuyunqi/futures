package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.HhrStatTemp;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface HhrStatTempService {
	
	//====================== 基本 C R U D 方法  ===========================
	public HhrStatTemp get(Long id) ;
	
	public void save(HhrStatTemp entity) ;
	
	public void delete(Long id) ;
	
	//查询最大批次号
	public Integer findMaxBatchNumber();
	
	//根据批次号删除记录
	public void deleteByBatchId(String batchId);
	
	//查询所有记录
	public List<Map<String, Object>> findAllTemps();
	
	public List<HhrStatTemp> queryHhrStatTempList(int i, int j, Map<String, Object> map);

	public Integer countHhrStatTemp(Map<String, Object> map);
	
}

