package com.hongwei.futures.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.SysBranch;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface SysBranchService {
	
	//====================== 基本 C R U D 方法  ===========================
	public SysBranch get(Long id) ;
	
	public void save(SysBranch entity) ;
	
	public void delete(Long id) ;
	
	public List<SysBranch> findBranchByCity(Long bankId,Long cityId);
	
	public SysBranch findBy(Long bankId,Long cityId,String branchName,String branchAddress,String branchPhone);
	
	public SysBranch findByParams(Long bankId,Long cityId,String branchName);
}

