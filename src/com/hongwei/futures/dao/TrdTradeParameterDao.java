package com.hongwei.futures.dao;

import com.hongwei.futures.model.TrdTradeParameter;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface TrdTradeParameterDao extends BaseDao<TrdTradeParameter, Long> {

	public TrdTradeParameter findByTradeVariety(String tradeVariety);

}

