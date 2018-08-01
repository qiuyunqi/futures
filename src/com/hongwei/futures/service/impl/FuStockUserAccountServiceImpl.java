package com.hongwei.futures.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongwei.futures.dao.FuStockUserAccountDao;
import com.hongwei.futures.model.FuStockUserAccount;
import com.hongwei.futures.service.FuStockUserAccountService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class FuStockUserAccountServiceImpl implements FuStockUserAccountService {
	
	@Autowired
	private FuStockUserAccountDao fuStockUserAccountDao;
	
	//====================== 基本 C R U D 方法  ===========================
	public FuStockUserAccount get(Long id) {
		return fuStockUserAccountDao.get(id);
	}
	
	public void save(FuStockUserAccount entity) {
		fuStockUserAccountDao.save(entity);
	}
	
	public void delete(Long id) {
		fuStockUserAccountDao.delete(id);
	}

	@Override
	public FuStockUserAccount findByUserAndStock(Long userId, Long stockId) {
		return fuStockUserAccountDao.findByUserAndStock(userId, stockId);
	}

	@Override
	public List<FuStockUserAccount> queryAccountList(int i, int pageSize, Map<String, Object> map) {
		return fuStockUserAccountDao.queryAccountList(i, pageSize, map);
	}

	@Override
	public Integer countUserAccount(Map<String, Object> map) {
		return fuStockUserAccountDao.countUserAccount(map);
	}
	
}

