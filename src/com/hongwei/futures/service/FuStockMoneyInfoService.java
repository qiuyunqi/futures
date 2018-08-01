package com.hongwei.futures.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import jxl.Sheet;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuStockAccount;
import com.hongwei.futures.model.FuStockMoneyInfo;
import com.hongwei.futures.model.FuUser;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuStockMoneyInfoService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuStockMoneyInfo get(Long id) ;
	
	public void save(FuStockMoneyInfo entity) ;
	
	public void delete(Long id) ;
	
	public FuStockMoneyInfo findMoneyInfoByMap(Map<String, Object> map);
	
	public List<FuStockMoneyInfo> queryStockMoneyInfo(int current, int pageNum, Map<String, Object> map);
	
	public Integer countStockMoneyInfo(Map<String, Object> map);
	
	public FuStockMoneyInfo findStockMoneyInfoByUserId(Long userId);
	
	public void saveNoneFee();
	
	public String saveOneKeyPay(FuUser fuUser,BigDecimal money);
	
}

