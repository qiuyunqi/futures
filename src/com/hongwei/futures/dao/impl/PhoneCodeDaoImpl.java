package com.hongwei.futures.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.PhoneCodeDao;
import com.hongwei.futures.model.PhoneCode;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author  samuel
 */
@SuppressWarnings("all")
@Repository
public class PhoneCodeDaoImpl extends BaseDaoImpl<PhoneCode, Long> implements PhoneCodeDao {
	// 根据电话号码查询这个电话号码对应的验证码
	
	public PhoneCode getByPhone(String phone) {
		String hql = "FROM PhoneCode as p WHERE p.phone = :phone order by p.id desc";
		List<PhoneCode> list = this.getSession().createQuery(hql)//
				.setParameter("phone", phone)//
				.list();
		if(null != list && list.size() > 0){
			return list.get(0);
		}else{
			return null;
		}
	}

}

