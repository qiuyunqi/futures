package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.FuDictionary;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuDictionaryService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuDictionary get(Long id) ;
	
	public void save(FuDictionary entity) ;
	
	public void delete(Long id) ;
	
	public List<FuDictionary> findListByMap(int i, int j, Map<String, Object> map);
	
	public Integer countFuDictionary(Map<String, Object> map);

	/**
	 * 根据父id 查询可用的数据字典
	 * @param pid			父id
	 * @param flag			是否可用 1: 可用   0: 不可用
	 * @return
	 */
	public List<FuDictionary> getByPid(long pid, int flag);
	
	public List<FuDictionary> findBaseDictionaries();

	/**
	 * 返回字典树结构数据
	 * @return
	 */
	public String findDictionaryTree(Long longId);
	
}

