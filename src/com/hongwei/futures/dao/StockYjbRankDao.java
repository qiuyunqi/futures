package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.model.StockYjbRank;

/**
 * 
 * @description 自动生成 dao
 * 
 * @author 小合
 */
public interface StockYjbRankDao extends BaseDao<StockYjbRank, Long> {

	/**
	 * 通过与收益排序 查询前20的数据
	 * 
	 * @param start
	 * @param pageSize
	 * @return
	 */
	List<StockYjbRank> getRank(int start, int pageSize);

	/**
	 * 查询所有的股票名称并组成一个JSON字符串
	 * 
	 * @return
	 */
	String getJsonList();

	public List<StockYjbRank> queryYqbRankList(int i, int pageSize, Map<String, Object> map);

	public Integer countYqbRank(Map<String, Object> map);

	/**
	 * 模糊查询
	 * 
	 * @param query
	 *            查询的条件
	 * @return
	 */
	public List<StockYjbRank> getSearcher(String query);

	public void saveYqbRankReset();

}
