package com.hongwei.futures.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.FuStockMoneyDetailTempDao;
import com.hongwei.futures.model.FuStockMoneyDetailTemp;
import com.hongwei.futures.service.FuStockMoneyDetailTempService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class FuStockMoneyDetailTempServiceImpl implements FuStockMoneyDetailTempService {
	
	@Autowired
	private FuStockMoneyDetailTempDao fuStockMoneyDetailTempDao;
	
	//====================== 基本 C R U D 方法  ===========================
	public FuStockMoneyDetailTemp get(Long id) {
		return fuStockMoneyDetailTempDao.get(id);
	}
	
	public void save(FuStockMoneyDetailTemp entity) {
		fuStockMoneyDetailTempDao.save(entity);
	}
	
	public void delete(Long id) {
		fuStockMoneyDetailTempDao.delete(id);
	}

	@Override
	public Integer findMaxBatchNumber() {
		return fuStockMoneyDetailTempDao.findMaxBatchNumber();
	}

	@Override
	public List<FuStockMoneyDetailTemp> queryBatchList(int i, int pageSize, Map<String, Object> map) {
		return fuStockMoneyDetailTempDao.queryBatchList(i, pageSize, map);
	}

	@Override
	public Integer countBatchTemp(Map<String, Object> map) {
		return fuStockMoneyDetailTempDao.countBatchTemp(map);
	}

	@Override
	public void deleteByBatchId(String batchId) {
		fuStockMoneyDetailTempDao.deleteByBatchId(batchId);
		
	}

	@Override
	public List<FuStockMoneyDetailTemp> findAllTemps() {
		return fuStockMoneyDetailTempDao.findAll();
	}
	
}

