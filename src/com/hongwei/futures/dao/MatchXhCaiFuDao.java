package com.hongwei.futures.dao;

import java.util.List;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.MatchXhCaiFu;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 小合
 */
public interface MatchXhCaiFuDao extends BaseDao<MatchXhCaiFu, Long> {

	/**
	 * 通过手机查询是否报名
	 * @param phone		手机号码
	 * @return
	 */
	public MatchXhCaiFu findByPhone(String phone);

	/**
	 * 查询第一名
	 * @param pageSize    每页大小
	 * @param currPage     当前页
	 * @return
	 */
	public List<MatchXhCaiFu> findMax(int currPage, int pageSize);

	/**
	 * 根据手机号和收益率查询自己的名次
	 * @param profit			手机收益率
	 * @return
	 */
	public int findByPhoneAndProfit(int profit);

	/**
	 * 通过推荐人数来查询自己的排名
	 * @param recommendNum	推荐人数
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

