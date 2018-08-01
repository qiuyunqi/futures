package com.hongwei.futures.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.StockStatusRecord;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface StockStatusRecordDao extends BaseDao<StockStatusRecord, Long> {

	/**
	 * 根据股票id查询最新的一条的记录信息
	 * @param id
	 * @return
	 */
	public StockStatusRecord findStatusByAccountId(Long stockAccountId);
	
	public StockStatusRecord findStatusByCurrentDate(long stockId, String currentDate, String maxDate);

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

