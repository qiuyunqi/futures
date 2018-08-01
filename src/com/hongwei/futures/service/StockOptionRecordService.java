package com.hongwei.futures.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.StockOptionRecord;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface StockOptionRecordService {
	
	//====================== 基本 C R U D 方法  ===========================
	public StockOptionRecord get(Long id) ;
	
	public void save(StockOptionRecord entity) ;
	
	public void delete(Long id) ;

	/**
	 * 根据用户id和接券退券状态查询数据对象
	 * @param id
	 * @param isOption
	 * @return
	 */
	public List<StockOptionRecord> findByUserId(Long userId, int isOption);
	
}

