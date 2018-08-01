package com.hongwei.futures.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.FuDrawMoneyDao;
import com.hongwei.futures.dao.FuUserDao;
import com.hongwei.futures.model.FuDrawMoney;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuDrawMoneyService;
import com.hongwei.futures.util.MoneyDetailUtil;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class FuDrawMoneyServiceImpl implements FuDrawMoneyService {
	
	@Autowired
	private FuDrawMoneyDao fuDrawMoneyDao;
	@Autowired
	private FuUserDao fuUserDao;
	@Autowired
	private MoneyDetailUtil MoneyDetailUtil;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public FuDrawMoney get(Long id) {
		return fuDrawMoneyDao.get(id);
	}
	
	@Override
	public void save(FuDrawMoney entity) {
		fuDrawMoneyDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		fuDrawMoneyDao.delete(id);
	}

	@Override
	public List<FuDrawMoney> findList(int i, int pageSize,
			Map<String, Object> map) {
		return fuDrawMoneyDao.findList(i, pageSize, map);
	}

	@Override
	public Integer getCount(Map<String, Object> map) {
		return fuDrawMoneyDao.getCount(map);
	}

	@Override
	public void saveDrawMoney(FuDrawMoney drawMoney,FuUser user,Integer dictionaryId,Boolean isIncome) {
		fuDrawMoneyDao.save(drawMoney);
		fuUserDao.save(user);
		if(dictionaryId!=null && isIncome!=null){
			MoneyDetailUtil.saveNewFuMoneyDetail(user, null, drawMoney.getFuBankCard(), null, dictionaryId, drawMoney.getDrawMoney(), user.getAccountBalance(), isIncome);
		}
	}

	@Override
	public List<FuDrawMoney> findListByUserId(Long userId) {
		return fuDrawMoneyDao.findListByUserId(userId);
	}
	
}

