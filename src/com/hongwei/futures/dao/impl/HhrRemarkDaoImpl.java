package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.HhrRemarkDao;
import com.hongwei.futures.model.HhrRemark;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class HhrRemarkDaoImpl extends BaseDaoImpl<HhrRemark, Long> implements HhrRemarkDao {
	public HhrRemark findRemarkByUserId(Long user_id,Long relevance_id){
		List<Object> params=new ArrayList<Object>();
		params.add(user_id);
		params.add(relevance_id);
		String hql=" from HhrRemark where fuUser.id=? and relevanceUser.id=? ";
		return this.findUniqueByHQL(hql, new Object[]{user_id,relevance_id});
	}
}

