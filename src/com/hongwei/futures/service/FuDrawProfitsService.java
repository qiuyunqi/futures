package com.hongwei.futures.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.FuDrawProfits;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuDrawProfitsService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuDrawProfits get(Long id) ;
	
	public void save(FuDrawProfits entity) ;
	
	public void delete(Long id) ;
	/**
	 * 待利润提取条数
	 * @param map
	 * @return
	 */
	public Integer countDrawProfits(Map<String, Object> map);
	/**
	 * 待利润提取分页列表
	 * @param map
	 * @return
	 */
	public List<FuDrawProfits> findDrawList(int i, int pageSize,
			Map<String, Object> map);
	/**
	 * 确认提取利润
	 * @param profits
	 */
	public void saveConfirmProfits(FuDrawProfits profits);
	/**
	 * 提取的利润记录
	 * @param id
	 * @return
	 */
	public List<FuDrawProfits> findDrawProfitsByProgramId(Long id);
	/**
	 * 根据方案和时间查提取利润明细数目
	 * @param id
	 * @param time1
	 * @param time2
	 * @return
	 */
	public int findProfitsByTime(Long programId, Date time1, Date time2);
	
}

