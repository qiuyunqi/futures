package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hongwei.futures.dao.FuTransactionDao;
import com.hongwei.futures.model.FuTransaction;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 小合
 */
@Repository
@SuppressWarnings("unchecked")
public class FuTransactionDaoImpl extends BaseDaoImpl<FuTransaction, Long> implements FuTransactionDao {

	// 根据用户id查询当前用户的身份
	public List<FuTransaction> findByUserId(Long userId) {
		String hql = "FROM FuTransaction AS f WHERE f.fuUser.id = :userId and f.isVerification = 1 and f.isDel = 1";
		return this.getSession().createQuery(hql)//
				.setParameter("userId", userId)//
				.list();
	}

	public Integer countTransaction(Map<String, Object> map) {
		String hql = "from FuTransaction where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (map.containsKey("userName")) {
			params.add(map.get("userName"));
			hql = hql + " and fuUser.userName=? ";
		}
		if (map.containsKey("name")) {
			params.add(map.get("name"));
			hql = hql + " and name=? ";
		}
		if (map.containsKey("isVerification")) {
			params.add(map.get("isVerification"));
			hql = hql + " and isVerification = ? ";
		}
		if (map.containsKey("rating")) {
			params.add(map.get("rating"));
			hql = hql + " and rating = ? ";
		}
		return this.countQueryResult(hql, params);
	}

	public List<FuTransaction> findTransactionByMap(int i, int pageSize, Map<String, Object> map) {
		String hql = "from FuTransaction where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (map.containsKey("userId")) {
			params.add(map.get("userId"));
			hql = hql + " and fuUser.id=? ";
		}
		if (map.containsKey("userName")) {
			params.add(map.get("userName"));
			hql = hql + " and fuUser.userName=? ";
		}
		if (map.containsKey("name")) {
			params.add(map.get("name"));
			hql = hql + " and name=? ";
		}
		if (map.containsKey("isVerification")) {
			params.add(map.get("isVerification"));
			hql = hql + " and isVerification = ? ";
		}
		if (map.containsKey("rating")) {
			params.add(map.get("rating"));
			hql = hql + " and rating = ? ";
		}
		hql = hql + " order by id desc ";
		return this.findListByHQL(i, pageSize, hql, params);
	}

}
