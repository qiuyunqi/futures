package com.hongwei.futures.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.SysCityDao;
import com.hongwei.futures.model.SysCity;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class SysCityDaoImpl extends BaseDaoImpl<SysCity, Long> implements SysCityDao {

	@Override
	public List<SysCity> findCityByProvince(Long provinceId) {
		String hql=" from SysCity where provinceId=? ";
		return this.findAllByHQL(hql, provinceId);
	}

	@Override
	public SysCity findByCityName(String cityName) {
		String hql=" from SysCity where cityName=? ";
		return this.findUniqueByHQL(hql,cityName);
	}

}

