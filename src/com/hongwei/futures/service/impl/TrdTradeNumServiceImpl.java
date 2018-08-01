package com.hongwei.futures.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.TrdTradeNumDao;
import com.hongwei.futures.model.TrdTradeNum;
import com.hongwei.futures.service.TrdTradeNumService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class TrdTradeNumServiceImpl implements TrdTradeNumService {
	
	@Autowired
	private TrdTradeNumDao trdTradeNumDao;
	
	//====================== 基本 C R U D 方法  ===========================
	public TrdTradeNum get(Long id) {
		return trdTradeNumDao.get(id);
	}
	
	public void save(TrdTradeNum entity) {
		trdTradeNumDao.save(entity);
	}
	
	public void delete(Long id) {
		trdTradeNumDao.delete(id);
	}

//	根据当前时间 当前用户 当前产品的编号 查询当前计数器
	public TrdTradeNum findNumByDateAndInstrumentId(long userId, String instrument_id, String currentDate) {
		return this.trdTradeNumDao.findNumByDateAndInstrumentId(userId, instrument_id, currentDate);
	}
	
}

