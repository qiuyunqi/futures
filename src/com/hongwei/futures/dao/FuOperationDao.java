package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.FuOperation;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 小合
 */
public interface FuOperationDao extends BaseDao<FuOperation, Long> {
	public Integer countOperation(Map<String, Object> map);
	
	public List<FuOperation> findOperationlist(int i, int pageSize, Map<String, Object> map);

}

