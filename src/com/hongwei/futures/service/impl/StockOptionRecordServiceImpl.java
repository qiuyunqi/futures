package com.hongwei.futures.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.StockOptionRecordDao;
import com.hongwei.futures.model.StockOptionRecord;
import com.hongwei.futures.service.StockOptionRecordService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class StockOptionRecordServiceImpl implements StockOptionRecordService {
	
	@Autowired
	private StockOptionRecordDao stockOptionRecordDao;
	
	//====================== 基本 C R U D 方法  ===========================
	public StockOptionRecord get(Long id) {
		return stockOptionRecordDao.get(id);
	}
	
	public void save(StockOptionRecord entity) {
		stockOptionRecordDao.save(entity);
	}
	
	public void delete(Long id) {
		stockOptionRecordDao.delete(id);
	}

	public List<StockOptionRecord> findByUserId(Long userId, int isOption) {
		return stockOptionRecordDao.findByUserId(userId, isOption);
	}
	
}

