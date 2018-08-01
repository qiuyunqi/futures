package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.FuContinueContract;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface FuContinueContractDao extends BaseDao<FuContinueContract, Long> {

	public Integer countContinue(Map<String, Object> map);

	public List<FuContinueContract> getContinueList(int i, int pageSize,
			Map<String, Object> map);
	
	public List<FuContinueContract> findContinueListByProgramId(Long id);

}

