package com.hongwei.futures.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.FuManageFee;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuManageFeeService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuManageFee get(Long id) ;
	
	public void save(FuManageFee entity) ;
	
	public void delete(Long id) ;
	/**
	 * 管理费核查统计
	 * @param map
	 * @return
	 */
	public Integer countFee(HashMap<String, Object> map);
	/**
	 * 管理费核查分页列表
	 * @param i
	 * @param pageSize
	 * @param map
	 * @return
	 */
	public List<FuManageFee> findFeeList(int i, int pageSize,
			HashMap<String, Object> map);
	/**
	 * 根据方案id查询所以的管理费记录
	 * @param id
	 * @return
	 */
	public List<FuManageFee> findFeeListByProgramId(Long id);
	/**
	 * 审核通过续交的管理费
	 */
	public void saveManageMoney(FuManageFee fee);
	/**
	 * 审核通过续约的管理费
	 */
	public void saveContinueManageMoney(FuManageFee fee);
	
}

