package com.hongwei.futures.dao;

import java.util.List;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.FuPromote;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface FuPromoteDao extends BaseDao<FuPromote, Long> {
	public List<FuPromote> findByPromote(int i,int pageSize,Long promoteId);
	public Integer getCountByPromote(Long promoteId);
	public FuPromote findBy(Long promotedId);

}

