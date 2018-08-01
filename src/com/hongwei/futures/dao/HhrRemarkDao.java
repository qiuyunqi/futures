package com.hongwei.futures.dao;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.HhrRemark;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface HhrRemarkDao extends BaseDao<HhrRemark, Long> {
	public HhrRemark findRemarkByUserId(Long user_id,Long relevance_id);
}

