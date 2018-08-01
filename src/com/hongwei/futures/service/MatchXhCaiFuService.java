package com.hongwei.futures.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.MatchXhCaiFu;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface MatchXhCaiFuService {
	
	//====================== 基本 C R U D 方法  ===========================
	public MatchXhCaiFu get(Long id) ;
	
	public void save(MatchXhCaiFu entity) ;
	
	public void delete(Long id) ;

	/**
	 * 根据手机号码查询是否已经报名了
	 * @return
	 */
	public MatchXhCaiFu findByPhone(String phone);

	/**
	 * 查询前几名
	 * @param pageSize  每页大小 	
	 * @param currPage 	当前页
	 * @return
	 */
	public List<MatchXhCaiFu> findMax(int currPage, int pageSize);

	/**
	 * 根据手机号和收益率查询自己的名次
	 * @param profit		收益率
	 * @return
	 */
	public int findByPhoneAndProfit(int profit);

	/**
	 * 按照推荐人来查询自己的排名
	 * @param recommendNum
	 * @return
	 */
	public int findByRecommendNum(int recommendNum);

	/**
	 * 查询最前面的幸运奖
	 * @param currPage		当前页
	 * @param pageSize		每页大小 	
	 * @return
	 */
	public List<MatchXhCaiFu> findLucky(int currPage, int pageSize);

	/**
	 * 查询最前面的推荐人奖
	 * @param currPage		当前页
	 * @param pageSize		每页大小 	
	 * @return
	 */
	public List<MatchXhCaiFu> findRecommend(int currPage, int pageSize);
	
}

