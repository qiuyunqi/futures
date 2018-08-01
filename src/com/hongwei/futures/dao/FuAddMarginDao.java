package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.FuAddMargin;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface FuAddMarginDao extends BaseDao<FuAddMargin, Long> {

	public Integer countSafeMoney(Map<String, Object> map);

	public List<FuAddMargin> findSafeMoneyList(int i, int pageSize,
			Map<String, Object> map);

	public List<FuAddMargin> findSafeMoneyByProgramId(Long id);

}

