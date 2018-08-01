package com.hongwei.futures.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hongwei.futures.model.FuDrawProfits;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface FuDrawProfitsDao extends BaseDao<FuDrawProfits, Long> {

	public Integer countDrawProfits(Map<String, Object> map);

	public List<FuDrawProfits> findDrawList(int i, int pageSize,
			Map<String, Object> map);

	public List<FuDrawProfits> findDrawProfitsByProgramId(Long id);

	public int findProfitsByTime(Long programId, Date time1,
			Date time2);

}

