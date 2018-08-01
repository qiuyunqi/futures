package com.hongwei.futures.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongwei.futures.dao.FuTransactionDao;
import com.hongwei.futures.dao.FuUserDao;
import com.hongwei.futures.model.FuTransaction;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuTransactionService;
import com.hongwei.futures.util.DateUtil;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class FuTransactionServiceImpl implements FuTransactionService {
	protected final Log log = LogFactory.getLog(FuTransactionServiceImpl.class);

	@Autowired
	private FuTransactionDao fuTransactionDao;
	@Autowired
	private FuUserDao fuUserDao;

	// ====================== 基本 C R U D 方法 ===========================
	public FuTransaction get(Long id) {
		return fuTransactionDao.get(id);
	}

	public void save(FuTransaction entity) {
		fuTransactionDao.save(entity);
	}
	
	public void saveTransc(FuTransaction transac, FuUser fuUser) {
		fuTransactionDao.save(transac);
		fuUserDao.save(fuUser);
	}

	public void delete(Long id) {
		fuTransactionDao.delete(id);
	}

	@Override
	public List<FuTransaction> findBaseTransactions() {
		return fuTransactionDao.findAll();
	}

	// 根据用户id查询当前用户的身份
	public FuTransaction findByUserId(Long userId) {
		List<FuTransaction> transactionList = fuTransactionDao.findByUserId(userId);
		if (null != transactionList && transactionList.size() > 1) {
			log.error(DateUtil.getLongStrFromDate(new Date()) + "--->" + userId + "是错误的数据");
			return null;
		} else if (null == transactionList || transactionList.size() == 0) {
			return null;
		} else {
			return transactionList.get(0);
		}
	}

	public Integer countTransaction(Map<String, Object> map) {
		return fuTransactionDao.countTransaction(map);
	}

	public List<FuTransaction> findTransactionByMap(int i, int pageSize, Map<String, Object> map) {
		return fuTransactionDao.findTransactionByMap(i, pageSize, map);
	}

	@Override
	public List<FuTransaction> findAllTrans() {
		return fuTransactionDao.findAll();
	}

}
