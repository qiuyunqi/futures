package com.hongwei.futures.service;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.TrdTradeNum;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface TrdTradeNumService {
	
	//====================== 基本 C R U D 方法  ===========================
	public TrdTradeNum get(Long id) ;
	
	public void save(TrdTradeNum entity) ;
	
	public void delete(Long id) ;
//	根据当前时间 当前用户 当前产品的编号 查询当前计数器
	public TrdTradeNum findNumByDateAndInstrumentId(long userId, String instrument_id, String currentDate);
	
}

