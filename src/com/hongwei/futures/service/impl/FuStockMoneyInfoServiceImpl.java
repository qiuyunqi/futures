package com.hongwei.futures.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongwei.futures.dao.FuParameterDao;
import com.hongwei.futures.dao.FuStockMoneyInfoDao;
import com.hongwei.futures.dao.FuUserDao;
import com.hongwei.futures.model.FuParameter;
import com.hongwei.futures.model.FuStockMoneyInfo;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuStockMoneyInfoService;
import com.hongwei.futures.service.HhrStatService;
import com.hongwei.futures.util.MoneyDetailUtil;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class FuStockMoneyInfoServiceImpl implements FuStockMoneyInfoService {

	@Autowired
	private FuStockMoneyInfoDao fuStockMoneyInfoDao;
	@Autowired
	private FuUserDao fuUserDao;
	@Autowired
	private FuParameterDao fuParameterDao;
	@Autowired
	private MoneyDetailUtil moneyDetailUtil;
	@Autowired
	private HhrStatService hhrStatService;

	// ====================== 基本 C R U D 方法 ===========================
	public FuStockMoneyInfo get(Long id) {
		return fuStockMoneyInfoDao.get(id);
	}

	public void save(FuStockMoneyInfo entity) {
		fuStockMoneyInfoDao.save(entity);
	}

	public void delete(Long id) {
		fuStockMoneyInfoDao.delete(id);
	}

	public FuStockMoneyInfo findMoneyInfoByMap(Map<String, Object> map) {
		return fuStockMoneyInfoDao.findMoneyInfoByMap(map);
	}

	@Override
	public List<FuStockMoneyInfo> queryStockMoneyInfo(int current, int pageNum, Map<String, Object> map) {
		return fuStockMoneyInfoDao.queryStockMoneyInfo(current, pageNum, map);
	}

	@Override
	public Integer countStockMoneyInfo(Map<String, Object> map) {
		return fuStockMoneyInfoDao.countStockMoneyInfo(map);
	}

	@Override
	public FuStockMoneyInfo findStockMoneyInfoByUserId(Long userId) {
		return fuStockMoneyInfoDao.findStockMoneyInfoByUserId(userId);
	}

	@Override
	public void saveNoneFee() {
		List<FuStockMoneyInfo> allMoneyInfo = fuStockMoneyInfoDao.findAll();
		for (FuStockMoneyInfo moneyInfo : allMoneyInfo) {
			// 累计未缴费用大于0的记录
			if (moneyInfo.getNoneFeeInfo().compareTo(BigDecimal.ZERO) == 1) {
				BigDecimal noneFee = moneyInfo.getNoneFeeInfo();
				FuUser fuUser = moneyInfo.getFuUser();
				// 扣用户余额,并更新累计已缴和累计未缴费用
				if (fuUser.getAccountBalance().compareTo(BigDecimal.ZERO) == 0) {
					// 用户余额为0,无法扣费,累计已缴费用和累计未缴费用不变

				} else {
					// 判断用户余额是否足够扣累计未缴费用
					if (fuUser.getAccountBalance().compareTo(noneFee) == -1) {
						// 如果余额不足,余额全部扣掉,更新累计未缴费用和累计已缴费用,并把扣去的那部分写资金流水
						moneyInfo.setNoneFeeInfo(moneyInfo.getNoneFeeInfo().subtract(fuUser.getAccountBalance()));
						moneyInfo.setPayFeeInfo(moneyInfo.getPayFeeInfo().add(fuUser.getAccountBalance()));
					} else if (fuUser.getAccountBalance().compareTo(noneFee) == 0) {
						moneyInfo.setNoneFeeInfo(BigDecimal.ZERO);
						moneyInfo.setPayFeeInfo(moneyInfo.getPayFeeInfo().add(noneFee));
					} else {
						moneyInfo.setNoneFeeInfo(BigDecimal.ZERO);
						moneyInfo.setPayFeeInfo(moneyInfo.getPayFeeInfo().add(noneFee));
					}
				}
				moneyInfo.setUpdatetime(new Date());
				fuStockMoneyInfoDao.save(moneyInfo);
			}
		}
	}

	public synchronized String saveOneKeyPay(FuUser fuUser, BigDecimal money) {
		if (fuUser.getAccountBalance().compareTo(BigDecimal.ZERO) == 0) {
			return "-1";
		} else {
			if (fuUser.getAccountBalance().compareTo(money) == -1) {
				money = fuUser.getAccountBalance();
			}
			fuUser.setAccountBalance(fuUser.getAccountBalance().subtract(money));
			fuUserDao.save(fuUser);
			// 资金明细
			moneyDetailUtil.saveNewFuMoneyDetail(fuUser, null, null, null, 14, money, fuUser.getAccountBalance(), false);
			// 个人资金统计
			FuStockMoneyInfo fuStockMoneyInfo = fuStockMoneyInfoDao.findStockMoneyInfoByUserId(fuUser.getId());
			fuStockMoneyInfo.setPayFeeInfo(fuStockMoneyInfo.getPayFeeInfo().add(money));
			fuStockMoneyInfo.setNoneFeeInfo(fuStockMoneyInfo.getNoneFeeInfo().subtract(money));
			fuStockMoneyInfoDao.save(fuStockMoneyInfo);

			// 从当前用户的上级用户开始分成
			FuUser parentUser = fuUserDao.get(fuUser.getHhrParentID());
			FuParameter parameter = fuParameterDao.findParameter();
			if (parentUser != null && parameter != null && parameter.getIsPayIncome() == 1) {
				hhrStatService.updateHhrIncome(parentUser.getId(), money, 5, null);
			}
			return "1";
		}
	}

}
