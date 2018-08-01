package com.hongwei.futures.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongwei.futures.dao.TrdTradeParameterDao;
import com.hongwei.futures.model.TrdTradeParameter;
import com.hongwei.futures.service.TrdTradeParameterService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class TrdTradeParameterServiceImpl implements TrdTradeParameterService {
	
	@Autowired
	private TrdTradeParameterDao trdTradeParameterDao;
	
	//====================== 基本 C R U D 方法  ===========================
	public TrdTradeParameter get(Long id) {
		return trdTradeParameterDao.get(id);
	}
	
	public void save(TrdTradeParameter entity) {
		trdTradeParameterDao.save(entity);
	}
	
	public void delete(Long id) {
		trdTradeParameterDao.delete(id);
	}

	@Override
	public List<TrdTradeParameter> findAllParams() {
		return trdTradeParameterDao.findAll();
	}

	/**
	 * 根据产品(tradeVariety/ instrument_id)ID查询产品
	 */
	public TrdTradeParameter findByTradeVariety(String tradeVariety) {
		return trdTradeParameterDao.findByTradeVariety(tradeVariety);
	}
	
}

