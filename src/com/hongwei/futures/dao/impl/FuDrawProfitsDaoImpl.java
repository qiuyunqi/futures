package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.FuDrawProfitsDao;
import com.hongwei.futures.model.FuDrawProfits;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class FuDrawProfitsDaoImpl extends BaseDaoImpl<FuDrawProfits, Long> implements FuDrawProfitsDao {

	@Override
	public Integer countDrawProfits(Map<String, Object> map) {
		List<Object> params = new ArrayList<Object>();
		String hql=" from FuDrawProfits where 1=1 ";
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
		if(map.containsKey("programId")){
			params.add(map.get("programId"));
			hql=hql+" and fuProgram.id=? ";
		}
		if(map.containsKey("state")){
			params.add(map.get("state"));
			hql=hql+" and state=? ";
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
			hql=hql+" and time<= ? ";
		}
		
		return this.countQueryResult(hql, params);
	}

	@Override
	public List<FuDrawProfits> findDrawList(int i, int pageSize,
			Map<String, Object> map) {
		List<Object> params = new ArrayList<Object>();
		String hql=" from FuDrawProfits where 1=1 ";
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
		if(map.containsKey("programId")){
			params.add(map.get("programId"));
			hql=hql+" and fuProgram.id=? ";
		}
		if(map.containsKey("state")){
			params.add(map.get("state"));
			hql=hql+" and state=? ";
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
			hql=hql+" and time<= ? ";
		}
		hql=hql+" order by id desc ";
		return this.findListByHQL(i, pageSize, hql, params);
	}

	@Override
	public List<FuDrawProfits> findDrawProfitsByProgramId(Long id) {
		String hql=" from FuDrawProfits where fuProgram.id=? ";
		return this.findAllByHQL(hql, id);
	}

	@Override
	public int findProfitsByTime(Long programId, Date time1,
			Date time2) {
		String hql="  from FuDrawProfits where fuProgram.id=? and time>=? and time<? ";
		return this.countQueryResult(hql, programId,time1,time2);
	}

}

