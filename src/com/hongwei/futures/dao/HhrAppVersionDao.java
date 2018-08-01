package com.hongwei.futures.dao;

import java.util.List;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.HhrAppVersion;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface HhrAppVersionDao extends BaseDao<HhrAppVersion, Long> {

	List<HhrAppVersion> findVersionList(Long appType);

}

