package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.model.FuRate;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface FuRateDao extends BaseDao<FuRate, Long> {
	public FuRate findRateByUserID(Long userid);
	
	public List<FuRate> findRateList(int i, int pageSize, Map<String, Object> map);

	public Integer countRate(Map<String, Object> map);
}

