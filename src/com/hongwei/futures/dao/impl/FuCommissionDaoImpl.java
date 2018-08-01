package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.FuCommissionDao;
import com.hongwei.futures.model.FuCommission;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class FuCommissionDaoImpl extends BaseDaoImpl<FuCommission, Long> implements FuCommissionDao {

	@Override
	public List<FuCommission> findList(int i, int pageSize,
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
		hqlB.append("from FuCommission where state=1");
		if(map.get("userId")!=null){
			hqlB.append(" and fuUserByUserId.id =?");
			params.add(map.get("userId"));
		}
		if(map.get("accountName")!=null){
			hqlB.append(" and fuUserByUserId.accountName like'%"+map.get("accountName")+"%'");
		}
		if(map.get("extendName")!=null){
			hqlB.append(" and fuUserByExtendUserId.accountName like'%"+map.get("extendName")+"%'");
		}
		if(map.get("programId")!=null){
			hqlB.append(" and fuProgram.id =?");
			params.add(map.get("programId"));
		}
		if(map.get("remark")!=null){
			hqlB.append(" and remark like'%"+map.get("remark")+"%'");
		}
		if(map.get("money1")!=null){
			hqlB.append(" and commissionMoney >=? ");
			params.add(map.get("money1"));
		}
		if(map.get("money2")!=null){
			hqlB.append(" and commissionMoney <=? ");
			params.add(map.get("money2"));
		}
		if(map.get("time1")!=null){
			hqlB.append(" and time >=? ");
			params.add(map.get("time1"));
		}
		if(map.get("time2")!=null){
			hqlB.append(" and time <=? ");
			params.add(map.get("time2"));
		}
		if(map.get("status")!=null){
			hqlB.append(" and status=?");
			params.add(map.get("status"));
		}
		hqlB.append(" order by id desc");
	}

}

