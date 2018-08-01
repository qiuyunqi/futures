package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.StockPublish;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public interface StockPublishService {

	// ====================== 基本 C R U D 方法 ===========================
	public StockPublish get(Long id);

	public void save(StockPublish entity);

	public void delete(Long id);

	public Integer getCount(Map<String, Object> map);

	public List<StockPublish> findPublishByMap(int i, int j, Map<String, Object> map);

}
