package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.model.StockPublish;

/**
 * 
 * @description 自动生成 dao
 * 
 * @author 小合
 */
public interface StockPublishDao extends BaseDao<StockPublish, Long> {
	public Integer getCount(Map<String, Object> map);

	public List<StockPublish> findPublishByMap(int i, int j, Map<String, Object> map);

}
