package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.AdminLoginLogDao;
import com.hongwei.futures.model.AdminLoginLog;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author  小合
 */
@Repository
public class AdminLoginLogDaoImpl extends BaseDaoImpl<AdminLoginLog, Long> implements AdminLoginLogDao {

	@Override
	public List<AdminLoginLog> queryAdminLoginLog(int i, int pageSize, Map<String, Object> map) {
		String hql=" from AdminLoginLog where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("adminId")){
			params.add(map.get("adminId"));
			hql=hql+" and fuAdmin.id=? ";
		}
		if(map.containsKey("time1")){
			params.add(map.get("time1"));
			hql=hql+" and logTime>=?";
		}
		if(map.containsKey("time2")){
			params.add(map.get("time2"));
			hql=hql+" and logTime<=?";
		}
		if(map.containsKey("logType")){
			params.add(map.get("logType"));
			hql=hql+" and logType=? ";
		}
		hql=hql+" order by logTime desc ";
		return this.findListByHQL(i, pageSize, hql, params);
	}

	@Override
	public Integer countAdminLoginLog(Map<String, Object> map) {
		String hql=" from AdminLoginLog where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("adminId")){
			params.add(map.get("adminId"));
			hql=hql+" and fuAdmin.id=? ";
		}
		if(map.containsKey("time1")){
			params.add(map.get("time1"));
			hql=hql+" and logTime>=?";
		}
		if(map.containsKey("time2")){
			params.add(map.get("time2"));
			hql=hql+" and logTime<=?";
		}
		if(map.containsKey("logType")){
			params.add(map.get("logType"));
			hql=hql+" and logType=? ";
		}
		return this.countQueryResult(hql,params);
	}

}

