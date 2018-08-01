package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.FuAddMargin;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuAddMarginService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuAddMargin get(Long id) ;
	
	public void save(FuAddMargin entity) ;
	
	public void delete(Long id) ;
	/**
	 * 待追加保证金条数
	 * @param map
	 * @return
	 */
	public Integer countSafeMoney(Map<String, Object> map);
	/**
	 * 带追加保证金分页列表
	 * @param i
	 * @param pageSize
	 * @param map
	 * @return
	 */
	public List<FuAddMargin> findSafeMoneyList(int i, int pageSize,
			Map<String, Object> map);
	/**
	 * 方案的证金记录
	 * @param id
	 * @return
	 */
	public List<FuAddMargin> findSafeMoneyByProgramId(Long id);
	/**
	 * 追加保证金审核同意
	 */
	public void saveConfirmProfits(FuAddMargin addMargin);
	
}

