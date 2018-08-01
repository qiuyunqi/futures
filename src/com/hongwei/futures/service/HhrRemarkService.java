package com.hongwei.futures.service;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.HhrRemark;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface HhrRemarkService {
	
	//====================== 基本 C R U D 方法  ===========================
	public HhrRemark get(Long id) ;
	
	public void save(HhrRemark entity) ;
	
	public void delete(Long id) ;
	
	public HhrRemark findRemarkByUserId(Long user_id,Long relevance_id);
	
}

