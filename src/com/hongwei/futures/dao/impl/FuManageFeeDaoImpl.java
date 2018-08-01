package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.FuManageFeeDao;
import com.hongwei.futures.model.FuManageFee;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class FuManageFeeDaoImpl extends BaseDaoImpl<FuManageFee, Long> implements FuManageFeeDao {

	@Override
	public Integer countFee(HashMap<String, Object> map) {
		List<Object> params=new ArrayList<Object>();
		String hql=" from FuManageFee where 1=1 ";
		if(map.containsKey("programId")){
			params.add(map.get("programId"));
			hql=hql+" and fuProgram.id=? ";
		}
		if(map.containsKey("userId")){
			params.add(map.get("userId"));
			hql=hql+" and fuUser.id=? ";
		}
		if(map.containsKey("accountName")){
			params.add(map.get("accountName"));
			hql=hql+" and fuUser.accountName=? ";
		}
		if(map.containsKey("userName")){
			params.add(map.get("userName"));
			hql=hql+" and fuUser.userName=? ";
		}
		if(map.containsKey("payCycle1")){
			params.add(map.get("payCycle1"));
			hql=hql+" and payCycle>=? ";
		}
		if(map.containsKey("payCycle2")){
			params.add(map.get("payCycle2"));
			hql=hql+" and payCycle<=? ";
		}
		if(map.containsKey("money1")){
			params.add(map.get("money1"));
			hql=hql+" and money >=? ";
		}
		if(map.containsKey("money2")){
			params.add(map.get("money2"));
			hql=hql+" and money <=? ";
		}
		if(map.containsKey("payTime1")){
			params.add(map.get("payTime1"));
			hql=hql+" and payTime>=? ";
		}
		if(map.containsKey("payTime2")){
			params.add(map.get("payTime2"));
			hql=hql+" and payTime<=?  ";
		}
		if(map.containsKey("state")){
			params.add(map.get("state"));
			hql=hql+" and state=?  ";
		}
		return this.countQueryResult(hql, params);
	}

	@Override
	public List<FuManageFee> findFeeList(int i, int pageSize,
			HashMap<String, Object> map) {
		List<Object> params=new ArrayList<Object>();
		String hql=" from FuManageFee where 1=1 ";
		if(map.containsKey("programId")){
			params.add(map.get("programId"));
			hql=hql+" and fuProgram.id=? ";
		}
		if(map.containsKey("userId")){
			params.add(map.get("userId"));
			hql=hql+" and fuUser.id=? ";
		}
		if(map.containsKey("accountName")){
			params.add(map.get("accountName"));
			hql=hql+" and fuUser.accountName=? ";
		}
		if(map.containsKey("userName")){
			params.add(map.get("userName"));
			hql=hql+" and fuUser.userName=? ";
		}
		if(map.containsKey("payCycle1")){
			params.add(map.get("payCycle1"));
			hql=hql+" and payCycle>=? ";
		}
		if(map.containsKey("payCycle2")){
			params.add(map.get("payCycle2"));
			hql=hql+" and payCycle<=? ";
		}
		if(map.containsKey("money1")){
			params.add(map.get("money1"));
			hql=hql+" and money >=? ";
		}
		if(map.containsKey("money2")){
			params.add(map.get("money2"));
			hql=hql+" and money <=? ";
		}
		if(map.containsKey("payTime1")){
			params.add(map.get("payTime1"));
			hql=hql+" and payTime>=? ";
		}
		if(map.containsKey("payTime2")){
			params.add(map.get("payTime2"));
			hql=hql+" and payTime<=?  ";
		}
		if(map.containsKey("state")){
			params.add(map.get("state"));
			hql=hql+" and state=?  ";
		}
		hql=hql+" order by state,id desc ";
		return this.findListByHQL(i, pageSize, hql, params);
	}

	@Override
	public List<FuManageFee> findFeeListByProgramId(Long id) {
		String hql=" from FuManageFee where fuProgram.id=? ";
		return this.findAllByHQL(hql, id);
	}
	
	@Override
	public FuManageFee findfeeByProidLast(Long id){
		String hql=" from FuManageFee where fuProgram.id=? order by id desc";
		List<FuManageFee> list=this.findAllByHQL(hql, id);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

}

