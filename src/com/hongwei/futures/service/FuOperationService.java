package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuOperation;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuOperationService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuOperation get(Long id) ;
	
	public void save(FuOperation entity) ;
	
	public void delete(Long id) ;
	
	public void saveOperationRecord(String modelMain,String modelSub,Integer operation,Long opId,String beforeContent,String afterContent,FuAdmin fuAdmin);
	
	public Integer countOperation(Map<String, Object> map);
	
	public List<FuOperation> findOperationlist(int i, int pageSize, Map<String, Object> map);
	
}

