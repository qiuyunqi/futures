package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;
import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.LiveDraw;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 小合
 */
public interface LiveDrawDao extends BaseDao<LiveDraw, Long> {
	public Integer countLive(Map<String, Object> map);
	
	public List<LiveDraw> findLiveDrawList(int i, int j, Map<String, Object> map);
}

