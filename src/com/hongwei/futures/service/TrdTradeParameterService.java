package com.hongwei.futures.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.TrdTradeParameter;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface TrdTradeParameterService {
	
	//====================== 基本 C R U D 方法  ===========================
	public TrdTradeParameter get(Long id) ;
	
	public void save(TrdTradeParameter entity) ;
	
	public void delete(Long id) ;
	
	/**
	 * 查询所有记录
	 * @return
	 */
	public List<TrdTradeParameter> findAllParams();
	/**
	 * 根据产品(tradeVariety/ instrument_id)ID查询产品
	 */
	public TrdTradeParameter findByTradeVariety(String tradeVariety);
}

