package com.hongwei.futures.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.FuPromoteVisitDao;
import com.hongwei.futures.model.FuPromoteVisit;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class FuPromoteVisitDaoImpl extends BaseDaoImpl<FuPromoteVisit, Long> implements FuPromoteVisitDao {


	@Override
	public List<FuPromoteVisit> findByPromote(int i,int pageSize,Long promoteId) {
		String hql = "from FuPromoteVisit where promoteId=?";
		return this.findListByHQL(i,pageSize, hql,promoteId);
	}

	@Override
	public FuPromoteVisit getByIP(Long promoteId,String IP) {
		String hql = "from FuPromoteVisit where promoteId=? and visitIp=?";
		return this.findUniqueByHQL(hql,promoteId,IP);
	}

	@Override
	public Integer getCountByPromote(Long promoteId) {
		String hql = "from FuPromoteVisit where promoteId=?";
		return this.countQueryResult(hql,promoteId);
	}

}

