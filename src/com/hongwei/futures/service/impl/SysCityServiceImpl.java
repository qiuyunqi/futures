package com.hongwei.futures.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.SysCityDao;
import com.hongwei.futures.model.SysCity;
import com.hongwei.futures.service.SysCityService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class SysCityServiceImpl implements SysCityService {
	
	@Autowired
	private SysCityDao sysCityDao;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public SysCity get(Long id) {
		return sysCityDao.get(id);
	}
	
	@Override
	public void save(SysCity entity) {
		sysCityDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		sysCityDao.delete(id);
	}

	@Override
	public List<SysCity> findCityByProvince(Long provinceId) {
		return sysCityDao.findCityByProvince(provinceId);
	}

	@Override
	public SysCity findByCityName(String cityName) {
		return sysCityDao.findByCityName(cityName);
	}
	
}

