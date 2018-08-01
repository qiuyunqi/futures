package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.FuRechargeDao;
import com.hongwei.futures.model.FuRecharge;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class FuRechargeDaoImpl extends BaseDaoImpl<FuRecharge, Long> implements FuRechargeDao {

	@Override
	public List<FuRecharge> findBy(int i, int pageSize, Map<String, Object> map) {
		StringBuilder hqlB = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
	    this.setHQL(hqlB, params, map);
		return this.findListByHQL(i,pageSize,hqlB.toString(),params);
	}

	@Override
	public Integer getCount(Map<String, Object> map) {
		StringBuilder hqlB = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		this.setHQL(hqlB, params, map);
		return this.countQueryResult(hqlB.toString(), params);
	}

	public void setHQL(StringBuilder hqlB,List<Object> params,Map<String,Object> map){
		 hqlB =  hqlB.append("from FuRecharge where state=1");
		 if(map.get("id")!=null){
		    	hqlB.append(" and id=?");
		    	params.add(map.get("id"));
		    }
		    if(map.get("userId")!=null){
				hqlB.append(" and fuUser.id=?");
				params.add(map.get("userId"));
			}
		    if(map.get("phone")!=null){
				hqlB.append(" and fuUser.phone =?");
				params.add(map.get("phone"));
			}
		    if(map.get("userName")!=null){
		    	hqlB.append(" and fuUser.userName like '%"+map.get("userName")+"%'");
		    }
		    if(map.get("rechargeStatus")!=null){
				hqlB.append(" and rechargeStatus=?");
				params.add(map.get("rechargeStatus"));
			}
		    if(map.get("type")!=null){
				hqlB.append(" and type=?");
				params.add(map.get("type"));
			}
		    if(map.get("orderNum")!=null){
				hqlB.append(" and orderNum=?");
				params.add(map.get("orderNum"));
			}
		    if(map.get("money1")!=null){
				hqlB.append(" and money>=?");
				params.add(map.get("money1"));
			}
		    if(map.get("money2")!=null){
				hqlB.append(" and money<=?");
				params.add(map.get("money2"));
			}
		    if(map.get("time1")!=null){
				hqlB.append(" and rechargeTime>=?");
				params.add(map.get("time1"));
			}
		    if(map.get("time2")!=null){
				hqlB.append(" and rechargeTime<=?");
				params.add(map.get("time2"));
			}
		    if(map.get("rechargeAccount")!=null){
		    	hqlB.append(" and rechargeAccount like '%"+map.get("rechargeAccount")+"%'");
		    }
		    if(map.get("state")!=null){
				hqlB.append(" and fuUser.state=? ");
				params.add(map.get("state"));
			}
		    hqlB.append(" and id not in(select id from FuRecharge where type=1 and rechargeStatus=0)");
			hqlB.append(" order by rechargeStatus,id desc");
			
	}

	@Override
	public List<FuRecharge> findListByUserId(Long userId) {
		String hql=" from FuRecharge where fuUser.id=? order by rechargeTime desc ";
		return this.findAllByHQL(hql, userId);
	}
}

