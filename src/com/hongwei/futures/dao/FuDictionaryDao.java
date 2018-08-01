package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.FuDictionary;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 小合
 */
public interface FuDictionaryDao extends BaseDao<FuDictionary, Long> {
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

	public String findDictionaryTree(Long longId);

}

