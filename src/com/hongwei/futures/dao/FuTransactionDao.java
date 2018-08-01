package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.model.FuTransaction;

/**
 * 
 * @description 自动生成 dao
 * 
 * @author 小合
 */
public interface FuTransactionDao extends BaseDao<FuTransaction, Long> {

	/**
	 * 根据用户id查询信息
	 * 
	 * @param userId
	 *            用户
	 * @return
	 */
	public List<FuTransaction> findByUserId(Long userId);

	public Integer countTransaction(Map<String, Object> map);

	public List<FuTransaction> findTransactionByMap(int i, int pageSize, Map<String, Object> map);

}
