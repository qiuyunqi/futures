package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.TrdKLines;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface TrdKLinesDao extends BaseDao<TrdKLines, Long> {

	public List<TrdKLines> queryKlinesList(Map<String, Object> map);
	
	public List<TrdKLines> queryKlinesList(int current, int pageNum, Map<String, Object> map);

	public Integer countKlines(Map<String, Object> map);

	public TrdKLines findKlineByInstrumentAndDate(String instrumentId, String tradingDay);

}

