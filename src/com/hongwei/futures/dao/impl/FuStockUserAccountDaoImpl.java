package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.FuStockUserAccountDao;
import com.hongwei.futures.model.FuStockMoneyDetailTemp;
import com.hongwei.futures.model.FuStockUserAccount;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author  小合
 */
@Repository
public class FuStockUserAccountDaoImpl extends BaseDaoImpl<FuStockUserAccount, Long> implements FuStockUserAccountDao {

	@Override
	public FuStockUserAccount findByUserAndStock(Long userId, Long stockId) {
		String hql=" from FuStockUserAccount where fuUser.id=? and fuStockAccount.id=? ";
		return this.findUniqueByHQL(hql, userId, stockId);
	}

	@Override
	public List<FuStockUserAccount> queryAccountList(int i, int pageSize, Map<String, Object> map) {
		String hql = "from FuStockUserAccount where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("userId")){
			params.add(map.get("userId"));
			hql=hql+" and fuUser.id=? ";
		}
		if(map.containsKey("phone")){
			params.add(map.get("phone"));
			hql=hql+" and fuUser.phone=? ";
		}
		if(map.containsKey("capitalAccount")){
			params.add(map.get("capitalAccount"));
			hql=hql+" and fuStockAccount.capitalAccount=? ";
		}
		return this.findListByHQL(i, pageSize, hql,params);
	}

	@Override
	public Integer countUserAccount(Map<String, Object> map) {
		String hql = "from FuStockUserAccount where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("userId")){
			params.add(map.get("userId"));
			hql=hql+" and fuUser.id=? ";
		}
		if(map.containsKey("phone")){
			params.add(map.get("phone"));
			hql=hql+" and fuUser.phone=? ";
		}
		if(map.containsKey("capitalAccount")){
			params.add(map.get("capitalAccount"));
			hql=hql+" and fuStockAccount.capitalAccount=? ";
		}
		return this.countQueryResult(hql,params);
	}

}

