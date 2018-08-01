package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.TrdKLines;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface TrdKLinesService {
	
	//====================== 基本 C R U D 方法  ===========================
	public TrdKLines get(Long id) ;
	
	public void save(TrdKLines entity) ;
	
	public void delete(Long id) ;

	/**
	 * 查询全部的K线
	 * @return
	 */
	public List<TrdKLines> findAll();
	
	public List<TrdKLines> queryKlinesList(Map<String, Object> map);
	
	/**
	 * 分页查询K线的数据
	 * @param current  从那条数据开始
	 * @param pageNum  那条数据结束
	 * @param map
	 * @return
	 */
	public List<TrdKLines> queryKlinesList(int current, int pageNum, Map<String, Object> map);

	public Integer countKlines(Map<String, Object> map);
	
	public TrdKLines findKlineByInstrumentAndDate(String instrumentId, String tradingDay);
	
}

