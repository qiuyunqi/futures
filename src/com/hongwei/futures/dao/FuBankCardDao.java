package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.FuBankCard;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface FuBankCardDao extends BaseDao<FuBankCard, Long> {

	public List<FuBankCard> findListByUser(Long userId);

	public FuBankCard findCardByNumber(Long userId, String cardNumber);

	public List<FuBankCard> queryFuBankCardList(int i, int j, Map<String, Object> map);

	public Integer countFuBankCard(Map<String, Object> map);

	public FuBankCard findBankCardByNum(String cardNumber);

	/**
	 * 根据绑卡人 绑卡银行 绑卡卡号查询是否被绑定
	 * @param userCard		绑卡人
	 * @param bankName		绑卡银行
	 * @param cardNum		绑卡卡号
	 * @return
	 */
	public List<FuBankCard> findByUserAndBankAndCard(String userCard, String bankName, String cardNum);
	
}

