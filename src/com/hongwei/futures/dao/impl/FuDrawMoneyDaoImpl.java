package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.FuDrawMoneyDao;
import com.hongwei.futures.model.FuDrawMoney;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class FuDrawMoneyDaoImpl extends BaseDaoImpl<FuDrawMoney, Long> implements FuDrawMoneyDao {

	@Override
	public List<FuDrawMoney> findList(int i, int pageSize,
			Map<String, Object> map) {
		StringBuilder hqlB = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		setHQL(hqlB,map,params);
		return this.findListByHQL(i, pageSize, hqlB.toString(), params);
	}

	@Override
	public Integer getCount(Map<String, Object> map) {
		StringBuilder hqlB = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		setHQL(hqlB,map,params);
		return this.countQueryResult(hqlB.toString(), params);
	}
	
	public void setHQL(StringBuilder hqlB,Map<String,Object> map,List<Object> params){
		hqlB.append("from FuDrawMoney where state=1");
		if(map.get("id")!=null){
			hqlB.append("from id=?");
			params.add(map.get("id"));
		}
		if(map.get("userId")!=null){
			hqlB.append(" and fuUser.id =?");
			params.add(map.get("userId"));
		}
		if(map.get("phone")!=null){
			hqlB.append(" and fuUser.phone =?");
			params.add(map.get("phone"));
		}
		if(map.get("userName")!=null){
			hqlB.append(" and fuUser.userName like'%"+map.get("userName")+"%'");
		}
		if(map.get("status")!=null){
			hqlB.append(" and status=?");
			params.add(map.get("status"));
		}
		if(map.get("comment")!=null){
			hqlB.append(" and comment like'%"+map.get("comment")+"%'");
		}
		if(map.get("money1")!=null){
			hqlB.append(" and money >=? ");
			params.add(map.get("money1"));
		}
		if(map.get("money2")!=null){
			hqlB.append(" and money <=? ");
			params.add(map.get("money2"));
		}
		if(map.get("time1")!=null){
			hqlB.append(" and drawTime >=? ");
			params.add(map.get("time1"));
		}
		if(map.get("time2")!=null){
			hqlB.append(" and drawTime <=? ");
			params.add(map.get("time2"));
		}
		if(map.get("state")!=null){
			hqlB.append(" and fuUser.state=? ");
			params.add(map.get("state"));
		}
		hqlB.append(" order by status,id desc");
	}

	@Override
	public List<FuDrawMoney> findListByUserId(Long userId) {
		String hql=" from FuDrawMoney where fuUser.id=? order by drawTime desc ";
		return this.findAllByHQL(hql, userId);
	}

}

