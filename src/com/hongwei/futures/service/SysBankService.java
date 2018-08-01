package com.hongwei.futures.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.SysBank;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface SysBankService {
	
	//====================== 基本 C R U D 方法  ===========================
	public SysBank get(Long id) ;
	
	public void save(SysBank entity) ;
	
	public void delete(Long id) ;
	
	public List<SysBank> findAllBank();
	
	public SysBank findBy(String name);
}

