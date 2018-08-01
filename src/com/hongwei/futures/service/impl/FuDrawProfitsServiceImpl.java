package com.hongwei.futures.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.FuDrawProfitsDao;
import com.hongwei.futures.dao.FuUserDao;
import com.hongwei.futures.model.FuDrawProfits;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuDrawProfitsService;
import com.hongwei.futures.util.MoneyDetailUtil;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class FuDrawProfitsServiceImpl implements FuDrawProfitsService {
	
	@Autowired
	private FuDrawProfitsDao fuDrawProfitsDao;
	@Autowired
	private FuUserDao fuUserDao;
	@Autowired
	private MoneyDetailUtil moneyDetailUtil;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public FuDrawProfits get(Long id) {
		return fuDrawProfitsDao.get(id);
	}
	
	@Override
	public void save(FuDrawProfits entity) {
		fuDrawProfitsDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		fuDrawProfitsDao.delete(id);
	}

	@Override
	public Integer countDrawProfits(Map<String, Object> map) {
		return fuDrawProfitsDao.countDrawProfits(map);
	}

	@Override
	public List<FuDrawProfits> findDrawList(int i, int pageSize,
			Map<String, Object> map) {
		return fuDrawProfitsDao.findDrawList(i,pageSize,map);
	}

	@Override
	public void saveConfirmProfits(FuDrawProfits profits) {
		// 余额添加
		FuUser user=profits.getFuUser();
		user.setAccountBalance(user.getAccountBalance().add(profits.getMoney()));
		fuUserDao.save(user);
		
		// 提取利润明细
		moneyDetailUtil.saveNewFuMoneyDetail(profits.getFuUser(), profits.getFuProgram(), null, null, 29,profits.getMoney(), profits.getFuUser().getAccountBalance(), true);
	}

	@Override
	public List<FuDrawProfits> findDrawProfitsByProgramId(Long id) {
		return fuDrawProfitsDao.findDrawProfitsByProgramId(id);
	}

	@Override
	public int findProfitsByTime(Long programId, Date time1,
			Date time2) {
		return fuDrawProfitsDao.findProfitsByTime(programId,time1,time2);
	}

	
	
}

