package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.FuYjbDao;
import com.hongwei.futures.model.FuYjb;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author  小合
 */
@Repository
public class FuYjbDaoImpl extends BaseDaoImpl<FuYjb, Long> implements FuYjbDao {

	@Override
	public List<FuYjb> findYqbList(int i, int pageSize, Map<String, Object> map) {
		String hql = "from FuYjb where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		return this.findListByHQL(i, pageSize, hql,params);
	}

}

