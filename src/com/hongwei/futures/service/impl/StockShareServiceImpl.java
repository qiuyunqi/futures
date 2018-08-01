package com.hongwei.futures.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.dao.StockShareDao;
import com.hongwei.futures.model.FuStockAccount;
import com.hongwei.futures.model.StockShare;
import com.hongwei.futures.service.StockShareService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class StockShareServiceImpl implements StockShareService {
	
	@Autowired
	private StockShareDao stockShareDao;
	
	//====================== 基本 C R U D 方法  ===========================
	public StockShare get(Long id) {
		return stockShareDao.get(id);
	}
	
	public void save(StockShare entity) {
		stockShareDao.save(entity);
	}
	
	public void delete(Long id) {
		stockShareDao.delete(id);
	}

	@Override
	public List<StockShare> findStockShareByAccount(Long stockId) {
		return stockShareDao.findStockShareByAccount(stockId);
	}

	@Override
	public List<StockShare> queryStockShareList(int i, int pageSize, Map<String, Object> map) {
		return stockShareDao.queryStockShareList(i, pageSize, map);
	}

	@Override
	public Integer countStockShare(Map<String, Object> map) {
		return stockShareDao.countStockShare(map);
	}

	// 通过模糊查询 code 或者 name 字段 得到FuStockAccount集合
	public List<FuStockAccount> findByCodeOrName(String query) {
		return stockShareDao.findByCodeOrName(query);
	}
	
}

