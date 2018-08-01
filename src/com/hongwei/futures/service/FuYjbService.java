package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.FuYjb;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuYjbService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuYjb get(Long id) ;
	
	public void save(FuYjb entity) ;
	
	public void delete(Long id) ;

	/**
	 * 查询所有的余劵宝产品
	 * @return
	 */
	public List<FuYjb> findAll();

	public List<FuYjb> findYqbList(int i, int pageSize, Map<String, Object> map);
	
}

