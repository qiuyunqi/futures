package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.FuTransaction;
import com.hongwei.futures.model.FuUser;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public interface FuTransactionService {

	// ====================== 基本 C R U D 方法 ===========================
	public FuTransaction get(Long id);

	public void save(FuTransaction entity);

	public void delete(Long id);

	public List<FuTransaction> findBaseTransactions();

	/**
	 * 根据用户id查询当前用户的身份
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 */
	public FuTransaction findByUserId(Long userId);

	public Integer countTransaction(Map<String, Object> map);

	public List<FuTransaction> findTransactionByMap(int i, int pageSize, Map<String, Object> map);

	public List<FuTransaction> findAllTrans();
	
	public void saveTransc(FuTransaction transac, FuUser fuUser);
}
