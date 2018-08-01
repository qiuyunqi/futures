package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.FuStockMoneyDetailTemp;
import com.hongwei.futures.model.FuStockUserAccount;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuStockUserAccountService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuStockUserAccount get(Long id) ;
	
	public void save(FuStockUserAccount entity) ;
	
	public void delete(Long id) ;
	
	public FuStockUserAccount findByUserAndStock(Long userId, Long stockId);

	public List<FuStockUserAccount> queryAccountList(int i, int pageSize, Map<String, Object> map);

	public Integer countUserAccount(Map<String, Object> map);
	
}

