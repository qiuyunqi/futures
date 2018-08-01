package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.FuOperationDao;
import com.hongwei.futures.model.FuOperation;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author  小合
 */
@Repository
public class FuOperationDaoImpl extends BaseDaoImpl<FuOperation, Long> implements FuOperationDao {
	public Integer countOperation(Map<String, Object> map){
		List<Object> params = new ArrayList<Object>();
		String hql=" from FuOperation where 1=1 ";
		if(map.containsKey("modelMain")){
			params.add(map.get("modelMain"));
			hql=hql+" and modelMain=? ";
		}
		if(map.containsKey("modelSub")){
			params.add(map.get("modelSub"));
			hql=hql+" and modelSub=? ";
		}
		if(map.containsKey("operation")){
			params.add(map.get("operation"));
			hql=hql+" and operation=? ";
		}
		if(map.containsKey("opId")){
			params.add(map.get("opId"));
			hql=hql+" and opId=? ";
		}
		if(map.containsKey("time1")){
			params.add(map.get("time1"));
			hql=hql+" and updateTime>=? ";
		}
		if(map.containsKey("time2")){
			params.add(map.get("time2"));
			hql=hql+" and updateTime<=? ";
		}
		return this.countQueryResult(hql, params);
	}
	
	public List<FuOperation> findOperationlist(int i, int pageSize, Map<String, Object> map){
		List<Object> params = new ArrayList<Object>();
		String hql=" from FuOperation where 1=1 ";
		if(map.containsKey("modelMain")){
			params.add(map.get("modelMain"));
			hql=hql+" and modelMain=? ";
		}
		if(map.containsKey("modelSub")){
			params.add(map.get("modelSub"));
			hql=hql+" and modelSub=? ";
		}
		if(map.containsKey("operation")){
			params.add(map.get("operation"));
			hql=hql+" and operation=? ";
		}
		if(map.containsKey("opId")){
			params.add(map.get("opId"));
			hql=hql+" and opId=? ";
		}
		if(map.containsKey("time1")){
			params.add(map.get("time1"));
			hql=hql+" and updateTime>=? ";
		}
		if(map.containsKey("time2")){
			params.add(map.get("time2"));
			hql=hql+" and updateTime<=? ";
		}
		hql=hql+" order by id desc";
		return this.findListByHQL(i, pageSize, hql, params);
	}
}

