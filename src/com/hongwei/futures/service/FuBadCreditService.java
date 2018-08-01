package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.FuBadCredit;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuBadCreditService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuBadCredit get(Long id) ;
	
	public void save(FuBadCredit entity) ;
	
	public void delete(Long id) ;
	/**
	 * 不良记录条数
	 * @param map
	 * @return
	 */
	public Integer countBadCredit(Map<String, Object> map);
	/**
	 * 不良记录分页列表
	 * @param i
	 * @param pageSize
	 * @param map
	 * @return
	 */
	public List<FuBadCredit> findBadList(int i, int pageSize,
			Map<String, Object> map);
	/**
	 * 根据方案id查找方案的不良记录
	 * @param id
	 * @return
	 */
	public List<FuBadCredit> findListByProgram(Long programId);
	
}

