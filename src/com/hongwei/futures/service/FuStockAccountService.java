package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.FuStockAccount;
import com.hongwei.futures.model.FuTransaction;
import com.hongwei.futures.model.FuUser;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuStockAccountService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuStockAccount get(Long id) ;
	
	public void save(FuStockAccount entity) ;
	
	public void delete(Long id) ;

	public List<FuStockAccount> findAccountByMap(int i, int pageSize,Map<String, Object> map);
	
	/**
	 * 根据id 查询账号信息
	 * @param stockId
	 * @return
	 */
	public FuStockAccount findAccountById(Long stockId);
	
	public List<FuStockAccount> queryAccountList(int current, int pageNum, Map<String, Object> map);
	
	public Integer countAccounts(Map<String, Object> map);
	
	public FuStockAccount findStockByCapitalAccount(String capitalAccount);

//	根据user_id 和 capitalAccount 查询这个账户
	public FuStockAccount findAccountByUserIdAndCap(Long userId, String capitalAccount);

	//  查询状态在 '中的状态 2 3 4'的主账户
	public List<FuStockAccount> getAccountByState();
	
	/**
	 * 查询这个用户是否有已经审核成功的 并可用的账号
	 * @param userId		用户id
	// * @param examineStatus	账号的审核状态 0:审核中  1:审核成功 2:审核失败 
	 * @param isDel			是否删除这个资金账号 0: 不删除  1:删除
	 * @return
	 */
	public List<FuStockAccount> findByUserIdAndIsDel(long userId,int isDel);

	/**
	 * 晚上跑任务
	 * @return
	 */
	public void updateTask();

	/**
	 * 根据状态查询账号列表
	 * @param examineStatus		账号的状态
	 * @param isDel		是否删除
	 * @return
	 */
	public List<FuStockAccount> findByStatus(int examineStatus, int isDel);

	/**
	 * 查询该交易团队下所有的融券
	 * @param fuTransactionId		交易团队的id
	 * @return
	 */
	public List<FuStockAccount> findByTransactionId(Long fuTransactionId);

	/**
	 * 保存用户的操作轨迹
	 * @param fuStockAccount		股票账户
	 * @param userId				用户id
	 * @param isOption				操作  0: 接券  1:退券
	 */
	public void saveAccountAndRecord(FuStockAccount fuStockAccount, FuUser fuUser, int isOption, FuTransaction fuTransaction );

	/**
	 * 微信   保存账户
	 * @param fsa
	 * @param httpSession
	 * @param serverId
	 */
	public void saveFsa(FuStockAccount fsa, HttpSession httpSession, String serverId) throws Exception;

	/**
	 * 查询全部的股票账户信息
	 * @param isDel		是否删除 0: 没有删除 1:删除  
	 * @param examin		是否审核 0: 审核中 1:审核成功 2:审核失败 3: 接单中 4: 接单成功
	 * @param start
	 * @param pageSize
	 * @return
	 */
	public List<FuStockAccount> findAll(int isDel, int examin, int start, int pageSize);

	/**
	 * 查询所有的股票message并组成一个JSON字符串
	 * @return
	 */
	public String getJsonList();

	/**
	 * 模糊查询message
	 * @param query  条件
	 * @return
	 */
	public List<FuStockAccount> findByMsg(String query);
}

