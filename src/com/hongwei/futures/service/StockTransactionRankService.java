package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import jxl.Sheet;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.StockTransactionRank;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public interface StockTransactionRankService {

	// ====================== 基本 C R U D 方法 ===========================
	public StockTransactionRank get(Long id);

	public void save(StockTransactionRank entity);

	public void delete(Long id);

	/**
	 * 查询前20条数据 按照月收益排序
	 * 
	 * @param start
	 * @param pageSize
	 * @return
	 */
	public List<StockTransactionRank> getRank(int start, int pageSize);

	public List<StockTransactionRank> queryTransRankList(int i, int pageSize, Map<String, Object> map);

	public Integer countTransRank(Map<String, Object> map);

	public void saveExcel(Sheet sheet);

}
