package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.FuIndexData;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuIndexDataService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuIndexData get(Long id) ;
	
	public void save(FuIndexData entity) ;
	
	public void delete(Long id) ;
	
	public List<FuIndexData> findAllByFuindexData();
	
	public List<FuIndexData> findListBy(int i,int pageSize,Map<String,Object> map);
	
	public Integer getCount(Map<String,Object> map);
	
	public List<FuIndexData> findTop10();
	
}

