package com.hongwei.futures.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.StockStatusRecordDao;
import com.hongwei.futures.model.StockStatusRecord;
import com.hongwei.futures.service.StockStatusRecordService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class StockStatusRecordServiceImpl implements StockStatusRecordService {
	
	@Autowired
	private StockStatusRecordDao stockStatusRecordDao;
	
	//====================== 基本 C R U D 方法  ===========================
	public StockStatusRecord get(Long id) {
		return stockStatusRecordDao.get(id);
	}
	
	public void save(StockStatusRecord entity) {
		stockStatusRecordDao.save(entity);
	}
	
	public void delete(Long id) {
		stockStatusRecordDao.delete(id);
	}

	/**
	 * 根据股票id查询最新的一条的记录信息
	 * @param id
	 * @return
	 */
	public StockStatusRecord findStatusByAccountId(Long stockAccountId) {
		return stockStatusRecordDao.findStatusByAccountId(stockAccountId);
	}

	// 根据表的id 和当前时间查询 是否有这条记录
	public StockStatusRecord findStatusByCurrentDate(long stockId, String currentDate, String maxDate) {
		return stockStatusRecordDao.findStatusByCurrentDate(stockId, currentDate, maxDate);
	}

	/**
	 * 判断今天是否有操作过状态的记录
	 * @param currDate
	 * @param maxDa
	 * @param isOperation
	 * @return
	 */
	public List<StockStatusRecord> findStockStatusByTodayAndIsoperation(
			Date currDate, Date maxDa, int isOperation) {
		return stockStatusRecordDao.findStockStatusByTodayAndIsoperation(currDate, maxDa, isOperation);
	}

	@Override
	public List<StockStatusRecord> queryStockStatusRecord(int i, int pageSize, Map<String, Object> map) {
		return stockStatusRecordDao.queryStockStatusRecord(i, pageSize, map);
	}

	@Override
	public Integer countStockStatusRecord(Map<String, Object> map) {
		return stockStatusRecordDao.countStockStatusRecord(map);
	}

	
}

