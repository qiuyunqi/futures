package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.FuIpBlacklistDao;
import com.hongwei.futures.model.FuIpBlacklist;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author  小合
 */
@Repository
@SuppressWarnings("all")
public class FuIpBlacklistDaoImpl extends BaseDaoImpl<FuIpBlacklist, Long> implements FuIpBlacklistDao {
	public FuIpBlacklist findBlackByRegIp(String registerIp){
		String hql="from FuIpBlacklist where ip=?";
		return this.findUniqueByHQL(hql, registerIp);
	}
	
	public Integer countIpBlack(Map<String, Object> map){
		List<Object> params=new ArrayList<Object>();
		String hql="from FuIpBlacklist where 1=1";
		if(map.containsKey("ip")){
			params.add(map.get("ip"));
			hql=hql+" and ip=? ";
		}
		if(map.containsKey("isBlack")){
			params.add(map.get("isBlack"));
			hql=hql+" and isBlack=? ";
		}
		return this.countQueryResult(hql, params);
	}
	
	public List<FuIpBlacklist> findIpBlackList(int i, int pageSize, Map<String, Object> map){
		List<Object> params=new ArrayList<Object>();
		String hql="from FuIpBlacklist where 1=1";
		if(map.containsKey("ip")){
			params.add(map.get("ip"));
			hql=hql+" and ip=? ";
		}
		if(map.containsKey("isBlack")){
			params.add(map.get("isBlack"));
			hql=hql+" and isBlack=? ";
		}
		hql=hql+" order by id desc ";
		return this.findListByHQL(i, pageSize, hql, params);
	}

	// 根据手机号码或者ip查询 fuIpBlackList对象
	public List<FuIpBlacklist> getBlackListByPhoneOrIp(String phone, String ip) {
		String hql = "FROM FuIpBlacklist AS f WHERE f.ip = :phone OR f.ip = :ip AND f.isBlack=1";
		return this.getSession().createQuery(hql)//
				.setParameter("phone", phone)//
				.setParameter("ip", ip)//
				.list();
	}
}

