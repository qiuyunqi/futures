package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.hongwei.futures.model.WqqContents;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface WqqContentsService {
	
	//====================== 基本 C R U D 方法  ===========================
	public WqqContents get(Long id) ;
	
	public void save(WqqContents entity) ;
	
	public void delete(Long id) ;
	
	public List<WqqContents> findContentsByMap(int i, int pageSize, Map<String, Object> map);
	
	public Integer countContents(Map<String, Object> map);
	
}

