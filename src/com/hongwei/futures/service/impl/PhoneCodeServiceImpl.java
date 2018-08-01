package com.hongwei.futures.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.PhoneCodeDao;
import com.hongwei.futures.model.PhoneCode;
import com.hongwei.futures.service.PhoneCodeService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class PhoneCodeServiceImpl implements PhoneCodeService {
	
	@Autowired
	private PhoneCodeDao phoneCodeDao;
	
	//====================== 基本 C R U D 方法  ===========================
	public PhoneCode get(Long id) {
		return phoneCodeDao.get(id);
	}
	
	public void save(PhoneCode entity) {
		phoneCodeDao.save(entity);
	}
	
	public void delete(Long id) {
		phoneCodeDao.delete(id);
	}

	// 根据电话号码查询这个电话号码对应的验证码
	public PhoneCode getByPhone(String phone) {
		return phoneCodeDao.getByPhone(phone);
	}
	
}

