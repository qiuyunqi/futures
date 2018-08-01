package com.hongwei.futures.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hongwei.futures.dao.FuServerDao;
import com.hongwei.futures.model.FuServer;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class FuServerDaoImpl extends BaseDaoImpl<FuServer, Long> implements FuServerDao {

	
	
	@Override
	public List<FuServer> findListByServer(){
		String hql="from FuServer where isDel=1 order by serverPriority desc ";
		return this.findAllByHQL(hql);

	}
	@Override
	public FuServer findByName(String name){
		String hql="from FuServer where serverName=? and isDel=1";
		return this.findUniqueByHQL(hql);
	}
	
	@Override
	public List<FuServer> findListAvalible(BigDecimal total) {
		String hql="from FuServer where serverMoney>? and isDel=1 order by serverPriority desc ";
		return this.findListByHQL(0, 1, hql, total);
	}
	
	public FuServer findServerByUserTypeId(Integer userTypeId){
		String hql="from FuServer where usertypeId=? and isDel=1";
		return this.findUniqueByHQL(hql, userTypeId);
	}

}

