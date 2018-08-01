package com.hongwei.futures.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.dao.FuStockAccountDao;
import com.hongwei.futures.model.FuStockAccount;
import com.hongwei.futures.model.FuTransaction;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.StockOptionRecord;
import com.hongwei.futures.model.StockStatusRecord;
import com.hongwei.futures.service.FuSmsLogService;
import com.hongwei.futures.service.FuStockAccountService;
import com.hongwei.futures.service.StockOptionRecordService;
import com.hongwei.futures.service.StockStatusRecordService;
import com.hongwei.futures.util.DateUtil;
import com.hongwei.futures.util.WeiXinUtil;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class FuStockAccountServiceImpl implements FuStockAccountService {

	@Autowired
	private FuStockAccountDao fuStockAccountDao;
	@Autowired
	private StockStatusRecordService stockStatusRecordService;
	@Autowired
	private StockOptionRecordService optionRecordService;
	@Autowired
	private FuSmsLogService fuSmsLogService;

	// ====================== 基本 C R U D 方法 ===========================
	public FuStockAccount get(Long id) {
		return fuStockAccountDao.get(id);
	}

	public void save(FuStockAccount entity) {
		fuStockAccountDao.save(entity);
	}

	public void delete(Long id) {
		fuStockAccountDao.delete(id);
	}

	@Override
	public List<FuStockAccount> queryAccountList(int current, int pageNum,
			Map<String, Object> map) {
		return fuStockAccountDao.queryAccountList(current, pageNum, map);
	}

	@Override
	public Integer countAccounts(Map<String, Object> map) {
		return fuStockAccountDao.countAccounts(map);
	}

	public List<FuStockAccount> findAccountByMap(int i, int pageSize,
			Map<String, Object> map) {
		return fuStockAccountDao.findAccountByMap(i, pageSize, map);
	}

	// 根据账号id查询账号的基本信息
	public FuStockAccount findAccountById(Long stockId) {
		return fuStockAccountDao.get(stockId);
	}

	@Override
	public FuStockAccount findStockByCapitalAccount(String capitalAccount) {
		return fuStockAccountDao.findStockByCapitalAccount(capitalAccount);
	}

	// 根据user_id 和 capitalAccount 查询这个账户
	public FuStockAccount findAccountByUserIdAndCap(Long userId,
			String capitalAccount) {
		return fuStockAccountDao.findAccountByUserIdAndCap(userId,
				capitalAccount);
	}

	// 查询状态在 '中的状态 2 3 4'的主账户
	public List<FuStockAccount> getAccountByState() {
		return fuStockAccountDao.getAccountByState();
	}

	// 查询这个用户是否有已经审核成功的 并可用的账号
	public List<FuStockAccount> findByUserIdAndIsDel(long userId, int isDel) {
		return fuStockAccountDao.findByUserIdAndIsDel(userId, isDel);
	}

	// 运行修改账号状态的任务
	public void updateTask() {
		// 查询所有主账户的状态是在中的数据 2 3 4
		List<FuStockAccount> accountList = fuStockAccountDao
				.getAccountByState();
		for (FuStockAccount fuStockAccount : accountList) {
			StockStatusRecord record = stockStatusRecordService
					.findStatusByAccountId(fuStockAccount.getId());
			if (record == null) {
				record = new StockStatusRecord();
			}
			if (fuStockAccount.getState() == 2) { // 申请开启委托中
				fuStockAccount.setState(0);
			} else if (fuStockAccount.getState() == 3) { // 申请暂停委托中
				fuStockAccount.setState(1);
			} else if (fuStockAccount.getState() == 4) { // 申请结算中
				fuStockAccount.setIsDel(1);
			}
			// 保存修改后的主账户
			fuStockAccountDao.save(fuStockAccount);
			// 根据主账户查询最新的主账户记录 并修改
			// record.setStockAccountId(fuStockAccount.getId());
			record.setFuStockAccount(fuStockAccount);
			record.setFuUser(fuStockAccount.getFuUser());
			record.setIsOperation(1);
			record.setOperationTime(new Date());
			stockStatusRecordService.save(record);
			System.out.println(new Date() + "修改的数据是: "
					+ record.getFuStockAccount().getId() + "----record_id"
					+ record.getId());
		}
	}

	//根据状态查询账号列表
	public List<FuStockAccount> findByStatus(int examineStatus, int isDel) {
		return fuStockAccountDao.findByStatus(examineStatus, isDel);
	}

	// 查询该交易团队下所有的融券
	public List<FuStockAccount> findByTransactionId(Long fuTransactionId) {
		return fuStockAccountDao.findByTransactionId(fuTransactionId);
	}

	// 保存用户的操作轨迹
	public void saveAccountAndRecord(FuStockAccount fuStockAccount, FuUser fuUser, int isOption, FuTransaction fuTransaction) {
		fuStockAccountDao.save(fuStockAccount);
		StockOptionRecord sor = new StockOptionRecord();
		sor.setCreateTime(new Date());
		sor.setUserId(fuUser.getId());
		sor.setIsOption(isOption);
		sor.setStockId(fuStockAccount.getId());
		optionRecordService.save(sor);
		// 给用户发送短信
		String reason = "";
		String contents = "";
		
		if (isOption == 1) { // 退券
			reason = fuTransaction.getName() + "申请退券";
			contents = fuTransaction.getName() + "在" + DateUtil.getLongStrFromDate(new Date()) +"申请退券"; 
		}else {
			reason = fuTransaction.getName() + "抢到券";
			contents = fuTransaction.getName() + "在" + DateUtil.getLongStrFromDate(new Date()) +"抢到券"; 
		}
		fuSmsLogService.saveSendServiceEmail(reason, contents);
		
	}

	// 微信保存账号
	public void saveFsa(FuStockAccount fsa, HttpSession httpSession, String serverId) throws Exception {
		// 上传图片到OSS
		String stockImage = WeiXinUtil.downloadPicAndUploadOSS(httpSession, serverId);
		fsa.setStockImage(stockImage);
		fuStockAccountDao.save(fsa);
	}

	// 查询全部的股票账户信息
	public List<FuStockAccount> findAll(int isDel, int examin, int start, int pageSize) {
		return fuStockAccountDao.findAll(isDel, examin, start, pageSize);
	}

	//查询所有的股票message并组成一个JSON字符串
	public String getJsonList() {
		return fuStockAccountDao.getJsonList();
	}

	// 模糊查询message
	public List<FuStockAccount> findByMsg(String query) {
		return fuStockAccountDao.findByMsg(query);
	}
}
