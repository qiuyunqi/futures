package com.hongwei.futures.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.dao.FuAdminDao;
import com.hongwei.futures.dao.FuRechargeDao;
import com.hongwei.futures.dao.FuUserDao;
import com.hongwei.futures.model.FuRecharge;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuRechargeService;
import com.hongwei.futures.util.MoneyDetailUtil;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class FuRechargeServiceImpl implements FuRechargeService {
	@Autowired
	private  FuAdminDao fuAdminDao;
	@Autowired
	private FuRechargeDao fuRechargeDao;
	@Autowired
	private FuUserDao fuUserDao;
	@Autowired
	private MoneyDetailUtil MoneyDetailUtil;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public FuRecharge get(Long id) {
		return fuRechargeDao.get(id);
	}
	
	@Override
	public void save(FuRecharge entity) {
		fuRechargeDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		fuRechargeDao.delete(id);
	}

	@Override
	public List<FuRecharge> findBy(int i, int pageSize, Map<String, Object> map) {
		return fuRechargeDao.findBy(i, pageSize, map);
	}

	@Override
	public Integer getCount(Map<String, Object> map) {
		return fuRechargeDao.getCount(map);
	}

	@Override
	public void saveRecharge(FuRecharge recharge, FuUser user) {
		fuRechargeDao.save(recharge);
		fuUserDao.save(user);
        MoneyDetailUtil.saveNewFuMoneyDetail(user,null,null,recharge.getType()==3?"支付宝转账":recharge.getType()==4?"银行转账":recharge.getRechargeBank(),8,recharge.getRechargeMoney(),user.getAccountBalance(),true);
	}

	@Override
	public List<FuRecharge> findListByUserId(Long userId) {
		return fuRechargeDao.findListByUserId(userId);
	}
	
	public void saveAliPay(FuUser fuUser,String out_trade_no,String total_fee){
		FuRecharge recharge=new FuRecharge();
		recharge.setFuUser(fuUser);
		recharge.setType(3);
		recharge.setRechargeBank("支付宝支付");
		recharge.setRechargeAccount(null);
		recharge.setPayStatus(1);//已付款
		recharge.setOrderNum(out_trade_no);
		recharge.setPayTime(new Date());
		recharge.setRechargeStatus(2);//通过
		recharge.setFuAdmin(fuAdminDao.get(1L));
		recharge.setCheckTime(new Date());
		recharge.setRechargeMoney(new BigDecimal(total_fee));
		recharge.setRechargeTime(new Date());
		recharge.setState(1);
		recharge.setProceedsType(0);
		recharge.setPayType(1);
		fuRechargeDao.save(recharge);
	
	  	//网站余额
		fuUser.setRechargeMoney((fuUser.getRechargeMoney()==null?new BigDecimal(0.00):fuUser.getRechargeMoney()).add(new BigDecimal(total_fee)));
		fuUser.setAccountBalance(fuUser.getAccountBalance().add(new BigDecimal(total_fee)));
		fuUserDao.save(fuUser);
		
		//资金明细
		MoneyDetailUtil.saveNewFuMoneyDetail(fuUser,null,null,"支付宝充值",8,new BigDecimal(total_fee),fuUser.getAccountBalance(),true);
	}
}

