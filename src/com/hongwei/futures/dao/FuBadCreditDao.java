package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.FuBadCredit;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface FuBadCreditDao extends BaseDao<FuBadCredit, Long> {

	public Integer countBadCredit(Map<String, Object> map);

	public List<FuBadCredit> findBadList(int i, int pageSize,
			Map<String, Object> map);

	public List<FuBadCredit> findListByProgram(Long programId);

}

