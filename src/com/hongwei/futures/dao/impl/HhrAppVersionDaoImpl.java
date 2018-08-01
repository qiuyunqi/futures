package com.hongwei.futures.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.HhrAppVersionDao;
import com.hongwei.futures.model.HhrAppVersion;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class HhrAppVersionDaoImpl extends BaseDaoImpl<HhrAppVersion, Long> implements HhrAppVersionDao {

	@Override
	public List<HhrAppVersion> findVersionList(Long appType) {
		String hql = " from HhrAppVersion where 1=1 ";
		if(appType != null){
			hql += " and appType = "+appType;
		}
		return this.findAllByHQL(hql);
	}

}

