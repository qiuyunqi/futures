package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.FuStockAccount;
import com.hongwei.futures.model.StockShare;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 小合
 */
public interface StockShareDao extends BaseDao<StockShare, Long> {

	public List<StockShare> findStockShareByAccount(Long stockId);

	public List<StockShare> queryStockShareList(int i, int pageSize, Map<String, Object> map);

	public Integer countStockShare(Map<String, Object> map);

	/**
	 * 通过模糊查询 code 或者 name 字段 得到FuStockAccount集合
	 * @param query
	 * @return
	 */
	public List<FuStockAccount> findByCodeOrName(String query);

}

