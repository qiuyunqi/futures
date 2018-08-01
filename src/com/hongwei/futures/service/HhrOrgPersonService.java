package com.hongwei.futures.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.HhrOrgPerson;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface HhrOrgPersonService {
	
	//====================== 基本 C R U D 方法  ===========================
	public HhrOrgPerson get(Long id) ;
	
	public void save(HhrOrgPerson entity) ;
	
	public void delete(Long id) ;
	
	public List<HhrOrgPerson> countByUserAndCer(String userName, String cerNum);
	
	public void deleteAll();
	
}

