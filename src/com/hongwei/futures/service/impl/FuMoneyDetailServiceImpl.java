package com.hongwei.futures.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.dao.FuMoneyDetailDao;
import com.hongwei.futures.model.FuMoneyDetail;
import com.hongwei.futures.service.FuMoneyDetailService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
	
public class FuMoneyDetailServiceImpl implements FuMoneyDetailService {
	@Autowired
	private FuMoneyDetailDao fuMoneyDetailDao;

	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public FuMoneyDetail get(Long id) {
		return fuMoneyDetailDao.get(id);
	}
	
	@Override
	public void save(FuMoneyDetail entity) {
		fuMoneyDetailDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		fuMoneyDetailDao.delete(id);
	}

	@Override
	public List<FuMoneyDetail> findListBy(int i, int pageSize,
			Map<String, Object> map) {
		return fuMoneyDetailDao.findListBy(i, pageSize, map);
	}

	@Override
	public Integer getCount(Map<String, Object> map) {
		return fuMoneyDetailDao.getCount(map);
	}

	@Override
	public List<FuMoneyDetail> findFuMoneyDetailByParams(Map<String, Object> map) {
		return fuMoneyDetailDao.findFuMoneyDetailByParams(map);
	}

	/**
	 * 根据用户查询用户的收入详情列表
	 * @param userId
	 * @param isInCome		0: 支出   1: 收入
	 * @param curPage		当前页
	 * @param pageSize		每页大小
	 * @return
	 */
	public List<FuMoneyDetail> findInComeByUserId(long userId, int isInCome, Integer curPage, Integer pageSize) {
		return fuMoneyDetailDao.findInComeByUserId(userId, isInCome, curPage, pageSize);
	}

	//  根据条件查询累计的收益
	public BigDecimal getCountMoneyByDictionaryIdAndUserId(Long userId,
			Long dictionaryId, int isEnabled) {
		return fuMoneyDetailDao.getCountMoneyByDictionaryIdAndUserId(userId, dictionaryId, isEnabled);
	}

	// 查找某个用户的资金明细
	public List<FuMoneyDetail> findListByDetailId(Long userId, long dictionaryId, Long detailId) {
		return fuMoneyDetailDao.findListByDetailId(userId, dictionaryId, detailId);
	}

	@Override
	public List<FuMoneyDetail> findListByUserIdCount(Long userId, Integer i) {
		return fuMoneyDetailDao.findListByUserIdCount(userId, i);
	}

	@Override
	public List<FuMoneyDetail> findListByUserId(Long userId) {
		return fuMoneyDetailDao.findListByUserId(userId);
	}
	

}

