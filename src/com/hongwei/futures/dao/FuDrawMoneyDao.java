package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.FuDrawMoney;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface FuDrawMoneyDao extends BaseDao<FuDrawMoney, Long> {
	public List<FuDrawMoney> findList(int i,int pageSize,Map<String,Object> map);
	public Integer getCount(Map<String,Object> map);
	public List<FuDrawMoney> findListByUserId(Long userId);
}

