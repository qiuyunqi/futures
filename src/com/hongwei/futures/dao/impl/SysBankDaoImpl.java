package com.hongwei.futures.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.SysBankDao;
import com.hongwei.futures.model.SysBank;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class SysBankDaoImpl extends BaseDaoImpl<SysBank, Long> implements SysBankDao {

	@Override
	public List<SysBank> findAllBank() {
		String hql = "from SysBank where state=1";
		return this.findAllByHQL(hql);
	}

	@Override
	public SysBank findBy(String name) {
		String hql = "from SysBank where bankName=? and state=1";
		return this.findUniqueByHQL(hql,name);
	}
}

