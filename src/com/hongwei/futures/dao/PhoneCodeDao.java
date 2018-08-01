package com.hongwei.futures.dao;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.PhoneCode;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 小合
 */
public interface PhoneCodeDao extends BaseDao<PhoneCode, Long> {

	// 根据电话号码查询这个电话号码对应的验证码
	public PhoneCode getByPhone(String phone);

}

