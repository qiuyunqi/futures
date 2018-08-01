package com.hongwei.futures.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.DrawInfo;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface DrawInfoService {
	
	//====================== 基本 C R U D 方法  ===========================
	public DrawInfo get(Long id) ;
	
	public void save(DrawInfo entity) ;
	
	public void delete(Long id) ;

	// 根据用户id 查询抽奖人的基本信息
	public DrawInfo getByUserId(Long userId);

	// 查询出所有中奖信息
	public List<DrawInfo> getDrawInfo();
	
}

