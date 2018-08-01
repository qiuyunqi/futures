package com.hongwei.futures.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.FuProduct;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuProductService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuProduct get(Long id) ;
	
	public void save(FuProduct entity) ;
	
	public void delete(Long id) ;

	// 获取全部的产品 isDelte = 0
	public List<FuProduct> getList();

	// 获取全部的产品列表
	public List<FuProduct> findList();
	
	// 获取最大顺序的值
	public FuProduct getMaxOrderNum();

	/**
	 * 获取最大的id值
	 * @return
	 */
	public Long getMaxId();
	
	public FuProduct findProductByName(String name);
	
}

