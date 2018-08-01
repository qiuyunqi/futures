package com.hongwei.futures.dao.impl;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.FuParameterDao;
import com.hongwei.futures.model.FuParameter;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class FuParameterDaoImpl extends BaseDaoImpl<FuParameter, Long> implements FuParameterDao {

	@Override
	public FuParameter findParameter() {
		String hql=" from FuParameter ";
		return this.findUniqueByHQL(hql);
	}

}

