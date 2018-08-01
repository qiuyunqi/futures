package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.WqqContents;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 小合
 */
public interface WqqContentsDao extends BaseDao<WqqContents, Long> {
	public List<WqqContents> findContentsByMap(int i, int pageSize, Map<String, Object> map);
	
	public Integer countContents(Map<String, Object> map);
}

