package com.hongwei.futures.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.FuProductDao;
import com.hongwei.futures.model.FuProduct;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author  小合
 */
@SuppressWarnings("all")
@Repository
public class FuProductDaoImpl extends BaseDaoImpl<FuProduct, Long> implements FuProductDao {

//	获取全部的产品
	public List<FuProduct> getList(int isDelete) {
		String hql = "FROM FuProduct as f WHERE f.isDelete = :isDelete ORDER BY f.orderNum ";
		return getSession().createQuery(hql)//
				.setParameter("isDelete", isDelete)//
				.list();
	}

	// 获取最大顺序的值
	public FuProduct getMaxOrderNum() {
		String hql = "FROM FuProduct as f ORDER BY f.orderNum desc";
		List<FuProduct> products = getSession().createQuery(hql).list();
		return null != products && products.size() >0 ? products.get(0) : null;
	}

	/**
	 * 获取最大的id值
	 */
	public Long getMaxId() {
		String sql = "FROM FuProduct order by id desc";
		List<FuProduct> list = this.getSession().createQuery(sql).list();
		Long id = null;
		if(null != list && list.size() > 0) {
			id = list.get(0).getId();
		}else {
			id=0L;
		}
		return id;
	}
	
	public FuProduct findProductByName(String name){
		String hql="from FuProduct where name=? and isDelete=0";
		return this.findUniqueByHQL(hql, name);
	}

}

