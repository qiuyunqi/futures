package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.FuContinueContract;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuContinueContractService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuContinueContract get(Long id) ;
	
	public void save(FuContinueContract entity) ;
	
	public void delete(Long id) ;
	/**
	 * 续约记录条数
	 * @param map
	 * @return
	 */
	public Integer countContinue(Map<String, Object> map);
	/**
	 * 续约记录分页列表
	 * @param i
	 * @param pageSize
	 * @param map
	 * @return
	 */
	public List<FuContinueContract> getContinueList(int i, int pageSize,
			Map<String, Object> map);
	
	/**
	 *后台续约审核保存
	 * @param con
	 */
	public void saveContAndPro(FuContinueContract con,String msg);
	/**
	 * 根据方案id查询续约记录
	 * @param id
	 * @return
	 */
	public List<FuContinueContract> findContinueListByProgramId(Long id);
}

