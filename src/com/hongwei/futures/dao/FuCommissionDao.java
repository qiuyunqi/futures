package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.FuCommission;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface FuCommissionDao extends BaseDao<FuCommission, Long> {
	public List<FuCommission> findList(int i,int pageSize,Map<String,Object> map);
	public Integer getCount(Map<String,Object> map);
	
}

