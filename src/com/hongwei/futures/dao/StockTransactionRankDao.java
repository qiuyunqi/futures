package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.model.StockTransactionRank;

/**
 * 
 * @description 自动生成 dao
 * 
 * @author 小合
 */
public interface StockTransactionRankDao extends BaseDao<StockTransactionRank, Long> {

	/**
	 * 查询前20条数据 按照月收益排序
	 * 
	 * @param start
	 * @param pageSize
	 * @return
	 */
	List<StockTransactionRank> getRank(int start, int pageSize);

	public List<StockTransactionRank> queryTransRankList(int i, int pageSize, Map<String, Object> map);

	public Integer countTransRank(Map<String, Object> map);

}
