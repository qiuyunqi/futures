package com.hongwei.futures.service;

import java.util.Date;
import java.util.List;

import java.util.Map;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.StockStatusRecord;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface StockStatusRecordService {
	
	//====================== 基本 C R U D 方法  ===========================
	public StockStatusRecord get(Long id) ;
	
	public void save(StockStatusRecord entity) ;
	
	public void delete(Long id) ;

	/**
	 * 根据股票id查询最新的一条的记录信息
	 * @param id
	 * @return
	 */
	public StockStatusRecord findStatusByAccountId(Long stockAccountId);

	// 根据当前时间查询 是否有这条记录
	public StockStatusRecord findStatusByCurrentDate(long StockId, String currentDate, String maxDate);

	/**
	 * 判断今天是否有操作过状态的记录
	 * @param currDate
	 * @param maxDa
	 * @param isOperation
	 * @return
	 */
	public List<StockStatusRecord> findStockStatusByTodayAndIsoperation(
			Date currDate, Date maxDa, int isOperation);

	public List<StockStatusRecord> queryStockStatusRecord(int i, int pageSize, Map<String, Object> map);

	public Integer countStockStatusRecord(Map<String, Object> map);
	
}

