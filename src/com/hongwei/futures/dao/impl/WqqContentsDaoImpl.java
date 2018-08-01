package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.WqqContentsDao;
import com.hongwei.futures.model.WqqContents;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author  小合
 */
@Repository
public class WqqContentsDaoImpl extends BaseDaoImpl<WqqContents, Long> implements WqqContentsDao {
	@Override
	public List<WqqContents> findContentsByMap(int i, int pageSize, Map<String, Object> map) {
		String hql=" from WqqContents where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("name")){
			params.add(map.get("name"));
			hql=hql+" and name=? ";
		}
		if(map.containsKey("createTime1")){
			params.add(map.get("createTime1"));
			hql=hql+" and createTime>=?";
		}
		if(map.containsKey("createTime2")){
			params.add(map.get("createTime2"));
			hql=hql+" and createTime<=?";
		}
		if(map.containsKey("state")){
			params.add(map.get("state"));
			hql=hql+" and state=?";
		}
		hql=hql+" order by id desc ";
		return this.findListByHQL(i, pageSize, hql, params);
	}

	@Override
	public Integer countContents(Map<String, Object> map) {
		String hql=" from WqqContents where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("name")){
			params.add(map.get("name"));
			hql=hql+" and name=? ";
		}
		if(map.containsKey("createTime1")){
			params.add(map.get("createTime1"));
			hql=hql+" and createTime>=?";
		}
		if(map.containsKey("createTime2")){
			params.add(map.get("createTime2"));
			hql=hql+" and createTime<=?";
		}
		if(map.containsKey("state")){
			params.add(map.get("state"));
			hql=hql+" and state=?";
		}
		return this.countQueryResult(hql,params);
	}
}

