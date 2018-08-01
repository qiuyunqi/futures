package com.hongwei.futures.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.SysProvinceDao;
import com.hongwei.futures.model.SysProvince;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class SysProvinceDaoImpl extends BaseDaoImpl<SysProvince, Long> implements SysProvinceDao {

	@Override
	public List<SysProvince> findAllProvince() {
		String hql=" from SysProvince ";
		return this.findAllByHQL(hql);
	}

	@Override
	public SysProvince findByName(String provinceName) {
		String hql = "from SysProvince where provinceName=?";
		return this.findUniqueByHQL(hql,provinceName);
	}
}

