package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.hongwei.futures.dao.StockYjbRankDao;
import com.hongwei.futures.model.StockYjbRank;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 小合
 */
@Repository
@SuppressWarnings("all")
public class StockYjbRankDaoImpl extends BaseDaoImpl<StockYjbRank, Long> implements StockYjbRankDao {

	public List<StockYjbRank> getRank(int start, int pageSize) {
		String hql = "FROM StockYjbRank ORDER BY serialNo ASC";
		return this.getSession().createQuery(hql)//
				.setFirstResult(start)//
				.setMaxResults(pageSize)//
				.list();
	}

	// 查询所有的股票名称并组成一个JSON字符串
	public String getJsonList() {
		String hql = "from StockYjbRank";
		List<StockYjbRank> mapList = this.getSession().createQuery(hql).list();
		JSONArray jsonArray = new JSONArray();
		for (StockYjbRank sk : mapList) {
			JSONObject obj = new JSONObject();
			obj.put("id", sk.getId());
			obj.put("label", sk.getStockName() + (null == sk.getCode() ? "" : sk.getCode()) + (null == sk.getStockPy() ? "" : sk.getStockPy()));
			obj.put("code", null == sk.getCode() ? "" : sk.getCode() + (null == sk.getStockPy() ? "" : sk.getStockPy()));
			obj.put("category", sk.getStockName());
			jsonArray.add(obj);
		}
		return jsonArray.toString();
	}

	@Override
	public List<StockYjbRank> queryYqbRankList(int i, int pageSize, Map<String, Object> map) {
		String hql = " from StockYjbRank where 1=1";
		List<Object> params = new ArrayList<Object>();
		if (map.containsKey("code")) {
			params.add(map.get("code"));
			hql = hql + " and code=? ";
		}
		if (map.containsKey("stockName")) {
			params.add(map.get("stockName"));
			hql = hql + " and stockName=? ";
		}
		if (map.containsKey("transactionName")) {
			params.add(map.get("transactionName"));
			hql = hql + " and transactionName=? ";
		}
		hql = hql + " ORDER BY serialNo desc ";
		return this.findListByHQL(i, pageSize, hql, params);
	}

	@Override
	public Integer countYqbRank(Map<String, Object> map) {
		String hql = "from StockYjbRank where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (map.containsKey("code")) {
			params.add(map.get("code"));
			hql = hql + " and code=? ";
		}
		if (map.containsKey("stockName")) {
			params.add(map.get("stockName"));
			hql = hql + " and stockName=? ";
		}
		if (map.containsKey("transactionName")) {
			params.add(map.get("transactionName"));
			hql = hql + " and transactionName=? ";
		}
		return this.countQueryResult(hql, params);
	}

	public List<StockYjbRank> getSearcher(String query) {
		String hql = "FROM StockYjbRank AS s WHERE s.stockName " + "like '%" + query + "%' or s.code like '%" + query + "%'" + "or s.stockPy like '%" + query + "%'";
		return this.getSession().createQuery(hql)//
				.list();
	}

	public void saveYqbRankReset() {
		String sql = "TRUNCATE TABLE stock_yjb_rank";
		this.executeSqlUpdate(sql, null);
	}

}
