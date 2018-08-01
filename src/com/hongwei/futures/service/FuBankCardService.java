package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.FuBankCard;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuBankCardService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuBankCard get(Long id) ;
	
	public void save(FuBankCard entity) ;
	
	public void delete(Long id) ;
	/**
	 * 查找某个用户的所以绑定的银行卡
	 * @param userId
	 * @return
	 */
	public List<FuBankCard> findListByUser(Long userId);
	/**
	 * 根据用户的银卡卡号查找是否已经绑定过了
	 * @param userId
	 * @param cardNumber
	 * @return
	 */
	public FuBankCard findCardByNumber(Long userId, String cardNumber);
	
	public List<FuBankCard> queryFuBankCardList(int i, int j, Map<String, Object> map);

	public Integer countFuBankCard(Map<String, Object> map);
	
	public FuBankCard findBankCardByNum(String cardNumber);

	// state = 0;
	public int deleteCard(long userId, String cardNum, int state);

	/**
	 * 根据绑卡人 绑卡银行 绑卡卡号查询是否被绑定
	 * @param userCard		绑卡人
	 * @param bankName		绑卡银行
	 * @param cardNum		绑卡卡号
	 * @return
	 */
	public List<FuBankCard> findByUserAndBankAndCard(String userCard, String bankName, String cardNum);
	
	
}

