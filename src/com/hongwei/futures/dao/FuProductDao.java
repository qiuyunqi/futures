package com.hongwei.futures.dao;

import java.util.List;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.FuProduct;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 小合
 */
public interface FuProductDao extends BaseDao<FuProduct, Long> {
//	获取全部不是删除状态的产品
	public List<FuProduct> getList(int isDelete);

	// 获取最大顺序的值
	public FuProduct getMaxOrderNum();

	/**
	 * 获取最大的id值
	 * @return	Integer
	 */
	public Long getMaxId();

	public FuProduct findProductByName(String name);
}

