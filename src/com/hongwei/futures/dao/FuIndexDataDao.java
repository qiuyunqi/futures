package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.FuIndexData;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface FuIndexDataDao extends BaseDao<FuIndexData, Long> {
	
	public List<FuIndexData> findAllByFuIndexData();
	
	public List<FuIndexData> findListBy(int i, int pageSize,
			Map<String, Object> map);
	
	public Integer getCount(Map<String, Object> map);
	
	public List<FuIndexData> findTop10();

}

