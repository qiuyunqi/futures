package com.hongwei.futures.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.dao.FuAppointmentInfoDao;
import com.hongwei.futures.model.FuAppointmentInfo;
import com.hongwei.futures.service.FuAppointmentInfoService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class FuAppointmentInfoServiceImpl implements FuAppointmentInfoService {
	
	@Autowired
	private FuAppointmentInfoDao fuAppointmentInfoDao;
	
	//====================== 基本 C R U D 方法  ===========================
	public FuAppointmentInfo get(Long id) {
		return fuAppointmentInfoDao.get(id);
	}
	
	public void save(FuAppointmentInfo entity) {
		fuAppointmentInfoDao.save(entity);
	}
	
	public void delete(Long id) {
		fuAppointmentInfoDao.delete(id);
	}

	// 查询全部的预约开户人的信息
	public List<FuAppointmentInfo> findAll() {
		return fuAppointmentInfoDao.findAll();
	}
	
}

