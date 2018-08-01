package com.hongwei.futures.service;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.PhoneCode;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface PhoneCodeService {
	
	//====================== 基本 C R U D 方法  ===========================
	public PhoneCode get(Long id) ;
	
	public void save(PhoneCode entity) ;
	
	public void delete(Long id) ;

	// 根据电话号码查询这个电话号码对应的验证码
	public PhoneCode getByPhone(String phone);
	
}

