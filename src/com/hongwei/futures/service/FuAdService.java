package com.hongwei.futures.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.FuAd;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuAdService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuAd get(Long id) ;
	
	public void save(FuAd entity) ;
	
	public void delete(Long id) ;
	// 根据位置获取全部可用的轮播图片
	public List<FuAd> getList(int position);
	// 查询全部的广告位
	public List<FuAd> findAll(int currentPage, int pageSize, HashMap<String, Object> map);
	// 查询最大的顺序的那个条数据
	public FuAd getMaxOrderAd();
	
}

