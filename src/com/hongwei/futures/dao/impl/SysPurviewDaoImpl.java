package com.hongwei.futures.dao.impl;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.SysPurviewDao;
import com.hongwei.futures.model.SysPurview;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
@SuppressWarnings("all")
public class SysPurviewDaoImpl extends BaseDaoImpl<SysPurview, Long> implements SysPurviewDao {
	public List<SysPurview> findPurviewList(int i, int pageSize, Map<String, Object> map){
		String hql="from SysPurview where 1=1";
		List<Object> params=new ArrayList<Object>();
		if(map.get("url")!=null){
			hql=hql+" and url like '%"+map.get("url")+"%'";
		}
		if(map.get("parentId")!=null){
			params.add(map.get("parentId"));
			hql=hql+" and parentId=?";
		}
		hql=hql+" order by id desc ";
		return this.findListByHQL(i, pageSize, hql, params);
	}

	/*查询出所有的顶级权限的URL*/
	public List<SysPurview> findTopPrivilege() {
		return getSession().createQuery(//
				"FROM SysPurview p WHERE p.parentId = 0")//
				.list();
	}

	/*查询所有的权限URL*/
	public Collection<String> getAllPrivilegeUrls() {
		return getSession().createQuery(//
				"SELECT DISTINCT p.url FROM SysPurview p WHERE p.url IS NOT NULL")//
				.list();
	}

	public Integer getCount(Map<String, Object> map){
		String hql="from SysPurview where 1=1";
		List<Object> params=new ArrayList<Object>();
		if(map.get("url")!=null){
			hql=hql+" and url like '%"+map.get("url")+"%'";
		}
		if(map.get("parentId")!=null){
			params.add(map.get("parentId"));
			hql=hql+" and parentId=?";
		}
		hql=hql+" order by id desc ";
		return this.countQueryResult(hql, params);
	}	
	
}

