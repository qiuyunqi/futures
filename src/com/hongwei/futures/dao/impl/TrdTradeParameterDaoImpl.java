package com.hongwei.futures.dao.impl;

import org.springframework.stereotype.Repository;

import com.hongwei.futures.dao.TrdTradeParameterDao;
import com.hongwei.futures.model.TrdTradeParameter;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class TrdTradeParameterDaoImpl extends BaseDaoImpl<TrdTradeParameter, Long> implements TrdTradeParameterDao {

	@Override
	public TrdTradeParameter findByTradeVariety(String tradeVariety) {
		String hql = " from TrdTradeParameter where tradeVariety = ? ";
		return this.findUniqueByHQL(hql, tradeVariety);
	}


}

