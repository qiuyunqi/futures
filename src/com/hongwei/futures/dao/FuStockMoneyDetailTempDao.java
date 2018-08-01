package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.FuStockMoneyDetailTemp;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 小合
 */
public interface FuStockMoneyDetailTempDao extends BaseDao<FuStockMoneyDetailTemp, Long> {

	public Integer findMaxBatchNumber();

	public List<FuStockMoneyDetailTemp> queryBatchList(int i, int pageSize, Map<String, Object> map);

	public Integer countBatchTemp(Map<String, Object> map);

	public void deleteByBatchId(String batchId);

}

