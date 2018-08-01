package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.StockShareDao;
import com.hongwei.futures.model.FuStockAccount;
import com.hongwei.futures.model.StockShare;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author  小合
 */
@Repository
public class StockShareDaoImpl extends BaseDaoImpl<StockShare, Long> implements StockShareDao {

	@Override
	public List<StockShare> findStockShareByAccount(Long stockId) {
		String hql = " from StockShare where fuStockAccount.id = ? ";
		return this.findAllByHQL(hql, stockId);
	}

	@Override
	public List<StockShare> queryStockShareList(int i, int pageSize, Map<String, Object> map) {
		String hql=" from StockShare where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("shareName")){
			params.add(map.get("shareName"));
			hql=hql+" and name=? ";
		}
		if(map.containsKey("shareCode")){
			params.add(map.get("shareCode"));
			hql=hql+" and code=? ";
		}
		if(map.containsKey("capitalAccount")){
			params.add(map.get("capitalAccount"));
			hql=hql+" and fuStockAccount.capitalAccount=? ";
		}
		return this.findListByHQL(i, pageSize, hql, params);
	}

	@Override
	public Integer countStockShare(Map<String, Object> map) {
		String hql=" from StockShare where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("shareName")){
			params.add(map.get("shareName"));
			hql=hql+" and name=? ";
		}
		if(map.containsKey("shareCode")){
			params.add(map.get("shareCode"));
			hql=hql+" and code=? ";
		}
		if(map.containsKey("capitalAccount")){
			params.add(map.get("capitalAccount"));
			hql=hql+" and fuStockAccount.capitalAccount=? ";
		}
		return this.countQueryResult(hql,params);
	}

	// 通过模糊查询 code 或者 name 字段 得到FuStockAccount集合
	@SuppressWarnings("unchecked")
	public List<FuStockAccount> findByCodeOrName(String query) {
		String hql = "FROM StockShare AS f WHERE f.name like '%" + query + "%' OR f.code like '%"+ query +"%' AND f.fuStockAccount.isDel = 0 GROUP BY f.fuStockAccount";
		List<StockShare> list = this.getSession().createQuery(hql).list();
		if (null != list && list.size() > 
		0) {
			List<FuStockAccount> accList = new ArrayList<FuStockAccount>();
			for (StockShare stockShare : list) {
				accList.add(stockShare.getFuStockAccount());
			}
			return accList;
		}else {
			return null;
		}
	}

}

