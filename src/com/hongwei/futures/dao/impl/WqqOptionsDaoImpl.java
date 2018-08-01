package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.WqqOptionsDao;
import com.hongwei.futures.model.WqqOptions;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author  小合
 */
@Repository
public class WqqOptionsDaoImpl extends BaseDaoImpl<WqqOptions, Long> implements WqqOptionsDao {

	@Override
	public List<WqqOptions> queryOptionsList(int i, int pageSize, Map<String, Object> map) {
		String hql=" from WqqOptions where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("userId")){
			params.add(map.get("userId"));
			hql=hql+" and fuUser.id=? ";
		}
		if(map.containsKey("phone")){
			params.add(map.get("phone"));
			hql=hql+" and fuUser.phone=? ";
		}
		if(map.containsKey("panicTime1")){
			params.add(map.get("panicTime1"));
			hql=hql+" and panicTime>=?";
		}
		if(map.containsKey("panicTime2")){
			params.add(map.get("panicTime2"));
			hql=hql+" and panicTime<=?";
		}
		if(map.containsKey("payTime1")){
			params.add(map.get("payTime1"));
			hql=hql+" and payTime>=?";
		}
		if(map.containsKey("payTime2")){
			params.add(map.get("payTime2"));
			hql=hql+" and payTime<=?";
		}
		if(map.containsKey("isPay")){
			params.add(map.get("isPay"));
			hql=hql+" and isPay=?";
		}
		if(map.containsKey("guessType")){
			params.add(map.get("guessType"));
			hql=hql+" and guessType=?";
		}
		if(map.containsKey("contentsId")){
			params.add(map.get("contentsId"));
			hql=hql+" and wqqContents.id=?";
		}
		hql=hql+" order by id desc ";
		return this.findListByHQL(i, pageSize, hql, params);
	}

	@Override
	public Integer countOptions(Map<String, Object> map) {
		String hql=" from WqqOptions where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("userId")){
			params.add(map.get("userId"));
			hql=hql+" and fuUser.id=? ";
		}
		if(map.containsKey("phone")){
			params.add(map.get("phone"));
			hql=hql+" and fuUser.phone=? ";
		}
		if(map.containsKey("panicTime1")){
			params.add(map.get("panicTime1"));
			hql=hql+" and panicTime>=?";
		}
		if(map.containsKey("panicTime2")){
			params.add(map.get("panicTime2"));
			hql=hql+" and panicTime<=?";
		}
		if(map.containsKey("payTime1")){
			params.add(map.get("payTime1"));
			hql=hql+" and payTime>=?";
		}
		if(map.containsKey("payTime2")){
			params.add(map.get("payTime2"));
			hql=hql+" and payTime<=?";
		}
		if(map.containsKey("isPay")){
			params.add(map.get("isPay"));
			hql=hql+" and isPay=?";
		}
		if(map.containsKey("guessType")){
			params.add(map.get("guessType"));
			hql=hql+" and guessType=?";
		}
		if(map.containsKey("contentsId")){
			params.add(map.get("contentsId"));
			hql=hql+" and wqqContents.id=?";
		}
		return this.countQueryResult(hql,params);
	}

	/**
	 * 根据userId 查询用户的所有的订单
	 */
	@SuppressWarnings("unchecked")
	public List<WqqOptions> getInfoByUserId(Long userId) {
		String hql = "FROM WqqOptions AS f WHERE f.fuUser.id = :userId AND f.isPay = 1  ORDER BY f.id desc";
		return this.getSession().createQuery(hql)//
				.setParameter("userId", userId)//
				.list();
	}

}

