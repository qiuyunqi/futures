package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.FuDrawMoney;
import com.hongwei.futures.model.FuUser;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuDrawMoneyService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuDrawMoney get(Long id) ;
	
	public void save(FuDrawMoney entity) ;
	
	public void delete(Long id) ;
	
	
	/**
	 * 查找所有的提款申请
	 * @param map 多条件查询
	 * @return
	 */
	public List<FuDrawMoney> findList(int i,int pageSize,Map<String,Object> map);
	public Integer getCount(Map<String,Object> map);
	
	
	/**
	 * 保存提款记录或审核记录
	 * @param
	 * @return
	 */
	public void saveDrawMoney(FuDrawMoney drawMoney,FuUser user,Integer dictionaryId,Boolean isIncome);
	
	
	/**
	 * 查询用户的提现记录
	 * */
	public List<FuDrawMoney> findListByUserId(Long userId);
	
}



