package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import jxl.Sheet;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.StockYjbRank;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public interface StockYjbRankService {

	// ====================== 基本 C R U D 方法 ===========================
	public StockYjbRank get(Long id);

	public void save(StockYjbRank entity);

	public void delete(Long id);

	/**
	 * 通过与收益排序 查询前20的数据
	 * 
	 * @param start
	 * @param pageSize
	 * @return
	 */
	public List<StockYjbRank> getRank(int start, int pageSize);

	/**
	 * 查询所有的股票名称并组成一个JSON字符串
	 * 
	 * @return
	 */
	public String getJsonList();

	/**
	 * 根据id 查询数据
	 * 
	 * @param id
	 *            StockYjbRank的主键
	 * @return
	 */
	public StockYjbRank getById(Long id);

	public List<StockYjbRank> queryYqbRankList(int i, int pageSize, Map<String, Object> map);

	public Integer countYqbRank(Map<String, Object> map);

	public void saveExcel(Sheet sheet) throws Exception;

	/**
	 * 模糊查询
	 * 
	 * @param query
	 * @return
	 */
	public List<StockYjbRank> getSearcher(String query);

	public void saveYqbRankReset();

}
