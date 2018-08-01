package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hongwei.futures.dao.StockPublishDao;
import com.hongwei.futures.model.StockPublish;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 小合
 */
@Repository
public class StockPublishDaoImpl extends BaseDaoImpl<StockPublish, Long> implements StockPublishDao {
	public Integer getCount(Map<String, Object> map) {
		String hql = "from StockPublish where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (map.containsKey("userId")) {
			params.add(map.get("userId"));
			hql = hql + " and fuUser.id=? ";
		}
		if (map.containsKey("userName")) {
			params.add(map.get("userName"));
			hql = hql + " and fuUser.userName=? ";
		}
		if (map.containsKey("titles")) {
			params.add(map.get("titles"));
			hql = hql + " and title=? ";
		}
		if (map.containsKey("description")) {
			params.add(map.get("description"));
			hql = hql + " and description=? ";
		}
		if (map.containsKey("isDel")) {
			params.add(map.get("isDel"));
			hql = hql + " and isDel=? ";
		}
		return this.countQueryResult(hql, params);
	}

	public List<StockPublish> findPublishByMap(int i, int j, Map<String, Object> map) {
		String hql = "from StockPublish where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (map.containsKey("userId")) {
			params.add(map.get("userId"));
			hql = hql + " and fuUser.id=? ";
		}
		if (map.containsKey("userName")) {
			params.add(map.get("userName"));
			hql = hql + " and fuUser.userName=? ";
		}
		if (map.containsKey("titles")) {
			params.add(map.get("titles"));
			hql = hql + " and title=? ";
		}
		if (map.containsKey("description")) {
			params.add(map.get("description"));
			hql = hql + " and description=? ";
		}
		if (map.containsKey("isDel")) {
			params.add(map.get("isDel"));
			hql = hql + " and isDel=? ";
		}
		hql = hql + " order by isDel desc,id desc";
		return this.findListByHQL(i, j, hql, params);
	}
}
