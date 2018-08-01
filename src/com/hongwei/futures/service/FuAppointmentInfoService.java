package com.hongwei.futures.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.FuAppointmentInfo;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuAppointmentInfoService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuAppointmentInfo get(Long id) ;
	
	public void save(FuAppointmentInfo entity) ;
	
	public void delete(Long id) ;

	/**
	 * 查询全部的预约开户人的信息
	 * @return FuAppointmentInfo 对象
	 */
	public List<FuAppointmentInfo> findAll();
	
}

