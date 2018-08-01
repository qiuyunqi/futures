package com.hongwei.futures.web.modulesforapp.unrelieve.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import net.sf.json.JSONObject;

import com.hongwei.futures.model.FuRate;
import com.hongwei.futures.model.FuStockAccount;
import com.hongwei.futures.model.FuStockMoneyDetail;
import com.hongwei.futures.model.FuStockMoneyInfo;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuRateService;
import com.hongwei.futures.service.FuStockAccountService;
import com.hongwei.futures.service.FuStockMoneyDetailService;
import com.hongwei.futures.service.FuStockMoneyInfoService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.util.Base64;
import com.hongwei.futures.util.ComParatorUtil;
import com.hongwei.futures.util.DateUtil;
import com.hongwei.futures.web.modulesforapp.unrelieve.AppTradeUnrelieveService;
@SuppressWarnings("all")
public class AppTradeUnrelieveServiceImpl implements AppTradeUnrelieveService {

	@Autowired
	private FuUserService fuUserService;
	@Autowired
	private FuStockAccountService  fuStockAccountService;
	@Autowired
	private FuStockMoneyDetailService fuStockMoneyDetailService;
	@Autowired
	private FuStockMoneyInfoService fuStockMoneyInfoService;
	@Autowired
	private FuRateService fuRateService;
	
	
	// 昨日盈亏 自己的账户基本信息 累积盈利 未缴管理费
	public String accountList(String userId, String version, String sign) {
//		String result = DesUtil.webserviceSignVerify(sign);
		JSONObject obj = new JSONObject();
		try{
//			if("success".equals(result)) {
				if("2.0.0".equals(version)) {
					if(null == userId || "".equals(userId)) {
						obj.put("is_success", 0);
						obj.put("message", "请先登录");
						return obj.toString();
					}
					FuUser fuUser = fuUserService.get(Long.parseLong(userId));
					if(null == fuUser) {
						obj.put("is_success", 0);
						obj.put("message", "该用户不存在");
						return obj.toString();
					}
					Map<String, Object> parames = new HashMap<String, Object>();
					parames.put("isDel", 0);
					parames.put("userId", fuUser.getId());
					List<FuStockAccount> stockAccountList = fuStockAccountService.findAccountByMap(0, 100, parames);
					BigDecimal totalProfit = new BigDecimal(0); // 今日总盈亏
//					BigDecimal totalNoMangeFee = new BigDecimal(0); // 累计未缴管理费
//					BigDecimal totalMustMangeFee = new BigDecimal(0); // 累计应缴管理费
					List<Object> list = new ArrayList<Object>();
					if(null != stockAccountList && stockAccountList.size() > 0){
						for (FuStockAccount fuStockAccount : stockAccountList) {
							Map<String, Object> map = new HashMap<String, Object>();
							if(fuStockAccount == null){
								map.put("id", 0);
								map.put("open_equity", "");
								map.put("partnter_account", "");
								map.put("capital_account", "");
							}else{
								map.put("id", fuStockAccount.getId());
								map.put("open_equity", fuStockAccount.getOpenEquity() == null ? "" : fuStockAccount.getOpenEquity());
								map.put("partnter_account", fuStockAccount.getPartnerAccount() == null ? "" : fuStockAccount.getPartnerAccount());
								map.put("capital_account", fuStockAccount.getCapitalAccount() == null ? "" : fuStockAccount.getCapitalAccount());
							}
							// 根据开户账户id 查询 开户详细信息
							FuStockMoneyDetail stockDetail = fuStockMoneyDetailService.findDetailByStockId(fuStockAccount.getId());
							if(stockDetail != null){
								map.put("now_profit", stockDetail.getNowProfit() == null ? 0 : stockDetail.getNowProfit());
								totalProfit = totalProfit.add(stockDetail.getNowProfit());
							}else{
								map.put("now_profit", 0);
							}
						
							int accountStatus = fuStockAccount.getState();
							map.put("account_status", accountStatus);
							
							list.add(map);
						}
						obj.put("stocks", list);
					}else {
						obj.put("message", "没有数据!");
						obj.put("is_success", 2);
						obj.put("stocks", "[]");
					}
					// 以及所有账户的今日盈利的和
					obj.put("day_total_profit", totalProfit == null ? 0 : totalProfit);
					//  累计
					Map<String, Object> infoParames = new HashMap<String, Object>();
					infoParames.put("userId", fuUser.getId());
					FuStockMoneyInfo moneyInfo = fuStockMoneyInfoService.findMoneyInfoByMap(infoParames);
					if(moneyInfo == null){
						// 所有账户的累计盈利
						obj.put("total_profit", 0);
						// 累计未缴管理费
						obj.put("none_total_fee", 0);
						// 累计应缴管理费
						//obj.put("must_total_fee", 0);
					}else{
						// 所有账户的累计盈利
						obj.put("total_profit", moneyInfo.getProfitInfo() == null ? 0 : moneyInfo.getProfitInfo());
						// 累计未缴管理费
						obj.put("none_total_fee", moneyInfo.getNoneFeeInfo() ==  null ? 0 : moneyInfo.getNoneFeeInfo());
						// 累计应缴管理费
						//obj.put("must_total_fee", moneyInfo.getMustFeeInfo() ==  null ? 0 : moneyInfo.getMustFeeInfo());
					}
					
//					obj.put("help_url",	"https://www.hhr360.com/index_guide/stockHelp.htm");
					obj.put("help_url",	"https://www.hhr360.com/user_stock/stockSolicitation.htm");
					obj.put("is_success", 1);
					obj.put("message", "Query release alliance success");
					System.out.println(obj.toString());
					return obj.toString();
				}
//			}else {
//				obj.put("is_success", 0);
//				obj.put("message", result);
//			}
		}catch(Exception e){
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "系统在更新, 请稍后再试");
		}
		System.out.println(obj.toString());
		return obj.toString();
	}


	// 保存账号
	public String addAccountInfo(String userId, String openUser,
			String openEquity, String salesDept, String capitalAccount,
			String capitalPassword, String partnerAccount, String accountType,
			String version, String sign) {
		
//		String result = DesUtil.webserviceSignVerify(sign);
		JSONObject obj = new JSONObject();
		try{
//			if("success".equals(result)) {
				if("2.0.0".equals(version)) {
					if(null == userId || "".equals(userId)){
						obj.put("is_success", 0);
						obj.put("message", "请先登录!");
						System.out.println(obj);
					 	return obj.toString();
					}
					FuUser user = fuUserService.get(Long.parseLong(userId));
					if(null == user){
						obj.put("is_success", 0);
						obj.put("message", "用户不存在");
						System.out.println(obj);
						return obj.toString();
					}
					
					//得到用户对应的费率
					FuRate param=fuRateService.getByUserID(user.getId());
					//系统参数还没有设置，请联系客服
					if(null == param){
						obj.put("is_success", 0);
						obj.put("message", "系统参数还没有设置，请联系客服");
						System.out.println(obj);
						return obj.toString();
					}
					if(accountType == null || "".equals(accountType)){
						accountType = "1";
					}
					FuStockAccount fsa = null;
					//  根据user_id 和 capitalAccount 查询这个账户
					fsa = fuStockAccountService.findAccountByUserIdAndCap(user.getId(), capitalAccount);
					if(null == fsa){
						Date nowDate = new Date();
						fsa = new FuStockAccount();
						fsa.setCreateTime(nowDate);
						fsa.setUpdateTime(nowDate);
					}
					fsa.setFuUser(user);
					fsa.setOpenUser(openUser);
					fsa.setOpenEquity(openEquity);
					fsa.setSalesDept(salesDept);
					fsa.setCapitalAccount(capitalAccount);
					fsa.setCapitalPassword(new String(Base64.encode(capitalPassword.getBytes())));
					fsa.setPartnerAccount(partnerAccount);
					fsa.setAccountType(Integer.parseInt(accountType));
					//fsa.setCreateTime(new Date());
					fsa.setUpdateTime(new Date());
					fsa.setExamineStatus(0);
					fsa.setState(0);
					fsa.setIsDel(0);
					fuStockAccountService.save(fsa);
					obj.put("is_success", 1);
					obj.put("message", "股票账户添加成功");
					
				}
//			}else {
//				obj.put("is_success", 0);
//				obj.put("message", result);
//			}
		}catch(Exception e){
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "系统在更新, 请稍后再试");
		}
		System.out.println(obj.toString());
		return obj.toString();
	}

	// 每日盈亏流水
	public String profitOrLossDetail(String userId, String version, String sign) {
		JSONObject obj = new JSONObject();
//		String result = DesUtil.webserviceSignVerify(sign);
		try{
			if("2.0.0".equals(version)){
//				if("success".equals(result)){
					if(userId == null || "".equals(userId)){
						obj.put("is_success", 0);
						obj.put("message", "请先登录!");
						return obj.toString();
					}
					FuUser user = fuUserService.get(Long.parseLong(userId));
					if(user == null){
						obj.put("is_success", 0);
						obj.put("message", "用户不存在");
						return obj.toString();
					}
					
					// 根据userid 查询stock_money_detail
					List<FuStockMoneyDetail> detailList = fuStockMoneyDetailService.findDetailByUserId(user.getId(), null, null);
					if(detailList == null || detailList.size() <= 0){
						obj.put("is_success", 2);
						obj.put("profits", "[]");
						obj.put("message", "没有盈亏流水记录");
						System.out.println(obj);
						return obj.toString();
					}
					List<Object> list = new ArrayList<Object>();
					for (FuStockMoneyDetail fuStockMoneyDetail : detailList) {
						Map<String, Object> map = new HashMap<String, Object>();
						// 开户劵商
						map.put("open_equity", fuStockMoneyDetail.getFuStockAccount().getOpenEquity() == null ? "" : fuStockMoneyDetail.getFuStockAccount().getOpenEquity());
						map.put("partnter_account", fuStockMoneyDetail.getFuStockAccount().getPartnerAccount() == null ? "" : fuStockMoneyDetail.getFuStockAccount().getPartnerAccount());
						// 当日盈利
						map.put("now_profit", fuStockMoneyDetail.getNowProfit() == null ? 0 : fuStockMoneyDetail.getNowProfit());
						map.put("trade_time", fuStockMoneyDetail.getTradeTime() == null ? "" : DateUtil.getStrFromDate(fuStockMoneyDetail.getTradeTime(), "yyyy-MM-dd"));
						map.put("capital_account", fuStockMoneyDetail.getFuStockAccount().getCapitalAccount() == null ? "" : fuStockMoneyDetail.getFuStockAccount().getCapitalAccount());
						list.add(map);
						
					}
					obj.put("profits", list);
					obj.put("is_success", 1);
					obj.put("message", "每日盈亏详细查询成功");
//				}else {
//					obj.put("message", result);
//					obj.put("is_success", 0);
//				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}


	// 查询费用流水
	@SuppressWarnings("unchecked")
	public String cost(String userId, String sign, String version) {
		JSONObject obj = new JSONObject();
//		String result = DesUtil.webserviceSignVerify(sign);
		try{
//			if("success".equals(result)){
				if("2.0.0".equals(version)){
					if(null == userId || "".equals(userId)) {
						obj.put("is_success", 0);
						obj.put("message", "请您先登录");
						return obj.toString();
					}
					FuUser fuUser = fuUserService.get(Long.parseLong(userId));
					if(null == fuUser){
						obj.put("is_success", 0);
						obj.put("message", "该用户不存在");
						return obj.toString();
					}
					
					// 获取用户的流水信息
					List<FuStockMoneyDetail> detailList = fuStockMoneyDetailService.findDetailByUserId(fuUser.getId(), null, null);
					List<Object> list = new ArrayList<Object>();
					if(null != detailList && detailList.size()> 0 ) {
						// 流水信息的结果
						for (FuStockMoneyDetail fuStockMoneyDetail : detailList) {
							HashMap<String, Object> map = new HashMap<String, Object>();
							BigDecimal manageFee = fuStockMoneyDetail.getManageFee(); // 管理费  > 0 管理费  < 0 平台赔付
							if(null != manageFee && manageFee.compareTo(BigDecimal.ZERO) == 1) {  // > 0
								map.put("name", "管理费用");
								map.put("manage_fee", manageFee);
							}else {
								map.put("name", "平台赔付");
								map.put("manage_fee", manageFee);
							}
							// 查询到股票账号的信息
							FuStockAccount fuStockAccount = fuStockMoneyDetail.getFuStockAccount();
							String openEquity = fuStockAccount.getOpenEquity();
							String capitalAccount = fuStockAccount.getCapitalAccount();
							map.put("open_equity", openEquity == null ? "" : openEquity);
							map.put("capital_account", capitalAccount == null ? "" : capitalAccount);
							map.put("trade_time", DateUtil.getStrFromDate(fuStockMoneyDetail.getTradeTime(), "yyyy-MM-dd"));
							list.add(map);
						}
						
						// 根据用户 id 查询用户每天交费的总和
						List<Object[]> payFeeList = fuStockMoneyDetailService.findListByUser(fuUser.getId());
						List<HashMap<String, Object>> list1 = new ArrayList<HashMap<String, Object>>();
						if(null != payFeeList && payFeeList.size() > 0 ) {
							for (int i = 0 ; i < payFeeList.size(); i ++){
								HashMap<String, Object> map1 = new HashMap<String, Object>();
								BigDecimal payFee = (BigDecimal) payFeeList.get(i)[1];
								if( null != payFee && payFee.compareTo(BigDecimal.ZERO) == 1) {
									map1.put("trade_time", DateUtil.getStrFromDate((Date) payFeeList.get(i)[0], "yyyy-MM-dd"));
									map1.put("total_pay_fee", payFee);
									map1.put("name", "交付费用");
									list1.add(map1);
								}
								
							}
						}
						list.addAll(list1);
						
						// 排序
						ComParatorUtil sort = new ComParatorUtil();
						Comparator<Object> c = Collections.reverseOrder(sort);
						Collections.sort(list, c);
						
						
						obj.put("detailList", list);
						obj.put("is_success", 1);
						obj.put("message", "查询流水信息成功");
						System.out.println(obj);
						return obj.toString();
					}else {
						obj.put("is_success", 2);
						obj.put("detailList", "[]");
						obj.put("message", "暂无费用记录");
						System.out.println(obj);
						return obj.toString();
					}
					
				}
//			}else {
//				obj.put("message", result);
//				obj.put("is_success", 0);
//			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}

	// 根据用户 id 查询账号的详细信息
	public String accountDetail(String stockId, String version, String sign) {
		JSONObject obj = new JSONObject();
//		String result = DesUtil.webserviceSignVerify(sign);
		try{
//			if("success".equals(result)){
				if("2.0.0".equals(version)) {
					if(stockId == null || "".equals(stockId)){
						obj.put("is_success", 0);
						obj.put("message", "没有这个账号");
						return obj.toString();
					}
					FuStockAccount fsa = fuStockAccountService.findAccountById(Long.parseLong(stockId));
					if(fsa != null){
						Map<String, Object> map = new HashMap<String, Object>();
						// 开户id
						map.put("id", fsa.getId());
						// 开户人姓名
						map.put("open_user", fsa.getOpenUser() == null ? "" : fsa.getOpenUser());
						// 开户券商
						map.put("open_equity", fsa.getOpenEquity() == null ? "" : fsa.getOpenEquity());
						// 营业部
						map.put("sales_dept", fsa.getSalesDept() == null ? "" : fsa.getSalesDept());
						// 资金账号
						map.put("capital_account", fsa.getCapitalAccount() == null ? "" : fsa.getCapitalAccount());
						// 股东账号
						map.put("partner_account", fsa.getPartnerAccount() == null ? "" : fsa.getPartnerAccount());
						// 股东账号
						map.put("account_type", fsa.getAccountType() == null ? "" : fsa.getAccountType());
						// 是否删除
						map.put("is_del", fsa.getIsDel() == null ? "" : fsa.getIsDel());
						int statusBefore = 0; //0:申请开启委托  1:申请暂停委托   3申请开启委托中  4申请暂停委托中
						//状态 0开启委托，1暂停委托，2申请开启委托中，3申请暂停委托中
						if(fsa.getState() == 0){
							statusBefore = 1;
						}else if(fsa.getState() == 1){
							statusBefore = 0;
						}else if(fsa.getState() == 2){
							statusBefore = 3;
						}else if(fsa.getState() == 3){
							statusBefore = 4;
						}else if(fsa.getState() == 4){
							statusBefore = 5;
						}
						map.put("status_before", statusBefore);
						obj.put("accInfo", map);
						obj.put("is_success", 1);
						obj.put("message", "查询账号的详细信息成功");
					}else {
						obj.put("accInfo", "[]");
						obj.put("is_success", 2);
						obj.put("message", "没有数据");
					}
				}
//			}else{
//				obj.put("message", result);
//				obj.put("is_success", 0);
//			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "invalid request");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}


	/**
	 * 一键缴费
	 */
	public String keyPayment(String userId, String version, String sign) {
		JSONObject obj = new JSONObject();
//		String result = DesUtil.webserviceSignVerify(sign);
		try{
//			if("success".equals(result)){
				if("2.0.0".equals(version)){
					if(userId == null || "".equals(userId)){
						obj.put("is_success", 0);
						obj.put("message", "请先登录!");
						System.out.println(obj.toString());
						return obj.toString();
					}
					FuUser fuUser = fuUserService.get(Long.parseLong(userId));
					if(null == fuUser){
						obj.put("is_success", 0);
						obj.put("message", "用户不存在");
						System.out.println(obj.toString());
						return obj.toString();
					}
					// 判断余额是否足够
//					BigDecimal accountBalance = fuUser.getAccountBalance(); // 账户余额
					
					Map<String, Object> infoParames = new HashMap<String, Object>();
					infoParames.put("userId", fuUser.getId());
					FuStockMoneyInfo moneyInfo = fuStockMoneyInfoService.findMoneyInfoByMap(infoParames);
					BigDecimal mf = BigDecimal.ZERO;
					if(null == mf){
						// 累计未缴管理费
						mf = BigDecimal.ZERO;
					}else{
						mf = (null == moneyInfo ? BigDecimal.ZERO : moneyInfo.getNoneFeeInfo() == null ? BigDecimal.ZERO : moneyInfo.getNoneFeeInfo());
					}
					
				/*	if(accountBalance != null && accountBalance.compareTo(mf) == -1) {
						obj.put("is_success", 0);
						obj.put("message", "账号余额不足, 请充值");
						System.out.println(obj.toString());
						return obj.toString();
					}else if(null == accountBalance) {
						obj.put("is_success", 0);
						obj.put("message", "账号余额不足, 请充值");
						System.out.println(obj.toString());
						return obj.toString();
					}else if(null != accountBalance && (accountBalance.compareTo(mf) == 0 || accountBalance.compareTo(mf) == 1)){
						// 用户的一键缴费
						fuStockMoneyInfoService.saveOneKeyPay(fuUser, mf);
						// 当前账户的用户的余额
						obj.put("account_balance", fuUser.getAccountBalance() == null ? BigDecimal.ZERO : fuUser.getAccountBalance());
						obj.put("time", DateUtil.getStrFromDate(new Date(), "yyyy-MM-dd HH:ss:mm"));
						obj.put("is_success", 1);
						obj.put("message", "缴费成功");
						System.out.println(obj.toString());
						return obj.toString();
					}*/
					if(null == mf || mf.compareTo(BigDecimal.ZERO) == 0) {
						obj.put("is_success", 0);
						obj.put("message", "暂无需缴费");
						System.out.println(obj.toString());
						return obj.toString();
					}
					// 用户的一键缴费
					String key = fuStockMoneyInfoService.saveOneKeyPay(fuUser, mf);
					if("-1".equals(key)) {
						obj.put("is_success", 0);
						obj.put("message", "账号余额不足, 请充值");
						System.out.println(obj.toString());
						return obj.toString();
					}
					if("1".equals(key)) {
						// 当前账户的用户的余额
						obj.put("account_balance", fuUser.getAccountBalance() == null ? BigDecimal.ZERO : fuUser.getAccountBalance());
						obj.put("time", DateUtil.getStrFromDate(new Date(), "yyyy-MM-dd HH:ss:mm"));
						obj.put("is_success", 1);
						obj.put("message", "缴费成功");
						System.out.println(obj.toString());
						return obj.toString();
					}
					
					
				}
//			}else{
//				obj.put("message", result);
//				obj.put("is_success", 0);
//			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "系统正在更新中, 请稍后再试");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}


	// 已缴费用  交付费用
	public String feesPaid(String userId, String sign, String version) {
		JSONObject obj = new JSONObject();
//		String result = DesUtil.webserviceSignVerify(sign);
		try{
//			if("success".equals(result)){
				if("2.0.0".equals(version)){
					if(null == userId || "".equals(userId)) {
						obj.put("is_success", 0);
						obj.put("message", "请您先登录");
						return obj.toString();
					}
					FuUser fuUser = fuUserService.get(Long.parseLong(userId));
					if(null == fuUser){
						obj.put("is_success", 0);
						obj.put("message", "该用户不存在");
						return obj.toString();
					}
					
					// 根据用户 id 查询用户每天交费的总和
					List<Object[]> payFeeList = fuStockMoneyDetailService.findListByUser(fuUser.getId());
					List<HashMap<String, Object>> list1 = new ArrayList<HashMap<String, Object>>();
					if(null != payFeeList && payFeeList.size() > 0 ) {
						for (int i = 0 ; i < payFeeList.size(); i ++){
							HashMap<String, Object> map1 = new HashMap<String, Object>();
							BigDecimal payFee = (BigDecimal) payFeeList.get(i)[1];
							if( null != payFee && payFee.compareTo(BigDecimal.ZERO) == 1) {
								map1.put("trade_time", DateUtil.getStrFromDate((Date) payFeeList.get(i)[0], "yyyy-MM-dd"));
								map1.put("total_pay_fee", payFee);
								map1.put("name", "交付费用");
								list1.add(map1);
							}
						}
						
						// 排序
						ComParatorUtil sort = new ComParatorUtil();
						Comparator<Object> c = Collections.reverseOrder(sort);
						Collections.sort(list1, c);
						
						
						obj.put("detailList", list1);
						obj.put("is_success", 1);
						obj.put("message", "查询流水信息成功");
						System.out.println(obj);
						return obj.toString();
					}else {
						obj.put("detailList", "[]");
						obj.put("is_success", 2);
						obj.put("message", "暂无交费记录");
						System.out.println(obj);
						return obj.toString();
					}
				}
//			}else {
//				obj.put("message", result);
//				obj.put("is_success", 0);
//			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "系统正在更新中, 请稍后再试");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}


	// 应缴费用  平台和管理费
	public String noFeesPaid(String userId, String sign, String version) {
		JSONObject obj = new JSONObject();
//		String result = DesUtil.webserviceSignVerify(sign);
		try{
//			if("success".equals(result)){
				if("2.0.0".equals(version)){
					if(null == userId || "".equals(userId)) {
						obj.put("is_success", 0);
						obj.put("message", "请您先登录");
						return obj.toString();
					}
					FuUser fuUser = fuUserService.get(Long.parseLong(userId));
					if(null == fuUser){
						obj.put("is_success", 0);
						obj.put("message", "该用户不存在");
						return obj.toString();
					}
					
					// 获取用户的流水信息
					List<FuStockMoneyDetail> detailList = fuStockMoneyDetailService.findDetailByUserId(fuUser.getId(), null, null);
					List<Object> list = new ArrayList<Object>();
					if(null != detailList && detailList.size()> 0 ) {
						// 流水信息的结果
						for (FuStockMoneyDetail fuStockMoneyDetail : detailList) {
							HashMap<String, Object> map = new HashMap<String, Object>();
							BigDecimal manageFee = fuStockMoneyDetail.getManageFee(); // 管理费  > 0 管理费  < 0 平台赔付
							if(null != manageFee && manageFee.compareTo(BigDecimal.ZERO) == 1) {  // > 0
								map.put("name", "管理费用");
								map.put("manage_fee", manageFee);
							}
							// 查询到股票账号的信息
							FuStockAccount fuStockAccount = fuStockMoneyDetail.getFuStockAccount();
							String openEquity = fuStockAccount.getOpenEquity();
							String capitalAccount = fuStockAccount.getCapitalAccount();
							map.put("open_equity", openEquity == null ? "" : openEquity);
							map.put("capital_account", capitalAccount == null ? "" : capitalAccount);
							map.put("trade_time", DateUtil.getStrFromDate(fuStockMoneyDetail.getTradeTime(), "yyyy-MM-dd"));
							list.add(map);
						}
						
						// 排序
						ComParatorUtil sort = new ComParatorUtil();
						Comparator<Object> c = Collections.reverseOrder(sort);
						Collections.sort(list, c);
						
						
						obj.put("detailList", list);
						obj.put("is_success", 1);
						obj.put("message", "查询流水信息成功");
						System.out.println(obj);
						return obj.toString();
					}else {
						obj.put("is_success", 2);
						obj.put("detailList", "[]");
						obj.put("message", "暂无应缴费用记录");
						System.out.println(obj);
						return obj.toString();
					}
					
				}
//			}else {
//				obj.put("message", result);
//				obj.put("is_success", 0);
//			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "系统在更新中, 请稍后再试");
			obj.put("is_success", 0);
		}
		System.out.println(obj);
		return obj.toString();
	}
	
}
