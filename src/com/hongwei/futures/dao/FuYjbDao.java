package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.FuYjb;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 小合
 */
public interface FuYjbDao extends BaseDao<FuYjb, Long> {

	public List<FuYjb> findYqbList(int i, int pageSize, Map<String, Object> map);

}

