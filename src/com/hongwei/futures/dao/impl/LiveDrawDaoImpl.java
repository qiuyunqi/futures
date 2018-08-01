package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.LiveDrawDao;
import com.hongwei.futures.model.LiveDraw;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author  小合
 */
@Repository
public class LiveDrawDaoImpl extends BaseDaoImpl<LiveDraw, Long> implements LiveDrawDao {
	public Integer countLive(Map<String, Object> map){
		List<Object> params =new ArrayList<Object>();
		String hql=" from LiveDraw where 1=1 ";
		if(map.containsKey("createAdmin")){
			params.add(map.get("createAdmin"));
			hql=hql+" and createAdmin.name=? ";
		}
		if(map.containsKey("time1")){
			params.add(map.get("time1"));
			hql=hql+" and createTime>=? ";
		}
		if(map.containsKey("time2")){
			params.add(map.get("time2"));
			hql=hql+" and createTime<=? ";
		}
		return this.countQueryResult(hql, params);
	}
	
	public List<LiveDraw> findLiveDrawList(int i, int j, Map<String, Object> map){
		List<Object> params =new ArrayList<Object>();
		String hql=" from LiveDraw where 1=1 ";
		if(map.containsKey("createAdmin")){
			params.add(map.get("createAdmin"));
			hql=hql+" and createAdmin.name=? ";
		}
		if(map.containsKey("time1")){
			params.add(map.get("time1"));
			hql=hql+" and createTime>=? ";
		}
		if(map.containsKey("time2")){
			params.add(map.get("time2"));
			hql=hql+" and createTime<=? ";
		}
		if(map.containsKey("time3")){
			params.add(map.get("time3"));
			hql=hql+" and date_format(createTime,'%Y-%m-%d')>=? ";
		}
		if(map.containsKey("time4")){
			params.add(map.get("time4"));
			hql=hql+" and date_format(createTime,'%Y-%m-%d')<=? ";
		}
		hql=hql+" order by id desc ";
		return this.findListByHQL(i, j, hql, params);
	}
}

