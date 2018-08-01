package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.FuStockMoneyDetailTemp;
import com.hongwei.futures.model.FuStockUserAccount;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 小合
 */
public interface FuStockUserAccountDao extends BaseDao<FuStockUserAccount, Long> {

	public FuStockUserAccount findByUserAndStock(Long userId, Long stockId);

	public List<FuStockUserAccount> queryAccountList(int i, int pageSize, Map<String, Object> map);

	public Integer countUserAccount(Map<String, Object> map);

}

