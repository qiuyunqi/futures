package com.hongwei.futures.dao.impl;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.HhrPromoteParameterDao;
import com.hongwei.futures.model.HhrPromoteParameter;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class HhrPromoteParameterDaoImpl extends BaseDaoImpl<HhrPromoteParameter, Long> implements HhrPromoteParameterDao {

	@Override
	public HhrPromoteParameter findParameter() {
		String hql=" from HhrPromoteParameter ";
		return this.findUniqueByHQL(hql);
	}

}

