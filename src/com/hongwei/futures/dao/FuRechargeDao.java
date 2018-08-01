package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.FuRecharge;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface FuRechargeDao extends BaseDao<FuRecharge, Long> {
	public List<FuRecharge> findBy(int i,int pageSize,Map<String,Object> map);
	public Integer getCount(Map<String,Object> map);
	public List<FuRecharge> findListByUserId(Long userId);
}

