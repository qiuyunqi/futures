package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.FuRecharge;
import com.hongwei.futures.model.FuUser;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuRechargeService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuRecharge get(Long id) ;
	
	public void save(FuRecharge entity) ;
	
	public void delete(Long id) ;
	
	/**
	 * 查询我的充值记录
	 * */
	public List<FuRecharge> findBy(int i,int pageSize,Map<String,Object> map);
	
	public Integer getCount(Map<String,Object> map);
	
	/**
	 * 保存充值、更新用户的充值总额、产生充值明细
	 * @param
	 * @return
	 */
	public void saveRecharge(FuRecharge recharge,FuUser user);
	
	/**
	 * 查询用户的充值记录
	 * */
	public List<FuRecharge> findListByUserId(Long userId);
	
	public void saveAliPay(FuUser fuUser,String out_trade_no,String total_fee);
}

