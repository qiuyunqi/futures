package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.FuStockAccount;
import com.hongwei.futures.model.StockShare;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface StockShareService {
	
	//====================== 基本 C R U D 方法  ===========================
	public StockShare get(Long id) ;
	
	public void save(StockShare entity) ;
	
	public void delete(Long id) ;
	
	/**
	 * 根据账户查询对应的股票信息
	 * @param stockId
	 * @return
	 */
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

