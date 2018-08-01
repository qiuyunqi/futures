package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.TrdKLinesDao;
import com.hongwei.futures.model.TrdKLines;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class TrdKLinesDaoImpl extends BaseDaoImpl<TrdKLines, Long> implements TrdKLinesDao {

	@Override
	public List<TrdKLines> queryKlinesList(Map<String, Object> map) {
		String hql=" from TrdKLines where 1=1";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("instrumentId")){
			params.add(map.get("instrumentId"));
			hql=hql+" and instrumentId=? ";
		}
		if(map.containsKey("tradingDay")){
			params.add(map.get("tradingDay"));
			hql=hql+" and tradingDay=? ";
		}
		if(map.containsKey("fromDate")){
			params.add(map.get("fromDate"));
			hql=hql+" and tradingDay>=? ";
		}
		if(map.containsKey("toDate")){
			params.add(map.get("toDate"));
			hql=hql+" and tradingDay<? ";
		}
		return this.findAllByHQL(hql, params);
	}
	
	@Override
	public List<TrdKLines> queryKlinesList(int current, int pageNum, Map<String, Object> map) {
		String hql=" from TrdKLines where 1=1";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("tradingDay")){
			params.add(map.get("tradingDay"));
			hql=hql+" and tradingDay=? ";
		}
		if(map.containsKey("instrumentId")){
			params.add(map.get("instrumentId"));
			hql=hql+" and instrumentId=? ";
		}
		if(map.containsKey("orderby")){
			hql=hql+" " + map.get("orderby");
		}
		return this.findListByHQL(current, pageNum, hql, params);
	}

	@Override
	public Integer countKlines(Map<String, Object> map) {
		String hql = "from TrdKLines where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("tradingDay")){
			params.add(map.get("tradingDay"));
			hql=hql+" and tradingDay=? ";
		}
		if(map.containsKey("instrumentId")){
			params.add(map.get("instrumentId"));
			hql=hql+" and instrumentId=? ";
		}	
		return this.countQueryResult(hql,params);
	}

	@Override
	public TrdKLines findKlineByInstrumentAndDate(String instrumentId, String tradingDay) {
		String hql = " from TrdKLines where instrumentId = ? and tradingDay = ?";
		return this.findUniqueByHQL(hql, instrumentId, tradingDay);
	}

}

