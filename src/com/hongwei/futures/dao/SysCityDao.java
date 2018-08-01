package com.hongwei.futures.dao;

import java.util.List;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.SysCity;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface SysCityDao extends BaseDao<SysCity, Long> {

	public List<SysCity> findCityByProvince(Long provinceId);
	public SysCity findByCityName(String cityName);

}

