package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.FuBankCardDao;
import com.hongwei.futures.model.FuBankCard;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class FuBankCardDaoImpl extends BaseDaoImpl<FuBankCard, Long> implements FuBankCardDao {

	@Override
	public List<FuBankCard> findListByUser(Long userId) {
		String hql=" from FuBankCard where state=1 and userId=? order by id desc";
		return this.findAllByHQL(hql, userId);
	}

	@Override
	public FuBankCard findCardByNumber(Long userId, String cardNumber) {
		String hql=" from FuBankCard where state=1 and userId=? and cardNumber=? ";
		return this.findUniqueByHQL(hql, userId,cardNumber);
	}

	@Override
	public List<FuBankCard> queryFuBankCardList(int i, int j, Map<String, Object> map) {
		String hql = "from FuBankCard where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("accountName")){
			params.add(map.get("accountName"));
			hql=hql+" and accountName=? ";
		}
		if(map.containsKey("cardNumber")){
			params.add(map.get("cardNumber"));
			hql=hql+" and cardNumber=? ";
		}		
		return this.findListByHQL(i, j, hql,params);
	}

	@Override
	public Integer countFuBankCard(Map<String, Object> map) {
		String hql = "from FuBankCard where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("accountName")){
			params.add(map.get("accountName"));
			hql=hql+" and accountName=? ";
		}
		if(map.containsKey("cardNumber")){
			params.add(map.get("cardNumber"));
			hql=hql+" and cardNumber=? ";
		}
		if(map.containsKey("userId")) {
			params.add(map.get("userId"));
			hql = hql + "and userId = ?";
		}
		if(map.containsKey("state")) {
			params.add(map.get("state"));
			hql = hql + "and state = ?";	
		}
		return this.countQueryResult(hql,params);
	}

	@Override
	public FuBankCard findBankCardByNum(String cardNumber) {
		String hql=" from FuBankCard where state=1 and cardNumber=? ";
		return this.findUniqueByHQL(hql,cardNumber);
	}

	// 根据绑卡人 绑卡银行 绑卡卡号查询是否被绑定
	@SuppressWarnings("unchecked")
	public List<FuBankCard> findByUserAndBankAndCard(String userCard,
			String bankName, String cardNum) {
		String hql = "FROM FuBankCard AS f WHERE f.accountName = :userCard AND f.bankName = :bankName AND f.cardNumber = :cardNum";
		return this.getSession().createQuery(hql)//
				.setParameter("userCard", userCard)//
				.setParameter("bankName", bankName)//
				.setParameter("cardNum", cardNum)//
				.list();
	}

}

