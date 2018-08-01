package com.hongwei.futures.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.dao.FuBankCardDao;
import com.hongwei.futures.model.FuBankCard;
import com.hongwei.futures.service.FuBankCardService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class FuBankCardServiceImpl implements FuBankCardService {
	
	@Autowired
	private FuBankCardDao fuBankCardDao;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public FuBankCard get(Long id) {
		return fuBankCardDao.get(id);
	}
	
	@Override
	public void save(FuBankCard entity) {
		fuBankCardDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		fuBankCardDao.delete(id);
	}

	@Override
	public List<FuBankCard> findListByUser(Long userId) {
		return fuBankCardDao.findListByUser(userId);
	}

	@Override
	public FuBankCard findCardByNumber(Long userId, String cardNumber) {
		return fuBankCardDao.findCardByNumber(userId,cardNumber);
	}

	@Override
	public List<FuBankCard> queryFuBankCardList(int i, int j, Map<String, Object> map) {
		return fuBankCardDao.queryFuBankCardList(i, j, map);
	}

	@Override
	public Integer countFuBankCard(Map<String, Object> map) {
		return fuBankCardDao.countFuBankCard(map);
	}

	@Override
	public FuBankCard findBankCardByNum(String cardNumber) {
		return fuBankCardDao.findBankCardByNum(cardNumber);
	}

// 	state = 0
	public int deleteCard(long userId, String cardNum, int state) {
		FuBankCard card = fuBankCardDao.findCardByNumber(userId, cardNum);
		if(null != card){
			card.setState(state);
			fuBankCardDao.save(card);
			return 1;
		}else {
			return 0; 
		}
		
	}

	// 根据绑卡人 绑卡银行 绑卡卡号查询是否被绑定
	public List<FuBankCard> findByUserAndBankAndCard(String userCard, String bankName, String cardNum) {
		return fuBankCardDao.findByUserAndBankAndCard(userCard, bankName, cardNum);
	}
	
}

