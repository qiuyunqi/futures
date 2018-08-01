package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hongwei.futures.dao.FuAddMarginDao;
import com.hongwei.futures.model.FuAddMargin;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class FuAddMarginDaoImpl extends BaseDaoImpl<FuAddMargin, Long> implements FuAddMarginDao {

	@Override
	public Integer countSafeMoney(Map<String, Object> map) {
		List<Object> params = new ArrayList<Object>(); 
		String hql=" from FuAddMargin where 1=1 ";
		if(map.containsKey("programId")){
			params.add(map.get("programId"));
			hql=hql+" and fuProgram.id=? ";
		}
		if(map.containsKey("detailId")){
			params.add(map.get("detailId"));
			hql=hql+" and id=? ";
		}
		if(map.containsKey("accountName")){
			params.add(map.get("accountName"));
			hql=hql+" and fuUser.accountName=? ";
		}
		if(map.containsKey("userName")){
			params.add(map.get("userName"));
			hql=hql+" and fuUser.userName=? ";
		}
		if(map.containsKey("money1")){
			params.add(map.get("money1"));
			hql=hql+" and money>=? ";
		}
		if(map.containsKey("money2")){
			params.add(map.get("money2"));
			hql=hql+" and money<=? ";
		}
		if(map.containsKey("time1")){
			params.add(map.get("time1"));
			hql=hql+" and time>=? ";
		}
		if(map.containsKey(map.get("time2"))){
			params.add(map.get("time2"));
			hql=hql+" and time<=? ";
		}
		if(map.containsKey("state")){
			params.add(map.get("state"));
			hql=hql+" and state=? ";
		}
		return this.countQueryResult(hql, params);
	}

	@Override
	public List<FuAddMargin> findSafeMoneyList(int i, int pageSize,
			Map<String, Object> map) {
		List<Object> params = new ArrayList<Object>();
		String hql=" from FuAddMargin where 1=1 ";
		if(map.containsKey("programId")){
			params.add(map.get("programId"));
			hql=hql+" and fuProgram.id=? ";
		}
		if(map.containsKey("detailId")){
			params.add(map.get("detailId"));
			hql=hql+" and id=? ";
		}
		if(map.containsKey("accountName")){
			params.add(map.get("accountName"));
			hql=hql+" and fuUser.accountName=? ";
		}
		if(map.containsKey("userName")){
			params.add(map.get("userName"));
			hql=hql+" and fuUser.userName=? ";
		}
		if(map.containsKey("money1")){
			params.add(map.get("money1"));
			hql=hql+" and money>=? ";
		}
		if(map.containsKey("money2")){
			params.add(map.get("money2"));
			hql=hql+" and money<=? ";
		}
		if(map.containsKey("time1")){
			params.add(map.get("time1"));
			hql=hql+" and time>=? ";
		}
		if(map.containsKey(map.get("time2"))){
			params.add(map.get("time2"));
			hql=hql+" and time<=? ";
		}
		if(map.containsKey("state")){
			params.add(map.get("state"));
			hql=hql+" and state=? ";
		}
		hql=hql+" order by id desc ";
		return this.findListByHQL(i, pageSize, hql, params);
	}

	@Override
	public List<FuAddMargin> findSafeMoneyByProgramId(Long id) {
		String hql=" from FuAddMargin where fuProgram.id=? ";
		return this.findAllByHQL(hql, id);
	}

}

