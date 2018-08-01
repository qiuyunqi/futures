package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.FuStockMoneyInfoDao;
import com.hongwei.futures.model.FuStockMoneyInfo;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class FuStockMoneyInfoDaoImpl extends BaseDaoImpl<FuStockMoneyInfo, Long> implements FuStockMoneyInfoDao {
	public FuStockMoneyInfo findMoneyInfoByMap(Map<String, Object> map){
		List<Object> params =new ArrayList<Object>();
		String hql = "from FuStockMoneyInfo where 1=1 ";
		if(map.containsKey("userId")){
			params.add(map.get("userId"));
			hql=hql+" and fuUser.id=? ";
		}
		hql=hql+" order by id desc ";
		if(this.findAllByHQL(hql, params).size()>0){
			return this.findAllByHQL(hql, params).get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<FuStockMoneyInfo> queryStockMoneyInfo(int current, int pageNum, Map<String, Object> map) {
		String hql=" from FuStockMoneyInfo where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("userId")){
			params.add(map.get("userId"));
			hql=hql+" and fuUser.id=? ";
		}
		if(map.containsKey("phone")){
			params.add(map.get("phone"));
			hql=hql+" and fuUser.phone=? ";
		}
		if(map.containsKey("time1")){
			params.add(map.get("time1"));
			hql=hql+" and createtime>=?";
		}
		if(map.containsKey("time2")){
			params.add(map.get("time2"));
			hql=hql+" and createtime<=?";
		}
		hql=hql+" order by updatetime desc,id desc ";
		return this.findListByHQL(current, pageNum, hql, params);
	}

	@Override
	public Integer countStockMoneyInfo(Map<String, Object> map) {
		String hql = "from FuStockMoneyInfo where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("userId")){
			params.add(map.get("userId"));
			hql=hql+" and fuUser.id=? ";
		}
		return this.countQueryResult(hql,params);
	}

	@Override
	public FuStockMoneyInfo findStockMoneyInfoByUserId(Long userId) {
		String hql=" from FuStockMoneyInfo where fuUser.id=? ";
		return this.findUniqueByHQL(hql, userId);
	}
}

