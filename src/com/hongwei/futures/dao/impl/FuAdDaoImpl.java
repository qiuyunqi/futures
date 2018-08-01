package com.hongwei.futures.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.FuAdDao;
import com.hongwei.futures.model.FuAd;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author  小合
 */
@SuppressWarnings("all")
@Repository
public class FuAdDaoImpl extends BaseDaoImpl<FuAd, Long> implements FuAdDao {

	public List<FuAd> getList(int position, int isDelete) {
		String hql = "FROM FuAd as f WHERE f.isDelete = :isDelete AND f.position = :position ORDER BY f.orderNum desc";
		return this.getSession().createQuery(hql)//
				.setParameter("isDelete", isDelete)//
				.setParameter("position", position)//
				.list();
	}

	// 查询全部的广告位
	public List<FuAd> findAll(int currentPage, int pageSize, HashMap<String, Object> map) {
		String hql = "FROM FuAd AS fa WHERE 1=1 ";
		if(null != map.get("isDel")) {
			hql += " AND isDelete = " + map.get("isDel");
		}
		if(null != map.get("position")) {
			hql += " AND position = " + map.get("position");
		}
		hql += " order by fa.orderNum desc";
		return this.getSession().createQuery(hql)//
				.setFirstResult(currentPage)//
				.setMaxResults(pageSize)//
				.list();
	}

	// 查询最大的顺序的那个条数据
	public FuAd getMaxOrderAd() {
		String hql = "FROM FuAd ORDER BY orderNum desc";
		List list = this.getSession().createQuery(hql).list();
		return (FuAd) (null != list && list.size() > 0 ? list.get(0) : null);
	}

}

