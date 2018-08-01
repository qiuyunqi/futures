package com.hongwei.futures.dao;

import java.util.List;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.SysProvince;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface SysProvinceDao extends BaseDao<SysProvince, Long> {

	public List<SysProvince> findAllProvince();
	public SysProvince findByName(String provinceName);
}

