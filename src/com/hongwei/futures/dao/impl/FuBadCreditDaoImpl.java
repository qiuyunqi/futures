package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.FuBadCreditDao;
import com.hongwei.futures.model.FuBadCredit;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class FuBadCreditDaoImpl extends BaseDaoImpl<FuBadCredit, Long> implements FuBadCreditDao {

	@Override
	public Integer countBadCredit(Map<String, Object> map) {
		List<Object> params = new ArrayList<Object>();
		String hql=" from FuBadCredit where 1=1 ";
		if(map.containsKey("state")){
			params.add(map.get("state"));
			hql=hql+" and state=? ";
		}
		if(map.containsKey("accountName")){
			params.add(map.get("accountName"));
			hql=hql+" and fuUser.accountName=? ";
		}
		if(map.containsKey("userName")){
			params.add(map.get("userName"));
			hql=hql+" and fuUser.userName=? ";
		}
		if(map.containsKey("programId")){
			params.add(map.get("programId"));
			hql=hql+" and fuProgram.id=? ";
		}
		if(map.containsKey("type")){
			params.add(map.get("type"));
			hql=hql+" and type=? ";
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
		if(map.containsKey("time2")){
			params.add(map.get("time2"));
			hql=hql+" and time<=? ";
		}
		return this.countQueryResult(hql, params);
	}

	@Override
	public List<FuBadCredit> findBadList(int i, int pageSize,
			Map<String, Object> map) {
		List<Object> params = new ArrayList<Object>();
		String hql=" from FuBadCredit where 1=1 ";
		if(map.containsKey("state")){
			params.add(map.get("state"));
			hql=hql+" and state=? ";
		}
		if(map.containsKey("accountName")){
			params.add(map.get("accountName"));
			hql=hql+" and fuUser.accountName=? ";
		}
		if(map.containsKey("userName")){
			params.add(map.get("userName"));
			hql=hql+" and fuUser.userName=? ";
		}
		if(map.containsKey("programId")){
			params.add(map.get("programId"));
			hql=hql+" and fuProgram.id=? ";
		}
		if(map.containsKey("type")){
			params.add(map.get("type"));
			hql=hql+" and type=? ";
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
		if(map.containsKey("time2")){
			params.add(map.get("time2"));
			hql=hql+" and time<=? ";
		}
		hql=hql+" order by id desc,state asc ";
		return this.findListByHQL(i, pageSize, hql, params);
	}

	@Override
	public List<FuBadCredit> findListByProgram(Long programId) {
		String hql=" from FuBadCredit where fuProgram.id=?  order by id desc ";
		return this.findAllByHQL(hql, programId);
	}

}

