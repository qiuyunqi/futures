package com.hongwei.futures.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.dao.MatchXhCaiFuDao;
import com.hongwei.futures.model.MatchXhCaiFu;
import com.hongwei.futures.service.MatchXhCaiFuService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class MatchXhCaiFuServiceImpl implements MatchXhCaiFuService {
	
	@Autowired
	private MatchXhCaiFuDao matchXhCaiFuDao;
	
	//====================== 基本 C R U D 方法  ===========================
	public MatchXhCaiFu get(Long id) {
		return matchXhCaiFuDao.get(id);
	}
	
	public void save(MatchXhCaiFu entity) {
		matchXhCaiFuDao.save(entity);
	}
	
	public void delete(Long id) {
		matchXhCaiFuDao.delete(id);
	}

	/**
	 * 通过手机查询是否报名
	 */
	public MatchXhCaiFu findByPhone(String phone) {
		return matchXhCaiFuDao.findByPhone(phone);
	}

	/**
	 * 查询第一名
	 */
	public List<MatchXhCaiFu> findMax(int currPage, int pageSize) {
		return matchXhCaiFuDao.findMax(currPage, pageSize);
	}

	/**
	 * 根据手机号和收益率查询自己的名次
	 * @param profit		收益率
	 * @return
	 */
	public int findByPhoneAndProfit(int profit) {
		return matchXhCaiFuDao.findByPhoneAndProfit(profit);
	}

	/**
	 * 通过推荐人数来查询自己的排名
	 */
	public int findByRecommendNum(int recommendNum) {
		return matchXhCaiFuDao.findByRecommendNum(recommendNum);
	}

	/**
	 * 查询最前面的幸运奖
	 * @param currPage		当前页
	 * @param pageSize		每页大小 	
	 * @return
	 */
	public List<MatchXhCaiFu> findLucky(int currPage, int pageSize) {
		return matchXhCaiFuDao.findLucky(currPage, pageSize);
	}

	/**
	 * 查询最前面的推荐人奖
	 * @param currPage		当前页
	 * @param pageSize		每页大小 	
	 * @return
	 */
	public List<MatchXhCaiFu> findRecommend(int currPage, int pageSize) {
		return matchXhCaiFuDao.findRecommend(currPage, pageSize);
	}
	
}

