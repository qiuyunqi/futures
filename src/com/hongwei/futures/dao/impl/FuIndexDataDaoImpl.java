package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.FuIndexDataDao;
import com.hongwei.futures.model.FuIndexData;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class FuIndexDataDaoImpl extends BaseDaoImpl<FuIndexData, Long> implements FuIndexDataDao {

	@Override
	public List<FuIndexData> findAllByFuIndexData() {
		String hql="from FuIndexData where isDel=1";
		return this.findAllByHQL(hql);
	}
	
	@Override
	public List<FuIndexData> findListBy(int i, int pageSize,
			Map<String, Object> map) {
		String hql="from FuIndexData Where isDel=1";
		List<Object> params = new ArrayList<Object>();
		return this.findListByHQL(i,pageSize,hql,params);
	}

	@Override
	public Integer getCount(Map<String, Object> map) {
		String hql="from FuIndexData Where isDel=1";
		List<Object> params = new ArrayList<Object>();
	    return this.countQueryResult(hql, params);
	}
	
	public List<FuIndexData> findTop10(){
		String hql="from FuIndexData where isDel=1 order by getMoney desc";
		return this.findListByHQL(0, 10, hql);
	}

}

