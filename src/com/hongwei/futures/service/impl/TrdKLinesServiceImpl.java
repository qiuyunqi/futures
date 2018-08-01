package com.hongwei.futures.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.dao.TrdKLinesDao;
import com.hongwei.futures.model.TrdKLines;
import com.hongwei.futures.service.TrdKLinesService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class TrdKLinesServiceImpl implements TrdKLinesService {
	
	@Autowired
	private TrdKLinesDao trdKLinesDao;
	
	//====================== 基本 C R U D 方法  ===========================
	public TrdKLines get(Long id) {
		return trdKLinesDao.get(id);
	}
	
	public void save(TrdKLines entity) {
		trdKLinesDao.save(entity);
	}
	
	public void delete(Long id) {
		trdKLinesDao.delete(id);
	}

	public List<TrdKLines> findAll() {
		return trdKLinesDao.findAll();
	}
	
	@Override
	public List<TrdKLines> queryKlinesList(Map<String, Object> map) {
		return trdKLinesDao.queryKlinesList(map);
		
	}
	
	@Override
	public List<TrdKLines> queryKlinesList(int current, int pageNum, Map<String, Object> map) {
		return trdKLinesDao.queryKlinesList(current, pageNum, map);
	}

	@Override
	public Integer countKlines(Map<String, Object> map) {
		return trdKLinesDao.countKlines(map);
	}

	@Override
	public TrdKLines findKlineByInstrumentAndDate(String instrumentId, String tradingDay) {
		return trdKLinesDao.findKlineByInstrumentAndDate(instrumentId, tradingDay);
	}
	
}

