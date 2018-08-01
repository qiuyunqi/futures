package com.hongwei.futures.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongwei.futures.dao.StockPublishDao;
import com.hongwei.futures.model.StockPublish;
import com.hongwei.futures.service.StockPublishService;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class StockPublishServiceImpl implements StockPublishService {

	@Autowired
	private StockPublishDao stockPublishDao;

	// ====================== 基本 C R U D 方法 ===========================
	public StockPublish get(Long id) {
		return stockPublishDao.get(id);
	}

	public void save(StockPublish entity) {
		stockPublishDao.save(entity);
	}

	public void delete(Long id) {
		stockPublishDao.delete(id);
	}

	public Integer getCount(Map<String, Object> map) {
		return stockPublishDao.getCount(map);
	}

	public List<StockPublish> findPublishByMap(int i, int j, Map<String, Object> map) {
		return stockPublishDao.findPublishByMap(i, j, map);
	}

}
