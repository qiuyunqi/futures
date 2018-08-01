package com.hongwei.futures.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.HhrOrgPersonDao;
import com.hongwei.futures.model.HhrOrgPerson;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class HhrOrgPersonDaoImpl extends BaseDaoImpl<HhrOrgPerson, Long> implements HhrOrgPersonDao {

	@Override
	public List<HhrOrgPerson> countByUserAndCer(String userName, String cerNum) {
		String hql = " from HhrOrgPerson where userName=? and cerNum=? ";
		return this.findAllByHQL(hql, userName, cerNum);
	}

	@Override
	public void deleteAll() {
		this.getJdbcTemplate().update("truncate table hhr_org_person");
	}

}

