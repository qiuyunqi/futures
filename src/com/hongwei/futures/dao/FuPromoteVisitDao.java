package com.hongwei.futures.dao;

import java.util.List;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.FuPromoteVisit;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface FuPromoteVisitDao extends BaseDao<FuPromoteVisit, Long> {
    public FuPromoteVisit getByIP(Long promoteId,String IP);
	public List<FuPromoteVisit> findByPromote(int i,int pageSize,Long promoteId);
	public Integer getCountByPromote(Long promotedId);
}

