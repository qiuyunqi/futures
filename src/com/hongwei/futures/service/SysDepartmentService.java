package com.hongwei.futures.service;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.SysDepartment;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface SysDepartmentService {
	
	//====================== 基本 C R U D 方法  ===========================
	public SysDepartment get(Long id) ;
	
	public void save(SysDepartment entity) ;
	
	public void delete(Long id) ;
	
}

