package com.hongwei.futures.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hongwei.futures.dao.MatchXhCaiFuDao;
import com.hongwei.futures.model.MatchXhCaiFu;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author  小合
 */
@SuppressWarnings("all")
@Repository
public class MatchXhCaiFuDaoImpl extends BaseDaoImpl<MatchXhCaiFu, Long> implements MatchXhCaiFuDao {

	public MatchXhCaiFu findByPhone(String phone) {
		String hql = "FROM MatchXhCaiFu WHERE phone = :phone";
		List<MatchXhCaiFu> list = this.getSession().createQuery(hql).setParameter("phone", phone).list();
		if(null != list && list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
	}

	public List<MatchXhCaiFu> findMax(int currPage, int pageSize) {
		String hql = "FROM MatchXhCaiFu ORDER BY profit desc";
		return this.getSession().createQuery(hql)//
				.setFirstResult(currPage)//
				.setMaxResults(pageSize)//
				.list();
	}

	/**
	 * 根据手机号和收益率查询自己的名次
	 * @param profit		收益率
	 * @return
	 */
	public int findByPhoneAndProfit(int profit) {
		String hql = "FROM MatchXhCaiFu WHERE profit >  ?";
		List list = this.getSession().createQuery(hql)//
		.setParameter(0, profit)//
		.list();
		return  (null == list || list.size() <= 0) ? 0 : list.size(); 
	}

	/**
	 * 通过推荐人数来查询自己的排名
	 * @param recommendNum	推荐人数
	 * @return
	 */
	public int findByRecommendNum(int recommendNum) {
		String hql = "FROM MatchXhCaiFu WHERE  recommendNum>  ?";
		List list = this.getSession().createQuery(hql)//
		.setParameter(0, recommendNum)//
		.list();
		return  (null == list || list.size() <= 0) ? 0 : list.size();
	}

	/**
	 * 查询最前面的幸运奖
	 * @param currPage		当前页
	 * @param pageSize		每页大小 	
	 * @return
	 */
	public List<MatchXhCaiFu> findLucky(int currPage, int pageSize) {
		String hql = "FROM MatchXhCaiFu WHERE isLucky = 1";
		return this.getSession().createQuery(hql)//
				.setFirstResult(currPage)//
				.setMaxResults(pageSize)//
				.list();
	}

	/**
	 * 查询最前面的推荐人奖
	 * @param currPage		当前页
	 * @param pageSize		每页大小 	
	 * @return
	 */
	public List<MatchXhCaiFu> findRecommend(int currPage, int pageSize) {
		String hql = "FROM MatchXhCaiFu ORDER BY recommendNum desc";
		return this.getSession().createQuery(hql)//
				.setFirstResult(currPage)//
				.setMaxResults(pageSize)//
				.list();
	}

}

