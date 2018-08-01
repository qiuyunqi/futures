package com.hongwei.futures.dao;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.TrdTradeNum;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 小合
 */
public interface TrdTradeNumDao extends BaseDao<TrdTradeNum, Long> {

//	根据当前时间 当前用户 当前产品的编号 查询当前计数器
	public TrdTradeNum findNumByDateAndInstrumentId(long userId, String instrument_id, String currentDate);

}

