package com.hongwei.futures.dao;

import java.util.List;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.HhrOrgPerson;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface HhrOrgPersonDao extends BaseDao<HhrOrgPerson, Long> {

	List<HhrOrgPerson> countByUserAndCer(String userName, String cerNum);

	void deleteAll();

}

