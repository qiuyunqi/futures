package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.FuCommission;
import com.hongwei.futures.model.FuPromote;
import com.hongwei.futures.model.FuUser;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuCommissionService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuCommission get(Long id) ;
	
	public void save(FuCommission entity) ;
	
	public void delete(Long id) ;
	
	/**
	 * 查找所有的佣金兑换记录
	 * @param map 多条件查询
	 * @return
	 */
	public List<FuCommission> findList(int i,int pageSize,Map<String,Object> map);
	public Integer getCount(Map<String,Object> map);
	
	
	/**
	 * 保存佣金记录、更新用户的佣金总额、更新对应推广记录的所得佣金
	 * @param
	 * @return
	 */
	public void saveCommission(FuCommission commission,FuUser user,FuPromote promote);

	/**
	 * 保存佣金记录、更新用户的兑换佣金、产生佣金兑换明细
	 * @param
	 * @return
	 */
	public void saveCommissionExchange(FuCommission commission, FuUser user);

} 

