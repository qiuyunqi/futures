package com.hongwei.futures.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.FuPromoteDao;
import com.hongwei.futures.model.FuPromote;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class FuPromoteDaoImpl extends BaseDaoImpl<FuPromote, Long> implements FuPromoteDao {

	public FuPromote findBy(Long promoteId, Long promotedId) {
		String hql = "from FuPromote where fuUserByPromoteId.id=? and fuUserByPromotedId.id=?";
		return this.findUniqueByHQL(hql,promoteId,promotedId);
	}

	@Override
	public List<FuPromote> findByPromote(int i, int pageSize, Long promoteId) {
		String hql = "from FuPromote where fuUserByPromoteId.id=?";
		return this.findListByHQL(i, pageSize, hql, promoteId);
	}

	@Override
	public Integer getCountByPromote(Long promoteId) {
		String hql = "from FuPromote where fuUserByPromoteId.id=?";
		return this.countQueryResult(hql, promoteId);
	}

	@Override
	public FuPromote findBy(Long promotedId) {
		String hql = "from FuPromote where fuUserByPromotedId.id=?";
		return this.findUniqueByHQL(hql,promotedId);
	}

}

