package com.hongwei.futures.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.StockOptionRecordDao;
import com.hongwei.futures.model.StockOptionRecord;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author  小合
 */
@Repository
@SuppressWarnings("all")
public class StockOptionRecordDaoImpl extends BaseDaoImpl<StockOptionRecord, Long> implements StockOptionRecordDao {

	public List<StockOptionRecord> findByUserId(Long userId, int isOption) {
		String hql = "FROM StockOptionRecord AS f WHERE f.userId = :userId AND f.isOption = :isOption group by f.stockId ORDER BY f.createTime desc";
		return this.getSession().createQuery(hql)//
				.setParameter("userId", userId)//
				.setParameter("isOption", isOption)//
				.list();
	}

}

