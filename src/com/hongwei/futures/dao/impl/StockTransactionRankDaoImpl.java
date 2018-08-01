package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hongwei.futures.dao.StockTransactionRankDao;
import com.hongwei.futures.model.StockTransactionRank;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 小合
 */
@Repository
@SuppressWarnings("all")
public class StockTransactionRankDaoImpl extends BaseDaoImpl<StockTransactionRank, Long> implements StockTransactionRankDao {

	// 查询前20条数据 按照月收益排序
	public List<StockTransactionRank> getRank(int start, int pageSize) {
		String hql = "FROM StockTransactionRank ORDER BY serialNo ASC";
		return this.getSession().createQuery(hql)//
				.setFirstResult(start)//
				.setMaxResults(pageSize)//
				.list();
	}

	@Override
	public List<StockTransactionRank> queryTransRankList(int i, int pageSize, Map<String, Object> map) {
		String hql = " from StockTransactionRank where 1=1";
		List<Object> params = new ArrayList<Object>();
		if (map.containsKey("rating")) {
			params.add(map.get("rating"));
			hql = hql + " and fuTransaction.rating=? ";
		}
		if (map.containsKey("transactionName")) {
			params.add(map.get("transactionName"));
			hql = hql + " and transactionName=? ";
		}
		hql = hql + " ORDER BY serialNo desc ";
		return this.findListByHQL(i, pageSize, hql, params);
	}

	@Override
	public Integer countTransRank(Map<String, Object> map) {
		String hql = "from StockTransactionRank where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (map.containsKey("rating")) {
			params.add(map.get("rating"));
			hql = hql + " and fuTransaction.rating=? ";
		}
		if (map.containsKey("transactionName")) {
			params.add(map.get("transactionName"));
			hql = hql + " and transactionName=? ";
		}
		return this.countQueryResult(hql, params);
	}

}
