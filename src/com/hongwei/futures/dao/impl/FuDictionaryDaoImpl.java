package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.hongwei.futures.dao.FuDictionaryDao;
import com.hongwei.futures.model.FuDictionary;
import com.hongwei.futures.model.FuUser;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author  小合
 */
@Repository
public class FuDictionaryDaoImpl extends BaseDaoImpl<FuDictionary, Long> implements FuDictionaryDao {
	@Override
	public List<FuDictionary> findListByMap(int i, int j, Map<String, Object> map){
		String hql = "from FuDictionary where pid<>0 ";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("isEnabled")){
			params.add(map.get("isEnabled"));
			hql=hql+" and isEnabled=? ";
		}
		if(map.containsKey("pid")){
			params.add(map.get("pid"));
			hql=hql+" and pid=? ";
		}
		if(map.containsKey("dictionaryName")){
			params.add(map.get("dictionaryName"));
			hql=hql+" and name=? ";
		}
		hql = hql + " and pid != 61";
		hql=hql+" order by id desc ";
		return this.findListByHQL(i, j, hql,params);
	}
	
	@Override
	public Integer countFuDictionary(Map<String, Object> map) {
		String hql = "from FuDictionary where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("isEnabled")){
			params.add(map.get("isEnabled"));
			hql=hql+" and isEnabled=? ";
		}
		if(map.containsKey("pid")){
			params.add(map.get("pid"));
			hql=hql+" and pid=? ";
		}
		if(map.containsKey("dictionaryName")){
			params.add(map.get("dictionaryName"));
			hql=hql+" and name=? ";
		}
		hql=hql+" order by id desc ";
		return this.countQueryResult(hql,params);
	}

	@SuppressWarnings("unchecked")
	public List<FuDictionary> getByPid(long pid, int flag) {
		String hql = "FROM FuDictionary AS fd WHERE fd.isEnabled = :flag AND fd.pid = :pid";
		return this.getSession().createQuery(hql)//
				.setParameter("flag", flag)//
				.setParameter("pid", pid)//
				.list();
	}

	@Override
	public List<FuDictionary> findBaseDictionaries() {
		String hql=" from FuDictionary where pid=0 and isEnabled=? ";
		return this.findAllByHQL(hql, 1);
	}

	@Override
	public String findDictionaryTree(Long id) {
		String sql = " select id, name, pid as pId, isEnabled from sys_dictionary union (select 0 as id, '全部' as name, -1 as pid, 1 as isEnabled from dual) ";
		if(id != 0){
			sql = " select id, name, pid as pId from sys_dictionary where pid = "+Long.valueOf(id);
		}
		List<Map<String, Object>> queryList = this.getJdbcTemplate().queryForList(sql);
		JSONArray arr = new JSONArray();
		for(Map<String, Object> dic : queryList){
			List<FuDictionary> dicList = this.findListByParentId(Long.valueOf(dic.get("id").toString()));
			JSONObject obj = new JSONObject();
			obj.put("id", dic.get("id").toString());
			obj.put("name", dic.get("name").toString());
			obj.put("pId", dic.get("pId").toString());
			obj.put("isParent", dicList.size()>0?"true":"false");
			obj.put("isEnabled", dic.get("isEnabled").toString());
			arr.add(obj);
		}
		return arr.toString();
	}

	public List<FuDictionary> findListByParentId(Long id) {
		String hql=" from FuDictionary where pid=? ";
		return this.findAllByHQL(hql, id);
	}
}

