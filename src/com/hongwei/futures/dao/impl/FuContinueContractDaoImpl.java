package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.FuContinueContractDao;
import com.hongwei.futures.model.FuContinueContract;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class FuContinueContractDaoImpl extends BaseDaoImpl<FuContinueContract, Long> implements FuContinueContractDao {

	@Override
	public Integer countContinue(Map<String, Object> map) {
		List<Object> params = new ArrayList<Object>();
		String hql=" from FuContinueContract where 1=1 ";
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
		if(map.containsKey("cycle1")){
			params.add(map.get("cycle1"));
			hql=hql+" and cycle>=? ";
		}
		if(map.containsKey("cycle2")){
			params.add(map.get("cycle2"));
			hql=hql+" and cycle<=? ";
		}
		if(map.containsKey("time1")){
			params.add(map.get("time1"));
			hql=hql+" and time>=? ";
		}
		if(map.containsKey("time2")){
			params.add(map.get("time2"));
			hql=hql+" and time<=? ";
		}
		if(map.containsKey("result")){
			params.add(map.get("result"));
			hql=hql+" and result=? ";
		}
		return this.countQueryResult(hql, params);
	}

	@Override
	public List<FuContinueContract> getContinueList(int i, int pageSize,
			Map<String, Object> map) {
		List<Object> params = new ArrayList<Object>();
		String hql=" from FuContinueContract where 1=1 ";
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
		if(map.containsKey("cycle1")){
			params.add(map.get("cycle1"));
			hql=hql+" and cycle>=? ";
		}
		if(map.containsKey("cycle2")){
			params.add(map.get("cycle2"));
			hql=hql+" and cycle<=? ";
		}
		if(map.containsKey("time1")){
			params.add(map.get("time1"));
			hql=hql+" and time>=? ";
		}
		if(map.containsKey("time2")){
			params.add(map.get("time2"));
			hql=hql+" and time<=? ";
		}
		if(map.containsKey("result")){
			params.add(map.get("result"));
			hql=hql+" and result=? ";
		}
		hql=hql+" order by id desc ";
		return this.findListByHQL(i, pageSize, hql, params);
	}
	
	@Override
	public List<FuContinueContract> findContinueListByProgramId(Long id){
		String hql=" from FuContinueContract where fuProgram.id=? ";
		return this.findAllByHQL(hql, id);
	}

}

