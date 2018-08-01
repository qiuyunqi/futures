package com.hongwei.futures.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.FuMoneyDetail;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuMoneyDetailService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuMoneyDetail get(Long id) ;
	
	public void save(FuMoneyDetail entity) ;
	
	public void delete(Long id) ;
	
	/**
	 * 查找某个用户的资金明细
	 * @param map
	 * @return
	 */
	public List<FuMoneyDetail> findListBy(int i,int pageSize,Map<String,Object> map);
	
	/**
	 * 根据条件查询方案资金明细的记录数
	 */
	public Integer getCount(Map<String,Object> map);
	
	/**
	 * 根据条件查询所有方案资金明细
	 */
	public List<FuMoneyDetail> findFuMoneyDetailByParams(Map<String,Object> map);

	/**
	 * 根据用户查询用户的收入详情列表
	 * @param userId
	 * @param isInCome		0: 支出   1: 收入
	 * @param curPage		当前页
	 * @param pageSize		每页大小
	 * @return
	 */
	public List<FuMoneyDetail> findInComeByUserId(long userId, int isInCome, Integer curPage, Integer pageSize);

	/**
	 * 根据条件查询累计的收益
	 * @param userId				用户id
	 * @param dictionaryId			字典id
	 * @param isEnabled				字典的可用值 1: 可用 0: 不可用
	 * @return
	 */
	public BigDecimal getCountMoneyByDictionaryIdAndUserId(Long userId, Long dictionaryId , int isEnabled);

	/**
	 * 查找某个用户的资金明细
	 * @param userId			用户id
	 * @param dictionaryId		字典id
	 * @param detailId			FuMoneyDetail 主键id
	 * @return
	 */
	public List<FuMoneyDetail> findListByDetailId(Long userId, long dictionaryId, Long detailId);

	public List<FuMoneyDetail> findListByUserIdCount(Long userId, Integer i);

	public List<FuMoneyDetail> findListByUserId(Long userId);
	
}