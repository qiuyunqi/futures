package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.FuStockMoneyDetailTemp;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuStockMoneyDetailTempService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuStockMoneyDetailTemp get(Long id) ;
	
	public void save(FuStockMoneyDetailTemp entity) ;
	
	public void delete(Long id) ;
	
	//查询最大批次号
	public Integer findMaxBatchNumber();

	public List<FuStockMoneyDetailTemp> queryBatchList(int i, int pageSize, Map<String, Object> map);

	public Integer countBatchTemp(Map<String, Object> map);
	
	//根据批次号删除记录
	public void deleteByBatchId(String batchId);
	
	public List<FuStockMoneyDetailTemp> findAllTemps();
	
}

